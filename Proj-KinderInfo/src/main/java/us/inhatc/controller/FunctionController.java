package us.inhatc.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import us.inhatc.domain.SidoVO;
import us.inhatc.service.KinderService;

@Controller
public class FunctionController {

	@Inject
	private KinderService service;
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map(Model model, SidoVO SidoVO) {
		try {
			model.addAttribute("jdata",service.selectKinderList(SidoVO));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/func/map";
	}
	
	@RequestMapping(value = "/grid", method = RequestMethod.GET)
	public String grid(Model model, SidoVO SidoVO) {
		try {
			ArrayList<SidoVO> selectSidoName = (ArrayList<SidoVO>)service.selectSidoName(SidoVO);
	    	model.addAttribute("selectSidoName", selectSidoName);
			model.addAttribute("jdata",service.selectKinderList(SidoVO));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/func/grid";
	}

	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public String chart(Model model) {
		try {
			JSONObject servicecomb = service.selectchart_cin();
			model.addAttribute("cdata",servicecomb.toString().substring(10,servicecomb.toString().length()-1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/func/chart";
	}
	
	@RequestMapping(value = "/area")
	public ModelAndView area(SidoVO SidoVO, ModelMap model) throws Exception {
		
		ArrayList<SidoVO> selectSidoName = (ArrayList<SidoVO>)service.selectSidoName(SidoVO);
    	model.put("selectSidoName", selectSidoName);
		
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("func/area");
    	
		return mav;
	}

	@RequestMapping(value = "/news")
	public String news(Model model, SidoVO SidoVO) throws Exception {

		ArrayList<SidoVO> selectSidoName = (ArrayList<SidoVO>)service.selectSidoName(SidoVO);
    	model.addAttribute("selectSidoName", selectSidoName);
		return "/func/news";
	}
}
