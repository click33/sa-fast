<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${t.packagePath}.${t.mkNameBig}Mapper">

	<!-- 增【G】 -->
	<insert id="add">
		insert into 
		${t.tableName} (<#list t.columnList as c>${c.columnName}<#if c_index != t.columnList?size - 1>, </#if></#list>) 
		values (<#list t.columnList as c><#if c.isFoType('date-create', 'date-update')>now()<#else><#noparse>#</#noparse>{${c.fieldName}}</#if><#if c_index != t.columnList?size - 1>, </#if></#list>) 
	</insert>

	<!-- 删 -->
	<delete id="delete">
		delete from ${t.tableName} 
		where ${t.primaryKey.columnName} = <#noparse>#</#noparse>{${t.primaryKey.fieldName}}
	</delete>
	
	<!-- 改【G】 -->
	<update id="update">
		update ${t.tableName} set
<#list t.columnList as c>
	<#if c.isFoType('no', 'date-create')>
	<#elseif c.foType == 'date-update'>
		${c.columnName} = now(),
	<#else>
		${c.columnName} = <#noparse>#</#noparse>{${c.fieldName}},
	</#if>
</#list>
		${t.primaryKey.columnName} = ${t.primaryKey.columnName} 
		where ${t.primaryKey.columnName} = <#noparse>#</#noparse>{${t.primaryKey.fieldName}}
	</update>
	
	
	<!-- ================================== 查询相关 ================================== -->
	<!-- select <#list t.columnList as c>${c.columnName}<#if c_index != t.columnList?size - 1>, </#if></#list> from ${t.tableName}  -->
	
	<!-- 通用映射 -->
	<resultMap id="model" autoMapping="true" type="${t.packagePath}.${t.modelName}"></resultMap>
	
	<!-- 公共查询sql片段 -->
	<sql id="select_sql">
		select *<#if t.getColumnListBy('fk-1', 'fk-2')?size != 0>, </#if>
<#list t.getColumnListBy('fk-1', 'fk-2') as c>
		(select ${c.fkPkConcatName} from ${c.fkPkTableName} where ${c.fkPkFieldName} = ${t.tableName}.${c.fieldName}) as ${c.fkPkTableName}_${c.fkPkConcatName}<#if c_index != t.getColumnListBy('fk-1', 'fk-2')?size-1>, </#if>
</#list>
		from ${t.tableName} 
	</sql>
	
	<!-- 查 -->
	<select id="getById" resultMap="model">
		<include refid="select_sql"></include>
		where ${t.primaryKey.columnName} = <#noparse>#</#noparse>{${t.primaryKey.fieldName}}
	</select>
	
	<!-- 查询，根据条件(参数为null或0时默认忽略此条件)【G】 -->
	<select id="getList" resultMap="model">
		<include refid="select_sql"></include>
		where 1 = 1 
<#list t.columnList as c>
	<#if c.isFoType('date', 'date-create', 'date-update', 'img', 'img_list')>
	<#else>
		<if test=' this.isNotNull("${c.fieldName}")  '>
			and ${c.columnName} = <#noparse>#</#noparse>{${c.fieldName}} 
		</if>
	</#if>
</#list>
		order by 
		<choose>
			<#list t.columnList as c>
			<when test='sort_type == ${c_index + 1}'>${c.columnName} desc</when> 
			</#list>
		 	<otherwise>${t.primaryKey.columnName} desc</otherwise>
		 </choose>
	</select>


</mapper>
