package ${PackageName}.Servlet;



import ${PackageName}.Service.${TableName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import ${PackageName}.pojo.${TableName};
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${TableName}")
public class ${TableName}Controller{
@Autowired
private ${TableName}Service  ${TableName}Service;
@PostMapping("/insert")
public Integer insert${TableName}( @RequestBody ${TableName} ${TableName}){//插入数据的路径
        log.info("insert:"+ ${TableName}.toString());
        return ${TableName}Service.insert${TableName}(${TableName});
        }
@PostMapping("/update")
public Integer update${TableName}(@Nullable ${TableName} ${TableName}){
        log.info("update:" + ${TableName}.toString());
        return ${TableName}Service.update${TableName}(${TableName});
        }//更新/修改
@RequestMapping("/delet")
public void delet${TableName}(${KeyType} ${KeyName}){
        log.info("delete:" + ${KeyName}.toString());
        ${TableName}Service.delet${TableName}(${KeyName});
        }//删除

@GetMapping("/select")
public List<${TableName}> selectListOf(${KeyType} ${KeyName},Integer start, Integer end){//查询
        if(start==null){
            start=0;
        }
        if(end==null){
            end=start+10;
        }
        List<${TableName}> list = ${TableName}Service.selectListOf${TableName}(${KeyName},start,end);
        log.info(list.toString());
        return list;

        }
        }
