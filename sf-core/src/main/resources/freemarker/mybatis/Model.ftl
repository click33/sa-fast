package ${t.packagePath};

import java.io.Serializable;
<#if t.is_import_util>import java.util.*;</#if>

import lombok.Data;

/**
 * Model: ${t.tableName} -- ${t.tableComment}
 * @author ${cfg.author} 
 */
@Data
public class ${t.modelName} implements Serializable {

	private static final long serialVersionUID = 1L;

<#list t.columnList as c>
	private ${c.fieldType} ${c.fieldName};		// ${c.columnComment} 
</#list>


}
