package ${t.packagePath};

import ${cfg.package_utils};

/**
 * 工具类：${t.tableName} -- ${t.tableComment}
 * @author ${cfg.author} 
 *
 */
public class ${t.mkNameBig}Util {

	
	
	

	// 验证一个${t.modelName} 是否符合标准 【G】
	static boolean check${t.modelName}(${t.modelName} ${t.varNameSimple}) {
		
		<#list t.columnList as c>
		<#if c.fieldType == "String">
		// 验证: ${c.columnComment}
		if(NbUtil.isNull(${t.varNameSimple}.get${c.getset}())) {
			throw AjaxException.get("${c.columnComment}不能为空");
		}
		</#if>
		<#if c.fieldType == "long" || c.fieldType == "int">
		// 验证: ${c.columnComment}
		if(${t.varNameSimple}.get${c.getset}() == 0) {
			throw AjaxException.get("${c.columnComment}不能为空");
		}
		</#if>
		</#list>
		
		// 重重检验，最终合格
		return true;
	}
	
	
}
