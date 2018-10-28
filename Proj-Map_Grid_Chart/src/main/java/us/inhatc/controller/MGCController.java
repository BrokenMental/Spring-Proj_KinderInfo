package us.inhatc.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONObject;
import us.inhatc.service.MGCService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MGCController {
	
	//private static final Logger logger = LoggerFactory.getLogger(GridController.class);

	@Inject
	private MGCService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String map(Model model) {
		
		
		try {
			model.addAttribute("jdata",service.selectgrid());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/map";
	}
	
	@RequestMapping(value = "/grid", method = RequestMethod.GET)
	public String grid(Model model) {
		
		
		try {
			model.addAttribute("jdata",service.selectgrid());
			
			//JSONObject servicecomb = service.selectgrid();
			//model.addAttribute("gdata",servicecomb.toString().substring(12,servicecomb.toString().length()-10));
			//model.addAttribute("gdatasize",servicecomb.toString().substring(servicecomb.toString().length()-2,servicecomb.toString().length()-1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/func/grid";
	}

	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public String chart(Model model) {
		
		
		try {
			JSONObject servicecomb = service.selectchart();
			//System.out.println(servicecomb.toString().substring(13,servicecomb.toString().length()-1));
			model.addAttribute("cdata",servicecomb.toString().substring(13,servicecomb.toString().length()-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/func/chart";
	}
}
