package ${PackageName}.Service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${PackageName}.pojo.${UpperTable};
import ${PackageName}.Mapper.${UpperTable}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${PackageName}.Service.${UpperTable}Service;
import java.util.List;


@Service
@Transactional
public class ${UpperTable}ServiceIMP implements  ${UpperTable}Service {
@Autowired
private ${UpperTable}Mapper ${TableName}Mapper;
@Override
    public Integer insert${UpperTable}(${UpperTable} ${TableName}){
        return ${TableName}Mapper.insert(${TableName});
    }//insert
@Override
public Integer update${UpperTable}(${UpperTable} ${TableName}){

        QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("${KeyName}", ${TableName}.get${UpperKeyName}());
        return ${TableName}Mapper.update(${TableName},queryWrapper);
        }//update
@Override
public Integer delete${UpperTable}(${KeyType} ${KeyName}){
    QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
    queryWrapper.eq("${KeyName}", ${KeyName});

    return ${TableName}Mapper.delete(queryWrapper);
    }//delet
@Override
public List<${UpperTable}> selectListOf${UpperTable}(Integer start, Integer size){//select all
    Page<${UpperTable}> page = new Page<>();
    page.setCurrent(start).setSize(size);
    QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
    Page<${UpperTable}> res = ${TableName}Mapper.selectPage(page, queryWrapper);
    return res.getRecords();
}
//single
@Override
public ${UpperTable} selectSingleOf${UpperTable}(${KeyType} ${KeyName}){
    QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
    queryWrapper.eq("${KeyName}",${KeyName} );
    return ${TableName}Mapper.selectOne(queryWrapper);
}
@Override
public ${UpperTable} selectByKeyWord(String KeyWord, Object Value){
    QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
    queryWrapper.eq(KeyWord,Value);
    return ${TableName}Mapper.selectOne(queryWrapper);
}
public List<${UpperTable}> selectByKeyWordPage(String KeyWord, Object Value,Integer start,Integer size){
    QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
    Page<${UpperTable}> page=new Page<>();
    page.setCurrent(start).setSize(size);
    Page<${UpperTable}> res = ${TableName}Mapper.selectPage(page,queryWrapper);
    return res.getRecords();
}


}
