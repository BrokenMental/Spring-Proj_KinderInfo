package us.inhatc.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
import us.inhatc.domain.SidoVO;
import us.inhatc.service.KinderService;

@Controller
public class AjaxController {

	@Inject
	private KinderService service;
	
	@RequestMapping(value = "/changeSel")
	public void changeSel(SidoVO SidoVO, HttpServletResponse res, String param) throws Exception{
		
		String sidoName = param;
		SidoVO.setSidoName(sidoName);
		
		ArrayList<SidoVO> selectSigunguName = (ArrayList<SidoVO>)service.selectSigunguName(SidoVO);
        
        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<selectSigunguName.size(); i++) {
        	jsonArray.add(selectSigunguName.get(i));
        }
        
        PrintWriter pw = res.getWriter();
        pw.print(jsonArray.toString());
        pw.flush();
        pw.close();
	}
	
	@RequestMapping(value = "/search")
	public ResponseEntity<JSONObject> search(SidoVO SidoVO, int sidoCode, int sigunguCode) throws Exception{
		
		ResponseEntity<JSONObject> entity = null;
		
		SidoVO.setSidoCode(sidoCode);
		SidoVO.setSigunguCode(sigunguCode);
		
		entity = new ResponseEntity<JSONObject>(service.selectKinderList(SidoVO), HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value = "/ndata")
	public ResponseEntity<String> ndata(String sdName, String sigunguName, HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8");
		ResponseEntity<String> entity = null;
		String test = service.selectNews(sdName, sigunguName);
		
		entity = new ResponseEntity<String>(test, HttpStatus.OK);
		
		return entity;
	}
}
