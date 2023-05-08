# 后端简易代码生成器
## 环境配置地址:
* 1. 数据库表类型的映射和java中类的映射配置在config/TypeName.properties内
* 2. 生成地址和输出地址也是在config/TypeName.properties下
* 3. 项目启动后是在http://localhost:8001/ 查看
## 代码生成后可根据自身项目需求进行更改，本项目只会生成增删改查四个接口

### 具体接口如下:

每个表的接口不同,前置路径为"/{表名}"

1. 插入接口: /insert -POST

2. 删除接口: /delete -DELETE
3. 查询单个接口: /single -GET
4. 分页接口

### 模板文件可根据具体项目需求进行更改，本项目具体提供的模板数据如下:

> 注:代码生成的不一定是Java文件，如果有需求可自行替换模板文件，进行其他语言的代码生成

```java
model.put("PackageName",package_name);//生成包名
model.put("TableName",tables.getTableName());//生成表的名字(实体类)
model.put("ColumName",tables.getColumns());//列的名字以及对应的Java的类型(实体类中参数)
model.put("DBName",basename);//数据库名字
model.put("KeyName",tables.getKeyName());//主键名字
model.put("UpperKeyName", CharsUtil.UpperFirstCode(tables.getKeyName()));//首字母大写,方便调用Get/Set方法
model.put("KeyType",tables.getKeyType());//主键类型
model.put("UpperTable",tables.getUpperTable());//大写
```

> 优点:
>
> 1. 可远程连接数据库进行生成,不用在配置文件中进行连接
> 2. 可选择连接数据库的方式:1.通过源码启动 2.部署之后通过web界面远程连接
> 3. 生成代码可下载为ZIP文件
> 4. 生成代码不用依赖于本项目使用，实现真正的开箱即用
> 5. 最大限度简化了项目单表的增删改查，还提供了自定义的响应类和mybatis的分页配置以及配置文件的一些配置



> TODO:
>
> 1. 拖拽生成前端代码与后端代码绑定
> 2. 优化一下页面
> 3. 后端新增分布式和redis相关内容
> 4. 后端新增多数据库的支持
