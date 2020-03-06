<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
    %>
<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
		<!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
		<meta name="renderer" content="webkit">
		<!--国产浏览器高速模式-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="穷在闹市" />
		<!-- 作者 -->
		<meta name="revised" content="穷在闹市.v3, 2019/05/01" />
		<!-- 定义页面的最新版本 -->
		<meta name="description" content="网站简介" />
		<!-- 网站简介 -->
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>穷在闹市出品</title>

		<!-- 公共样式 开始 -->
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="fonts/iconfont.css">
		<script type="text/javascript" src="framework/jquery-1.11.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
		<script type="text/javascript" src="layui/layui.js"></script>
		<script src="framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
		<!-- 公共样式 结束 -->

	</head>

	<body>
		<div class="cBody">
			<form class=""  >
				<div class="layui-form-item">
					<label class="layui-form-label">我的id: </label>
					<div class="layui-input-inline shortInput">
						<input value="${user.id}" type="text" required disabled="disabled" style="border: 0;" name="id" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-inline shortInput">
						<input value="${user.userInfo.name}" required  disabled="disabled" type="text"  name="name" required lay-verify="required" autocomplete="off" class="layui-input a">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-inline shortInput" style="margin-right: 0; padding-right: 0; top:8px;">
						<label id="sex" style="display: none">${user.userInfo.sex}</label>
						<input type="radio" id="man"  name="sex" value="男"   class="a"/>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            <input type="radio" id="woman" name="sex" value="女"  class="a" style="position: relative; left:35px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;女
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">年龄</label>
					<div class="layui-input-inline shortInput">
						<input value="${user.userInfo.age}" required  disabled="disabled" name="age" required lay-verify="required" autocomplete="off" class="layui-input a" oninput="this.value=this.value.replace(/\D/g,'')">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">联系电话</label>
					<div class="layui-input-inline shortInput">
						<input value="${user.userInfo.phoneNumber}" required  disabled="disabled" name="phoneNumber" required lay-verify="required" autocomplete="off" class="layui-input a" oninput="this.value=this.value.replace(/\D/g,'')">
					</div>
				</div>
				<button id="alter"  style="display: none;" class="layui-btn layui-btn-primary ready-alter"></button>
			</form>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-primary ready-alter">修改</button>
						<button disabled="disabled" id="alterUser-btn" class="layui-btn" >确认修改</button>
					</div>
				</div>
			
			<script>
			window.onload=function(){
			session();
			if($("#sex").html()=="男"){
				$("#man").attr("checked",true);
			}else if($("#sex").html()=="女"){
				$("#woman").attr("checked",true);
			}
			
			$("input[name=sex]").attr("disabled", "disabled");
			$(".ready-alter").click(function(){
				$(".a").removeAttr("disabled");
				$("#alterUser-btn").removeAttr("disabled");
				$("#alterUser-btn").unbind().click(function(){
					if($("input[name=name]").val().length<1){
						alert("姓名不能为空");
					}else if($("input[name=age]").val().length<1){
						alert("年龄不能为空");
					}else if($("input[name=phoneNumber]").val().length<1){
						alert("联系电话不能为空");
					}else{
						
					$.ajax({
						   type : "post",
						   url : "user/alterUserInfo",
						   data : {"id":$("input[name=id]").val(),  
							   "name":$("input[name=name]").val(),
							   "sex":$("input[name=sex]:checked").val(),
							   "age":$("input[name=age]").val(),
							   "phoneNumber":$("input[name=phoneNumber]").val()},
						   async : true,
						   success : function(data) {
								if(data==1){
									alert("修改成功");
									$("#alter").click();
								}else if(data==0){
									alert("修改失败");
								}else{
									window.location.href = "http://127.0.0.1:8080/Swim/login.jsp";
								}
							}	
					});		
					}
				});
			});
			
			
			
			function session() {
			if($("input[name=id]").val()==""){
				window.location.href = "http://127.0.0.1:8080/Swim/login.jsp";
				}		
			}
			
		}
		

			</script>

		</div>
	</body>

</html>