// ======================= upload-util.js 公共方法 ===========================
// 依赖库：jquery   
// 本代码更新于：2019-1-6 
// 新增apk支持

// 相关配置 
var upload_cfg = {
	upload_image_url: sa.cfg.api_url + '/upload/image',	// 图片上传地址
	upload_video_url: sa.cfg.api_url + '/upload/video',	// 视频上传地址
	upload_audio_url: sa.cfg.api_url + '/upload/audio',	// 音频上传地址
	upload_apk_url: sa.cfg.api_url + '/upload/apk',	// apk安装包上传地址
}

// 开始上传，图片版
function startUploadImage(file, successCB) {
	startUpload(file, upload_cfg.upload_image_url, successCB);
}
var startUploadImage2 = startUploadImage;	// 兼容以前的写法 

// 开始上传，视频版
function startUploadVideo(file, successCB) {
	startUpload(file, upload_cfg.upload_video_url, successCB);
}
// 开始上传，音频版
function startUploadAudio(file, successCB) {
	startUpload(file, upload_cfg.upload_audio_url, successCB);
}
// 开始上传，apk版
function startUploadApk(file, successCB) {
	startUpload(file, upload_cfg.upload_apk_url, successCB);
}



// 开始上传 --- 上传一个音乐
function startUploadAudio2(file, successCB) {
	startUpload(file, sa.cfg.api_url + '/userSongs/backAddSongs', successCB);
}


// 开始上传
function startUpload(file, url, successCB) {
	
	// 准备参数 
	var form = new FormData();
	form.append('file', file);
	
	// 开始上传 
	$.ajax({
		url: url,
		data: form,
		processData: false, // 默认true，设置为 false，不需要进行序列化处理
		cache: false, 		// 设置为false将不会从浏览器缓存中加载请求信息
		contentType: false, // 避免服务器不能正常解析文件
		dataType: 'json',
		type: 'post',
		beforeSend: function (xhr) {
			show_jdt();
        },
		complete: function (xhr) {
			close_jdt();
        },
		xhr: xhrOnProgress(function(e) {
			var percent = e.loaded / e.total; // 计算进度百分比, 取值结果为 0~1 之间的小无限不循环小数 
			// progressCB(percent * 100);
			set_jdt_value(percent * 100);
			// console.log('进度百分比' + percent);
		}),
		success: function(res) { 
			if(res.code == 200) {
				successCB(res.data);	// 把地址给回调函数 
			} else {
				sa.alert(res.msg);
			}
		},
		error: function(e) {
			sa.alert('异常: ' + JSON.stringify(e));
		}
	});
	
}




// ======================= 私有方法 =========================== 



// 返回后缀名
function get_suffix(filename) {
	var pos = filename.lastIndexOf('.');
	if (pos != -1) {
		suffix = filename.substring(pos + 1);
	}
	return suffix;
}

// 返回带有上传回调功能的 xhr 
function xhrOnProgress(fun) {
	xhrOnProgress.onprogress = fun; //绑定监听
	//使用闭包实现监听绑
	return function() {
		//通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
		var xhr = $.ajaxSettings.xhr();
		//判断监听函数是否为函数
		if (typeof xhrOnProgress.onprogress !== 'function')
			return xhr;
		//如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
		if (xhrOnProgress.onprogress && xhr.upload) {
			xhr.upload.onprogress = xhrOnProgress.onprogress;
		}
		return xhr;
	}
}



// ======================= 进度条相关 ===========================
// 显示进度条 
function show_jdt() {
	close_jdt();	// 先清除原来的 
	// 创建节点并添加到body 
	var str = '' +
		'<div class="jdt-fox" style="z-index: 999999999; width: 500px; height: 20px; position: fixed; top: calc(50% - 5px); left: calc(50% - 250px); ">'+
		'	<div class="jdt-fox2" style="width: calc(100% - 100px); height: 6px; margin-top: 7px; border-radius: 3px; float: left; background-color: #FFF; box-shadow: 0 0 10px #aaa;">'+
		'		<div class="jdt-fox-value" style=" transition: all 0.1s; position: relative; width: 0.0%; height: 100%; border-radius: 3px; background-color: green; box-shadow: 0 0 10px green;">'+
		'			<div class="jdt-fox-yh" style="position: absolute; right: -10px; top: -5px;">'+
		'				<div style="transition: all 1s; background-color: green; width: 16px; height: 16px; border-radius: 50%;"></div>'+
		'			</div>'+
		'		</div>'+
		'	</div>'+
		'	<div class="jdt-value-text" style="float: left; font-size: 14px; margin-left: 14px; color: #111;"> 0.0% </div>'+
		'</div>';
	var div = document.createElement("div");
	div.innerHTML = str;
	div.className = "jdt-box";
	document.body.appendChild(div);
	// 开启圆点的呼吸动画效果
	if (window.my_interval_index) {
		clearInterval(window.my_interval_index);
	}
	window.my_interval_index = setInterval(function() {
		if (window.one_num === undefined) {
			window.one_num = 0;
		}
		window.one_num++;
		var n_px = window.one_num % 2 == 0 ? '0px' : '20px';
		var box_shadow = "0 0 " + n_px + " green";
		document.querySelector('.jdt-fox-yh div').style.boxShadow = box_shadow;
	}, 1000);
}

// 关闭进度条 
function close_jdt() {
	// 先关闭动画 
	if (window.my_interval_index) {
		clearInterval(window.my_interval_index);
	}
	// 再销毁dom 
	var box = document.querySelector('.jdt-box');
	if (box) {
		box.parentNode.removeChild(box);
	}
}

// 设置进度条进度, 参数为一个0~100之间的小数 
function set_jdt_value(value) {
	value = parseInt(value * 10) / 10.0 + '%';	// 保证小数点后一位 
	// 改变进度条宽度
	var dft = document.querySelector('.jdt-fox-value');
	if(dft){
		dft.style.width = value;	
	}
	// 改变文字百分比值  
	var dvt = document.querySelector('.jdt-value-text');
	if(dvt) {
		dvt.innerHTML = value;	
	} 
	// console.log(value);
}









