// 一个菜单可以包括的所有属性 
// {
// 	id: '12345',		// 菜单id, 必须唯一
// 	name: '用户中心',		// 菜单名称, 同时也是tab选项卡上显示的名称
// 	icon: 'el-icon-user',	// 菜单图标, 参考地址:  https://element.eleme.cn/#/zh-CN/component/icon
//	info: '管理所有用户',	// 菜单介绍, 在菜单预览和分配权限时会有显示 
// 	url: 'sa-html/user/user-list.html',	// 菜单指向地址
// 	parent_id: 1,			// 所属父菜单id, 如果指定了一个值, sa-admin在初始化时会将此菜单转移到指定菜单上 
// 	is_show: true,			// 是否显示, 默认true
// 	is_blank: false,		// 是否属于外部链接, 如果为true, 则点击菜单时从新窗口打开 
// 	childList: [			// 指定这个菜单所有的子菜单, 子菜单可以继续指定子菜单, 至多支持三级菜单
// 		// .... 
// 	],
//	click: function(){}		// 点击菜单执行一个函数 
// }

// 定义菜单列表 
var menuList =	[
	{
		id: 'ser-goods',
		name: '商品表',
		icon: 'el-icon-folder-opened',
		info: '商品表表数据的维护',
		childList: [
			{id: 'ser-goods-list', name: '商品表-列表', url: 'sa-html/ser-goods/ser-goods-list.html'},
			{id: 'ser-goods-add', name: '商品表-添加', url: 'sa-html/ser-goods/ser-goods-add.html'},
		]
	},
	{
		id: 'sys-notice',
		name: '公告表',
		icon: 'el-icon-folder-opened',
		info: '公告表表数据的维护',
		childList: [
			{id: 'sys-notice-list', name: '公告表-列表', url: 'sa-html/sys-notice/sys-notice-list.html'},
			{id: 'sys-notice-add', name: '公告表-添加', url: 'sa-html/sys-notice/sys-notice-add.html'},
		]
	},
	{
		id: 'sys-redeem',
		name: '兑换码表',
		icon: 'el-icon-folder-opened',
		info: '兑换码表表数据的维护',
		childList: [
			{id: 'sys-redeem-list', name: '兑换码表-列表', url: 'sa-html/sys-redeem/sys-redeem-list.html'},
			{id: 'sys-redeem-add', name: '兑换码表-添加', url: 'sa-html/sys-redeem/sys-redeem-add.html'},
		]
	},
	{
		id: 'sys-type',
		name: '商品分类表',
		icon: 'el-icon-folder-opened',
		info: '商品分类表表数据的维护',
		childList: [
			{id: 'sys-type-list', name: '商品分类表-列表', url: 'sa-html/sys-type/sys-type-list.html'},
			{id: 'sys-type-add', name: '商品分类表-添加', url: 'sa-html/sys-type/sys-type-add.html'},
		]
	},
]