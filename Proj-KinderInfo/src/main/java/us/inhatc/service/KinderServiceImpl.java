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

	// JSON 데이터를 받아와서 전달하는 메서드
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject selectchart_cin() throws Exception {
		final String doname[] = { "seoul", "busan", "daegu", "gwangju", "daejeon", "ulsan", "sejong", "gyeonggi", "gangwon",
				"chungbuk", "chungnam", "jeonbuk", "jeonnam", "gyeongbuk", "gyeongnam", "jeju" };
		/*
		* JSONArray의 행렬 선언은 사용불가
		* JSONArray[] tempA = new JSONArray[doname.length];
		*/
		
		JSONObject resultJ = new JSONObject();
		JSONObject tempO = new JSONObject();
		Map<String,Integer> tempM = new HashMap<String,Integer>();
		List<Chart_cinVO> cl = dao.selectcinm();
		
		// doname.length는 index 0부터 총 16개, cl.size()는 index 1부터 총 5개
		for(int i=0; i<cl.size(); i++){
			JSONArray tempA = new JSONArray();
			
			for(int j=1; j<=doname.length; j++){
				tempM.put((i)+"data"+(j-1), Integer.parseInt((cl.get(i).toJsonN().get(j)).toString()));
				if(i==cl.size()-1){
					JSONArray tempJ = new JSONArray();
					for(int k=0; k<cl.size(); k++){
						tempJ.add(tempM.get((k)+"data"+(j-1)));
					}
					tempO.put("data", tempJ);
					tempO.put("name", doname[j-1]);
					tempA.add(tempO);
				}
			}
			resultJ.put("chartD", tempA);
		}
		return resultJ;
	}

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
