package name.huatong.dtwl.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import name.huatong.dtwl.dao.WutongLineRefreshDao;
import name.huatong.dtwl.dao.WutongLineRefreshLogDao;
import name.huatong.dtwl.dto.WtBaseResultModel;
import name.huatong.dtwl.model.WutongLineRefresh;
import name.huatong.dtwl.model.WutongLineRefreshLog;
import name.huatong.dtwl.page.table.PageTableHandler;
import name.huatong.dtwl.page.table.PageTableHandler.CountHandler;
import name.huatong.dtwl.page.table.PageTableHandler.ListHandler;
import name.huatong.dtwl.page.table.PageTableRequest;
import name.huatong.dtwl.page.table.PageTableResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wutongLineRefreshs")
public class WutongLineRefreshController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private WutongLineRefreshDao wutongLineRefreshDao;

    @Autowired
    private WutongLineRefreshLogDao wutongLineRefreshLogDao;

    @GetMapping("/doRefresh")
    public void lineRefresh(){
        String getUrl = "http://android.chinawutong.com/Manage.ashx?operObj=4&cust_id=1998049&operType=9&ver_version=1&r_17178=5978";
        String cookie = "ASP.NET_SessionId=oakpayvs1bfz4nognofoursb";
        String userAgent = "2767BBB06DD5982858A816130C08A4812E366B0C72FAC81B1201DA8D6BBEFF72515DC2E2EEF3547B";
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
                System.out.println("创建总刷新记录表失败");
                //使用日志
                return;
            }

            //更新日志表
            WutongLineRefreshLog wutongLineRefreshLog = new WutongLineRefreshLog();
            wutongLineRefreshLog.setRefreshTime(new Date());
            wutongLineRefreshLog.setResultCode(wtBaseResultModel.getRet()+"");
            wutongLineRefreshLog.setResultMessage(wtBaseResultModel.getMsg());
            if(wutongLineRefreshLogDao.save(wutongLineRefreshLog) < 1){
                System.out.println("创建刷新记录明细表失败败");
                //使用日志
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

}
