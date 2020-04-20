package ${t.packagePath};

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ${cfg.package_utils};

/**
 * Mapper: ${t.tableName} -- ${t.tableComment}
 * @author ${cfg.author} 
 */
@Mapper
public interface ${t.mkNameBig}Mapper {

	/** 增 */
	int add(${t.modelName} ${t.varName});

	/** 删 */
	int delete(${t.primaryKey.fieldType} ${t.primaryKey.fieldName});	<#-- 根据主键 --> 

	/** 改 */
	int update(${t.modelName} ${t.varName});

	/** 查 */
	${t.modelName} getById(${t.primaryKey.fieldType} ${t.primaryKey.fieldName});	<#-- 根据主键 --> 

	/** 查 - 集合（参数为null或0时默认忽略此条件） */
	List<${t.modelName}> getList(SoMap so);


}
