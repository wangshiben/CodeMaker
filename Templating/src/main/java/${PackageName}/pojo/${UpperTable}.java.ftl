package ${PackageName}.pojo;

import lombok.Data;

@Data
public class ${UpperTable} {
    <#list ColumName as colum>
    private  ${colum.getColumnType()} ${colum.getColumnName()};
    </#list>
}
