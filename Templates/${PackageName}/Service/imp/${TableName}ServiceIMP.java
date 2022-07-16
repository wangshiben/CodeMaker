package ${PackageName}.Service.imp;

import ${PackageName}.pojo.${TableName};
import ${PackageName}.Mapper.${TableName}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${PackageName}.Service.${TableName}Service;
import java.util.List;



@Service
@Transactional
public class ${TableName}ServiceIMP implements  ${TableName}Service {
    @Autowired
    private ${TableName}Mapper ${TableName}Mapper;
public Integer insert${TableName}(${TableName} ${TableName}){
        return ${TableName}Mapper.insert${TableName}(${TableName});
        }//添加
public Integer update${TableName}(${TableName} ${TableName}){
        return ${TableName}Mapper.update${TableName}(${TableName});
        }//更新/修改
public void delet${TableName}(${KeyType} ${KeyName}){
        ${TableName}Mapper.delet${TableName}(${KeyName});
        }//删除
public List<${TableName}> selectListOf${TableName}(${KeyType} ${KeyName},Integer start, Integer end){
        return ${TableName}Mapper.selectListOf${TableName}(${KeyName},start,end);
        }//查询


}