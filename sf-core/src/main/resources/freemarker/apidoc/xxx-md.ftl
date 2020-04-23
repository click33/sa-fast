# ${t.tableComment}

---

### 1、增加
- 接口
``` api
	/${t.mkNameBig}/add
```
- 参数
``` p
<#list t.columnList as c>
	{${c.fieldType}}	${c.fieldName} = ${c.defaultValue}			${c.columnComment2} 
</#list>
```
- 返回 
@import(res)


### 2、删除
- 接口
``` api
	/${t.mkNameBig}/delete
```
- 参数
``` p
	{${t.primaryKey.fieldType}}	${t.primaryKey.fieldName}			要删除的记录${t.primaryKey.fieldName}
```
- 返回
@import(res)


### 3、修改
- 接口
``` api
	/${t.mkNameBig}/update
```
- 参数
``` p
<#list t.columnList as c>
	{${c.fieldType}}	${c.fieldName} = ${c.defaultValue}			${c.columnComment2} 
</#list>
```
- 返回
@import(res)


### 4、查 - 根据id
- 接口
```  api 
	/${t.mkNameBig}/getById
```
- 参数
``` p
	{${t.primaryKey.fieldType}}	${t.primaryKey.fieldName}			要删除的记录${t.primaryKey.fieldName}
```
- 返回示例
``` js
	{
		"code": 200,
		"msg": "ok",
		"data": {
		<#list t.columnList as c>
			"${c.fieldName}": ${c.defaultValue},		// ${c.columnComment2}
		</#list>
		},
		"dataCount": null
	}
```


---
### 5、查 - 列表
- 接口
``` api
	/${t.mkNameBig}/getList
```
- 参数 (参数为null或者0时代表不限制此条件)
``` p
	{int}	pageNo = 1			当前页
	{int}	pageSize = 10		页大小 
<#list t.columnList as c>
	{${c.fieldType}}	${c.fieldName} = ${c.defaultValue}			${c.columnComment2} 
</#list>
```
- 返回 
``` js
	{
		"code": 200,
		"msg": "ok",
		"data": [
			// 数据列表，格式参考getById 
		],
		"dataCount": 100	// 数据总数
	}
```










