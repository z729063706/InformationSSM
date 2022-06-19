<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<rapid:override name="frame-header-style">
	<link rel="stylesheet" type="text/css" href="resources/css/index-page.css" ></link>
</rapid:override>

<rapid:override name="frame-content">
	<blockquote class="layui-elem-quote">
		<span class="layui-breadcrumb" lay-separator="/"> <a
			href="index">首页</a> <a><cite>文章列表</cite></a>
		</span>
	</blockquote>

	<div class="layui-tab layui-tab-card">
		<form id="articleForm" method="post">
			<input type="hidden" name="currentUrl" id="currentUrl" value="">
			<table class="layui-table">
				<colgroup>
					<col width="300">
					<col width="150">
					<col width="100">
					<col width="150">
					<col width="100">
					<col width="50">
				</colgroup>
				<thead>
					<tr>
						<th>标题</th>
						<th>所属分类</th>
						<th>状态</th>
						<th>发布时间</th>
						<th>操作</th>
						<th>id</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${pageInfo.list}">
						<tr>
							<td><a href="/Article/${a.articleId }" target="_blank"> ${a.articleTitle }</a></td>
							<td>
								<c:forEach var="c" items="${a.categoryList}">
								 	 <a href="/category/${c.categoryId }" target="_blank">${c.categoryName}</a> &nbsp;
								</c:forEach>	
						    </td>
							<td>
								<a href="/admin/article?status=1">
								    <c:if test="${a.articleStatus==1 }">
								    	 <span style="color: #5FB878;">已发布</span>
								    </c:if>
								    
								     <c:if test="${a.articleStatus==0 }">
								    	 <span style="color: red">草稿</span>
								    </c:if>
								</a>
							</td>
							<td>  <fmt:formatDate value="${a.articleCreateTime    }" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
							<td><a href="Article/update/${a.articleId}"
								class="layui-btn layui-btn-mini">编辑</a> <a
								href="Article/delete?articleId=${a.articleId}" onclick="return confirm('是否删除文章 ${a.articleTitle}')"
								class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
							<td>${a.articleId}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<nav class="navigation pagination" role="navigation">
			<div class="nav-links">
				<%@ include file="/view/page.jsp" %>
			</div>
		</nav>
	</div>
			<!-- 重写结束 -->
</rapid:override>

<%@ include file="../framework.jsp"  %>