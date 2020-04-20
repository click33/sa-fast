package ${t.packagePath};

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${cfg.packagePath}.FC;
import ${cfg.package_utils};

/**
 * Controller: ${t.tableName} -- ${t.tableComment}
 * @author ${cfg.author} 
 */
@RestController
@RequestMapping("/${t.mkNameBig}/")
public class ${t.mkNameBig}Controller {


	// 增  
	@RequestMapping("add")
	AjaxJson add(${t.modelName} ${t.varNameSimple}){
		// StpUtil.checkPermission("");	// 鉴权 
		int line = FC.${t.varName}Mapper.add(${t.varNameSimple});
		return AjaxJson.getByLine(line);
	}

	// 删  
	@RequestMapping("delete")
	AjaxJson delete(${t.primaryKey.fieldType} ${t.primaryKey.fieldName}){
		// StpUtil.checkPermission("");	// 鉴权 
		int line = FC.${t.varName}Mapper.delete(${t.primaryKey.fieldName});
		return AjaxJson.getByLine(line);
	}

	// 改  
	@RequestMapping("update")
	AjaxJson update(${t.modelName} ${t.varNameSimple}){
		// StpUtil.checkPermission("");	// 鉴权 
		int line = FC.${t.varName}Mapper.update(${t.varNameSimple});
		return AjaxJson.getByLine(line);
	}

	// 查  
	@RequestMapping("getById")
	AjaxJson getById(${t.primaryKey.fieldType} ${t.primaryKey.fieldName}){
		// StpUtil.checkPermission("");	// 鉴权 
		${t.modelName} ${t.varNameSimple} = FC.${t.varName}Mapper.getById(${t.primaryKey.fieldName});
		return AjaxJson.getSuccessData(${t.varNameSimple});
	}

	// 查 - 集合（参数为null或0时默认忽略此条件）  
	@RequestMapping("getList")
	AjaxJson getList() { 
		// StpUtil.checkPermission("");	// 鉴权 
		SoMap so = SoMapUtil.getSoMap();	// 获取本次查询参数 
		so.startPage();	// 开启分页
		List<${t.modelName}> list = FC.${t.varName}Mapper.getList(so);
		return AjaxJson.getPageData(so.endPage(), list);
	}
	

}
