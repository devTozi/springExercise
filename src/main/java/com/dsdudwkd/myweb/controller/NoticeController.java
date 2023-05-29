package com.dsdudwkd.myweb.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dsdudwkd.myweb.command.TripVO;
import com.dsdudwkd.myweb.trip.service.TripService;
import com.dsdudwkd.myweb.util.Criteria;
import com.dsdudwkd.myweb.util.PageVO;

@Controller
@RequestMapping("/trip")
public class NoticeController {
	
	@Autowired
	@Qualifier("tripservice")
	private TripService tripService;
	
	@RequestMapping("/notice_list")
	public String notice_list(Model model, Criteria cri) {
		
		/* 
		 * 1. 화면에서는 page, amount, searchType, searchName을 넘긴다.
		 * 2. criteria에서 검색값을 받는다.
		 * 3. sql을 바꾼다.(동적쿼리)
		 * 4. total sql을 바꾼다.(동적쿼리)
		 * 5. 페이지 a태그 클릭시 searchType, searchName을 쿼리스트링으로 넘긴다.
		 * 6. 검색 키워드 유지
		 * 7. amount값 핸들링
		 */
		
		// 페이지네이션 + 검색 처리
		ArrayList<TripVO> list = tripService.getList(cri);
		int total = tripService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		return "trip/notice_list";
	}
	
	@RequestMapping("/notice_view")
	public String notice_view(@RequestParam("tno") int tno, 
							  Model model, 
							  HttpServletResponse response,
							  HttpServletRequest request) {
		
		TripVO vo = tripService.getContent(tno);
		model.addAttribute("vo", vo);
		
		
		// 조회수 쿠키 처리
		boolean flag = true;
		if(request.getCookies() != null) {
			
			Cookie[] car = request.getCookies();
			for(Cookie c : car) {
				if(c.getName().equals("cookie"+tno)) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				// 조회수 증가
				tripService.upHit(tno);
				// 서버에서 쿠키 생성해서 전송
				Cookie cookie = new Cookie("cookie"+tno, String.valueOf(tno));
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
			}
			
		}
	
		// 이전글 다음글
		ArrayList<TripVO> list = tripService.getPrevNext(tno);
		model.addAttribute("list" ,list);
		
		return "trip/notice_view";
	}
	
	@RequestMapping("/notice_modify")
	public String notice_modify(@RequestParam("tno") int tno, Model model) {
		
		TripVO vo = tripService.getContent(tno);
		model.addAttribute("vo", vo);
		
		return "trip/notice_modify";
	}
	
	@RequestMapping(value="/modifyForm", method=RequestMethod.POST)
	public String modifyForm(TripVO vo, RedirectAttributes ra) {
		
		// 업데이트 - tno가 필요하기 때문에 화면에서 hidden태그를 이용해서 넘겨준다. 
		int result = tripService.noticeModify(vo);
		String msg = result == 1? "문의 사항이 수정되었습니다." : "수정에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/trip/notice_list";
		// return "redirect:/trip/notice_view?tno="+vo.getTno();
	}
	
	@RequestMapping("/notice_write")
	public String notice_write() {
		
		return "trip/notice_write";
	}
	
	
	//글 등록
	@RequestMapping(value="/registForm", method=RequestMethod.POST)
	public String registForm(TripVO vo, RedirectAttributes ra) {
		
		int result = tripService.noticeRegist(vo);
		String msg = result == 1? "문의 사항이 정상 등록되었습니다." : "문의 등록에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/trip/notice_list";
	}
	
	// 글 삭제
	@RequestMapping(value="/deleteForm", method=RequestMethod.POST)
	public String deleteForm(@RequestParam("tno") int tno, RedirectAttributes ra) {
		
		int result = tripService.noticeDelete(tno);
		String msg = result == 1 ? "삭제되었습니다." : "삭제에 실패했습니다."; 
		ra.addFlashAttribute("msg" ,msg);
		
		return "redirect:/trip/notice_list";
	}
	
	
	// 수정, 상세 화면이 완전 동일하다면
//	@RequestMapping({"/notice_modify", "/notice_view"})
//	public void notice_view(@RequestParam("tno") int tno, Model model) {
//		
//		TripVO vo = tripService.getContent(tno);
//		model.addAttribute("vo", vo);
//	}
	
	
}
