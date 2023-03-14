package com.mj.jwork.reservation.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.jwork.common.model.vo.PageInfo;
import com.mj.jwork.reservation.model.dao.CarResDao;
import com.mj.jwork.reservation.model.vo.CarReservation;


@Service
public class CarResServiceImpl implements CarResService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Autowired
	private CarResDao cDao;


	@Override
	public int insertReservation(CarReservation cRes) {
		
		return cDao.insertReservation(sqlSession,cRes);
	}


	@Override
	public int selectMyResCount(int resevation) {
		return cDao.selectMyResCount(sqlSession,resevation);
	}


	@Override
	public ArrayList<CarReservation> selectMyRes(PageInfo pi,int reservation) {
		return cDao.selectMyRes(sqlSession, pi,reservation);
	}
	
	
}