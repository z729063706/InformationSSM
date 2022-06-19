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
              <a><cite>评论列表</cite></a>
        </span>
    </blockquote>
    <div class="layui-tab layui-tab-card">
        <table class="layui-table" lay-even lay-skin="nob">
            <colgroup>
                <col width="100">
                <col width="300">
                <col width=200">
                <col width="150">
                <col width="50">
            </colgroup>
            <thead>
            <tr>
                <th>作者</th>
                <th>评论内容</th>
                <th>回复至</th>
                <th>提交于</th>
                <th>ID</th>
            </tr>
            </thead>
            <tbody>
            	<c:forEach var="comment" items="${pageInfo.list}">
	            	<tr>
	                    <td>
	                        <img src="http://cn.gravatar.com/avatar/01459f970ce17cd9e1e783160ecc951c?s=128&d=identicon&r=PG" alt="" width="64px">
	                        <strong>${comment.commentAuthorName}</strong>
	                        <br>
	                            ${comment.commentAuthorUrl}<br>
	                          	${comment.commentAuthorEmail}<br>
	                            ${comment.commentIp}</td>
	                    <td class="dashboard-comment-wrap">
	                        <span class="at">@ </span><a href="https://liuyanzhao.com">${comment.commentPname}</a>
	                        ${comment.commentContent}<div class="row-actions">
	                                     <span class="">
	                                        <a href="Comment/reply/${comment.commentId}">
	                                            回复
	                                        </a>
	                                     </span>
	                            <span class=""> |
	                                        <a href="Comment/delete/${comment.commentId}" onclick="deleteComment(31)">删除</a>
	                                     </span>
	                        </div>
	                    </td>
	                    <td>
	                        <a href="/article/6"
	                           target="_blank">${comment.article.articleTitle}</a>
	                    </td>
	                    <td>
	                        <fmt:formatDate value="${comment.commentCreateTime}" pattern="yyyy年MM月dd日 HH:mm:ss" /></td>
	                    <td>${comment.commentId}</td>
	                </tr>
            	</c:forEach>
            </tbody>
        </table>

        <div id="nav" style="">
			<nav class="navigation pagination" role="navigation">
				<div class="nav-links">
					<%@ include file="/view/page.jsp" %>
				</div>
			</nav>
    	</div>
    </div>
</rapid:override>

<%@ include file="../framework.jsp"  %>