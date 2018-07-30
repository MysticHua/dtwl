package name.huatong.dtwl.dao;

import name.huatong.dtwl.model.CityArea;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CityAreaDao {

    @Select("select * from city_area t where t.id = #{id}")
    CityArea getById(Long id);

    @Delete("delete from city_area where id = #{id}")
    int delete(Long id);

    int update(CityArea cityArea);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into city_area(province_id, city_id, province_name, city_name, createTime, updateTime) values(#{provinceId}, #{cityId}, #{provinceName}, #{cityName}, #{createTime}, #{updateTime})")
    int save(CityArea cityArea);
    
    int count(@Param("params") Map<String, Object> params);

    List<CityArea> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
