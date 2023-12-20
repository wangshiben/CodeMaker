package ${PackageName}.service;

import ${PackageName}.pojo.${UpperTable};
import java.util.List;

public interface ${UpperTable}Service {
//CRUD
    public Integer insert${UpperTable}(${UpperTable} ${TableName});//insert
    public Integer update${UpperTable}(${UpperTable} ${TableName});//update
    public Integer delete${UpperTable}(${KeyType} ${KeyName});//delet
    public List<${UpperTable}> selectListOf${UpperTable}(Integer start, Integer size);//query
    public ${UpperTable} selectSingleOf${UpperTable}(${KeyType} ${KeyName});//single Select
    public ${UpperTable} selectByKeyWord(String KeyWord, Object Value);//SelectByKeyWord
    public ${UpperTable} selectByKeyWordPage(String KeyWord, Object Value,Integer start,Integer size);//
}
