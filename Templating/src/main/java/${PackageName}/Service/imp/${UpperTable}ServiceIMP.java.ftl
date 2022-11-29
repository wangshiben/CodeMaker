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
        }//添加
@Override
public Integer update${UpperTable}(${UpperTable} ${TableName}){

        QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("${KeyName}", ${TableName}.get${UpperKeyName}());

        return ${TableName}Mapper.update(${TableName},queryWrapper);
        }//更新/修改
@Override
public Integer delete${UpperTable}(${KeyType} ${KeyName}){
        QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("${KeyName}", ${KeyName});

        return ${TableName}Mapper.delete(queryWrapper);
        }//删除
@Override
public List<${UpperTable}> selectListOf${UpperTable}(Integer start, Integer size){//查询所有
        Page<${UpperTable}> res=new Page<>(start+1,size);

        return ${TableName}Mapper.selectPage(res,null).getRecords();
        }
        //查询单个
@Override
public ${UpperTable} selectSingleOf${UpperTable}(${KeyType} ${KeyName}){
        QueryWrapper<${UpperTable}> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("${KeyName}",${KeyName} );
        return ${TableName}Mapper.selectOne(queryWrapper);
        }


}