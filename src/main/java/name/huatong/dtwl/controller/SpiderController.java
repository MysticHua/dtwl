package name.huatong.dtwl.controller;

import name.huatong.dtwl.dao.CityAreaDao;
import name.huatong.dtwl.dao.CountryAreaDao;
import name.huatong.dtwl.model.CityArea;
import name.huatong.dtwl.model.CountryArea;
import name.huatong.dtwl.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬虫控制器
 * Created by tong.hua on 2018/7/26.
 */
@RestController
@RequestMapping("/spiderController")
public class SpiderController {

    private static final Logger log = LoggerFactory.getLogger(SpiderController.class);

    @Autowired
    CityAreaDao cityAreaDao;

    @Autowired
    CountryAreaDao countryAreaDao;

    //要抓取的网站
    private static String BASIC_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/";
    //匹配总省份的HTML正则表达式
    private static String provinceHtmlRegEx = "<tr class='provincetr'>[\\s\\S]*</tr>";
    //匹配各省份
    private static String provinceItemRegEx = "<td><a href=[\\s\\S]{0,}?<br/></a></td>";
    //匹配各省份ID
    private static String provinceIdRegEx = "\\d+";
    //匹配城市名称
    private static String areaNameRegEx = "[\\u4e00-\\u9fa5]+";
    //匹配各县城 区
    private static String cityHtmlRegEx = "<tr class='citytr'>[\\s\\S]{0,}?</tr>";
    //匹配各县城 区 ID
    private static String AREA_ID_REG_EX = "\\d{12}";
    //匹配各县城 区url
    private static String CITY_URL_REG_EX = "\\d{2}/\\d{4}\\.html";
    //匹配各县城 区 名称
    private static String countryHtmlRegEx = "<tr class='countytr'>[\\s\\S]{0,}?</tr>";

    @GetMapping("/doSpiderCity")
    public void doSpiderCity() {
        //抓取省份的url
        String provinceHtmlUrl = BASIC_URL + "index.html";
        String provinceMatcherResult;//省份匹配到的结果
        String provinceHtmlResult = HttpRequestUtil.sendGet(provinceHtmlUrl, "", "gb2312", "");

        //开始匹配省份
        CityArea cityAreaForInsert = new CityArea();

        Pattern provinceHtmlPattern = Pattern.compile(provinceHtmlRegEx);
        Matcher provinceHtmlMatcher = provinceHtmlPattern.matcher(provinceHtmlResult);
        if (provinceHtmlMatcher.find()) {
            provinceMatcherResult = provinceHtmlMatcher.group(0);
        } else {
            log.error("正则匹配省份结果失败，结果为空");
            return;
        }

        Pattern provinceItemPattern = Pattern.compile(provinceItemRegEx);
        Matcher provinceItemMatcher = provinceItemPattern.matcher(provinceMatcherResult);

        int i = 0;
        while (provinceItemMatcher.find()) {
            String provinceItemHtml = provinceItemMatcher.group();

            Pattern proviceIdPattern = Pattern.compile(provinceIdRegEx);
            Pattern areaNamePattern = Pattern.compile(areaNameRegEx);
            Matcher proviceIdMatcher = proviceIdPattern.matcher(provinceItemHtml);
            Matcher proviceNameMatcher = areaNamePattern.matcher(provinceItemHtml);
            if(proviceIdMatcher.find() && proviceNameMatcher.find()){
                String provinceId =proviceIdMatcher.group();
                String provinceName = proviceNameMatcher.group();
                System.out.println("省份的ID为:"+provinceId+",名字为："+provinceName);

                //从这里开始匹配每一个省的地区
                //要抓取的每个市的url
                String cityHtmlUrl = BASIC_URL + provinceId + ".html";
                String cityHtmlResult = HttpRequestUtil.sendGet(cityHtmlUrl, "", "gb2312", "");
                Pattern cityHtmlPattern = Pattern.compile(cityHtmlRegEx);
                Matcher cityHtmlMatcher = cityHtmlPattern.matcher(cityHtmlResult);
                while (cityHtmlMatcher.find()){
                    String cityItem = cityHtmlMatcher.group();
                    Pattern areaIdPattern = Pattern.compile(AREA_ID_REG_EX);
                    Matcher cityIdMatcher = areaIdPattern.matcher(cityItem);

                    Pattern cityNamePattern = Pattern.compile(areaNameRegEx);
                    Matcher cityNameMatcher = cityNamePattern.matcher(cityItem);

                    Pattern cityUrlPattern = Pattern.compile(CITY_URL_REG_EX);
                    Matcher cityUrlMatcher = cityUrlPattern.matcher(cityItem);
                    if (cityIdMatcher.find() && cityNameMatcher.find() && cityUrlMatcher.find()){
                        String cityId = cityIdMatcher.group();
                        String cityName = cityNameMatcher.group();
                        System.out.println("城市的ID为:"+cityId+",城市的名字为："+cityName);

                        //插入省份信息
                        cityAreaForInsert.setProvinceId(provinceId);
                        cityAreaForInsert.setProvinceName(provinceName);
                        cityAreaForInsert.setCityId(cityId);
                        cityAreaForInsert.setCityName(cityName);
                        cityAreaForInsert.setCreateTime(new Date());

                        cityAreaDao.save(cityAreaForInsert);

                        //从这里还是匹配县级url
                        CountryArea countryAreaForInsert = new CountryArea();

                        String countyUrl = BASIC_URL+cityUrlMatcher.group();//县级url
                        String countyHtmlResult = HttpRequestUtil.sendGet(countyUrl, "", "gb2312", "");

                        Pattern countryPattern = Pattern.compile(countryHtmlRegEx);
                        Matcher countryMatcher = countryPattern.matcher(countyHtmlResult);
                        while (countryMatcher.find()){
                            String countryHtml = countryMatcher.group();
                            Matcher countryIdMather = areaIdPattern.matcher(countryHtml);
                            Matcher countryNameMather = areaNamePattern.matcher(countryHtml);
                            if(countryIdMather.find() && countryNameMather.find()){
                                String countryId = countryIdMather.group();
                                String countryName = countryNameMather.group();
                                System.out.println("县城的ID为:"+countryId+",县城的名字为："+countryName);

                                countryAreaForInsert.setCityId(cityId);
                                countryAreaForInsert.setCityName(cityName);
                                countryAreaForInsert.setCountyId(countryId);
                                countryAreaForInsert.setCountyName(countryName);

                                countryAreaDao.save(countryAreaForInsert);
                            }
                        }

                    }else{
                        System.out.println("匹配城市错误");
                        return;
                    }
                    //System.out.println("找到了一个市的html"+cityHtmlMatcher.group());
                }
            }
            //System.out.println("找到了第" + (i + 1) + "个，结果为：" + provinceItemMatcher.group());
            i++;
        }
        System.out.println("共匹配到了"+i +"个");
    }

}
