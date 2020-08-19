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

	/** 权限常量 */
	static final String PERMISSION_CODE = "${t.kebabName}";

	/** 底层 Mapper 对象 */
	@Autowired
	${t.mkNameBig}Mapper ${t.varName}Mapper;

	/** 增 */  
	@RequestMapping("add")
	AjaxJson add(${t.modelName} ${t.varNameSimple}){
		StpUtil.checkPermission(PERMISSION_CODE);
		int line = ${t.varName}Mapper.add(${t.varNameSimple});
		return AjaxJson.getByLine(line);
	}

	/** 删 */  
	@RequestMapping("delete")
	AjaxJson delete(${t.primaryKey.fieldType} id){
		StpUtil.checkPermission(PERMISSION_CODE);
		int line = ${t.varName}Mapper.delete(id);
		return AjaxJson.getByLine(line);
	}

	/** 改 */  
	@RequestMapping("update")
	AjaxJson update(${t.modelName} ${t.varNameSimple}){
		StpUtil.checkPermission(PERMISSION_CODE);
		int line = ${t.varName}Mapper.update(${t.varNameSimple});
		return AjaxJson.getByLine(line);
	}

	/** 查 */  
	@RequestMapping("getById")
	AjaxJson getById(${t.primaryKey.fieldType} id){
		// StpUtil.checkPermission(PERMISSION_CODE);
		${t.modelName} ${t.varNameSimple} = ${t.varName}Mapper.getById(id);
		return AjaxJson.getSuccessData(${t.varNameSimple});
	}

	/** 查 - 集合（参数为null或0时默认忽略此条件） */  
	@RequestMapping("getList")
	AjaxJson getList() { 
		// StpUtil.checkPermission(PERMISSION_CODE);
		SoMap so = SoMap.getRequestSoMap().startPage();
		List<${t.modelName}> list = ${t.varName}Mapper.getList(so);
		return AjaxJson.getPageData(so.getDataCount(), list);
	}
	

}
