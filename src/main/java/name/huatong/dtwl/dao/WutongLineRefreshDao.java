package name.huatong.dtwl.dao;

import name.huatong.dtwl.model.WutongLineRefresh;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface WutongLineRefreshDao {

    @Select("select * from wutong_line_refresh t where t.id = #{id}")
    WutongLineRefresh getById(Long id);

    @Delete("delete from wutong_line_refresh where id = #{id}")
    int delete(Long id);

    int update(WutongLineRefresh wutongLineRefresh);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into wutong_line_refresh(total_refresh_count, succ_refresh_count, fail_refresh_count, last_succ_time, create_time, update_time, memo) values(#{totalRefreshCount}, #{succRefreshCount}, #{failRefreshCount}, #{lastSuccTime}, #{createTime}, #{updateTime}, #{memo})")
    int save(WutongLineRefresh wutongLineRefresh);
    
    int count(@Param("params") Map<String, Object> params);

    List<WutongLineRefresh> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
