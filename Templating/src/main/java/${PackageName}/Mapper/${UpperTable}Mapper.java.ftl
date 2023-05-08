package ${PackageName}.Mapper;

import org.apache.ibatis.annotations.Mapper;
import ${PackageName}.pojo.${UpperTable};
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ${UpperTable}Mapper extends BaseMapper<${UpperTable}>{
public List<${UpperTable}> selectAll(@Param("start")int start,@Param("end") int end);


}
