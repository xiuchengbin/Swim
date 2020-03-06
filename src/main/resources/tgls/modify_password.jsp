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
		<label id="id" style="display: none">${user.id}</label>
		<label id="username" style="display: none">${user.username}</label>
			<form id="addForm" class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">原始密码</label>
					<div class="layui-input-inline shortInput">
						<input type="password" name="oldpassword" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">新密码</label>
					<div class="layui-input-inline shortInput">
						<input type="password" name="newpassword" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">确认新密码</label>
					<div class="layui-input-inline shortInput">
						<input type="password" name="newpassword2" required lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button id="alterUser-btn" class="layui-btn" lay-submit lay-filter="submitBut">确认修改</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
			
			<script>
			window.onload = function() {
				$("#alterUser-btn").click(function(){
					if($("input[name=newpassword]").val().length<5){
						alert("密码至少5位");
					}else if($("input[name=newpassword]").val()!=$("input[name=newpassword2]").val()){
						alert("俩次密码输入不一致");
						$("input[name=newpassword2]").val("");
					}else{
						$.ajax({
							   type : "post",
							   url : "user/alterPassword",
							   data : {"id":$("#id").text(),
								   "username":$("#username").text(),
								   "newpassword":$("input[name=newpassword]").val(),
								   "oldpassword":$("input[name=oldpassword]").val(),  
							   },  
							   async : true, // 同步调用
							   success : function(data) {
								   if(data=="1"){
									   alert(data);
									   layui.alert('');
									   $("input[name=oldpassword]").val("");
									   $("input[name=newpassword]").val("");
									   $("input[name=newpassword2]").val("");
								   }else{

								   }
								},
						      /*  error:function(data){
						    	   alert("注册失败");
						       } */
							});
					}
					
				});

				
			}

			
			</script>
			
			<script>
				layui.use('form', function() {
					var form = layui.form;
					//监听提交
					form.on('submit(submitBut)', function(data) {
						/* layer.msg(JSON.stringify(data.field)); */
						return false;
					});
				});

				// $("#sendFile").click(function(){
				// 	var formData = new FormData(document.getElementById("test"));
				// 	/* formData.append("file", $("input[name=file]").val());
                //     formData.append("id", 1);
                //     formData.append("receiveId", $("input[name=id]").val()); */
				// 	$.ajax({
				// 		type : "post",
				// 		url : "send/file",
				// 		processData: false, //禁止jquery对DAta数据的处理,默认会处理
				// 		contentType: false,
				// 		data : formData,
				// 		async : true,
				// 		success : function(data) {
				// 		}
				// 	});
				// });
			</script>

		</div>
	</body>

</html>