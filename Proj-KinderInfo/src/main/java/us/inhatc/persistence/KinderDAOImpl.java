package us.inhatc.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import us.inhatc.domain.ChartVO;
import us.inhatc.domain.CrimeVO;
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
	
	public List<CrimeVO> selectcrimejungbu() throws Exception {
		return session.selectList(namespace + ".selectcrimejungbu");
	}
	public List<CrimeVO> selectcrimenambu() throws Exception {
		return session.selectList(namespace + ".selectcrimenambu");
	}
	public List<CrimeVO> selectcrimenamdong() throws Exception {
		return session.selectList(namespace + ".selectcrimenamdong");
	}
	public List<CrimeVO> selectcrimebupheung() throws Exception {
		return session.selectList(namespace + ".selectcrimebupheung");
	}
	public List<CrimeVO> selectcrimeseobu() throws Exception {
		return session.selectList(namespace + ".selectcrimeseobu");
	}
	public List<CrimeVO> selectcrimegyeyang() throws Exception {
		return session.selectList(namespace + ".selectcrimegyeyang");
	}
	public List<CrimeVO> selectcrimeganghwa() throws Exception {
		return session.selectList(namespace + ".selectcrimeganghwa");
	}
	public List<CrimeVO> selectcrimeyunsu() throws Exception {
		return session.selectList(namespace + ".selectcrimeyunsu");
	}
	public List<CrimeVO> selectcrimesamsan() throws Exception {
		return session.selectList(namespace + ".selectcrimesamsan");
	}
	public List<CrimeVO> selectcrimenonhyeon() throws Exception {
		return session.selectList(namespace + ".selectcrimenonhyeon");
	}
}
