<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOME | 9</title>
<!-- Bootstrap core CSS -->
<link	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">

<link type="text/css" rel="stylesheet" href="./css/main.css" />
</head>
<body>
	<div class="head_line"></div>

	<div class="container" id="main">
	
		<div id="header"></div>

		<div class="row c_center">
			<div class="col-md-3" id="left_content">

				<div id="title">
					<h2><a href="/blog/login.html">MyBlog</a></h2>
					<h5 class="text-muted">블로그를 시작하지</h5>
				</div>

				<div class="c_center" id="person_info">
					<img src="/blog/img/header.jpg" height="130" width="130"
						alt="로드실패?" class="img-circle">
					<h4 class="text-muted">배고파</h4>
					<h5 class="text-muted">복자 & 말로</h5>
				</div>

				<div class="c_center">
					<!-- 这里初始化侧边栏的4个标签 -->
					<div class="inline ">
						<a href="#">${article_number}<br/>다이어리</a>
					</div>
					<div class="inline ">
						<a href="/blog/SortServlet?get=all"><span> ${sort_number} </span><br/>카테고리</a>
					</div>
					<div class="inline " >
						<a href="/blog/TagsServlet?get=all"><span>${tags_number}</span><br/>태크</a>
					</div>
					
				</div>
			

				<div id="list">
					<table class="table table-hover c_center">
						<tr class="active">
							<td><a href="/blog/index.jsp	"><span class="glyphicon glyphicon-home"></span>
								&nbsp;&nbsp;home</a></td>
						</tr>
						<tr>	
							<td><a href="/blog/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
								&nbsp;&nbsp;catagory</a></td>
						</tr>
						<tr>
							<td><a href="/blog/TagsServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
								&nbsp;&nbsp;tag</a></td>
						</tr>						
						<tr>
							<td><a href="/blog/AxisServlet"><span class="glyphicon glyphicon-book"></span>
								&nbsp;&nbsp;timeline</a></td>
						</tr>
						<tr>
							<td><a href="/blog/page/about.html"><span class="glyphicon glyphicon-user"></span>
								&nbsp;&nbsp;about</a></td>
						</tr>
					</table>
				</div>
				<!-- list -->
					<br/>
					
				<div class="sort">
					<div class="list-group">
						<span class="list-group-item active">분류</span>						
						<!-- 这里初始化分类 -->
						<c:forEach var="entity"  items="${sort_count_map}">
						 <a href="/blog/SortServlet?get=${entity.key}" class="list-group-item">${entity.key}&nbsp;&nbsp;&nbsp;&nbsp; (${entity.value})</a>						
						</c:forEach>									
						<!-- 初始化结束 -->						
					</div>
				</div><!-- sort -->

				
				<div class="visit">
					<div class="list-group">
						<span class="list-group-item active">추천</span>						
						<!-- 这里初始化阅读排行 -->
						<c:forEach var="a"  items="${visit_rank}">
						 <a href="/blog/ArticleServlet?id=${a.id}" class="list-group-item">${a.title}&nbsp;&nbsp; <span class="c_right">(${a.visit})</span></a>						
						</c:forEach>									
						<!-- 初始化结束 -->						
					</div>
				</div><!-- visit-->
	

				<div id="tag">
					<div class="list-group">
						<span class="list-group-item active">태크</span>					
						<br/>
						
						<!-- 这里初始化标签 -->				
						<c:forEach var="t"  varStatus="status" items="${tag_list}" >		
						<c:choose>
						<c:when test="${status.count%2==1}">
							<span class="label label-info"><a href="/blog/TagsServlet?get=${t.tag}">
							&nbsp;${t.tag}&nbsp;</a></span>							
						</c:when>
						<c:otherwise>
								<span class="label label-success"><a href="/blog/TagsServlet?get=${t.tag}">
								&nbsp;${t.tag}&nbsp;</a></span>						
						</c:otherwise>
						</c:choose>
					
						</c:forEach>						
						<!-- 初始化标签完成 -->							
					</div>
				</div><!-- tag -->
				
				
			<!-- admin here -->
			<c:if test="${sessionScope.user!=null}">
			<a href="/blog/AddServlet">
			<span class="glyphicon glyphicon-plus">&nbsp;&nbsp;글쓰기&nbsp;&nbsp;</span>
			</a>
			<a href="/blog/AdminServlet">
			<span class="glyphicon glyphicon glyphicon-user">&nbsp;&nbsp;관리자&nbsp;&nbsp;</span>
			</a>
			</c:if>
			<!--  -->
			
			</div>
			<div class="col-md-2" id="center_content">		
			</div>
					
			
			<div  class="col-md-7 " id="right_Content">
				<br />
				<br />
				<div class="list-group">							
					<a href="#" class="list-group-item active">글</a>
					<!-- 这里初始化文章列表 -->
					<c:forEach var="article"   items="${article_list}" >	
					<div  class="list-group-item">									
					<h4><a href="/blog/ArticleServlet?id=${article.id}">${article.title}</a></h4>
					<br/>
					<span>${article.time}&nbsp;&nbsp;|</span>
					<a href="/blog/SortServlet?get=${article.sort}">${article.sort}</a>&nbsp;&nbsp;|
					<span>조회수: ${article.visit}</span>
					<br/><br/>					
					<span>${article.content}</span>
					<br/><br/><br/>	
					<a href="/blog/ArticleServlet?id=${article.id}">보기</a>
					<br/>			
					</div>
					</c:forEach>
					<!-- 初始化文章列表完成 -->			
				</div>
			</div>	
		</div>				
		<div class="foot_line"></div>
			</div><!-- container -->
	<div id="footer">
		<div>
			<a href="https://github.com/nahoong"><img src="/blog/img/github.png"width="20px" height="20px" class="img-circle">&nbsp;&nbsp;GitHub</a>
			&nbsp;|
			<a href="#">&nbsp;&nbsp;MyBlog</a>
			<br/>
			copyright © 2018
		</div>		
		
		<div class="r_div">
		<a href="#"><input  class="btn btn-default"   value="올라가기"  style="width:50%;"/></a>
		<h6> 방문수 ${visited }</h6>
		<h6> ${member}명 가입</h6>    
		</div>
		
	</div><!-- footer -->
</body>
</html>