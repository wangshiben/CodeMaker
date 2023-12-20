package ${PackageName}.Servlet;


import ${PackageName}.Resp.BaseRespones;
import ${PackageName}.Service.${UpperTable}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import ${PackageName}.pojo.${UpperTable};
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${TableName}")
public class ${UpperTable}Controller{
@Autowired
private ${UpperTable}Service  ${TableName}Service;
@PostMapping("/insert")
public BaseRespones<Integer> insert${UpperTable}( @RequestBody ${UpperTable} ${TableName}){//insertPath
    log.info("insert:"+ ${TableName}.toString());

    return BaseRespones.success( ${TableName}Service.insert${UpperTable}(${TableName}));
    }
    @PostMapping("/update")
    public BaseRespones<Integer> update${UpperTable}(@Nullable @RequestBody ${UpperTable} ${TableName}){
        log.info("update:" + ${TableName}.toString());
        return BaseRespones.success(${TableName}Service.update${UpperTable}(${TableName}));
        }//update
    @DeleteMapping("/delete")
    public BaseRespones<Integer> delet${UpperTable}(${KeyType} ${KeyName}){
            log.info("delete:" + ${KeyName}.toString());
            return BaseRespones.success(${TableName}Service.delete${UpperTable}(${KeyName}));
            }//deletAll

    @GetMapping("/select")
    public BaseRespones<List< ${UpperTable}> > selectListOf(Integer start, Integer size){//selectAll Data
        if(start==null||start<0){
            start=0;
        }
        if(size==null||size<0){
            size=10;
        }
        List<${UpperTable}> list = ${TableName}Service.selectListOf${UpperTable}(start,size);
        log.info(list.toString());
        return BaseRespones.success(list);

    }
    @GetMapping("/single")
    public BaseRespones<${UpperTable}> selectSingleOf${TableName}(${KeyType} ${KeyName}){
         ${UpperTable} ${TableName} = ${TableName}Service.selectSingleOf${UpperTable}(${KeyName});
         log.info(${TableName}.toString());
         return BaseRespones.success(${TableName});
    }


}
