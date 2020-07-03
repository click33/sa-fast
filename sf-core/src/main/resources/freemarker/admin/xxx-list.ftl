<!DOCTYPE html>
<html>
	<head>
		<title>${t.tableComment}-列表</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<!-- 所有的 css & js 资源 -->
		<link rel="stylesheet" href="https://unpkg.com/element-ui@2.13.0/lib/theme-chalk/index.css">
		<link rel="stylesheet" href="../../static/sa.css">
		<script src="https://unpkg.com/vue@2.6.10/dist/vue.min.js"></script>
		<script src="https://unpkg.com/element-ui@2.13.0/lib/index.js"></script>
		<script src="https://unpkg.com/jquery@3.4.1/dist/jquery.js"></script>
		<script src="https://www.layuicdn.com/layer-v3.1.1/layer.js"></script>
		<script src="../../static/sa.js"></script>
	</head>
	<body>
		<div style="margin-top: -1em;" title="防止margin向下击穿"><br></div>
		<div class="vue-box" style="display: none;" :style="'display: block;'">
			<div class="c-panel">
				<!--------------- 参数栏 --------------->
				<div class="c-title">检索参数</div>
				<el-form @submit.native.prevent>
<#list t.columnList as c>
	<#if c.foType == 'text' || c.foType == 'textarea' || c.foType == 'richtext'>
					<div class="c-item">
						<label class="c-label">${c.columnComment3}：</label>
						<el-input size="mini" v-model="p.${c.fieldName}"></el-input>
					</div>
	<#elseif c.foType == 'num'>
					<div class="c-item">
						<label class="c-label">${c.columnComment3}：</label>
						<el-input size="mini" v-model="p.${c.fieldName}" type="number" ></el-input>
					</div>
	<#elseif c.foType == 'enum'>
					<div class="c-item">
						<label class="c-label">${c.columnComment3}：</label>
						<el-radio-group v-model="p.${c.fieldName}" class="s-radio-text">
							<el-radio :label="0">不限</el-radio>
		<#list c.jvList?keys as jv>
							<el-radio :label="${jv}">${c.jvList[jv]}</el-radio>
		</#list>
						</el-radio-group>
					</div>
	<#elseif c.foType == 'img'>
	<#elseif c.foType == 'img_list'>
	<#elseif c.isFoType('date', 'date-create', 'date-update')>
	<#elseif c.foType == 'fk-1'>
					<div class="c-item">
						<label class="c-label">${c.fkPkConcatComment}：</label>
						<el-select size="mini" v-model="p.${c.fieldName}">
							<el-option label="不限" :value="0"></el-option>
							<el-option v-for="${c.fkPkTableName} in ${c.fkPkTableName}List" :label="${c.fkPkTableName}.${c.fkPkConcatName}" :value="${c.fkPkTableName}.${c.fkPkColumnName}" :key="${c.fkPkTableName}.${c.fkPkColumnName}"></el-option>
						</el-select>
					</div>
	<#else>
					<div class="c-item">
						<label class="c-label">${c.columnComment3}：</label>
						<el-input size="mini" v-model="p.${c.fieldName}"></el-input>
					</div>
	</#if>
</#list>
					<div class="c-item" style="min-width: 0px;">
						<el-button size="mini" type="primary" icon="el-icon-search" @click="p.pageNo = 1; f5()">查询</el-button>
					</div>
					<br />
					<div class="c-item s-radio-text">
						<label class="c-label">综合排序：</label>
						<el-radio-group v-model="p.sort_type">
							<el-radio :label="0">最近添加</el-radio>
					<#list t.columnList as c>
						<#if c_index <= 3>
							<el-radio :label="${c_index + 1}">${c.columnComment3}</el-radio>
						<#else>
							<!-- <el-radio :label="${c_index + 1}">${c.columnComment3}</el-radio> -->
						</#if>
					</#list>
						</el-radio-group>
					</div>
				</el-form>
				<!--------------- 数据列表 --------------->
				<div class="c-title">数据列表</div>
				<el-table :data="dataList" size="mini">
