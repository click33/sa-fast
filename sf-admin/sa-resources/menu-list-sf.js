// 此处定义所有有关 sa-fast 的路由 
window.menuList = window.menuList || [];
window.menuList.unshift(
	{
		id: '-1',
		name: '身份相关',
		is_show: false,	// 隐藏显示 
		info: '身份相关权限，不显示在菜单上', 
		childList: [
			{id: '1', name: '身份-超管', info: '最高权限，超管身份的代表（请谨慎授权）', is_show: false},
			{id: '11', name: '身份-普通账号', is_show: false, info: '无特殊权限'},
			{id: '99', name: '允许进入后台管理', is_show: false, info: '只有拥有这个权限的角色才可以进入后台'},
		]
	},
	{	// 添加 menuList 的开头
		id: '101',
		name: '权限控制',
		icon: 'el-icon-unlock',
		info: '对系统角色权限的分配等设计，敏感度较高，请谨慎授权',
		childList: [
			{id: '101-1', name: '角色列表', url: 'sa-html-sf/sf-role/role-list.html', info: '管理系统各种角色'},
			{id: '101-2', name: '菜单列表', url: 'sa-html-sf/sf-role/menu-list.html', info: '所有菜单项预览'},
			{id: '101-3', name: '管理员列表', url: 'sa-html-sf/sf-admin/admin-list.html', info: '所有管理员账号'},
			{id: '101-4', name: '管理员添加', url: 'sa-html-sf/sf-admin/admin-add.html', info: '添加一个管理员'},
			{id: '101-5', name: '请求日志监控', url: 'sa-html-sf/sf-apilog/api-log-list.html', info: '记录本系统所有的api请求'},
		]
	}
);