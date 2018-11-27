package us.inhatc.controller;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
