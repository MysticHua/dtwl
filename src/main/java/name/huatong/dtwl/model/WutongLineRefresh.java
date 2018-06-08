package name.huatong.dtwl.model;

import lombok.Data;

import java.util.Date;

@Data
public class WutongLineRefresh extends BaseEntity<Long> {

	private Integer totalRefreshCount;//总刷新次数
	private Integer succRefreshCount;//成功刷新的次数
	private Integer failRefreshCount;//失败的次数
	private Date lastSuccTime;//最后成功的时间
	private String memo;//

}
