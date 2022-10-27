package ${PackageName}.Service;

import ${PackageName}.pojo.${UpperTable};
import java.util.List;

public interface ${TableName}Service {
//增删改查
    public Integer insert${TableName}(${UpperTable} ${TableName});//添加
    public Integer update${TableName}(${UpperTable} ${TableName});//更新/修改
    public void delet${TableName}(${KeyType} ${KeyName});//删除
    public List<${UpperTable}> selectListOf${TableName}(${KeyType} ${KeyName},Integer start, Integer end);//查询
}
