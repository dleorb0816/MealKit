<%@page import="com.exam.vo.AttachVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.vo.FileVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
	<%-- head 영역 --%>
	<jsp:include page="/WEB-INF/views/include/headContent.jsp"/>

<style>
	span.delete-oldfile, span.delete-addfile {
		color: red;
		background-color: yellow;
		font-weight: bold;
		margin-left: 10px;
		border: 1px solid gray;
		border-radius: 5px;
	}
</style>
</head>
<body>

<%-- preLoader 영역 --%>
<jsp:include page="/WEB-INF/views/include/preLoader.jsp"/>

<!-- ##### Header Area Start ##### -->
<header class="header-area">

	<!-- Top Header Area -->
	<div class="top-header-area bg-img bg-overlay" style="background-image: url(img/bg-img/header.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center justify-content-between">
				<div class="col-12 col-sm-6">
					<!-- Top Social Info -->
					<div class="top-social-info">
						<a href="#" data-toggle="tooltip" data-placement="bottom" title="Pinterest"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
						<a href="#" data-toggle="tooltip" data-placement="bottom" title="Facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a>
						<a href="#" data-toggle="tooltip" data-placement="bottom" title="Twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a>
						<a href="#" data-toggle="tooltip" data-placement="bottom" title="Dribbble"><i class="fa fa-dribbble" aria-hidden="true"></i></a>
						<a href="#" data-toggle="tooltip" data-placement="bottom" title="Behance"><i class="fa fa-behance" aria-hidden="true"></i></a>
						<a href="#" data-toggle="tooltip" data-placement="bottom" title="Linkedin"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
					</div>
				</div>
				<div class="col-12 col-sm-6 col-lg-5 col-xl-4">
					<!-- Top Search Area, 검색바 -->
					<div class="top-search-area">
						<form action="#" method="post">
							<input type="search" name="top-search" id="topSearch" placeholder="Search">
							<button type="submit" class="btn"><i class="fa fa-search"></i></button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Logo Area -->
	<div class="logo-area">
		<a href="index.do"><img src="img/core-img/logo.png" alt=""></a>
	</div>

	<!-- Navbar Area -->
	<div class="bueno-main-menu" id="sticker">
		<div class="classy-nav-container breakpoint-off">
			<div class="container">
				<!-- Menu -->
				<nav class="classy-navbar justify-content-between" id="buenoNav">

					<!-- Toggler, 삼선메뉴 -->
					<div id="toggler"><img src="img/core-img/toggler.png" alt=""></div>

					<!-- Navbar Toggler, 빈공간인듯 에바임 -->
					<div class="classy-navbar-toggler">
						<span class="navbarToggler"><span></span><span></span><span></span></span>
					</div>

					<!-- Menu -->
					<div class="classy-menu">
						<!-- Close Button -->
						<div class="classycloseIcon">
							<div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
						</div>

						<%-- Nav 영역 --%>
						<jsp:include page="/WEB-INF/views/include/classyNav.jsp"/>
					</div>
				</nav>
			</div>
		</div>
	</div>
</header>
<!-- ##### Header Area End ##### -->

