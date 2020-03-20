package us.inhatc.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import us.inhatc.domain.SidoVO;
import us.inhatc.service.KinderService;

@Controller
public class KinderController {

	@Inject
	private KinderService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model, SidoVO SidoVO) {
		try {
			//처음부터 VO를 호출해도 되는 이유는 VO에서 가져오는 api Code가 0일 경우 status : SUCCESS이면서 아무 값도 안가져오기 때문.
			ArrayList<SidoVO> selectSidoName = (ArrayList<SidoVO>)service.selectSidoName(SidoVO);
	    	model.addAttribute("selectSidoName", selectSidoName);
	    	
			model.addAttribute("jdata",service.selectKinderList(SidoVO));
			
			//차트??? 뭐한거지
			JSONObject servicecomb = service.selectchart_cin();
			model.addAttribute("cdata",servicecomb.toString().substring(10,servicecomb.toString().length()-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/main";
	}
}
