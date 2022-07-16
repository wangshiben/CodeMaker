package ${PackageName}.pojo;

import lombok.Data;

@Data
public class ${TableName} {
    <#list ColumName as colum>
    private  ${colum.getColumnType()} ${colum.getColumnName()};
    </#list>
}
