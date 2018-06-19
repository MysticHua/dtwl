package name.huatong.dtwl.quartz;

import org.springframework.stereotype.Component;

/**
 * Created by tong.hua on 2018/6/19.
 */
@Component
public class ChinaWuTongServiceTask {

    //    每10秒
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void timerToNow(){
//        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//    }

    /*@Scheduled(cron = "0/10 * * * * ?")
    public void lineRefresh(){
        String postUrl = "http://member.chinawutong.com/member/ManageWlLeasedLine.aspx";
        Map<String, Object> map = new HashMap<String, Object>();
        String cookie = "_qddaz=QD.hlw1dg.63bqv1.ji9y4mek; pgv_pvi=6297016320; UserInfo=F51F49624763D244F4527ADA288CB682FE8B3437179F764A; client=1; LoginName=13391256206; ASP.NET_SessionId=amryuppbdmd03pgh1s4j5yct; ShowMenuURL=%u7BA1%u7406%u6211%u7684%u7EBF%u8DEF; pgv_si=s3636311040; Hm_lvt_b056f6db54a055cf5bfde997b9ed913f=1529379060,1529379064,1529379081,1529379113; Hm_lpvt_b056f6db54a055cf5bfde997b9ed913f=1529381382";
        map.put("__VIEWSTATE","/wEPDwULLTE0OTM2NjkwNzNkZLSVofqctHv7itlY3wLkqk1LHWZYdZ2ZEQ7YBCr7gGfo");
        map.put("__VIEWSTATEGENERATOR","C5872AEC");
        map.put("__EVENTVALIDATION","/wEdAAaH2epOQrCRiNAaob2zev6oFL1U+Tb/NEBmWYpic2yQRsOE97UXJ49Hi0EOTJ6HPlTdZLR3NlEsUd1FjXzSE6ku8Hh57quTql3e1Wd2WFG+kopk7VcwMcnjDrbe1KtcSbnP9spJ5VJXHozOCZMIhkSzOLXiKywgk0r0n+l8OnRoJQ==");
        map.put("ctl00$cphMember$refreshAllBtn","刷新全部线路");
        map.put("ctl00$cphMember$hidFrom","上海市-上海市-嘉定区");
        HttpRequestUtil.sendPost(postUrl, map, "utf-8",cookie);
    }*/


}
