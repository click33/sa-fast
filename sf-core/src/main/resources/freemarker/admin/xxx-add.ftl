<!DOCTYPE html>
<html>
	<head>
		<title>${t.tableComment}-添加/修改</title>
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
	</head>
	<body>
		<div style="margin-top: -1em;" title="防止margin向下击穿"><br></div>
		<div class="vue-box" style="display: none;" :style="'display: block;'">
			<div class="c-panel" v-if="m">
				<div class="c-title" v-if="id == 0">数据添加</div>
				<div class="c-title" v-else>数据修改</div>
				<el-form size="mini" v-if="m">
<#list t.columnList as c>
					<div class="c-item br">
						<label class="c-label">${c.columnComment}：</label>
						<el-input size="mini" v-model="m.${c.fieldName}"></el-input>
					</div>
</#list>
					<div class="c-item br">
						<label class="c-label"></label>
						<el-button size="mini" type="primary" icon="el-icon-plus" @click="ok()">保存</el-button>
					</div>
				</el-form>
			</div>
		</div>
		<script>
			// 创建一个空model
			function create_m() {
				return {
					<#list t.columnList as c>
					${c.fieldName}: '',		// ${c.columnComment} 
					</#list>
				}
			}
		</script>
        <script>
			
			var app = new Vue({
				el: '.vue-box',
				data: {
					id: sa.p('id', 0),		// 获取超链接中的id参数（0=添加，非0=修改） 
					m: null		// 实体对象 
				},
				methods: {
					// 表单验证
					submit_check: function() {
						var m = this.m; 
						<#list t.columnList as c>
						if(sa.isNull(m.${c.fieldName})) {
							return sa.error('请输入${c.columnComment}');
						}
						</#list>
						return 'ok';	// ok表示正确 
					},
					// 提交数据 
					ok: function(){
						// 验证 
						if(this.submit_check() != 'ok') {
							return;
						}
						// 开始增加或修改
						this.m.create_time = undefined;	
						this.m.createTime = undefined;	
						if(this.id == 0) {	// 添加
							sa.ajax('/${t.mkNameBig}/add', this.m, function(res){
								sa.alert('增加成功', function() {
									app.m = create_m();
								}); 
							});
							
						} else {	// 修改
							sa.ajax('/${t.mkNameBig}/update', this.m, function(res){
								sa.alert('修改成功', function(){
									parent.app.f5();		// 刷新父页面列表 
									sa.closeCurrIframe();	// 关闭本页 
								});
							});
						}
					}
				},
				mounted: function(){
					// 初始化数据 
					if(this.id == 0) {	
						this.m = create_m();
					} else {	
						sa.ajax('/${t.mkNameBig}/getById?id=' + this.id, function(res) {
							this.m = res.data;
						}.bind(this))
					}
				}
			})
			
		</script>
	</body>
</html>