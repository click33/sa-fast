package ${t.packagePath};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${cfg.package_utils};

import cn.dev33.satoken.stp.StpUtil;

/**
 * Controller: ${t.tableName} -- ${t.tableComment}
 * @author ${cfg.author} 
 */
@RestController
@RequestMapping("/${t.mkNameBig}/")
public class ${t.mkNameBig}Controller {

	/** 底层 Service 对象 */
	@Autowired
	${t.mkNameBig}Service ${t.varName}Service;

	// 增  
	@RequestMapping("add")
	AjaxJson add(${t.modelName} ${t.varNameSimple}){
		StpUtil.checkPermission("${t.kebabName}");	// 鉴权 
		int line = ${t.varName}Service.add(${t.varNameSimple});
		return AjaxJson.getByLine(line);
	}

	// 删  
	@RequestMapping("delete")
	AjaxJson delete(${t.primaryKey.fieldType} id){
		StpUtil.checkPermission("${t.kebabName}");	// 鉴权 
		int line = ${t.varName}Service.delete(id);
		return AjaxJson.getByLine(line);
	}

	// 改  
	@RequestMapping("update")
	AjaxJson update(${t.modelName} ${t.varNameSimple}){
		StpUtil.checkPermission("${t.kebabName}");	// 鉴权 
		int line = ${t.varName}Service.update(${t.varNameSimple});
		return AjaxJson.getByLine(line);
	}

	// 查  
	@RequestMapping("getById")
	AjaxJson getById(${t.primaryKey.fieldType} id){
		// StpUtil.checkPermission("${t.kebabName}");	// 鉴权 
		${t.modelName} ${t.varNameSimple} = ${t.varName}Service.getById(id);
		return AjaxJson.getSuccessData(${t.varNameSimple});
	}

	// 查 - 集合（参数为null或0时默认忽略此条件）  
	@RequestMapping("getList")
	AjaxJson getList() { 
		// StpUtil.checkPermission("${t.kebabName}");	// 鉴权 
		SoMap so = SoMap.getRequestSoMap();	// 获取本次请求参数 
		so.startPage();	// 开启分页
		List<${t.modelName}> list = ${t.varName}Service.getList(so);	// 查询数据 
		return AjaxJson.getPageData(so.getDataCount(), list);	// 返回数据给前端 
	}
	

}
