package name.huatong.dtwl.model;

import java.util.Date;

public class CountryArea extends BaseEntity<Long> {

	private String cityId;//������������
	private String countyId;//�س���������
	private String cityName;//��������
	private String countyName;//�س�����

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
