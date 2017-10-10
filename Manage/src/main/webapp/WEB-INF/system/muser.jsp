<%--
  Created by IntelliJ IDEA.
  User: Xushd
  Date: 2017/10/10
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../head.jsp"%>
<style>
    .layui-form-label{
        width: auto;
    }
</style>
<body class="childrenBody">
<blockquote class="layui-elem-quote">

    <div class="layui-inline">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="用户名称">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户手机</label>
                <div class="layui-input-inline">
                    <input type="tel" name="phone" lay-verify="phone" autocomplete="off" class="layui-input" placeholder="用户手机">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn search_btn" lay-submit="return false" lay-filter="search">查询</a>
            </div>
        </div>
    </form>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-normal newsAdd_btn">添加文章</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn recommend" style="background-color:#5FB878">推荐文章</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn audit_btn">审核文章</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-danger batchDel">批量删除</a>
    </div>
</blockquote>

<div class="layui-form">
    <table class="layui-table" >
        <thead>
        <tr>
            <th>ID</th>
            <th>用户名</th>
            <th>手机号</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <script id="tpl" type="text/html">
            {{#  layui.each(d, function(index, item){ }}
            <tr>
                <td>{{ item.id }}</td>
                <td>{{ item.name }}</td>
                <td>{{ item.phone }}</td>
                <td align="left">{{# if (item.status==0) { }}
                    不可用
                    {{# } }}
                    {{# if (item.status==1) { }}
                    可用
                    {{# } }}
                </td>
                <td></td>
            </tr>
            {{#  }); }}
        </script>
        </tbody>
    </table>
</div>
<div id="page"></div>

<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({base:"/resources/js/"}).use(['app','form','laytpl'],function(){
        var $ = layui.$, app = layui.app,form = layui.form;

        form.on("submit(search)",function(data){
            console.log(data)
        });
        var tpl = laytpl($("#tpl").html());
        app.post('/admin/user/list',{}).then(d=>{
            tpl.render(d.data.list, function(html){
                $("#tbody").html(html);
            });
        },e=>{})

    })
</script>
</body>
</html>
