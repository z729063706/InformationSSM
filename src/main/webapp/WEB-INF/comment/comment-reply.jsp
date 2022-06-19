<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>

<rapid:override name="frame-content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="index">首页</a>
              <a href="Comment">评论列表</a>
              <a><cite>回复评论</cite></a>
        </span>
    </blockquote>

    <form class="layui-form"  method="post" id="myForm" action="Comment/reply/${comment.commentId}">
        <input type="hidden" name="commentPid" value="30">
        <input type="hidden" name="commentPname" value="言曌">
        <input type="hidden" name="commentArticleId" value="6">
        <input type="hidden" name="commentRole" value="1">

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">原内容</label>
            <div class="layui-input-block">
                <textarea  class="layui-textarea" disabled>${comment.commentContent}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">我的昵称 </label>
            <div class="layui-input-block">
                <input type="text" name="commentAuthorName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">我的邮箱 </label>
            <div class="layui-input-block">
                <input type="text" name="commentAuthorEmail" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">我的网址 </label>
            <div class="layui-input-block">
                <input type="text" name="commentAuthorUrl" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">我的回复</label>
            <div class="layui-input-block">
                <textarea name="commentContent"  class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">回复</button>
                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>

    </form>
</rapid:override>

<%@ include file="../framework.jsp" %>