<#list t.columnList as c>
	<#if c.foType == 'text' || c.foType == 'textarea' || c.foType == 'num' >
					<el-table-column label="${c.columnComment3}" prop="${c.fieldName}" ></el-table-column>
	<#elseif c.foType == 'richtext'>
					<el-table-column label="${c.columnComment3}" min-width="150px">
						<template slot-scope="s"><span>{{sa.maxLength(sa.text(s.row.${c.fieldName}), 100)}}</span></template>
					</el-table-column>
	<#elseif c.isFoType('date', 'date-create', 'date-update')>
					<el-table-column label="${c.columnComment3}" min-width="150px">
						<template slot-scope="s"><span>{{sa.forDate(s.row.${c.fieldName}, 2)}}</span></template>
					</el-table-column>
	<#elseif c.foType == 'img'>
					<el-table-column label="${c.columnComment3}">
						<template slot-scope="s">
							<img :src="s.row.${c.fieldName}" style="width: 3em; height: 3em; border-radius: 3px; cursor: pointer;" 
								@click="sa.showImage(s.row.${c.fieldName}, '400px', '400px')" v-if="s.row.${c.fieldName}" />
							<div v-else>无</div>
						</template>
					</el-table-column>
	<#elseif c.foType == 'audio' || c.foType == 'video'>
					<el-table-column label="${c.columnComment3}">
						<template slot-scope="s">
							<el-link type="info" :href="s.row.${c.fieldName}" target="_blank" v-if="!sa.isNull(s.row.${c.fieldName})">预览</el-link>
							<div v-else>无</div>
						</template>
					</el-table-column>
	<#elseif c.foType == 'img_list'>
					<el-table-column label="${c.columnComment3}" width="130px">
						<template slot-scope="s">
							<div @click="sa.showImageList(s.row.${c.fieldName}.split(','))" style="cursor: pointer;" v-if="s.row.${c.fieldName}">
								<img :src="s.row.${c.fieldName}.split(',')[0]" style="width: 3em; height: 3em; border-radius: 3px; cursor: pointer;" />
								<span style="color: #999; padding-left: 0.5em;">点击预览</span>
							</div>
							<div v-else>无</div>
						</template>
					</el-table-column>
	<#elseif c.foType == 'enum'>
					<el-table-column label="${c.columnComment3}">
						<template slot-scope="s">
		<#list c.jvList?keys as jv>
							<p v-if="s.row.${c.fieldName} == ${jv}">${c.jvList[jv]}</p>
		</#list>
						</template>
					</el-table-column>
	
	<#elseif c.foType == 'fk-1' || c.foType == 'fk-2'>
			<#if c.isTx('showfk')>
				<#if c.isTx('link')>
					<el-table-column label="${c.columnComment3}">
						<template slot-scope="s">
							<el-link type="primary" @click="sa.showIframe(' id = ' + s.row.${c.fieldName} + '  详细信息', '../${c.fkPkTableKebabName}/${c.fkPkTableKebabName}-info.html?id=' + s.row.${c.fieldName})">
								{{s.row.${c.fieldName}}} 
							</el-link>
						</template>
					</el-table-column>
				<#else>
					<el-table-column label="${c.columnComment3}" prop="${c.fieldName}" ></el-table-column>
				</#if>
			</#if>
			<#list c.fkPkConcatList as fk>
				<#if c.isTx('link')>
					<el-table-column label="${fk.fkPkConcatComment}">
						<template slot-scope="s">
							<el-link type="primary" @click="sa.showIframe('id = ' + s.row.${c.fieldName} + ' 详细信息', '../${c.fkPkTableKebabName}/${c.fkPkTableKebabName}-info.html?id=' + s.row.${c.fieldName})">
								{{s.row.${fk.fieldName}}} 
							</el-link>
						</template>
					</el-table-column>
				<#else>
					<el-table-column label="${fk.fkPkConcatComment}" prop="${fk.fieldName}" ></el-table-column>
				</#if>
			</#list>
	<#else>
					<el-table-column label="${c.columnComment3}" prop="${c.fieldName}" ></el-table-column>
	</#if>
</#list>
					<el-table-column label="操作" width="240px">
						<template slot-scope="s">
							<el-button class="c-btn" type="success" icon="el-icon-view" @click="get(s.row)">查看</el-button>
							<el-button class="c-btn" type="primary" icon="el-icon-edit" @click="update(s.row)">修改</el-button>
							<el-button class="c-btn" type="danger" icon="el-icon-delete" @click="del(s.row)">删除</el-button>
						</template>
					</el-table-column>
				</el-table>
				<!-- 分页 -->
				<div class="page-box">
					<el-pagination background
						layout="total, prev, pager, next, sizes, jumper" 
						:current-page.sync="p.pageNo" 
						:page-size.sync="p.pageSize" 
						:total="dataCount" 
						:page-sizes="[1, 10, 20, 30, 40, 50, 100, 1000]" 
						@current-change="f5()" 
						@size-change="f5()">
					</el-pagination>
				</div>
			</div>
		</div>
		<script>
			var app = new Vue({
				el: '.vue-box',
				data: {
					p: { // 查询参数  
				<#list t.columnList as c>
					<#if c.foType == 'enum'>
						${c.fieldName}: 0,		// ${c.columnComment} 
					<#else>
						${c.fieldName}: '',		// ${c.columnComment} 
					</#if>
				</#list>
						pageNo: 1,		// 当前页 
						pageSize: 10,	// 页大小 
						sort_type: 0	// 排序方式 
					},
					dataCount: 0,
					dataList: [], // 数据集合 
				<#list t.getColumnListBy('fk-1') as c>
					${c.fkPkTableName}List: [],		// ${c.fkPkConcatComment}集合
				</#list>
				},
				methods: {
					// 刷新
					f5: function() {
						sa.ajax('/${t.mkNameBig}/getList', sa.removeNull(this.p), function(res) {
							this.dataList = res.data; // 数据
							this.dataCount = res.dataCount; // 数据总数 
						}.bind(this));
					},
					// 查看
					get: function(data) {
						sa.showIframe('数据详情', '${t.kebabName}-info.html?id=' + data.${t.primaryKey.fieldName}, '1000px', '90%');
					},
					// 修改
					update: function(data) {
						sa.showIframe('修改数据', '${t.kebabName}-add.html?id=' + data.${t.primaryKey.fieldName}, '1000px', '90%');
					},
					// 删除
					del: function(data) {
						sa.confirm('是否删除，此操作不可撤销', function() {
							sa.ajax('/${t.mkNameBig}/delete?id=' + data.${t.primaryKey.fieldName}, function(res) {
								sa.arrayDelete(this.dataList, data);
								sa.ok('删除成功');
							}.bind(this))
						}.bind(this));
					},
				},
				created: function() {
					this.f5();
			<#if t.getColumnListBy('fk-1')?size != 0>
				
					// ============ 加载所需外键列表 ============
				<#list t.getColumnListBy('fk-1') as c>
					// 加载 ${c.fkPkConcatComment}
					sa.ajax('/${c.fkPkTableMkName}/getList?pageSize=1000', function(res) {
						this.${c.fkPkTableName}List = res.data; // 数据集合 
					}.bind(this), {msg: null});
				</#list>
			</#if>
				}
			})
		</script>
	</body>
</html>
