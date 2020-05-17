package ${t.packagePath};

import java.io.Serializable;
<#if t.hasFo("date", "date-create", "date-update") >import java.util.*;
</#if>
	
<#if t.hasFo("date") >import org.springframework.format.annotation.DateTimeFormat;
</#if>



import lombok.Data;

/**
 * Model: ${t.tableName} -- ${t.tableComment}
 * @author ${cfg.author} 
 */
@Data
public class ${t.modelName} implements Serializable {

	private static final long serialVersionUID = 1L;

<#list t.columnList as c>
<#if c.foType == 'date'>
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
</#if>
	private ${c.fieldType} ${c.fieldName};		// ${c.columnComment} 
</#list>


<#if t.getColumnListBy('fk-1', 'fk-2')?size != 0>
	// 额外字段 
<#list t.getColumnListBy('fk-1', 'fk-2') as c>
	private String ${c.fkPkTableName}_${c.fkPkConcatName};		// 外键: ${c.fkPkConcatComment} 
</#list>
</#if>

	


}
