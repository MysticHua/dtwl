package name.huatong.dtwl.model;

import lombok.Data;

import java.util.Date;

@Data
public class WutongLineRefreshLog extends BaseEntity<Long> {

	private Date refreshTime;//
	private String resultCode;//SUCC 成功  FAIL 失败
	private String resultMessage;//错误原因

}
