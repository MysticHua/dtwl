package name.huatong.dtwl.controller;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import name.huatong.dtwl.utils.MD5Signaturer;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tong.hua on 2018/6/22.
 */
@RestController
@RequestMapping("/p2pLotteryController")
public class P2pLotteryController {

    private MD5Signaturer md5                          = new MD5Signaturer();

    public static String huiYingBaseUrl = "https://api.huiyingcaifu.com/front";
    public static String youMiBaseUrl = "https://api.yomijinrong.com/front/";
    public static String dongFangBaseUrl = "https://api.ztdfjr.com/front";

    public void hyBounsRaion(){
        String getUrl = huiYingBaseUrl+"/getRedRain.do";
        Map<String,String> param = new HashMap<>();
        param.put("userId","");
//        HttpRequestUtil.sendPost();
    }

    @RequestMapping("/fuxinghaoSignIn")
    public void fuxinghaoSignIn(){
        String uri = dongFangBaseUrl+"/signIn.do";

        String userId = "3600020170713000059";
        String token = "864033038781296";
        String version = "1.1.6";
        String appType = "ANDROID";

        //stime加密
        long stime = System.currentTimeMillis();
        //String encryTime = md5.sign(stime+"");

        StringBuilder sb = new StringBuilder("DONGFANG|").append(appType).append("|")
                .append(stime+"").append("|").append(token).append("|").append(userId).append("|").append(version);
        String encryTime = md5.sign(md5.sign(sb.toString()));

        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("userId",userId);
        reqMap.put("token",token);
        reqMap.put("version",version);
        reqMap.put("appType",appType);
        reqMap.put("stime",stime+"");
        reqMap.put("sign",encryTime);

        RestTemplate restTemplate = new RestTemplate(new ArrayList<HttpMessageConverter<?>>(){{add(new FastJsonHttpMessageConverter());}});
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.ALL);
        HttpEntity entity = new HttpEntity(reqMap,headers);

        ResponseEntity response  = restTemplate.exchange(uri,HttpMethod.GET, entity, Map.class);


        System.out.println("返回结果"+response);
//        String response = HttpRequestUtil.sendGet(getUrl, reqMap, "utf-8", "");

    }
}
