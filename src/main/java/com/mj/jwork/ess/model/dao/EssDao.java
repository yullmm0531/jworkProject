package com.mj.jwork.ess.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mj.jwork.common.model.vo.PageInfo;
import com.mj.jwork.employee.model.vo.Employee;
import com.mj.jwork.ess.model.vo.Annual;
import com.mj.jwork.ess.model.vo.Businesstrip;
import com.mj.jwork.ess.model.vo.Leave;
import com.mj.jwork.ess.model.vo.LeaveCategory;
import com.mj.jwork.ess.model.vo.Overtime;

@Repository
public class EssDao {

	public LeaveCategory selectLeaveCategory(SqlSessionTemplate sqlSession, int no) {
		return sqlSession.selectOne("essMapper.selectLeaveCategory", no);
	}
	
	public int insertLeave(SqlSession sqlSession, Leave le) {
		return sqlSession.insert("essMapper.insertLeave", le);
	}
	
	public int selectLeaveListCount(SqlSession sqlSession, Employee e) {
		return sqlSession.selectOne("essMapper.selectLeaveListCount", e);
	}
	
	public ArrayList<Leave> selectLeaveList(SqlSession sqlSession, Employee e, PageInfo pi){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("essMapper.selectLeaveList", e, rowBounds);
	}
	
	public Leave selectLeaveDetail(SqlSession sqlSession, Leave le) {
		return sqlSession.selectOne("essMapper.selectLeaveDetail", le);
	}
	
	public int deleteLeave(SqlSession sqlSession, int leaveNo) {
		return sqlSession.update("essMapper.deleteLeave",leaveNo);
	}
	
	public int adminSelectLeaveListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("essMapper.adminSelectLeaveListCount");
	}
	
	public ArrayList<Leave> adminSelectLeaveList(SqlSession sqlSession, PageInfo pi){

		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("essMapper.adminSelectLeaveList", null, rowBounds);
	}
	
	public Leave adminLeaveDetail(SqlSession sqlSession, int leaveNo) {
		return sqlSession.selectOne("essMapper.adminLeaveDetail", leaveNo);
	}
	
	public int insertOvertime(SqlSession sqlSession, Overtime o) {
		return sqlSession.insert("essMapper.insertOvertime", o);
	}
	
	public int insertBusinesstrip(SqlSession sqlSession, Businesstrip b) {
		return sqlSession.insert("essMapper.insertBusinesstrip", b);
	}
	
	public int selectOvertimeListCount(SqlSession sqlSession, int empNo) {
		return sqlSession.selectOne("essMapper.selectOvertimeListCount", empNo);
	}
	
	public int selectBusinesstripListCount(SqlSession sqlSession, int empNo) {
		return sqlSession.selectOne("essMapper.selectBusinesstripListCount", empNo);
	}
	
	public ArrayList<Overtime> selectOvertimeList(SqlSession sqlSession, int empNo, PageInfo oPi){
		
		int offset = (oPi.getCurrentPage() - 1) * oPi.getBoardLimit();
		int limit = oPi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("essMapper.selectOvertimeList", empNo, rowBounds);
	}
	
	public ArrayList<Businesstrip> selectBusinesstripList(SqlSession sqlSession, int empNo, PageInfo bPi){
		
		int offset = (bPi.getCurrentPage() - 1) * bPi.getBoardLimit();
		int limit = bPi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("essMapper.selectBusinesstripList", empNo, rowBounds);
	}
	
	public int deleteOvertime(SqlSession sqlSession, int otNo) {
		return sqlSession.update("essMapper.deleteOvertime", otNo);
	}
	
	public int deleteBusinesstrip(SqlSession sqlSession, int btNo) {
		return sqlSession.update("essMapper.deleteBusinesstrip", btNo);
	}
	
	public int adminSelectOvertimeListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("essMapper.adminSelectOvertimeListCount");
	}
	
	public int adminSelectBusinesstripListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("essMapper.adminSelectBusinesstripListCount");
	}
	
	public ArrayList<Overtime> adminSelectOvertimeList(SqlSession sqlSession, PageInfo pi){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("essMapper.adminSelectOvertimeList", null, rowBounds);
	}
	
	public ArrayList<Businesstrip> adminSelectBusinesstripList(SqlSession sqlSession, PageInfo pi){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("essMapper.adminSelectBusinessetripList", null, rowBounds);
	}
	
	public Businesstrip selectBusinesstrip(SqlSession sqlSession, int btNo) {
		return sqlSession.selectOne("essMapper.selectBusinesstrip", btNo);
	}
	
	public Overtime selectOvertime(SqlSession sqlSession, int otNo) {
		return sqlSession.selectOne("essMapper.selectOvertime", otNo);
	}
	
	public Businesstrip adminSelectBusinesstrip(SqlSession sqlSession, int btNo) {
		return sqlSession.selectOne("essMapper.adminSelectBusinesstrip", btNo);
	}
	
	public Overtime adminSelectOvertime(SqlSession sqlSession, int otNo) {
		return sqlSession.selectOne("essMapper.adminSelectOvertime", otNo);
	}
	
	public int adminUpdateFirstBusinesstrip(SqlSession sqlSession, Businesstrip b) {
		return sqlSession.update("essMapper.adminUpdateFirstBusinesstrip", b);
	}
	
	public int adminUpdateSecondBusinesstrip(SqlSession sqlSession, Businesstrip b) {
		return sqlSession.update("essMapper.adminUpdateSecondBusinesstrip", b);
	}
	
	public int adminUpdateFirstOvertime(SqlSession sqlSession, Overtime o) {
		return sqlSession.update("essMapper.adminUpdateFirstBusinesstrip", o);
	}
	
	public int adminUpdateSecondOvertime(SqlSession sqlSession, Overtime o) {
		return sqlSession.update("essMapper.adminUpdateSecondBusinesstrip", o);
	}
	
	public int adminReturnBusinesstrip(SqlSession sqlSession, int btNo) {
		return sqlSession.update("essMapper.adminReturnBusinesstrip", btNo);
	}
	
	public int adminReturnOvertime(SqlSession sqlSession, int otNo) {
		return sqlSession.update("essMapper.adminReturnOvertime", otNo);
	}
	
	public int adminReturnLeave(SqlSession sqlSession, int leaveNo) {
		return sqlSession.update("essMapper.adminReturnLeave", leaveNo);
	}
	
	public int adminAnnualListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("essMapper.adminAnnualListCount");
	}
	
	public ArrayList<Annual> adminAnnualList(SqlSession sqlSession, PageInfo pi){
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return (ArrayList)sqlSession.selectList("essMapper.adminAnnualList", null, rowBounds);
	}
	
	public Annual adminAnnualDetail(SqlSession sqlSession, int empNo) {
		return sqlSession.selectOne("essMapper.adminAnnualDeatil", empNo);
	}
	
	public int adminUpdateYear(SqlSession sqlSession) {
		return sqlSession.update("essMapper.adminUpdateYear");
	}
	
	public int adminInsertAnnual(SqlSession sqlSession, int empNo) {
		return sqlSession.insert("essMapper.adminInsertAnnual", empNo);
	}
	
	public int adminUpdateMonth(SqlSession sqlSession) {
		return sqlSession.update("essMapper.adminUpdateMonth");
	}
	
	public Annual selectAnnualCount(SqlSession sqlSession, int empNo) {
		return sqlSession.selectOne("essMapper.selectAnnualCount", empNo);
	}
	
	
	
	
	
	
}
