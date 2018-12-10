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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONArray;
import us.inhatc.domain.SidoVO;
import us.inhatc.service.KinderService;

@Controller
public class KinderController {

	@Inject
	private KinderService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model, SidoVO SidoVO) {
		try {
			ArrayList<SidoVO> selectSidoName = (ArrayList<SidoVO>)service.selectSidoName(SidoVO);
	    	model.addAttribute("selectSidoName", selectSidoName);
	    	
			model.addAttribute("jdata",service.selectKinderList(SidoVO));
			
			JSONObject servicecomb = service.selectchart_cin();
			model.addAttribute("cdata",servicecomb.toString().substring(10,servicecomb.toString().length()-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/main";
	}

	@RequestMapping(value = "/changeSel")
	public void changeSel(@ModelAttribute SidoVO SidoVO, ModelMap model, HttpServletResponse res, HttpServletRequest request, String param) throws Exception{
		
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
	public ResponseEntity<JSONObject> search(@ModelAttribute SidoVO SidoVO, ModelMap model, HttpServletResponse res, HttpServletRequest request, int sidoCode, int sigunguCode) throws Exception{
		
		ResponseEntity<JSONObject> entity = null;
		
		int sdCode = sidoCode;
		int sggCode = sigunguCode;
		
		SidoVO.setSidoCode(sdCode);
		SidoVO.setSigunguCode(sggCode);
		
		entity = new ResponseEntity<JSONObject>(service.selectKinderList(SidoVO), HttpStatus.OK);
		
		return entity;
	}
}
