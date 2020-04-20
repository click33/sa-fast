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
		AjaxException.getBy(NbUtil.isNull(${t.varNameSimple}.get${c.getset}()), "${c.columnComment}不能为空");	// 验证: ${c.columnComment}
		</#if>
		<#if c.fieldType == "long" || c.fieldType == "int">
		AjaxException.getBy(${t.varNameSimple}.get${c.getset}() == 0, "${c.columnComment}不能为空");	// 验证: ${c.columnComment}
		</#if>
		</#list>
		
		// 重重检验，最终合格
		return true;
	}
	
	
	// 获取一个${t.modelName} 【G】
	static ${t.modelName} get${t.modelName}() {
		
		${t.modelName} ${t.varNameSimple} = new ${t.modelName}();
		<#list t.columnList as c>
		<#if c.fieldType == "String">
		${t.varNameSimple}.set${c.getset}("");	// ${c.columnComment}
		</#if>
		<#if c.fieldType == "long" || c.fieldType == "int">
		${t.varNameSimple}.set${c.getset}(0);	// ${c.columnComment}
		</#if>
		</#list>
		
		return ${t.varNameSimple};
	}
	
	
	
}
