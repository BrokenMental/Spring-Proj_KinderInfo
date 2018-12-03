package us.inhatc.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import us.inhatc.domain.ChartVO;
import us.inhatc.domain.Chart_cinVO;
import us.inhatc.domain.GridVO;
import us.inhatc.domain.SidoVO;

@Repository
public class KinderDAOImpl{

	@Inject
	private SqlSession session;

	private static String namespace = "us.inhatc.mappers.KinderMapper";

	public List<GridVO> selectgrid() throws Exception {
		return session.selectList(namespace + ".selectgrid");
	}
	public List<ChartVO> selectchartsolo() throws Exception {
		return session.selectList(namespace + ".selectchartsolo");
	}
	public List<ChartVO> selectcharttoget() throws Exception {
		return session.selectList(namespace + ".selectcharttoget");
	}
	public List<ChartVO> selectchartairtel() throws Exception {
		return session.selectList(namespace + ".selectchartairtel");
	}
	public List<ChartVO> selectchartetc() throws Exception {
		return session.selectList(namespace + ".selectchartetc");
	}

	public List<Chart_cinVO> selectcinm() throws Exception {
		return session.selectList(namespace + ".selectcinm");
	}
	
	public List<SidoVO> selectSidoName(SidoVO sidoVO) throws Exception {
		return session.selectList(namespace + ".selectSidoName");
	}
	public List<SidoVO> selectSigunguName(SidoVO sidoVO) throws Exception {
		return session.selectList(namespace + ".selectSigunguName", sidoVO.getSidoName());
	}
}
