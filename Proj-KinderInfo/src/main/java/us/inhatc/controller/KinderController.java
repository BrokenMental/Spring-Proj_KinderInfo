package us.inhatc.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import us.inhatc.domain.SidoVO;
import us.inhatc.service.KinderService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class KinderController {

	@Inject
	private KinderService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		try {
			model.addAttribute("jdata",service.selectgrid());
			
			JSONObject servicecomb = service.selectchart();
			model.addAttribute("cdata",servicecomb.toString().substring(13,servicecomb.toString().length()-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/main";
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map(Model model) {
		try {
			model.addAttribute("jdata",service.selectgrid());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/func/map";
	}
	
	@RequestMapping(value = "/grid", method = RequestMethod.GET)
	public String grid(Model model) {
		
		
		try {
			model.addAttribute("jdata",service.selectgrid());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/func/grid";
	}

	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public String chart(Model model) {
		
		
		try {
			//JSONObject servicecomb = service.selectchart();
			JSONObject servicecomb = service.selectchart_cin();
			model.addAttribute("cdata",servicecomb.toString().substring(9,servicecomb.toString().length()-1));
			
			/*
			Map<String,Object> servicecomb = service.selectchart_cin();
			model.addAttribute("cdata",servicecomb);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/func/chart";
	}
	
	@RequestMapping(value = "/area")
	public ModelAndView area(@ModelAttribute SidoVO SidoVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		ArrayList<SidoVO> selectSidoName = (ArrayList<SidoVO>)service.selectSidoName(SidoVO);
    	model.put("selectSidoName", selectSidoName);
    	
//    	ArrayList<SidoVO> selectSigunguName = (ArrayList<SidoVO>)service.selectSigunguName(SidoVO);
//    	model.put("selectSigunguName", selectSigunguName);
    	
//    	System.out.println("여기야 여기! : "+selectSidoName.toString());
		
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("func/area");
    	
		return mav;
	}
	
	@RequestMapping(value = "/changeSel")
	public void selectBook(@ModelAttribute SidoVO SidoVO, ModelMap model, HttpServletResponse res, HttpServletRequest request, String param) throws Exception{
		
		String sidoName = param;
		SidoVO.setSidoName(sidoName);
		System.out.println(sidoName);
		
		ArrayList<SidoVO> selectSigunguName = (ArrayList<SidoVO>)service.selectSigunguName(SidoVO);
        System.out.println(selectSigunguName);
        
        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<selectSigunguName.size(); i++) {
        	jsonArray.add(selectSigunguName.get(i).getSigunguName());
        }
        
        PrintWriter pw = res.getWriter();
        pw.print(jsonArray.toString());
        pw.flush();
        pw.close();
	}
	
//	@RequestMapping(value = "/changeSel")
//	public ModelAndView selectBook(@ModelAttribute SidoVO SidoVO, ModelMap model, HttpServletRequest request, String param) throws Exception{
//		
//		String sidoName = param;
//		SidoVO.setSidoName(sidoName);
//		
//    	ModelAndView mav = new ModelAndView();
//    	mav.setViewName("book/home");
//    	
//        System.out.println(sidoName);
//        
//        return mav;
//	}
}
