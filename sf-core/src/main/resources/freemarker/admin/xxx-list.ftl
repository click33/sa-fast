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
					<div class="c-item">
						<label class="c-label">${c.columnComment}：</label>
						<el-input size="mini" v-model="p.${c.fieldName}"></el-input>
					</div>
</#list>
					<div class="c-item" style="min-width: 0px;">
						<el-button size="mini" type="primary" icon="el-icon-search" @click="p.pageNo = 1; f5()">查询</el-button>
					</div>
					<br />
					<div class="c-item s-radio-text">
						<label class="c-label">综合排序：</label>
						<el-radio-group v-model="p.sort_type">
							<el-radio :label="0">默认</el-radio>
							<el-radio :label="1">最近添加</el-radio>
						</el-radio-group>
					</div>
				</el-form>
				<!--------------- 数据列表 --------------->
				<div class="c-title">数据列表</div>
				<el-table :data="dataList" size="mini">
					<#list t.columnList as c>
					<el-table-column label="${c.columnComment}" prop="${c.fieldName}" ></el-table-column>
					</#list>
					<!-- <el-table-column label="创建时间" width="150px">
						<template slot-scope="s"><span>{{sa.forDate(s.row.create_time, 2)}}</span></template>
					</el-table-column> -->
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
						:page-sizes="[1, 10, 20, 30, 40, 50, 100]" 
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
						${c.fieldName}: '',
						</#list>
						pageNo: 1,		// 当前页 
						pageSize: 10,	// 页大小 
						sort_type: 0	// 排序方式 
					},
					dataCount: 0,
					dataList: [], // 数据集合
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
						sa.showIframe('数据详情', '${t.kebabName}-info.html?id=' + data.id);
					},
					// 修改
					update: function(data) {
						sa.showIframe('修改数据', '${t.kebabName}-add.html?id=' + data.id);
					},
					// 删除
					del: function(data) {
						sa.confirm('是否删除，此操作不可撤销', function() {
							sa.ajax('/${t.mkNameBig}/delete?id=' + data.id, function(res) {
								sa.arrayDelete(this.dataList, data);
								sa.ok('删除成功');
							}.bind(this))
						}.bind(this));
					},
				},
				created: function() {
					this.f5();
				}
			})
		</script>
	</body>
</html>
