package ${PackageName}.Mapper;

import org.apache.ibatis.annotations.Mapper;
import ${PackageName}.pojo.${UpperTable};
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ${TableName}Mapper extends BaseMapper<${UpperTable}>{


}
