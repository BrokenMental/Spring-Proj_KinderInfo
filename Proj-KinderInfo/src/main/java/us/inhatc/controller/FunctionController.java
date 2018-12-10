package us.inhatc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ModelAndView area(@ModelAttribute SidoVO SidoVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		ArrayList<SidoVO> selectSidoName = (ArrayList<SidoVO>)service.selectSidoName(SidoVO);
    	model.put("selectSidoName", selectSidoName);
		
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("func/area");
    	
		return mav;
	}

	@RequestMapping(value = "/news")
	public String news(ModelMap model, HttpServletRequest request) throws Exception {
		
        String clientId = "0qXYAmRCeHPKrvdChXeu";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "FlNUhSqBup";//애플리케이션 클라이언트 시크릿값";

        try {
            String text = URLEncoder.encode("네이벙", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        
		return "/func/news";
	}
}
