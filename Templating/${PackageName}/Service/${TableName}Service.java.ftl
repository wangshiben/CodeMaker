package ${PackageName}.Service;

import ${PackageName}.pojo.${UpperTable};
import java.util.List;

public interface ${TableName}Service {
//增删改查
    public Integer insert${UpperTable}(${UpperTable} ${TableName});//添加
    public Integer update${UpperTable}(${UpperTable} ${TableName});//更新/修改
    public Integer delete${UpperTable}(${KeyType} ${KeyName});//删除
    public List<${UpperTable}> selectListOf${UpperTable}(Integer start, Integer size);//查询
    public ${UpperTable} selectSingleOf${UpperTable}(${KeyType} ${KeyName});//查询单个
}
