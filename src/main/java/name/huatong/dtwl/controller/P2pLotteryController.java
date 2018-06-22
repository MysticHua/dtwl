package name.huatong.dtwl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tong.hua on 2018/6/22.
 */
@RestController
@RequestMapping("/p2pLotteryController")
public class P2pLotteryController {

    public static String huiYingBaseUrl = "https://api.huiyingcaifu.com/front";
    public static String youMiBaseUrl = "https://api.yomijinrong.com/front/";
    public static String dongFangBaseUrl = "https://api.ztdfjr.com/front/";

    public void hyBounsRaion(){
        String getUrl = huiYingBaseUrl+"/getRedRain.do";
        Map<String,String> param = new HashMap<>();
        param.put("userId","");
//        HttpRequestUtil.sendPost();
    }
}
