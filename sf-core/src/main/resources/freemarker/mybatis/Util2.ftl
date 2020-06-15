package ${t.packagePath};

import ${cfg.package_utils};

/**
 * 工具类：${t.tableName} -- ${t.tableComment}
 * @author ${cfg.author} 
 *
 */
// @Component
public class ${t.mkNameBig}Util {

	
//	/** 底层 Mapper 对象 */
//	static ${t.mkNameBig}Mapper ${t.varName}Mapper;
//	@Autowired
//	public void set${t.mkNameBig}Mapper(${t.mkNameBig}Mapper ${t.varName}Mapper) {
//		${t.mkNameBig}Util.${t.varName}Mapper = ${t.varName}Mapper;
//	}
	
<#-- private时 -->
<#if cfg.modelVisitWay == 1>
	// 验证一个${t.modelName} 是否符合标准 (方便表单校验用)【G】
	static void check${t.modelName}(${t.modelName} ${t.varNameSimple}) {
		
		<#list t.columnList as c>
		AjaxError.byIsNull(${t.varNameSimple}.get${c.getset}(), "${c.columnComment}不能为空");	// 验证: ${c.columnComment}
		</#list>
		
		// 重重检验，最终合格
	}
</#if>
<#-- public时 -->
<#if cfg.modelVisitWay == 2>
	// 验证一个${t.modelName} 是否符合标准 (方便表单校验用)【G】
	static boolean check${t.modelName}(${t.modelName} ${t.varNameSimple}) {
		
		<#list t.columnList as c>
		AjaxError.byIsNull(${t.varNameSimple}.${c.fieldName}, "${c.columnComment}不能为空");	// 验证: ${c.columnComment}
		</#list>
		
		// 重重检验，最终合格
		return true;
	}
</#if>




<#-- private时 -->
<#if cfg.modelVisitWay == 1>
	// 获取一个${t.modelName} (方便复制代码用)【G】
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
</#if>
<#-- public时 -->
<#if cfg.modelVisitWay == 2>
	// 获取一个${t.modelName} (方便复制代码用)【G】
	static ${t.modelName} get${t.modelName}() {
		
		${t.modelName} ${t.varNameSimple} = new ${t.modelName}();
		<#list t.columnList as c>
		<#if c.fieldType == "String">
		${t.varNameSimple}.${c.fieldName} = "";	// ${c.columnComment}
		</#if>
		<#if c.fieldType == "long" || c.fieldType == "int">
		${t.varNameSimple}.${c.fieldName} = 0;	// ${c.columnComment}
		</#if>
		</#list>
		
		return ${t.varNameSimple};
	}
</#if>

	
	
	
	
	
}
