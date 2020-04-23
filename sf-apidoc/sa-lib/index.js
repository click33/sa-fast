// 声明所有全局内容 
var sa_mcontent = {
	// 例如：定义res, 你可以在md文档中使用 import(res)，来导入这一段话 
	res: '``` js\n成功时：code=200,\n失败时：code=500，msg=失败原因 \n```'
};


// 声明插件 
var sa_plugins = function(hook) {
	// 解析之前执行
	hook.beforeEach(function(content) {
		content = refMd_p2table(content); 	// 参数转表格 
		content = refMd_import2content(content);	// 加载import
		content = refMd_api2trim(content);	// api去除空格
		return content;
	});

	// 解析之后执行 
	hook.afterEach(function(html) {
		var footer = [
			'<br/><br/><br/><br/><br/><br/><br/><hr/>',
			'<footer>',
			'&emsp;<span>本接口文档使用 <a href="https://github.com/click33/sa-doc" target="_blank">sa-doc</a> 编写</span>',
			// '<span>Proudly published with <a href="https://github.com/docsifyjs/docsify" target="_blank">docsify</a>.</span>',
			'</footer>'
		].join('');
		return html + footer;
	});
};


// 移除数组中所有空白字符串 
function arrayTrimSpace(array) {
	for (var i = 0; i < array.length; i++) {
		if (array[i] == "" || array[i] == " " || array[i] == null || typeof(array[i]) == "undefined") {
			array.splice(i, 1);
			i = i - 1;
		}
	}
	return array;
}


// 获取一行表格 
// 参数名字，类型，默认值，说明 
function getTrMd(name, type, default_value, remrak) {
	var str = '\n|' + name + '|' + type + '|' + default_value + '|' + remrak + '|';
	return str;
}

// 加工md，将其中的```p 转换为table格式 
function refMd_p2table(content) {
	// 1、取出全文中所有的 ```d 
	var reg = /```\s*p[\s\S]*?```/igm; // [\s\S]*=任意字符n次，?=非贪婪模式
	var pArr = content.match(reg)|| [];; // 返回所有匹配项数组 
	// 遍历并转换 
	pArr.forEach(function(p) {
		// 声明表头 
		var table = '\n' +
			'| 参数名称| 类型| 默认值|说明|\n' +
			'| :--------| :--------| :--------|:--------|';	
			
		// 将这个p内容按换行符切割  
		var canStr = p.replace(/```\s*p/, '').replace('```', '').trim();	// 去除首行尾行 
		var canArr = canStr.split('\n') || [];	// 按行切割 
		
		// 开始逐行转换为tr 
		canArr.forEach(function(can) {
			
			// 切割成两份, 使其成为： i0=参数，i1=说明 
			var canArray = can.split(/[\s\t\n]/) || [];	
			canArray = arrayTrimSpace(canArray); 
			// 如果开发者写的不规范，使其超过2个元素，则强制改为2个元素  
			if(canArray.length > 2) {
				var str = '';
				for (var i = 0; i < canArray.length; i++) {
					if(i != canArray.length - 1) {
						str += canArray[i];
					}
				}
				canArray[0] = str;
				canArray[1] = canArray[canArray.length - 1];
			}
			
			// 声明四大变量的值   
			let name = '';		// 参数名子 
			let type = '';		// 数据类型
			let default_value = '';	// 默认值 
			let remrak = '';		// 参数说明 
			
			// 开始计算
			name = canArray[0] || ''; 	// 参数部分
			remrak = canArray[1] || ''; 	// 说明部分 
			// 如果带默认值 
			if(name.indexOf('=') > -1) {
				let dyArr = name.split('=');	// 按照=分割 
				name = dyArr[0];
				default_value = dyArr[1];
			}
			// 如果带有数据类型 
			if(name.indexOf('{') > -1 && name.indexOf('}') > -1) {
				name = name.replace('{', '');	// 去除掉前{
				let sjArr = name.split('}');	// 按照}分割 
				type = sjArr[0];
				name = sjArr[1];
			}
			// 添加到表格 
			table += getTrMd(name, type, default_value, remrak);
		})
		
		// console.log(p);
		// 将原始p内容替换成为table内容  
		table += '\n';
		content = content.replace(p, table); 
	});
	return content;
}


// 加工md，将其中的@import  转换为真实内容 
function refMd_import2content(content) {
	// 1、取出全文中所有的 ```d
	var reg = /@import\([\s\S]*?\)/igm; // [\s\S]*=任意字符n次，?=非贪婪模式
	var importArr = content.match(reg)|| []; // 返回所有匹配项数组 
	
	// 遍历并转换
	importArr.forEach(function(import_item) {
		// 过滤空格 
		// console.log(import_item);
		var item = import_item.replace(' ', '').replace('@import(', '').replace(')', '');
		
		// 开始替换 
		content = content.replace(import_item, sa_mcontent[item] || ''); 
	});
	
	return content;
}



// 加工md，将其中的```api 去除空格
function refMd_api2trim(content) {
	// 1、取出全文中所有的 ```d 
	var reg = /```\s*api[\s\S]*?```/igm; // [\s\S]*=任意字符n次，?=非贪婪模式
	var pArr = content.match(reg)|| [];; // 返回所有匹配项数组 
	
	// 遍历并转换 
	pArr.forEach(function(p) {
		// 将这个p内容按换行符切割
		var canStr = p.replace(/```\s*api/, '').replace('```', '').trim();	// 去除首行尾行 
		var canArr = canStr.split('\n') || [];	// 按行切割 
		var str = '';
		
		// 遍历并转换 
		canArr.forEach(function(p) {
			str += p;
		});
		
		str = '``` api\n' + str + '```\n';
		// console.log(str);
		
		// 将原始p内容替换成为后来内容  
		content = content.replace(p, str); 
	})
	
	
	
	return content;
}



// 打印信息 
console.log('欢迎使用sa-doc(一个基于markdown的接口文档编写工具)，当前版本：v1.0.0，更新于：2020-04-24，GitHub地址：https://github.com/click33/sa-doc');
console.log('如在使用中发现任何bug或者疑问，请加入QQ群交流：782974737，点击加入：https://jq.qq.com/?_wv=1027&k=5DHN5Ib');
