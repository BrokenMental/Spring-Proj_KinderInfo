package us.inhatc.domain;

public class ChartVO {
	
	private Integer solo;
	private Integer toget;
	private Integer airtel;
	private Integer etc;
	
	public Integer getSolo() {
		return solo;
	}
	public void setSolo(Integer solo) {
		this.solo = solo;
	}
	public Integer getToget() {
		return toget;
	}
	public void setToget(Integer toget) {
		this.toget = toget;
	}
	public Integer getAirtel() {
		return airtel;
	}
	public void setAirtel(Integer airtel) {
		this.airtel = airtel;
	}
	public Integer getEtc() {
		return etc;
	}
	public void setEtc(Integer etc) {
		this.etc = etc;
	}
	
	@Override
	public String toString() {
		return "ChartVO [solo=" + solo + ", toget=" + toget + ", airtel=" + airtel + ", etc=" + etc + "]";
	}
	
}
