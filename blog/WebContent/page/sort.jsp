<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메뉴 | MyBlog</title>


<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">


<link type="text/css" rel="stylesheet" href="/Blog/css/public.css" />
<link type="text/css" rel="stylesheet" href="/Blog/css/sort.css" />
</head>
<body>
	<div class="head_line"></div>

	<div class="container" id="main">

		<div id="header"></div>

		<div class="row c_center">
			<div class="col-md-3" id="left_content">

				<div id="title">
					<h2>
						<a href="/blog/index.jsp">MyBlog</a>
					</h2>
					<h5 class="text-muted">CSS가 제일 어려워요</h5>
				</div>

				<div class="c_center" id="person_info">
					<img src="/blog/img/header.jpg" height="130" width="130"
						alt="what?" class="img-circle">
					<h5 class="text-muted">복자자니?</h5>
				</div>


				<div id="list">
					<table class="table table-hover c_center">
						<tr>
							<td><a href="/blog/index.jsp	"><span class="glyphicon glyphicon-home"></span>
								&nbsp;&nbsp;home</a></td>
						</tr>
						<tr>	
							<td class="active"><a href="/blog/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
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
				<br />
			</div>
			<div class="col-md-2" id="center_content"></div>
			<div class="col-md-7 " id="right_Content">
				<br /> <br />
				<div class="list-group">
					<a href="#" class="list-group-item active">분류</a>
					<!-- 这里初始化列表 -->					
					<c:forEach var="map" items="${sort_article_map}">					
					<div class="sort_name">
					<!-- 分类名字 -->
					<span class="glyphicon glyphicon-triangle-bottom"></span>	&nbsp;	&nbsp;${map.key}					
					</div>
					<div>
					<!-- 分类信息 -->
					<ul class="list-group">
					
					<c:forEach var="list" items="${map.value}">
					<li class="list-group-item">
								<div>
									<div>
										<a href="/blog/ArticleServlet?id=${list.id}">${list.title}</a>
									</div>
									<div class="c_right">
									<img src="/blog/img/time.png">
									${list.time}&nbsp;&nbsp; 
									<span class="visit"><img src="/blog/img/visit.png">
									${list.visit}	</span>
									</div>
								</div>
					</li>
					</c:forEach>					
					</ul>
					</div>								
					</c:forEach>					
					<!-- 初始化列表完成 -->

				</div>
			</div>
		</div>

		<div class="foot_line"></div>
	</div>
	<!-- container -->


	<div id="footer">
		<a href="#">&nbsp;&nbsp;MyBlog</a>
	</div>
	<!-- footer -->

</body>
</html>