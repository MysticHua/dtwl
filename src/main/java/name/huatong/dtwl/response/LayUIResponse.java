package name.huatong.dtwl.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linjunjie on 2015/11/28 (linjunjie@onway.com).
 */
@Data
@Accessors(chain = true)
public class LayUIResponse implements Serializable {
    private static final long serialVersionUID = 9173910332219164028L;
    private int code = 0;
    private String  msg = "";
    private long  count;
    private List data = new ArrayList();
}