<div id="wrap" align="center">

	<div class="clear"></div>
	<div id="sub_img_center"></div>

	<div class="clear"></div>

	<article>

	<h1>공지사항 글수정</h1>

	<form action="adminNoticeModifyPro.do" method="post" enctype="multipart/form-data" name="frm">
	<input type="hidden" name="pageNum" value="${pageNum} }">
	<input type="hidden" name="num" value="${ noticeVo.num }">
	<table id="notice" border="2">
		<tr>
			<th scope="col" class="twrite">작성자</th>
			<td class="left" width="500">
				<input type="text" name="id" value="${ noticeVo.id }" readonly style="border: none;">
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">글제목</th>
			<td class="left">
				<input type="text" name="subject" value="${ noticeVo.subject }" style="width: 509px; border: none;">
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">글내용</th>
			<td class="left">
				<textarea rows="13" cols="70" name="content">${ noticeVo.content }</textarea>
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">첨부파일</th>
			<td class="left">
				<div id="oldFileBox">

				<c:forEach var="attachVo" items="${ noticeAttachList }">
					<input type="hidden" name="oldfile" value="${noticeAttachVo.num}">
					<div>
							${noticeAttachVo.filename}
							<span class="delete-oldfile">X</span>
						</div>
				</c:forEach>


				</div>
				<div id="newFileBox"></div>
				<input type="button" id="btnAddFile" value="첨부파일 추가">
			</td>
		</tr>
	</table>

	<div id="table_search">
		<input type="submit" value="글수정" class="btn">
		<input type="reset" value="다시쓰기" class="btn">
		<input type="button" value="목록보기" class="btn" onclick="location.href = 'adminNoticeBoard.jsp?pageNum=${pageNum}'">
	</div>
	</form>

	<div class="clear"></div>
	<div id="page_control">
	</div>

	</article>

	<div class="clear"></div>
</div>

<script src="/script/jquery-3.5.1.js"></script>
<script>
const maxFileCount = 5;  // 최대 첨부파일 갯수
var fileCount = ${fn:length(attachlist)};  // 현재 첨부된 파일 갯수
var fileIndex = 0;

// [첨부파일 추가] 버튼을 클릭할 때
$('#btnAddFile').click(function () {

	if (fileCount >= maxFileCount) {
		alert('첨부파일은 최대 5개 까지만 가능합니다.')
		return;
	}

	// 백틱 문자열 안에서 변수값을 표현할때는
	// \${}로 표현함
	var str = `
		<div>
			<input type="file" name="filename\${ fileIndex }">
			<span class="delete-addfile">X</span>
		</div>
	`;

	$('div#newFileBox').append(str);

	fileIndex++;
	fileCount++;
});


// 동적 이벤트 바인딩. 이벤트 바인딩 작업을 이미 존재하는 요소에게 위임하기.
// 이미 존재하는 div#newFileBox 요소에게
// 안쪽에 새로운 span.delete-addfile가 들어오면 클릭이벤트 연결하기
$('div#newFileBox').on('click', 'span.delete-addfile', function () {
	$(this).parent().remove();
	fileCount--;
});


// 정적 이벤트 바인딩. 기존 첨부파일에 삭제버튼을 눌렀을때
$('span.delete-oldfile').on('click', function () {
	// 현재 클릭한 요소의 직계부모(parent)의 앞(prev) 요소
	$(this).parent().prev().prop('name', 'delfile');
	// 현재 클릭한 요소의 직계부모(parent)를 삭제. 현재요소안에 자식요소도 모두 삭제됨
	$(this).parent().remove();
	fileCount--;
});

</script>
<%-- footer 영역 --%>
<jsp:include page="/WEB-INF/views/include/bottomFooter.jsp" />
</div>

<!-- ##### All Javascript Script ##### -->
<!-- jQuery-2.2.4 js -->
<script src="js/jquery/jquery-2.2.4.min.js"></script>
<!-- Popper js -->
<script src="js/bootstrap/popper.min.js"></script>
<!-- Bootstrap js -->
<script src="js/bootstrap/bootstrap.min.js"></script>
<!-- All Plugins js -->
<script src="js/plugins/plugins.js"></script>
<!-- Active js -->
<script src="js/active.js"></script>

<!--Start of Tawk.to Script-->
<script type="text/javascript">
	var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
	(function(){
		var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
		s1.async=true;
		s1.src='https://embed.tawk.to/5fd03c53920fc91564ced709/default';
		s1.charset='UTF-8';
		s1.setAttribute('crossorigin','*');
		s0.parentNode.insertBefore(s1,s0);
	})();
</script>
<!--End of Tawk.to Script-->

</body>
</html>

