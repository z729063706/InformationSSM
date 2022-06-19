<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>"  >
    <meta charset="utf-8">
    <link rel="shortcut icon" href="resources/img/logo.png">
    <title>信息系统后台 </title>       
           
    <link rel="stylesheet" href="resources/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="resources/css/back.css">
    <link rel="stylesheet" href="resources/plugin/font-awesome/css/font-awesome.min.css">
    
    <!-- 留给别的页面去覆盖.让它们可以引入自已的css或 js -->
    <rapid:block name="frame-header-style"></rapid:block>
    <rapid:block name="frame-header-script"></rapid:block>

</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><a href="/admin" style="color:#009688;">
          信息系统后台
        </a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="login" target="_blank">前台</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">新建</a>
                <dl class="layui-nav-child">
                    <dd><a href="Article/add">文章</a></dd>
                    <dd><a href="/admin/page/insert">页面</a></dd>
                    <dd><a href="/admin/category/insert">分类</a></dd>
                    <dd><a href="/admin/notice/insert">公告</a></dd>
                    <dd><a href="/admin/link/insert">链接</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
		   <li class="layui-nav-item">
		       <a href="javascript:;">
		           <img src="User/photo/${loginuser.userId}" class="layui-nav-img">
		           ${loginuser.userName}</a>
		       <dl class="layui-nav-child">
		           <dd><a href="/admin/user/profile">基本资料</a></dd>
		       </dl>
		   </li>
		   <li class="layui-nav-item">
		      <a href="logout" onclick="return confirm('是否退出')">退出</a>
		   </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">文章</a>
                    <dl class="layui-nav-child">
                        <dd><a href="Article">全部文章</a></dd>
                        <dd><a href="Article/add">写文章</a></dd>
                        <dd><a href="Category">全部分类</a></dd>
                        <dd><a href="Tag">全部标签</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">页面</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/page">全部页面</a></dd>
                        <dd><a href="/admin/page/insert">添加页面</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        链接
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/link">全部链接</a></dd>
                        <dd><a href="/admin/link/insert">添加链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">公告</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/notice">全部公告</a></dd>
                        <dd><a href="/admin/notice/insert">添加公告</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="Comment">
                        评论
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        用户
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="User/userlist">全部用户</a></dd>
                        <dd><a href="User/add">添加用户</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/menu">菜单</a></dd>
                        <dd><a href="/admin/options">主要选项</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
			<div style="padding:15px">
				<rapid:block name="frame-content">
					这是主体区内容,每个页面这里都各不相同
				</rapid:block>
			</div>
    </div>
</div>

<script src="resources/js/jquery.min.js"></script>
<script src="resources/plugin/layui/layui.all.js"></script>
<script src="resources/js/back.js"></script>

<!-- 留给别的页面覆盖,让它们可以引入自已的js -->
<rapid:block name="frame-footer-script"></rapid:block>

</body>
</html>