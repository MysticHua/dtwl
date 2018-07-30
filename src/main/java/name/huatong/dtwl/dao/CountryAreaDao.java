package name.huatong.dtwl.dao;

import name.huatong.dtwl.model.CountryArea;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CountryAreaDao {

    @Select("select * from country_area t where t.id = #{id}")
    CountryArea getById(Long id);

    @Delete("delete from country_area where id = #{id}")
    int delete(Long id);

    int update(CountryArea countryArea);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into country_area(city_id, county_id, city_name, county_name, createTime, updateTime) values(#{cityId}, #{countyId}, #{cityName}, #{countyName}, #{createTime}, #{updateTime})")
    int save(CountryArea countryArea);
    
    int count(@Param("params") Map<String, Object> params);

    List<CountryArea> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
