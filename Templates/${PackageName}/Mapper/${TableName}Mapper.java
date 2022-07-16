package ${PackageName}.Mapper;

import org.apache.ibatis.annotations.Mapper;
import ${PackageName}.pojo.${TableName};
import java.util.List;
@Mapper
public interface ${TableName}Mapper {
    public int insert${TableName}(${TableName} ${TableName});//插入变量
    public int update${TableName}(${TableName} ${TableName});//更新变量
    public List<${TableName}> selectListOf${TableName}(${KeyType} ${KeyName},Integer start, Integer end);//查询变量
    public void delet${TableName}(${KeyType} ${KeyName});//删除变量
}
