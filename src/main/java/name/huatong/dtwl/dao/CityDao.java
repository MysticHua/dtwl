package name.huatong.dtwl.dao;

import name.huatong.dtwl.model.City;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CityDao {

    @Select("select * from city t where t.id = #{id}")
    City getById(Long id);

    @Delete("delete from city where id = #{id}")
    int delete(Long id);

    int update(City city);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into city(province_id, city_name, description) values(#{provinceId}, #{cityName}, #{description})")
    int save(City city);
    
    int count(@Param("params") Map<String, Object> params);

    List<City> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
