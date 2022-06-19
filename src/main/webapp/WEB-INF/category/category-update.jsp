<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<rapid:override name="frame-header-style">
	<link rel="stylesheet" type="text/css" href="resources/css/index-page.css" ></link>
</rapid:override>

<rapid:override name="frame-content">
        <!-- 内容主体区域 -->
     <div style="padding:15px;">
         <blockquote class="layui-elem-quote">
	        <span class="layui-breadcrumb" lay-separator="/">
	              <a href="index">首页</a>
	              <a href="Category">分类列表</a>
	              <a><cite>修改分类</cite></a>
	        </span>
	    </blockquote>
   
    
    <div class="layui-row">
        <div class="layui-col-md4">
            <form class="layui-form"  method="post" id="myForm" action="Category/update/${category.categoryId}">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>修改分类</strong>
                    </div>
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="categoryName" placeholder="请输入分类名称" autocomplete="off" class="layui-input" value="${category.categoryName}" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        父节点 <span style="color: #FF5722; ">*</span>
                        <select name="categoryPid" class="layui-input" required>
                        	<c:forEach var="parent" items="${categoryList}">
                        	     <option value="${parent.categoryId}">${parent.categoryName}</option>
                        	</c:forEach>
                        </select>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        分类描述
                        <input type="text" name="categoryDescription" placeholder="请输入分类描述" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        图标样式
                        <input type="text" name="categoryIcon" placeholder="请输入图标样式,如 fa fa-coffee" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">修改</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-col-md8" >
            <table class="layui-table" >
                <colgroup>
                    <col width="300">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="50">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>操作</th>
                    <th>ID</th>
                    <th>pid</th>
                </tr>
                </thead>
                <tbody>
                	<c:forEach var="category" items="${categoryList}">
                		<tr>
                		    <td>
                                <a href="/category/1" target="_blank">-----${category.categoryName}</a>
                            </td>
                            <td>
                                <a href="/category/1" target="_blank">${category.articleCount}</a>
                            </td>
                            <td>
                                <a href="/admin/category/edit/1" class="layui-btn layui-btn-mini">编辑</a>
                                </td>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryPid}</td>
                        </tr>
                        <c:forEach var="children" items="${category.clidrenCategory}">
	                         <tr>
	                		    <td>
	                                <a href="/category/1" target="_blank">${children.categoryName}</a>
	                            </td>
	                            <td>
	                                <a href="/category/1" target="_blank">${children.articleCount}</a>
	                            </td>
	                            <td>
	                                <a href="Category/update/${children.categoryId}" class="layui-btn layui-btn-mini">编辑</a>
	                                <c:if test="${children.articleCount==0 }">
						    	 		<span><a href="Category/delete/${children.categoryId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a></span>
						    		</c:if>
	                            </td>
	                            <td>${children.categoryId}</td>
	                            <td>${children.categoryPid}</td>
	                        </tr>
                        </c:forEach>
                	</c:forEach>
                	
                 </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>分类最多只有两级，一级分类pid=0，二级分类pid=其父节点id</li>
                    <li>如果该分类包含文章，将不可删除</li>
                </ul>
            </blockquote>
        </div>
    </div>
</rapid:override>

<%@ include file="../framework.jsp"  %>