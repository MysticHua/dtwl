package name.huatong.dtwl.model;

import java.util.Date;

public class CountryArea extends BaseEntity<Long> {

	private String cityId;//市区区划代码
	private String countyId;//县城区划代码
	private String cityName;//城市名称
	private String countyName;//县城名称

	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

}
