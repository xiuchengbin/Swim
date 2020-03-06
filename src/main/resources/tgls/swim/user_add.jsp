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

	<style>
		.layui-form{
			margin-right: 30%;
		}
	</style>

</head>

<body>
<div class="cBody">
	<form>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-inline shortInput">
				<input type="text" name="username" maxlength="15" required lay-verify="required|identity" autocomplete="off" class="layui-input"
					   required
					   oninput="value=value.replace(/[^\w\.\/]/ig,''),setCustomValidity('')">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">密码(12345)</label>
			<div class="layui-input-inline shortInput">
				<input type="password" name="password" maxlength="20" required lay-verify="required|PriceCheck" autocomplete="off" class="layui-input"  value="12345">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline shortInput">
				<input type="text" name="password" maxlength="20" required lay-verify="required|PriceCheck" autocomplete="off" class="layui-input"  value="12345">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-inline shortInput" style="position: relative;top:8px; left: 5px;">
				<input type="radio" id="man"  name="sex" value="男"   class="a"/>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="woman" name="sex" value="女"  class="a" style="position: relative; left:30px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;女
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">年龄</label>
			<div class="layui-input-inline shortInput">
				<input type="text" name="password" maxlength="20" required lay-verify="required|PriceCheck" autocomplete="off" class="layui-input"  value="12345">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">联系电话</label>
			<div class="layui-input-inline shortInput">
				<input type="text" name="password" maxlength="20" required lay-verify="required|PriceCheck" autocomplete="off" class="layui-input"  value="12345">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="submitBut">完成</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">

</script>
</body>
</html>