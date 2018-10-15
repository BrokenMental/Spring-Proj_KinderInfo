package us.inhatc.service;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import us.inhatc.domain.ChartVO;
import us.inhatc.domain.GridVO;
import us.inhatc.persistence.MGCDAOImpl;

@Service
public class MGCServiceImpl implements MGCService {

	@Inject
	private MGCDAOImpl dao;
	
	@Override
	public JSONObject selectgrid() throws Exception {
		
		JSONArray garray = new JSONArray();
		JSONObject gobj = new JSONObject();
		
		ArrayList<?> gvo = (ArrayList<?>)dao.selectgrid();
		GridVO gdata = null;
		
		
		for(int i=0; i<gvo.size(); i++){
			
			gdata = (GridVO)gvo.get(i);
			JSONObject gtemp = new JSONObject();
			
			gtemp.put("field1",gdata.getField1());
			gtemp.put("field2",gdata.getField2());
			gtemp.put("field3",gdata.getField3());
			gtemp.put("field4",gdata.getField4());
			gtemp.put("field5",gdata.getField5());
			
			garray.add(gtemp);
		}
		gobj.put("gridData",garray);
		gobj.put("size",gvo.size());
		
		return gobj; 
	}

	@Override
	public JSONObject selectchart() throws Exception {
		ArrayList<ChartVO> cvos = (ArrayList<ChartVO>)dao.selectchartsolo();
		ArrayList<ChartVO> cvot = (ArrayList<ChartVO>)dao.selectcharttoget();
		ArrayList<ChartVO> cvoa = (ArrayList<ChartVO>)dao.selectchartairtel();
		ArrayList<ChartVO> cvoe = (ArrayList<ChartVO>)dao.selectchartetc();
		
		JSONArray carray = new JSONArray();
		JSONObject cobj = new JSONObject();
		
		carray.add(forchart(cvos, "s"));
		carray.add(forchart(cvot, "t"));
		carray.add(forchart(cvoa, "a"));
		carray.add(forchart(cvoe, "e"));

		cobj.put("chartData",carray);
		
		return cobj;
	}
	
	public JSONObject forchart(ArrayList<ChartVO> tc, String cstr){
		JSONObject ctemp = new JSONObject();
		JSONArray cja = new JSONArray();
		
		for(int i = 0; i < tc.size(); i++){
			if(i == 0){
				if(cstr == "s"){
					ctemp.put("name","solo");
				}else if(cstr == "t"){
					ctemp.put("name","toget");
				}else if(cstr == "a"){
					ctemp.put("name","airtel");
				}else if(cstr == "e"){
					ctemp.put("name","etc");
				}
			}
			
			if(cstr == "s"){
				cja.add(tc.get(i).getSolo());
			}else if(cstr == "t"){
				cja.add(tc.get(i).getToget());
			}else if(cstr == "a"){
				cja.add(tc.get(i).getAirtel());
			}else if(cstr == "e"){
				cja.add(tc.get(i).getEtc());
			}
		}
		ctemp.put("data",cja);
		return ctemp;
	}
}
