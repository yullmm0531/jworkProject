package com.mj.jwork.reservation.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mj.jwork.common.model.vo.PageInfo;
import com.mj.jwork.reservation.model.vo.CarReservation;

@Repository
public class CarResDao {
	
	public int insertReservation(SqlSessionTemplate sqlSession,CarReservation cRes) {
		
		
		return sqlSession.insert("carMapper.insertReservation",cRes);
	}
	
	public int selectMyResCount(SqlSessionTemplate sqlSession,int reservation) {
		
		return sqlSession.selectOne("carMapper.selectMyResCount",reservation);
	}
	
	public ArrayList<CarReservation> selectMyRes(SqlSessionTemplate sqlSession, PageInfo pi,int reservation){
		
		int offset= (pi.getCurrentPage()-1)*pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return (ArrayList)sqlSession.selectList("carMapper.selectMyRes", reservation, rowBounds);
	}
	public int selectResCount(SqlSessionTemplate sqlSession,String option) {
		
		return sqlSession.selectOne("carMapper.selectResCount",option);
	}
	
	
	  public ArrayList<CarReservation> ajaxselectResList(SqlSessionTemplate
	  sqlSession,PageInfo pi,String option){
	  
	  int offset = (pi.getCurrentPage() -1)* pi.getBoardLimit(); int limit =
	  pi.getBoardLimit(); RowBounds rowBounds = new RowBounds(offset, limit);
	  return (ArrayList)sqlSession.selectList("carMapper.ajaxselectResList", option,
	  rowBounds);
	  
	  }
	 
	
	public ArrayList<CarReservation> selectResList(SqlSessionTemplate sqlSession){
		
		return (ArrayList)sqlSession.selectList("carMapper.selectResList");
		
	}
	
	public CarReservation selectCarRes(SqlSessionTemplate sqlSession, int resNo) {
		
		return sqlSession.selectOne("carMapper.selectCarRes",resNo);
		
	}
	
	public int updateApStatus(SqlSessionTemplate sqlSession,CarReservation c) {
		
		return sqlSession.update("carMapper.updateApStatus",c);
	}
	
	public ArrayList<CarReservation> selectResList(SqlSessionTemplate sqlSession,CarReservation c){
		
		return (ArrayList)sqlSession.selectList("carMapper.selectResList2",c);
		
	}

	public CarReservation ajaxselectCarRes(SqlSessionTemplate sqlSession, int resNo) {
	
	return sqlSession.selectOne("carMapper.ajaxselectCarRes",resNo);
	
	}
	  public ArrayList<CarReservation> selectResStatus(SqlSessionTemplate sqlSession,PageInfo pi,String option){
			  
	  int offset = (pi.getCurrentPage() -1)* pi.getBoardLimit(); int limit =
	  pi.getBoardLimit(); RowBounds rowBounds = new RowBounds(offset, limit);
	  return (ArrayList)sqlSession.selectList("carMapper.selectResStatus", option,rowBounds);
	  
	  }
	
	public int selectResStatusCount(SqlSessionTemplate sqlSession,String option) {
		
		return sqlSession.selectOne("carMapper.selectResStatusCount",option);
	}
		
}
