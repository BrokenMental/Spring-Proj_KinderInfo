package us.inhatc.service;

import java.util.List;

import org.json.simple.JSONObject;

import us.inhatc.domain.SidoVO;

public interface KinderService {

	public JSONObject selectKinderList(SidoVO sidoVO)throws Exception;
	public JSONObject selectchart_cin()throws Exception;
	public String selectNews(String sdName, String sigunguName)throws Exception;
	
	public List<SidoVO> selectSidoName(SidoVO sidoVO) throws Exception;
	public List<SidoVO> selectSigunguName(SidoVO sidoVO) throws Exception;

}
