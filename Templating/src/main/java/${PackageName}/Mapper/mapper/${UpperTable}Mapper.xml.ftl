<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${PackageName}.Mapper.${UpperTable}Mapper">
    <select id="selectAll" resultType="${PackageName}.pojo.${UpperTable}">
        SELECT  <#list ColumName as colum>`${colum.getColumnName()}`<#if colum_has_next>,</#if></#list> FROM`${TableName}` LIMIT <#noparse>#{</#noparse>start<#noparse>}</#noparse>,<#noparse>#{</#noparse>end<#noparse>}</#noparse>
    </select>
</mapper>