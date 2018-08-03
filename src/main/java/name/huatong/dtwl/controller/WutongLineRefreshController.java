package name.huatong.dtwl.controller;

import com.alibaba.fastjson.JSON;
import name.huatong.dtwl.dao.WutongLineRefreshDao;
import name.huatong.dtwl.dao.WutongLineRefreshLogDao;
import name.huatong.dtwl.dto.WtBaseResultModel;
import name.huatong.dtwl.model.WutongLineRefresh;
import name.huatong.dtwl.model.WutongLineRefreshLog;
import name.huatong.dtwl.utils.HttpRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/wutong")
public class WutongLineRefreshController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private WutongLineRefreshDao wutongLineRefreshDao;

    @Autowired
    private WutongLineRefreshLogDao wutongLineRefreshLogDao;

    //商户号
    public static String userAgent = "2767BBB06DD5982858A816130C08A4812E366B0C72FAC81B1201DA8D6BBEFF72515DC2E2EEF3547B";
    //cookie
    public static String cookie = "ASP.NET_SessionId=oakpayvs1bfz4nognofoursb";


    @GetMapping("/publishLine")
    public void publishLine(){


        String postUrl = "http://android.chinawutong.com/AddData2.ashx";
        //String param1 = "DeliverySource=0&Loadingtime=&Operating=1&UserType=4&cheChang=1.8&cheType=0&detailFromArea=%E5%B8%82%E8%BE%96%E5%8C%BA&detailToArea=%E5%B8%82%E8%BE%96%E5%8C%BA&distance=175.92&flat=31.249161710015&flng=121.48789948569&from_area=798&goods_name=%E8%AE%BE%E5%A4%87&goods_type=1&huiyuan_id=1998049&huiyuan_name=13391256206&huo_contact=%E8%8A%B1%E7%BB%8F%E7%90%86&huo_contact_to=&huo_phone=13391256205&huo_phone_to=&huounit=0&isChangqi=0&isNewCarType=2&isNewCheChang=1&qwYunjia=0&r_12459=36784&sendCheId=&sendCust_id=&shuliang=1&shuoming=&source=iPhone&tiji=10&tlat=30.259244461536&tlng=120.21937541572&to_area=927&trans_mode=2&type=addGoods&ver_version=1&weight=100&yxq=";
        //上海市辖区-浙江杭州-设备-10吨-10方
        String param = "DeliverySource=0&Loadingtime=&Operating=1&UserType=4&cheChang=1.8&cheType=0&detailFromArea=%E5%B8%82%E8%BE%96%E5%8C%BA&detailToArea=%E5%B8%82%E8%BE%96%E5%8C%BA&distance=176.07&flat=31.249161710015&flng=121.48789948569&from_area=798&goods_name=%E8%AE%BE%E5%A4%87&goods_type=1&huiyuan_id=1998049&huiyuan_name=13391256205&huo_contact=%E8%8A%B1%E7%BB%8F%E7%90%86&huo_contact_to=&huo_phone=13391256205&huo_phone_to=&huounit=1&isChangqi=0&isNewCarType=2&isNewCheChang=1&qwYunjia=0&r_3951=28672&sendCheId=&sendCust_id=&shuliang=1&shuoming=&source=iPhone&tiji=10&tlat=30.259244461536&tlng=120.21937541572&to_area=927&trans_mode=2&type=addGoods&ver_version=1&weight=10&yxq=";
        //上海市辖区-江苏苏州-服装-1吨-1方
        String param2 = "DeliverySource=0&Loadingtime=&Operating=1&UserType=4&cheChang=1.8&cheType=0&detailFromArea=%E5%98%89%E5%AE%9A%E5%8C%BA&detailToArea=%E5%B8%82%E8%BE%96%E5%8C%BA&distance=76.12&flat=31.364338055434&flng=121.25101353756&from_area=805&goods_name=%E6%9C%8D%E8%A3%85%E9%9E%8B%E5%8C%85&goods_type=1&huiyuan_id=1998049&huiyuan_name=13391256206&huo_contact=%E8%8A%B1%E7%BB%8F%E7%90%86&huo_contact_to=&huo_phone=13391256205&huo_phone_to=&huounit=1&isChangqi=0&isNewCarType=2&isNewCheChang=1&qwYunjia=0&r_40668=27702&sendCheId=&sendCust_id=&shuliang=1&shuoming=&source=iPhone&tiji=1&tlat=31.317987367952&tlng=120.61990711549&to_area=851&trans_mode=2&type=addGoods&ver_version=1&weight=1&yxq=";
        //上海市市辖区-江苏无锡江阴-汽配摩托-1吨-1方
        String param3 = "DeliverySource=0&Loadingtime=&Operating=1&UserType=4&cheChang=1.8&cheType=0&detailFromArea=%E5%98%89%E5%AE%9A%E5%8C%BA&detailToArea=%E6%B1%9F%E9%98%B4%E5%B8%82&distance=115.05&flat=31.364338055434&flng=121.25101353756&from_area=805&goods_name=%E6%B1%BD%E8%BD%A6%E6%91%A9%E6%89%98&goods_type=1&huiyuan_id=1998049&huiyuan_name=13391256206&huo_contact=%E8%8A%B1%E7%BB%8F%E7%90%86&huo_contact_to=&huo_phone=13391256205&huo_phone_to=&huounit=1&isChangqi=0&isNewCarType=2&isNewCheChang=1&qwYunjia=0&r_33734=36319&sendCheId=&sendCust_id=&shuliang=1&shuoming=&source=iPhone&tiji=1&tlat=31.837425422051&tlng=120.31067896716&to_area=829&trans_mode=2&type=addGoods&ver_version=1&weight=1&yxq=";
        String[] goodsArr = {param,param2,param3};

        String result = "";
        for(int i = 0 ;i<goodsArr.length;i++){
            result = publishLine(postUrl,goodsArr[i], "utf-8", userAgent);
            WtBaseResultModel wtBaseResultModel = JSON.parseObject(result, WtBaseResultModel.class);

            //逻辑判断 设计表结构 入库

        }

        System.out.println(result);
    }

    @GetMapping("/doRefresh")
    public void lineRefresh(){
        String getUrl = "http://android.chinawutong.com/Manage.ashx?operObj=4&cust_id=1998049&operType=9&ver_version=1&r_17178=5978";
        String cookie = "ASP.NET_SessionId=oakpayvs1bfz4nognofoursb";

        //解析返回结果
        String result = doRefreshLine(getUrl, cookie, userAgent);
        WtBaseResultModel wtBaseResultModel = JSON.parseObject(result, WtBaseResultModel.class);

        //更新总记录
        WutongLineRefresh wutongLineRefresh = wutongLineRefreshDao.getById(1L);
        if(wutongLineRefresh == null){
            //新建
            WutongLineRefresh lineForInsert = new WutongLineRefresh();
            lineForInsert.setId(1L);
            lineForInsert.setTotalRefreshCount(1);
            lineForInsert.setSuccRefreshCount(0);
            lineForInsert.setFailRefreshCount(0);
            lineForInsert.setLastSuccTime(null);
            if(wutongLineRefreshDao.save(lineForInsert) < 1){
                log.error("创建总刷新记录表失败，refreshId:"+lineForInsert.getId());
                return;
            }
        }else{
            //更新总表
            wutongLineRefresh.setId(1L);
            int totalRefreshCount = wutongLineRefresh.getTotalRefreshCount();
            int succRefreshCount = wutongLineRefresh.getSuccRefreshCount();
            int failRefreshCount = wutongLineRefresh.getFailRefreshCount();
            if(wtBaseResultModel.getRet() == 0){
                succRefreshCount ++;
            }else{
                failRefreshCount++;
            }
            wutongLineRefresh.setTotalRefreshCount(totalRefreshCount+1);
            wutongLineRefresh.setSuccRefreshCount(succRefreshCount);
            wutongLineRefresh.setFailRefreshCount(failRefreshCount);
            if(wutongLineRefreshDao.update(wutongLineRefresh) < 1){
                log.error("创建总刷新记录表失败");
                return;
            }

            //更新日志表
            WutongLineRefreshLog wutongLineRefreshLog = new WutongLineRefreshLog();
            wutongLineRefreshLog.setRefreshTime(new Date());
            wutongLineRefreshLog.setResultCode(wtBaseResultModel.getRet()+"");
            String msg = "";
            if(wtBaseResultModel.getRet() == 0 ){
                msg = "刷新成功";
            }else{
                msg = wtBaseResultModel.getMsg();
            }
            wutongLineRefreshLog.setResultMessage(msg);
            if(wutongLineRefreshLogDao.save(wutongLineRefreshLog) < 1){
                log.error("创建刷新记录明细表失败败");
                return;
            }
        }

        System.out.println("response："+JSON.toJSONString(wtBaseResultModel));
    }

    /**
     * 刷新操作
     * @param getUrl
     * @param cookie
     * @param userAgent
     * @return
     */
    private String doRefreshLine(String getUrl, String cookie, String userAgent) {
        String result = "";
        String line;
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(getUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //设置cookie
            if(StringUtils.isNoneBlank()){
                conn.setRequestProperty("Cookie", cookie);
            }
            //设置userAgent
            conn.setRequestProperty("User-Agent",userAgent);
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应,设置接收格式
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发布货源线路
     * @param postUrl
     * @param param
     * @param charset
     * @param userAgent
     * @return
     */
    private String publishLine(String postUrl,String param,String charset,String userAgent){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String line;
        StringBuffer sb = new StringBuffer();
        try {
            URL realUrl = new URL(postUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            if(StringUtils.isNoneBlank(cookie)){
                //设置cookie
                conn.setRequestProperty("Cookie", cookie);
            }
            //设置userAgent
            conn.setRequestProperty("User-Agent",userAgent);
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", charset);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("发送 POST请求出现异常!" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
