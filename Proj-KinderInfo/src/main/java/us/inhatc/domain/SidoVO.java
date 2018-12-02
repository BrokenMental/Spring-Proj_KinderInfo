package us.inhatc.domain;

public class SidoVO {
	
	public String sidoName;
	public String sigunguName;
	
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
		return "SidoVO [sidoName=" + sidoName + ", sigunguName=" + sigunguName + "]";
	}
}
