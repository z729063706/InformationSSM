<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<rapid:override name="frame-header-style">
	<link rel="stylesheet" type="text/css" href="resources/css/index-page.css" ></link>
</rapid:override>

<rapid:override name="frame-content">
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="index">首页</a>
              <a><cite>标签列表</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4">
            <form class="layui-form"  method="post" id="myForm" action="Tag/update/${tag.tagId}">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>编辑标签</strong>
                    </div>
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="tagName" placeholder="请输入标签名称" autocomplete="off" class="layui-input" value=${tag.tagName} required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        标签描述
                        <input type="text" name="tagDescription" placeholder="请输入标签描述" autocomplete="off" class="layui-input" value=${tagDescription}>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">修改</button>
                    </div>
                </div>
            </form>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>1、标签名必选，建议不要太长</li>
                    <li>2、标签名勿重复</li>
                </ul>
            </blockquote>
        </div>
        <div class="layui-col-md8" >

            <table class="layui-table" >
                <colgroup>
                    <col width="300">
                    <col width="50">
                    <col width="100">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>操作</th>
                    <th>ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tag" items="${tagList}">
                	<tr>
                        <td>
                            <a href="/tag/1" target="_blank">${tag.tagName}</a>
                        </td>
                        <td >
                            <a href="/tag/1" target="_blank"  lay-data="{sort:true}">${tag.articleCount}</a>
                        </td>
                        <td>
                            <a href="Tag/update/${tag.tagId}" class="layui-btn layui-btn-mini">编辑</a>
                           	<c:if test="${tag.articleCount==0 }">
						    	 <span><a href="Tag/delete/${tag.tagId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a></span>
						    </c:if>
                         </td>
                        <td >${tag.tagId}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>如果该标签包含文章，将不可删除</li>
                </ul>
            </blockquote>
        </div>
    </div>
</rapid:override>

<%@ include file="../framework.jsp"  %>