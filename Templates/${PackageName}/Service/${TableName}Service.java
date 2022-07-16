package ${PackageName}.Service;

import ${PackageName}.pojo.${TableName};
import java.util.List;

public interface ${TableName}Service {
//增删改查
    public Integer insert${TableName}(${TableName} ${TableName});//添加
    public Integer update${TableName}(${TableName} ${TableName});//更新/修改
    public void delet${TableName}(${KeyType} ${KeyName});//删除
    public List<${TableName}> selectListOf${TableName}(${KeyType} ${KeyName},Integer start, Integer end);//查询
}
