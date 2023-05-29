package com.dsdudwkd.myweb.trip.service;

import java.util.ArrayList;

import com.dsdudwkd.myweb.command.TripVO;
import com.dsdudwkd.myweb.util.Criteria;

public interface TripService {

	public int noticeRegist(TripVO vo);
	public ArrayList<TripVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public TripVO getContent(int tno);
	
	public int noticeModify(TripVO vo);
	public int noticeDelete(int tno);
	public void upHit(int tno);
	
	public ArrayList<TripVO> getPrevNext(int tno);
	
	
}
