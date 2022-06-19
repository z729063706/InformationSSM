<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<rapid:override name="frame-header-style">
	<link rel="stylesheet" type="text/css" href="resources/css/index-page.css" ></link>
</rapid:override>

<rapid:override name="frame-content">
	<blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
             <a href="index">首页</a>
             <a><cite>用户列表</cite></a>
       </span>
	</blockquote>
	
	<table class="layui-table" lay-even lay-skin="nob" >
	    <colgroup>
	        <col width="100">
	        <col width=100">
	        <col width="100">
	        <col width="50">
	        <col width="50">
	        <col width="100">
	        <col width="50">
	    </colgroup>
	    <thead>
	    <tr>
	        <th>用户名</th>
	        <th>昵称</th>
	        <th>电子邮件</th>
	        <th>文章</th>
	        <th>状态</th>
	        <th>操作</th>
	        <th>ID</th>
	    </tr>
	    </thead>
	    <tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
		            <td>
		                 <img src="User/photo/${user.userId}" width="48" height="48">
		                 <strong><a href="/admin/user/edit/2">${user.userName}</a></strong>
		            </td>
		            <td>
		                ${user.userNickname}</td>
		            <td >
		                ${user.userEmail}</td>
		            <td>
		                ${user.articleCount}</td>
		            <td>
		                
		                        <a href="/admin/user?status=1">
								    <c:if test="${user.userStatus==1 }">
								    	 <span style="color: #5FB878;">正常</span>
								    </c:if>
								    
								     <c:if test="${user.userStatus==0 }">
								    	 <span style="color: red">异常</span>
								    </c:if>
									 
								</a>
		                    </td>
		            <td>
		                <a href="User/update/${user.userId}" class="layui-btn layui-btn-mini">编辑</a>
		                <a href="User/delete/${user.userId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
		            </td>
		            <td>
		                ${user.userId}</td>
		        </tr>
			</c:forEach>
	
	    </tbody>
	</table>
</rapid:override>

<%@ include file="../framework.jsp"  %>