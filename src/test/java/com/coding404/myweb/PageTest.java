package com.coding404.myweb;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsdudwkd.myweb.command.TripVO;
import com.dsdudwkd.myweb.trip.service.TripMapper;
import com.dsdudwkd.myweb.util.Criteria;

@RunWith(SpringJUnit4ClassRunner.class) // junit으로 테스트 환경을 구성  
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/root-context.xml") // 동작시킬 스프링 설정 파일
public class PageTest {
	
	@Autowired
	TripMapper tripMapper;
	
//	@Test
//	public void testCode01() {
//		
//		for(int i=0; i<300; i++) {
//			TripVO vo = new TripVO(0, "2023-02-08", "admin"+i, "test"+i, "example"+i, 0, null);
//			tripMapper.noticeRegist(vo);
//		}
//		
//	}

	@Test
	public void testCode02() {
		ArrayList<TripVO> list = tripMapper.getList(new Criteria(2, 10)); // 기본값 1, 10
		
		list.stream().forEach(x -> System.out.println(x));
	}
	
	
}
