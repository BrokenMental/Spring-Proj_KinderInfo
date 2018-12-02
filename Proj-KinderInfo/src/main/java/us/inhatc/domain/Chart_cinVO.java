package us.inhatc.domain;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.sf.json.JSONArray;



public class Chart_cinVO {
	private String year;
	private int seoul;
	private int busan;
	private int daegu;
	private int gwangju;
	private int daejeon;
	private int ulsan;
	private int sejong;
	private int gyeonggi;
	private int gangwon;
	private int chungbuk;
	private int chungnam;
	private int jeonbuk;
	private int jeonnam;
	private int gyeongbuk;
	private int gyeongnam;
	private int jeju;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getSeoul() {
		return seoul;
	}
	public void setSeoul(int seoul) {
		this.seoul = seoul;
	}
	public int getBusan() {
		return busan;
	}
	public void setBusan(int busan) {
		this.busan = busan;
	}
	public int getDaegu() {
		return daegu;
	}
	public void setDaegu(int daegu) {
		this.daegu = daegu;
	}
	public int getGwangju() {
		return gwangju;
	}
	public void setGwangju(int gwangju) {
		this.gwangju = gwangju;
	}
	public int getDaejeon() {
		return daejeon;
	}
	public void setDaejeon(int daejeon) {
		this.daejeon = daejeon;
	}
	public int getUlsan() {
		return ulsan;
	}
	public void setUlsan(int ulsan) {
		this.ulsan = ulsan;
	}
	public int getSejong() {
		return sejong;
	}
	public void setSejong(int sejong) {
		this.sejong = sejong;
	}
	public int getGyeonggi() {
		return gyeonggi;
	}
	public void setGyeonggi(int gyeonggi) {
		this.gyeonggi = gyeonggi;
	}
	public int getGangwon() {
		return gangwon;
	}
	public void setGangwon(int gangwon) {
		this.gangwon = gangwon;
	}
	public int getChungbuk() {
		return chungbuk;
	}
	public void setChungbuk(int chungbuk) {
		this.chungbuk = chungbuk;
	}
	public int getChungnam() {
		return chungnam;
	}
	public void setChungnam(int chungnam) {
		this.chungnam = chungnam;
	}
	public int getJeonbuk() {
		return jeonbuk;
	}
	public void setJeonbuk(int jeonbuk) {
		this.jeonbuk = jeonbuk;
	}
	public int getJeonnam() {
		return jeonnam;
	}
	public void setJeonnam(int jeonnam) {
		this.jeonnam = jeonnam;
	}
	public int getGyeongbuk() {
		return gyeongbuk;
	}
	public void setGyeongbuk(int gyeongbuk) {
		this.gyeongbuk = gyeongbuk;
	}
	public int getGyeongnam() {
		return gyeongnam;
	}
	public void setGyeongnam(int gyeongnam) {
		this.gyeongnam = gyeongnam;
	}
	public int getJeju() {
		return jeju;
	}
	public void setJeju(int jeju) {
		this.jeju = jeju;
	}
	
	@Override
	public String toString() {
		return "Chart_cinVO [year=" + year + ", seoul=" + seoul + ", busan=" + busan + ", daegu=" + daegu + ", gwangju="
				+ gwangju + ", daejeon=" + daejeon + ", ulsan=" + ulsan + ", sejong=" + sejong + ", gyeonggi="
				+ gyeonggi + ", gangwon=" + gangwon + ", chungbuk=" + chungbuk + ", chungnam=" + chungnam + ", jeonbuk="
				+ jeonbuk + ", jeonnam=" + jeonnam + ", gyeongbuk=" + gyeongbuk + ", gyeongnam=" + gyeongnam + ", jeju="
				+ jeju + "]";
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJson() {
		JSONObject jObj = new JSONObject();
		
		jObj.put("year",year);
		jObj.put("seoul",seoul);
		jObj.put("busan",busan);
		jObj.put("daegu",daegu);
		jObj.put("daegu",daegu);
		jObj.put("gwangju",gwangju);
		jObj.put("daejeon",daejeon);
		jObj.put("ulsan",ulsan);
		jObj.put("sejong",sejong);
		jObj.put("gyeonggi",gyeonggi);
		jObj.put("gangwon",gangwon);
		jObj.put("chungbuk",chungbuk);
		jObj.put("chungnam",chungnam);
		jObj.put("jeonbuk",jeonbuk);
		jObj.put("jeonnam",jeonnam);
		jObj.put("gyeongbuk",gyeongbuk);
		jObj.put("gyeongnam",gyeongnam);
		jObj.put("jeju",jeju);
		
		return jObj;
	}
	

	@SuppressWarnings("unchecked")
	public JSONObject toJsonN() {
		JSONObject jObj = new JSONObject();
		
		jObj.put(0,year);
		jObj.put(1,seoul);
		jObj.put(2,busan);
		jObj.put(3,daegu);
		jObj.put(4,daegu);
		jObj.put(5,gwangju);
		jObj.put(6,daejeon);
		jObj.put(7,ulsan);
		jObj.put(8,sejong);
		jObj.put(9,gyeonggi);
		jObj.put(10,gangwon);
		jObj.put(11,chungbuk);
		jObj.put(12,chungnam);
		jObj.put(13,jeonbuk);
		jObj.put(14,jeonnam);
		jObj.put(15,gyeongbuk);
		jObj.put(16,gyeongnam);
		jObj.put(17,jeju);
		
		return jObj;
	}
}
