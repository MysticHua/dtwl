package name.huatong.dtwl.dto;

import lombok.Data;

/**
 * 物通网结果模型
 * Created by tong.hua on 2018/6/19.
 */
@Data
public class WtBaseResultModel {

    /**
     * ret : 1
     * data :
     * msg : 专线已经全部刷新,请在2小时之后再次刷新
     */

    private int ret;
    private String data;
    private String msg;

}
