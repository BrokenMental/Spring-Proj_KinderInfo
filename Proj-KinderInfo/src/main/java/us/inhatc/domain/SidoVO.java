package us.inhatc.domain;

public class SidoVO {
	
	public String sidoName;
	public int sidoCode;
	public String sigunguName;
	public int sigunguCode;
	
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public int getSigunguCode() {
		return sigunguCode;
	}
	public void setSigunguCode(int sigunguCode) {
		this.sigunguCode = sigunguCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getSigunguName() {
		return sigunguName;
	}
	public void setSigunguName(String sigunguName) {
		this.sigunguName = sigunguName;
	}
	
	@Override
	public String toString() {
		return "SidoVO [sidoName=" + sidoName + ", sidoCode=" + sidoCode + ", sigunguName=" + sigunguName
				+ ", sigunguCode=" + sigunguCode + "]";
	}
}
