package us.inhatc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import us.inhatc.domain.ChartVO;
import us.inhatc.domain.SidoVO;
import us.inhatc.domain.Chart_cinVO;
import us.inhatc.persistence.KinderDAOImpl;

@Service
public class KinderServiceImpl implements KinderService {

	@Inject
	private KinderDAOImpl dao;

	@Override
	public JSONObject selectgrid() throws Exception {

		// JSON형식의 페이지 URL
		String jsdata = readJsonFromUrl(
				"http://e-childschoolinfo.moe.go.kr/api/notice/basicInfo.do?key=cba3828f0113465fa66bc6123d70903f&sidoCode=28&sggCode=28177");
		jsdata = jsdata + "," + readJsonFromUrl(
				"http://e-childschoolinfo.moe.go.kr/api/notice/basicInfo.do?key=cba3828f0113465fa66bc6123d70903f&sidoCode=28&sggCode=28185");
		jsdata = "{\"kinderInfo\":[" + jsdata + ","
				+ readJsonFromUrl(
						"http://e-childschoolinfo.moe.go.kr/api/notice/basicInfo.do?key=cba3828f0113465fa66bc6123d70903f&sidoCode=28&sggCode=28110")
				+ "]}";

		JSONParser ps = new JSONParser();
		JSONObject jobj = (JSONObject) ps.parse(jsdata);

		// jobj.get("kinderInfo") //JSONObject 내의 속성 뽑아내는 방법

		return jobj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject selectchart() throws Exception {
		ArrayList<ChartVO> cvos = (ArrayList<ChartVO>) dao.selectchartsolo();
		ArrayList<ChartVO> cvot = (ArrayList<ChartVO>) dao.selectcharttoget();
		ArrayList<ChartVO> cvoa = (ArrayList<ChartVO>) dao.selectchartairtel();
		ArrayList<ChartVO> cvoe = (ArrayList<ChartVO>) dao.selectchartetc();

		JSONArray carray = new JSONArray();
		JSONObject cobj = new JSONObject();

		carray.add(forchart(cvos, "s"));
		carray.add(forchart(cvot, "t"));
		carray.add(forchart(cvoa, "a"));
		carray.add(forchart(cvoe, "e"));

		cobj.put("chartData", carray);

		return cobj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject selectchart_cin() throws Exception {
		String doname[] = { "seoul", "busan", "daegu", "gwangju", "daejeon", "ulsan", "sejong", "gyeonggi", "gangwon",
				"chungbuk", "chungnam", "jeonbuk", "jeonnam", "gyeongbuk", "gyeongnam", "jeju" };

		JSONObject resultJ = new JSONObject();
		List<Chart_cinVO> cl = dao.selectcinm();
		
		for(int i=0; i<cl.size(); i++){
			// 마지막 index를 찾아서 for문 돌리고
			// for문에서 json array[]에 add,
			// 순서에 맞는 doname String을 찾아서 put
			// 그런데, csv파일의 컬럼이 도명 | 2016 | 2017 ~ 데이터라면??? 오...
			System.out.println(cl.get(i).toJsonN().get(i));
		}
		
		//System.out.println("JSON DATA => "+resultJ);
		
		/*
		JSONObject resultJ = new JSONObject();
		JSONArray resultA = new JSONArray();
		List<Chart_cinVO> cin = dao.selectchartcin();
		
		for(int i=0; i<doname.length; i++){
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			int j=1;
			for(Chart_cinVO voL : cin){
				if(i==0){
					ja.add(voL.getSeoul());
				}else if(i==1){
					ja.add(voL.getBusan());
				}else if(i==2){
					ja.add(voL.getDaegu());
				}else if(i==3){
					ja.add(voL.getGwangju());
				}else if(i==4){
					ja.add(voL.getDaejeon());
				}else if(i==5){
					ja.add(voL.getUlsan());
				}else if(i==6){
					ja.add(voL.getSejong());
				}else if(i==7){
					ja.add(voL.getGyeonggi());
				}else if(i==8){
					ja.add(voL.getGangwon());
				}else if(i==9){
					ja.add(voL.getChungbuk());
				}else if(i==10){
					ja.add(voL.getChungnam());
				}else if(i==11){
					ja.add(voL.getJeonbuk());
				}else if(i==12){
					ja.add(voL.getJeonnam());
				}else if(i==13){
					ja.add(voL.getGyeongbuk());
				}else if(i==14){
					ja.add(voL.getGyeongnam());
				}else if(i==15){
					ja.add(voL.getJeju());
				}
				if(j == cin.size()){
					jo.put("name", doname[i]);
					jo.put("data", ja);
					resultA.add(jo);
				}
				j++;
			}
		}
		resultJ.put("chart", resultA);
*/
		return null;
	}

	/*
	 * @SuppressWarnings("unchecked") // 컴파일러의 지양 경고를 무시하는 어노테이션 public
	 * JSONObject castJson(List<Chart_cinVO> cstr) throws IOException {
	 * 
	 * JSONObject jo = new JSONObject(); JSONArray ja =
	 * JSONArray.fromObject(cstr); jo.put("chart", ja); // json의 경우 "chart" : 로
	 * 시작
	 * 
	 * //Map <String, Object> resultM = new HashMap<String, Object>();
	 * //resultM.put("chart", ja); // Map의 경우 chart = 로 시작
	 * 
	 * return jo; }
	 */

	// 참조 :
	// https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
	// http JSON 페이지 가져오기
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	// 읽어온 JSON형식의 페이지 변환하기
	public String readJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			jsonText = jsonText.substring(jsonText.indexOf('[') + 1, jsonText.length() - 2);
			return jsonText;
		} finally {
			is.close();
		}
	}

	// JSON 형식의 Chart 변환하기
	@SuppressWarnings("unchecked")
	public JSONObject forchart(ArrayList<ChartVO> tc, String cstr) {
		JSONObject ctemp = new JSONObject();
		JSONArray cja = new JSONArray();

		for (int i = 0; i < tc.size(); i++) {
			if (i == 0) {
				if (cstr == "s") {
					ctemp.put("name", "solo");
				} else if (cstr == "t") {
					ctemp.put("name", "toget");
				} else if (cstr == "a") {
					ctemp.put("name", "airtel");
				} else if (cstr == "e") {
					ctemp.put("name", "etc");
				}
			}

			if (cstr == "s") {
				cja.add(tc.get(i).getSolo());
			} else if (cstr == "t") {
				cja.add(tc.get(i).getToget());
			} else if (cstr == "a") {
				cja.add(tc.get(i).getAirtel());
			} else if (cstr == "e") {
				cja.add(tc.get(i).getEtc());
			}
		}
		ctemp.put("data", cja);
		return ctemp;
	}
	
	@Override
	public List<SidoVO> selectSidoName(SidoVO sidoVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectSidoName(sidoVO);
	}

	@Override
	public List<SidoVO> selectSigunguName(SidoVO sidoVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectSigunguName(sidoVO);
	}
}
