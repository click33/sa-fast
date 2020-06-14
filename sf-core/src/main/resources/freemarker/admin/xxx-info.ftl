<!DOCTYPE html>
<html>
	<head>
		<title>${t.tableComment}-详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<!-- 所有的 css js 资源 -->
		<link rel="stylesheet" href="https://unpkg.com/element-ui@2.13.0/lib/theme-chalk/index.css">
		<link rel="stylesheet" href="../../static/sa.css"> 
		<script src="https://unpkg.com/vue@2.6.10/dist/vue.min.js"></script>
		<script src="https://unpkg.com/element-ui@2.13.0/lib/index.js"></script>
		<script src="https://unpkg.com/jquery@3.4.1/dist/jquery.js"></script>
		<script src="https://www.layuicdn.com/layer-v3.1.1/layer.js"></script>
		<script src="../../static/sa.js"></script>
		<style type="text/css">
			.c-panel .c-label{width: 8em;}
			
		<#if t.hasFo('img_list') >
			/* 图集照片样式 */
			.image-box{width: 700px; padding-left: 4px;}
			.image-box-2{width: 90px; height: 90px; cursor: pointer; float: left;}
			.image-box-2 img{width: 90px; height: 90px; border-radius: 2px; box-shadow: 0 0 1px #aaa;}
			.image-box-2{display: inline-block; margin-right: 5px; margin-bottom: 5px;}
		</#if>
		
		<#if t.hasFo('richtext') >
			/* 富文本样式 */
			.content-box{width: 700px; min-height: 100px; border: 1px #ddd solid; padding: 1em; transition: all 0.2s;overflow: hidden;}
			.content-box img{max-width: 200px !important;}
		</#if>
			
		</style>
	</head>
	<body>
		<div class="vue-box s-bot-btn" style="display: none;" :style="'display: block;'">
			<!-- ------- 内容部分 ------- -->
			<div class="s-body">
				<div class="c-panel">
					<el-form size="mini" v-if="m">
<#list t.columnList as c>
	<#if c.foType == 'text'>	
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<span>{{m.${c.fieldName}}}</span>
						</div>
	<#elseif c.foType == 'richtext'>
						<div class="c-item br">
							<label class="c-label" style="float: left;">${c.columnComment3}：</label>
							<div class="content-box" style="float: left;">
								<div v-html="m.${c.fieldName}"></div>
							</div>
						</div>
						<div style="clear: both;"></div>
	<#elseif c.foType == 'enum'>
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
		<#list c.jvList?keys as jv>
							<span v-if="m.${c.fieldName} == ${jv}">${c.jvList[jv]}</span>
		</#list>
						</div>
	<#elseif c.foType == 'img'>
						<div class="c-item br">
							<label class="c-label" style="vertical-align: top;">${c.columnComment3}：</label>
							<img :src="m.${c.fieldName}" style="width: 3em; height: 3em; cursor: pointer;" 
								@click="sa.showImage(m.${c.fieldName}, '400px', '400px')" v-if="m.${c.fieldName}">
							<span v-else>无</span>
						</div>
	<#elseif c.foType == 'audio' || c.foType == 'video'>
						<div class="c-item br">
							<label class="c-label" style="vertical-align: top;">${c.columnComment3}：</label>
							<el-link type="info" :href="m.${c.fieldName}" target="_blank" v-if="!sa.isNull(m.${c.fieldName})">{{m.${c.fieldName}}}</el-link>
							<span v-else>无</span>
						</div>
	<#elseif c.foType == 'img_list'>
						<div class="c-item br">
							<label class="c-label" style="float: left;">${c.columnComment3}：</label>
							<div class="image-box" style="float: left;" v-if="m.${c.fieldName}">
								<div class="image-box-2" v-for="image in m.${c.fieldName}.split(',')">
									<img :src="image" @click="sa.showImage(image, '500px', '400px')" />
								</div>
							</div>
							<span v-else>无</span>
						</div>
						<div style="clear: both;"></div>
	<#elseif c.isFoType('date', 'date-create', 'date-update')>
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<span>{{sa.forDate(m.${c.fieldName}, 2)}}</span>
						</div>
	<#elseif c.foType == 'fk-1' || c.foType == 'fk-2'>
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<span>{{m.${c.fieldName}}}</span>
						</div>
						<div class="c-item">
							<label class="c-label">${c.fkPkConcatComment}：</label>
							<span>{{m.${c.fkPkTableName}_${c.fkPkConcatName}}}</span>
						</div>
	<#else>
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<span>{{m.${c.fieldName}}}</span>
						</div>
	</#if>
</#list>
					</el-form>
				</div>
			</div>
			<!-- ------- 底部按钮 ------- -->
			<div class="s-foot" v-if = "id != 0">
				<el-button size="mini" type="success" @click="sa.closeCurrIframe()">确定</el-button>
				<el-button size="mini" @click="sa.closeCurrIframe()">取消</el-button>
			</div>
		</div>
		<script>
			var app = new Vue({
				el: '.vue-box',
				data: {
					id: sa.p('id', 0),	// 获取数据ID 
					m: null
				},
				methods: {
				},
				mounted: function() {
					sa.ajax('/${t.mkNameBig}/getById?id=' + this.id, function(res) {
						this.m = res.data;
					}.bind(this))
				}
			})
		</script>
	</body>
</html>
