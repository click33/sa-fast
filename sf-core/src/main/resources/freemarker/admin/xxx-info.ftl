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
		</style>
	</head>
	<body>
		<div style="margin-top: -1em;" title="防止margin向下击穿"><br></div>
		<div class="vue-box" style="display: none;" :style="'display: block;'">
			<!-- 参数栏 -->
			<div class="c-panel" v-if="m">
				<el-form>	
					<!-- ---------------------- 基础信息 ---------------------- -->
					<h4 class="c-title">基础信息</h4>
	<#list t.columnList as c>
					<div class="c-item br">
						<label class="c-label">${c.columnComment}：</label>
						<span>{{m.${c.fieldName}}}</span>
					</div>
	</#list>
					<div class="c-item br">
						<label class="c-label"></label>
						<el-button type="success" icon="el-icon-plus" size="mini" @click="sa.closeCurrIframe()">确定</el-button>
					</div>
				</el-form>
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
