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
<#if t.hasFo('img', 'img_list', 'video', 'video_list', 'audio', 'audio_list','richtext') >
	<#if cfg.fileUploadWay == 1 >
		<script src="../../static/kj/upload-util.js"></script>
	</#if>
	<#if cfg.fileUploadWay == 2 >
		<script src="../../static/kj/oss-util.js"></script>
	</#if>
</#if>
	<#if t.hasFo('richtext') >
		<script src="../../static/kj/wangEditor.up.js"></script>
	</#if>
		<style type="text/css">
			.c-panel .el-form .c-label{width: 8em !important;}
			.c-panel .el-form .el-input,
			.c-panel .el-form .el-textarea__inner{width: 250px;}
		<#if t.hasFo('img_list') >
			/* ==== wang图集照片样式 ==== */
			.image-box{max-width: 700px; padding-left: 4px;}
			.image-box-2{width: 90px; height: 125px; cursor: pointer; float: left;}
			.image-box-2 img{width: 90px; height: 90px; border-radius: 2px;}
			.image-box-2{display: inline-block; margin-right: 5px; margin-bottom: 5px;}
			.image-box-2 p{text-align: center; color: #999; margin-top: -10px;}
			.up_img{text-align: center; background-color: #f8f8f8; height: 90px;}
			.up_img img{width: 40px; height: 40px; margin-top: 25px;}
		</#if>
		<#if t.hasFo('richtext') >
			/* ==== wang富文本编辑器 ==== */
			.editor-item{width: 100%; height: auto;}
			.editor-item .c-label{float: left;}
			.editor-item .editor-box{float: left; width: 80%; margin-top: 0px; margin-left: 5px; transition: all 0.2s;} 
			.editor-item #editor{min-height: 300px; background-color: #FFF;}
			.editor-item .w-e-toolbar{padding-top: 5px !important;}
			/* 尽量小点 */
			.editor-item .editor-box{float: left; width: 700px;} 
			.editor-item .editor-box img{max-width: 200px !important;}
			/* .editor-box img{margin-left: -1em;}
			.editor-box img{margin-top: -5px;} */
			/* 仿移动端样式兼容 */
			/* .editor-item .editor-box{float: left; width: 400px;} 
			.editor-item .w-e-toolbar{width: 400px; flex-wrap: wrap; } */
			.fold{height: 100px !important; overflow: hidden;}
			.el-select-dropdown{z-index: 9999999 !important;}
			/*  普通文本和富文本一起变长  */
			.c-panel .el-form .el-input,
			.c-panel .el-form .el-textarea__inner{width: 700px;}
		</#if>
		
		</style>
	</head>
	<body>
		<div class="vue-box s-bot-btn" style="display: none;" :style="'display: block;'">
			<!-- ------- 内容部分 ------- -->
			<div class="s-body">
				<div class="c-panel" v-if="m">
					<!-- <div class="c-title" v-if="id == 0">数据添加</div>
					<div class="c-title" v-else>数据修改</div> -->
					<el-form size="mini" v-if="m">
<#list t.columnList as c>
	<#if c.foType == 'text'>	
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<el-input size="mini" v-model="m.${c.fieldName}"></el-input>
						</div>
	<#elseif c.foType == 'num'>
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<el-input size="mini" v-model="m.${c.fieldName}" type="number"></el-input>
						</div>
	<#elseif c.foType == 'textarea'>
						<div class="c-item br">
							<label class="c-label" style="vertical-align: top;">${c.columnComment3}：</label>
							<div style="display: inline-block;">
								<el-input size="mini" v-model="m.${c.fieldName}" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
							</div>
						</div>
	<#elseif c.foType == 'richtext'>
						<div class="c-item br editor-item" style="margin-top: 10px;">
							<label class="c-label">${c.columnComment3}：</label>
							<div class="editor-box">
								<div id="editor"></div>
							</div>
						</div>
						<div style="clear: both;"></div>
	<#elseif c.foType == 'enum'>
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<el-radio-group v-model="m.${c.fieldName}" size="mini">
								<#list c.jvList?keys as jv>
								<el-radio-button :label="${jv}">${c.jvList[jv]}</el-radio-button>
								</#list>
							</el-radio-group>
						</div>
	<#elseif c.foType == 'img'>
						<div class="c-item br">
							<label class="c-label" style="vertical-align: top;">${c.columnComment3}：</label>
							<img :src="m.${c.fieldName}" style="width: 3em; height: 3em; cursor: pointer;" 
								@click="sa.showImage(m.${c.fieldName}, '400px', '400px')" v-if="!sa.isNull(m.${c.fieldName})">
							<el-link type="primary" @click="sa.uploadImage(src => {m.${c.fieldName} = src; sa.ok2('上传成功');})">上传</el-link>
						</div>
	<#elseif c.foType == 'audio'>
						<div class="c-item br">
							<label class="c-label" style="vertical-align: top;">${c.columnComment3}：</label>
							<el-link type="info" :href="m.${c.fieldName}" target="_blank" v-if="!sa.isNull(m.${c.fieldName})">{{m.${c.fieldName}}}</el-link>
							<el-link type="primary" @click="sa.uploadAudio(src => {m.${c.fieldName} = src; sa.ok2('上传成功');})">上传</el-link>
						</div>
	<#elseif c.foType == 'video'>
						<div class="c-item br">
							<label class="c-label" style="vertical-align: top;">${c.columnComment3}：</label>
							<el-link type="info" :href="m.${c.fieldName}" target="_blank" v-if="!sa.isNull(m.${c.fieldName})">{{m.${c.fieldName}}}</el-link>
							<el-link type="primary" @click="sa.uploadVideo(src => {m.${c.fieldName} = src; sa.ok2('上传成功');})">上传</el-link>
						</div>
	<#elseif c.foType == 'img_list'>
						<div class="c-item br">
							<label class="c-label" style="float: left;">${c.columnComment3}：</label>
							<div class="image-box" style="float: left;">
								<div class="image-box-2" v-for="image in m.${c.fieldName}_arr">
									<img :src="image" @click="sa.showImage(image, '500px', '400px')" />
									<p>
										<i class="el-icon-close" style="position: relative; top: 2px;"></i>
										<el-link @click="sa.arrayDelete(m.${c.fieldName}_arr, image)" style="color: #999;">删除这张 </el-link>
									</p>
								</div>
								<!-- 上传图集 -->
								<div class="image-box-2 up_img" @click="sa.uploadImageList(src => m.${c.fieldName}_arr.push(src))">
									<img src="../../static/img/up_icon.png">
								</div>
							</div>
						</div>
						<div style="clear: both;"></div>
	<#elseif c.foType == 'date'>
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<el-date-picker size="mini" v-model="m.${c.fieldName}" type="datetime" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
						</div>
	<#elseif c.foType == 'date-create' || c.foType == 'date-update'>
						<!-- ${c.foType}字段： m.${c.fieldName} - ${c.columnComment3} -->
	<#elseif c.foType == 'fk-1'>
					<div class="c-item">
						<label class="c-label">${c.fkPkConcatComment}：</label>
						<el-select size="mini" v-model="m.${c.fieldName}">
							<el-option label="不限" :value="0"></el-option>
							<el-option v-for="${c.fkPkTableName} in ${c.fkPkTableName}List" :label="${c.fkPkTableName}.${c.fkPkConcatName}" :value="${c.fkPkTableName}.${c.fkPkFieldName}" :key="${c.fkPkTableName}.${c.fkPkFieldName}"></el-option>
						</el-select>
					</div>
	<#elseif c.foType == 'no'>
						<!-- no字段： m.${c.fieldName} - ${c.columnComment3} -->
	<#else>
						<div class="c-item br">
							<label class="c-label">${c.columnComment3}：</label>
							<el-input size="mini" v-model="m.${c.fieldName}"></el-input>
						</div>
	</#if>
</#list>
						<div class="c-item br" v-if = "id == 0">
							<label class="c-label"></label>
							<el-button size="mini" type="primary" icon="el-icon-plus" @click="ok()">保存</el-button>
						</div>
					</el-form>
				</div>
			</div>
			<!-- ------- 底部按钮 ------- -->
			<div class="s-foot" v-if = "id != 0">
				<el-button size="mini" type="primary" @click="ok()">确定</el-button>
				<el-button size="mini" @click="sa.closeCurrIframe()">取消</el-button>
			</div>
			<div class="s-foot" v-if = "id == 0"></div>
		</div>
		<script>
			// 创建一个空model
			function create_m() {
				return {
			<#list t.columnList as c>
				<#if c.foType == 'no' || c.foType == 'date-create' || c.foType == 'date-update'>
					// ${c.fieldName}: '',		// ${c.columnComment} 
				<#elseif c.foType == 'img_list'>
					${c.fieldName}: '',		// ${c.columnComment} 
					${c.fieldName}_arr: [],		// ${c.columnComment} - 转数组
				<#else>
					${c.fieldName}: '',		// ${c.columnComment} 
				</#if>
			</#list>
				}
			}
		<#if t.hasFo('richtext') >
			// 创建编辑器
			function create_editor(content) {
				var E = window.wangEditor;
				window.editor = new E('#editor');

				editor.customConfig.menus = [
					'head', 'fontSize', 'fontName', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list',
					'justify', 'quote', 'emoticon', 'image', 'table', 'code', 'undo', 'redo' // 重复
				]
				editor.customConfig.debug = true; // debug模式
				// editor.customConfig.uploadFileName = 'file'; // 图片流name
				editor.customConfig.withCredentials = true; // 跨域携带cookie
				editor.customConfig.uploadImgMaxSize = 100 * 1024 * 1024;	// 图片大小最大100M
				// editor.customConfig.uploadImgShowBase64 = true   	// 使用 base64 保存图片
				// 重写上传图片的函数到OSS 
				editor.customConfig.customUploadImg = function(files, insert) {
					var file = files[0]; // 文件对象 
					startUploadImage2(file, function(src) {
						insert(src);
						sa.msg('上传成功');
					});
				}
				editor.create(); // 创建
				editor.txt.html(content);	// 为编辑器赋值
				setTimeout(function() {
					$('.editor-box').height($('.editor-box').height());
				})
			}
		</#if>
		</script>
        <script>
			
			var app = new Vue({
				el: '.vue-box',
				data: {
					id: sa.p('id', 0),		// 获取超链接中的id参数（0=添加，非0=修改） 
					m: null,		// 实体对象 
				<#list t.getColumnListBy('fk-1') as c>
					${c.fkPkTableName}List: [],		// ${c.fkPkConcatComment}
				</#list>
				},
				methods: {
					// 表单验证
					submit_check: function() {
						var m = this.m; 
				<#list t.columnList as c>
					<#if c.isFoType('no', 'date-create', 'date-update')>
						// if(sa.isNull(m.${c.fieldName})) {
						// 	return sa.error('请输入${c.columnComment3}');
						// }
					<#else>
						if(sa.isNull(m.${c.fieldName})) {
							return sa.error('请输入${c.columnComment3}');
						}
					</#if>
				</#list>
						return 'ok';	// ok表示正确 
					},
					// 提交数据 
					ok: function(){
						// 验证 
				<#list t.columnList as c>
					<#if c.foType == 'img_list'>
						this.m.${c.fieldName} = this.m.${c.fieldName}_arr.join(',');	// 图片数组转字符串 
					</#if>
					<#if c.foType == 'richtext'>
						this.m.${c.fieldName} = editor.txt.html();	// 获取富文本值 
					</#if>
					<#if c.foType == 'date'>
						this.m.${c.fieldName} = sa.forDate(this.m.${c.fieldName}, 2);	// 日期格式转换 
					</#if>
				</#list>
						if(this.submit_check() != 'ok') {
							return;
						}
						// 开始增加或修改
					<#list t.columnListByNotAdd as c>
						this.m.${c.fieldName} = undefined;		// 不提交属性：${c.columnComment3}
					</#list>
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
					<#if t.hasFo('richtext')>
						this.$nextTick(function() {
							create_editor('');
						})
					</#if>
					} else {	
						sa.ajax('/${t.mkNameBig}/getById?id=' + this.id, function(res) {
					<#list t.columnList as c>
						<#if c.foType == 'date'>
							res.data.${c.fieldName} = new Date(res.data.${c.fieldName});		// ${c.columnComment} 日期格式转换 
						</#if>
						<#if c.foType == 'img_list'>
							res.data.${c.fieldName}_arr = (res.data.${c.fieldName} || '').split(',');		// ${c.columnComment} 字符串转数组 
						</#if>
					</#list>
							this.m = res.data;
					<#list t.columnList as c>
						<#if c.foType == 'richtext'>
							this.$nextTick(function() {
								create_editor(this.m.${c.fieldName});
							})
						</#if>
					</#list>
						}.bind(this))
					}
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