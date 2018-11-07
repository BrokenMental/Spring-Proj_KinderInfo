package us.inhatc.domain;

public class CrimeVO {
	
	private Integer jungbu;
	private Integer nambu;
	private Integer namdong;
	private Integer bupheung;
	private Integer seobu;
	private Integer gyeyang;
	private Integer ganghwa;
	private Integer yunsu;
	private Integer samsan;
	private Integer nonhyeon;
	
	public Integer getJungbu() {
		return jungbu;
	}
	public void setJungbu(Integer jungbu) {
		this.jungbu = jungbu;
	}
	public Integer getNambu() {
		return nambu;
	}
	public void setNambu(Integer nambu) {
		this.nambu = nambu;
	}
	public Integer getNamdong() {
		return namdong;
	}
	public void setNamdong(Integer namdong) {
		this.namdong = namdong;
	}
	public Integer getBupheung() {
		return bupheung;
	}
	public void setBupheung(Integer bupheung) {
		this.bupheung = bupheung;
	}
	public Integer getSeobu() {
		return seobu;
	}
	public void setSeobu(Integer seobu) {
		this.seobu = seobu;
	}
	public Integer getGyeyang() {
		return gyeyang;
	}
	public void setGyeyang(Integer gyeyang) {
		this.gyeyang = gyeyang;
	}
	public Integer getGanghwa() {
		return ganghwa;
	}
	public void setGanghwa(Integer ganghwa) {
		this.ganghwa = ganghwa;
	}
	public Integer getYunsu() {
		return yunsu;
	}
	public void setYunsu(Integer yunsu) {
		this.yunsu = yunsu;
	}
	public Integer getSamsan() {
		return samsan;
	}
	public void setSamsan(Integer samsan) {
		this.samsan = samsan;
	}
	public Integer getNonhyeon() {
		return nonhyeon;
	}
	public void setNonhyeon(Integer nonhyeon) {
		this.nonhyeon = nonhyeon;
	}
	
	@Override
	public String toString() {
		return "CrimeVO [jungbu=" + jungbu + ", nambu=" + nambu + ", namdong=" + namdong + ", bupheung=" + bupheung + ", seobu=" + seobu + 
				", gyeyang=" + gyeyang + ", ganghwa=" + ganghwa + ", yunsu=" + yunsu + ", samsan=" + samsan + ", nonhyeon=" + nonhyeon +"]";
	}
}
