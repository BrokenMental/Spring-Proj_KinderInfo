package us.inhatc.service;

import java.util.List;

import org.json.simple.JSONObject;

import us.inhatc.domain.SidoVO;

public interface KinderService {
	
	public JSONObject selectgrid()throws Exception;
	
	public JSONObject selectchart()throws Exception;
	
	List<SidoVO> selectSidoName(SidoVO sidoVO) throws Exception;
	List<SidoVO> selectSigunguName(SidoVO sidoVO) throws Exception;

	public JSONObject selectchart_cin()throws Exception;
}
