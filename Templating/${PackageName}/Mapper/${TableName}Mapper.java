package ${PackageName}.Mapper;

import org.apache.ibatis.annotations.Mapper;
import ${PackageName}.pojo.${UpperTable};
import java.util.List;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ${TableName}Mapper {
    public int insert${TableName}(@Param("${TableName}") ${UpperTable} ${TableName});//插入变量
    public int update${TableName}(@Param("${TableName}") ${UpperTable} ${TableName});//更新变量
    public List<${UpperTable}> selectListOf${TableName}(@Param("start")Integer start, @Param("end") Integer end);//查询所有
    public void delet${TableName}(@Param("${KeyName}") ${KeyType} ${KeyName});//删除变量
    public ${UpperTable} selectSingleOf${TableName}(@Param("${KeyName}") ${KeyType} ${KeyName});

}
