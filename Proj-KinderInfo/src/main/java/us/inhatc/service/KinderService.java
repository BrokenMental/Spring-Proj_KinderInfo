package us.inhatc.service;

import org.json.simple.JSONObject;

public interface KinderService {
	
	public JSONObject selectgrid()throws Exception;
	
	public JSONObject selectchart()throws Exception;

	public JSONObject selectchart_cin()throws Exception;
}
