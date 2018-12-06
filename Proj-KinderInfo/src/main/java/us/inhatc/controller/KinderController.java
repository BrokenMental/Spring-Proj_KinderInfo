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

@Controller
public class KinderController {

	@Inject
	private KinderService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		try {
			model.addAttribute("jdata",service.selectgrid());
			
			JSONObject servicecomb = service.selectchart();
			System.out.println(servicecomb.toString().substring(13,servicecomb.toString().length()-1));
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
			JSONObject servicecomb = service.selectchart_cin();
			model.addAttribute("cdata",servicecomb.toString().substring(10,servicecomb.toString().length()-1));
			
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
		
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("func/area");
    	
		return mav;
	}
	
	@RequestMapping(value = "/changeSel")
	public void changeSel(@ModelAttribute SidoVO SidoVO, ModelMap model, HttpServletResponse res, HttpServletRequest request, String param) throws Exception{
		
		String sidoName = param;
		SidoVO.setSidoName(sidoName);
		System.out.println(sidoName);
		
		ArrayList<SidoVO> selectSigunguName = (ArrayList<SidoVO>)service.selectSigunguName(SidoVO);
        System.out.println(selectSigunguName);
        
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
	public void search(@ModelAttribute SidoVO SidoVO, ModelMap model, HttpServletResponse res, HttpServletRequest request, String sidoName, String sigunguName) throws Exception{
		
		String sdName = sidoName;
		String sggName = sigunguName;
		System.out.println(sdName);
		System.out.println(sggName);
		
//		ArrayList<SidoVO> selectSigunguName = (ArrayList<SidoVO>)service.selectSigunguName(SidoVO);
//        System.out.println(selectSigunguName);
//        
//        JSONArray jsonArray = new JSONArray();
//        for(int i=0; i<selectSigunguName.size(); i++) {
//        	jsonArray.add(selectSigunguName.get(i).getSigunguName());
//        }
//        
//        PrintWriter pw = res.getWriter();
//        pw.print(jsonArray.toString());
//        pw.flush();
//        pw.close();
	}
}
