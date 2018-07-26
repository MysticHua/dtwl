package name.huatong.dtwl.controller;

import name.huatong.dtwl.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //要抓取的网站
    private static String basicUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";
    //匹配总省份的HTML正则表达式
    private static String provinceHtmlRegEx = "<tr class='provincetr'>[\\s\\S]*</tr>";
    //匹配各省份
    private static String provinceItemRegEx = "<td><a href=[\\s\\S]{0,}?<br/></a></td>";

    private static String proviceIdRegEx = "[^0-9]+?";

    private static String proviceNameRegEx = "[^\\u4e00-\\u9fa5]+?";


    @GetMapping("/doSpiderCity")
    public void doSpiderCity() {
        String provinceMatcherResult;//省份匹配到的结果
        String result = HttpRequestUtil.sendGet(basicUrl, "", "gb2312", "");

        //开始匹配省份
        Pattern provinceHtmlPattern = Pattern.compile(provinceHtmlRegEx);
        Matcher provinceHtmlMatcher = provinceHtmlPattern.matcher(result);
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

            Pattern proviceIdPattern = Pattern.compile(proviceIdRegEx);
            Pattern proviceNamePattern = Pattern.compile(proviceNameRegEx);
            Matcher proviceIdMatcher = proviceIdPattern.matcher(provinceItemHtml);
            Matcher proviceNameMatcher = proviceNamePattern.matcher(provinceItemHtml);
            if(proviceIdMatcher.find() && proviceNameMatcher.find()){
                String provinceId =proviceIdMatcher.replaceAll("").trim();
                String provinceName = proviceNameMatcher.replaceAll("").trim();
                //System.out.println("省份的ID为:"+provinceId+",名字为："+provinceName);

                //从这里开始匹配每一个省的地区
            }
            //System.out.println("找到了第" + (i + 1) + "个，结果为：" + provinceItemMatcher.group());
            i++;
        }
        System.out.println("共匹配到了"+i +"个");
    }

}
