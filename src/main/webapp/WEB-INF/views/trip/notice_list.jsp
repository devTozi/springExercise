<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	
	<div id="container">
		<!-- location_area -->
		<div class="location_area customer">
			<div class="box_inner">
				<h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
				<p class="location">고객센터 <span class="path">/</span> 공지사항</p>
				<ul class="page_menu clear">
					<li><a href="#" class="on">공지사항</a></li>
					<li><a href="#">문의하기</a></li>
				</ul>
			</div>
		</div>	
		<!-- //location_area -->

		<!-- bodytext_area -->
		<div class="bodytext_area box_inner">
		
			<form action="notice_list" class="minisrch_form" name="actionForm">
				
				<fieldset>
					
					<select id="handleAmount" style="height: 35px;">
						<option value="10" ${pageVO.amount == 10 ? 'selected' : '' }>10개씩 보기</option>
						<option value="15" ${pageVO.amount == 15 ? 'selected' : '' }>15개씩 보기</option>
						<option value="20" ${pageVO.amount == 20 ? 'selected' : '' }>20개씩 보기</option>
						<option value="30" ${pageVO.amount == 30 ? 'selected' : '' }>30개씩 보기</option>
					</select>
				
					<select name="searchType" style="height: 35px;">
						<option value="title" ${pageVO.cri.searchType == 'title' ? 'selected' : '' }>제목</option>
						<option value="content" ${pageVO.cri.searchType == 'content' ? 'selected' : '' }>내용</option>
						<option value="writer" ${pageVO.cri.searchType == 'writer' ? 'selected' : '' }>작성자</option>
						<option value="titcont" ${pageVO.cri.searchType == 'titcont' ? 'selected' : '' }>제목+내용</option>
					</select>
					<legend>검색</legend>
					<input type="text" class="tbox" name="searchName" value="${pageVO.cri.searchName }" title="검색어를 입력해주세요" placeholder="검색어를 입력해주세요">
					
					<input type="hidden" name="page" value="1">
					<input type="hidden" name="amount" value="${pageVO.amount }">
					
					<input type="submit" class="btn_srch" value="검색" style="border: none;">
				</fieldset>
			</form>
			
			<table class="bbsListTbl" summary="번호,제목,조회수,작성일 등을 제공하는 표">
				<caption class="hdd">공지사항  목록</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">조회수</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list }" varStatus="num">
						<tr>
							<td>${item.tno }</td>
							<td class="tit_notice"><a href="notice_view?tno=${item.tno }">${item.title }</a> </td>
							<td>${item.hit }</td>
							<td><fmt:formatDate value="${item.regdate }" pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
			<!-- pagination -->
			<%--
			<div class="pagination">
			
				<!-- 처음 페이지 이동 -->
				<a href="notice_list?page=1&amount=${pageVO.amount}" class="firstpage  pbtn"><img src="${pageContext.request.contextPath}/resources/img/btn_firstpage.png" alt="첫 페이지로 이동"></a>
				
				<!-- 이전 페이지 이동 -->
				<c:if test="${pageVO.prev }">
					<a href="notice_list?page=${pageVO.start-1 }&amount=${pageVO.amount}" class="prevpage  pbtn"><img src="${pageContext.request.contextPath}/resources/img/btn_prevpage.png" alt="이전 페이지로 이동"></a>
				</c:if>
				<!-- 페이지 번호 출력 -->
				<c:forEach var="num" begin="${pageVO.start }" end="${pageVO.end }">				
					<a href="notice_list?page=${num }&amount=${pageVO.amount}"><span class="pagenum ${pageVO.page == num ? 'currentpage' : '' }">${num }</span></a>
				</c:forEach>
				
				<!-- 다음 페이지 이동 -->
				<c:if test="${pageVO.next }">
					<a href="notice_list?page=${pageVO.end+1 }&amount=${pageVO.amount}" class="nextpage  pbtn"><img src="${pageContext.request.contextPath}/resources/img/btn_nextpage.png" alt="다음 페이지로 이동"></a>
				</c:if>
				
				<!-- 마지막 페이지 이동 -->
				<a href="notice_list?page=${pageVO.realEnd }&amount=${pageVO.amount}" class="lastpage  pbtn"><img src="${pageContext.request.contextPath}/resources/img/btn_lastpage.png" alt="마지막 페이지로 이동"></a>
			</div>
			 --%>
			 
			 <%-- 
			 	컨트롤러에서 사용자 클래스를 매개변수로 지정을 하면 이름이 클래스명 소문자로 지정되서 자동으로 다음 화면으로 전달된다.
			 	${criteria}
			  --%>
			 
			 <div class="pagination">
			 	<!-- 검색 기능 추가 -->
			 	<!-- 처음 페이지 이동 -->
				<a href="notice_list?page=1&amount=${pageVO.amount}&searchType=${pageVO.cri.searchType}&searchName=${pageVO.cri.searchName}" class="firstpage  pbtn"><img src="${pageContext.request.contextPath}/resources/img/btn_firstpage.png" alt="첫 페이지로 이동"></a>
				
				<!-- 이전 페이지 이동 -->
				<c:if test="${pageVO.prev }">
					<a href="notice_list?page=${pageVO.start-1 }&amount=${pageVO.amount}&searchType=${pageVO.cri.searchType}&searchName=${pageVO.cri.searchName}" class="prevpage  pbtn"><img src="${pageContext.request.contextPath}/resources/img/btn_prevpage.png" alt="이전 페이지로 이동"></a>
				</c:if>
				<!-- 페이지 번호 출력 -->
				<c:forEach var="num" begin="${pageVO.start }" end="${pageVO.end }">				
					<a href="notice_list?page=${num }&amount=${pageVO.amount}&searchType=${pageVO.cri.searchType}&searchName=${pageVO.cri.searchName}"><span class="pagenum ${pageVO.page == num ? 'currentpage' : '' }">${num }</span></a>
				</c:forEach>
				
				<!-- 다음 페이지 이동 -->
				<c:if test="${pageVO.next }">
					<a href="notice_list?page=${pageVO.end+1 }&amount=${pageVO.amount}&searchType=${pageVO.cri.searchType}&searchName=${pageVO.cri.searchName}" class="nextpage  pbtn"><img src="${pageContext.request.contextPath}/resources/img/btn_nextpage.png" alt="다음 페이지로 이동"></a>
				</c:if>
				
				<!-- 마지막 페이지 이동 -->
				<a href="notice_list?page=${pageVO.realEnd }&amount=${pageVO.amount}&searchType=${pageVO.cri.searchType}&searchName=${pageVO.cri.searchName}" class="lastpage  pbtn"><img src="${pageContext.request.contextPath}/resources/img/btn_lastpage.png" alt="마지막 페이지로 이동"></a>
			</div>
			<!-- //pagination -->
			
		</div>
		<!-- //bodytext_area -->

	</div>
	<!-- //container -->

	<script>
		var msg = "${msg}";
		if(msg !== ''){
			alert(msg);
		}
	</script>
	
	<script>
		var handleAmount = document.getElementById("handleAmount");
		
		handleAmount.onchange = function(){
			// console.log(event.target.value)
			document.actionForm.amount.value = event.target.value;
			document.actionForm.submit();
		}
	
	</script>






