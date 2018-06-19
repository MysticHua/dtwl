package name.huatong.dtwl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import name.huatong.dtwl.model.WutongLineRefreshLog;

@Mapper
public interface WutongLineRefreshLogDao {

    @Select("select * from wutong_line_refresh_log t where t.id = #{id}")
    WutongLineRefreshLog getById(Long id);

    @Delete("delete from wutong_line_refresh_log where id = #{id}")
    int delete(Long id);

    int update(WutongLineRefreshLog wutongLineRefreshLog);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into wutong_line_refresh_log(refresh_time, result_code, result_message) values(#{refreshTime}, #{resultCode}, #{resultMessage})")
    int save(WutongLineRefreshLog wutongLineRefreshLog);
    
    int count(@Param("params") Map<String, Object> params);

    List<WutongLineRefreshLog> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
