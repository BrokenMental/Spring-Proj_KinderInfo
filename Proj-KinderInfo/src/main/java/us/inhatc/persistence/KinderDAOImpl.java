package us.inhatc.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import us.inhatc.domain.ChartVO;
import us.inhatc.domain.GridVO;

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
	
}
