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
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="fonts/iconfont.css">
<script type="text/javascript" src="framework/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
<script type="text/javascript" src="layui/layui.js"></script>
<script src="framework/cframe.js"></script>
<script src="js/bootstrap.js"></script>
<!-- 仅供所有子页面使用 -->
<!-- 公共样式 结束 -->

</head>

<body>
	<input name="sessionId" value="${user.id}" style="display: none">
					
	<div class="cBody">
		<div class="console">
			
				<div class="layui-form-item">
					<div class="layui-input-inline">
						<input type="text" name="keyword"
							placeholder="输入搜索关键字" autocomplete="off" class="layui-input" style="position: relative;width: 150px;">
					
				<div style="width: 105px; display: inline-block; position: relative; left:160px;bottom: 38px;" >
					<select name="selectTemp" class="form-control">
					  <option value="id">账号id</option>
					  <option value="username">账号名</option>
					  <option value="name">用户姓名</option>
					  <option value="phoneNumber">手机号码</option>
					</select>
				</div>
					</div>
					<button class="layui-btn" id="selectUser"  style="position: relative;left: 80px;">检索</button>
				</div>
					
			

			<script>
				layui.use('form', function() {
					var form = layui.form;
					//监听提交
					form.on('submit(submitBut)', function(data) {
						layer.msg(JSON.stringify(data.field));
						return false;
					});
				});
			</script>
		</div>

		<table class="layui-table" style="position: relative;bottom: 40px;">
			<thead>
				<tr>
					<th style="text-align: center;">用户id</th>
					<th style="text-align: center;">用户名</th>
					<th style="text-align: center;">姓名</th>
					<th style="text-align: center;">性别</th>
					<th style="text-align: center;">联系电话</th>
					<th style="text-align: center;">剩余积分</th>
					<th style="text-align: center;">余额</th>
					<th style="text-align: center;">剩余游泳时间</th>
					<th style="width: 90px; text-align: center;">会员状态</th>
					<th style="text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr class="tr">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<button class="layui-btn layui-btn-xs">修改</button>
						<button class="layui-btn layui-btn-xs">充值</button>
					</td>
				</tr>
				<tr id="" class="">
					<td colspan="10">
						<div style="position: relative;">
							<button id="lastPage" class="glyphicon glyphicon-chevron-left"></button>
							<label style="margin-left: 10px; margin-right: 1px">第</label> <input
								name="page" maxlength="4"
								style="width: 32px; text-align: center; vertical-align: middel;"
								oninput="value=value.replace(/[^\d]/g,'')"> <label
								style="margin-left: 1px; margin-right: 1px">页</label>
							<label style="margin-left: 10px; margin-right: 1px">共</label> 
							<label id="totalPage" style="margin-left: 1px; margin-right: 1px"></label>
							<label style="margin-left: 1px; margin-right: 10px">页</label>
							<button id="nextPage" class="glyphicon glyphicon-chevron-right"></button>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg" id="readyModify" style="display: none;"
		data-toggle="modal" data-target="#myModal">Yong</button>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true" id="exitModify">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">用户信息修改</h4>
				</div>
				<div class="modal-body" style="padding-right: 50px;">
					<!-- 主体 -->
					<form class="form-horizontal" action="user/alterUserInfo"
						method="post">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">id</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputEmail3" name="modifyid"
									readonly="readonly" style="border: 0;">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputEmail3" name="modifyname">
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">性别</label>&nbsp;&nbsp;&nbsp;
							<label class="radio-inline"> <input type="radio"
								name="modifysex" id="inlineRadio1" value="男"> 男
							</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label class="radio-inline">
								<input type="radio" name="modifysex" id="inlineRadio2" value="女">
								女
							</label>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">年龄</label>
							<div class="col-sm-10">
								<input type="text" maxlength="3" class="form-control" id="inputPassword3" name="modifyage" oninput="this.value=this.value.replace(/\D/g,'')">
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">联系电话</label>
							<div class="col-sm-10">
								<input type="text" maxlength="11" class="form-control" id="inputPassword3" name="modifyphoneNumber" oninput="this.value=this.value.replace(/\D/g,'')">
							</div>
						</div>
					</form>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary" id="modifyUserInfo">确认修改</button>
						</div>
				</div>
			</div>
		</div>
	</div>
	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" 
	data-target="#myModal2" id="readyAddMoney" style="display: none">
  		充值
	</button>

<!-- Modal -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">充值页面</h4>
      </div>
      <div class="modal-body" style="padding-right: 50px;">
      <form class="form-horizontal" action="user/alterUserInfo"
						method="post">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">id</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputEmail3" name="rechargeid"
									readonly="readonly" style="border: 0;">
							</div>
						</div>
						
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputEmail3" name="rechargename"
									readonly="readonly" style="border: 0;">
							</div>
						</div>
						
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">余额</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputEmail3" name="recharge"
									readonly="readonly" style="border: 0;">
							</div>
						</div>
						
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">充值金额</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" maxlength="5" id="inputEmail3" name="rechargeMoney" oninput="this.value=this.value.replace(/\D/g,'')">
							</div>
						</div>

					</form>
      			
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
        <button type="button" class="btn btn-primary" id="rechargeMoney">确认充值</button>
      </div>
    </div>
  </div>
</div>

</body>
<script type="text/javascript">
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').focus()
	});

	window.onload = function() {
		if($("input[name=sessionId]").val()==""){
			window.location.href = "http://127.0.0.1:8080/Swim/login.jsp";
		}
		
		var page = 1;
		var data
		selectAll();
		$("#selectUser").unbind('click').click(function(){
			selectAny();
		});
		
		$("#modifyUserInfo").click(function(){	
			if($("input[name=modifyname]").val().length<1){
				alert("姓名不能为空");
				return;
			}
			if($("input[name=modifysex]:checked").val()==null){
				alert("性别不能为空");
				return;
			}
			if($("input[name=modifyage]").val().length<1){
				alert("年龄不能为空");
				return;
			}
			if($("input[name=modifyphoneNumber]").val().length<1){
				alert("联系电话不能为空");
				return;
			}
			
			$.ajax({
				   type : "post",
				   url : "user/alterUserInfo2",
				   data : {"id":$("input[name=modifyid]").val(),
					   "name":$("input[name=modifyname]").val(),
					   "sex":$("input[name=modifysex]:checked").val(),
					   "age":$("input[name=modifyage]").val(),
					   "phoneNumber":$("input[name=modifyphoneNumber]").val()
				   },  
				   async : true, // 同步调用
				   success : function(data) {
						if(data==1){
							alert("修改成功");
							$("#exitModify").click();
							selectAll();
						}else if(data==1){
							alert("修改失败");
						}else{
							location.replace(location.href);
						}
					}
				});
		});
		
		$("#rechargeMoney").click(function(){
			if($("input[name=rechargeMoney]").val().length<1){
				alert("请输入充值金额");
				return;
			}
			
			$.ajax({
				   type : "post",
				   url : "account/rechargeMoney",
				   data : {"money":$("input[name=rechargeMoney]").val(),
					   "id":$("input[name=rechargeid]").val() 
				   },  
				   async : true, // 同步调用
				   success : function(data) {
					   if(data){
						   alert("充值成功");
						   location.replace(location.href);
					   }else{
						   alert("充值失败");
						   location.replace(location.href);
					   }
					   
					}
				});
			
		});
		
		

		function selectAll() {
			$.ajax({
				type : "get",
				url : "user/selectAll",
				async : true,
				success : function(data) {
					for (var k = 0; k < 10; k++) {//清空之前显示的值
						$("#tbody").children().eq(k).children().eq(0).html("");
						$("#tbody").children().eq(k).children().eq(1).html("");
						$("#tbody").children().eq(k).children().eq(2).html("");
						$("#tbody").children().eq(k).children().eq(3).html("");
						$("#tbody").children().eq(k).children().eq(4).html("");
						$("#tbody").children().eq(k).children().eq(5).html("");
						$("#tbody").children().eq(k).children().eq(6).html("");
						$("#tbody").children().eq(k).children().eq(7).html("");
						$("#tbody").children().eq(k).children().eq(8).html("");
						$("#tbody").children().eq(k).children().eq(9).children().eq(0).unbind();
						$("#tbody").children().eq(k).children().eq(9).children().eq(1).unbind();
					}
					for ( var i in data) {//显示值
						const n = i;
						if (i >= (page - 1) * 10 && i < page * 10) {
							$("#tbody").children().eq(i % 10).children().eq(0)
									.html(data[i].id);
							$("#tbody").children().eq(i % 10).children().eq(1)
									.html(data[i].username);
							$("#tbody").children().eq(i % 10).children().eq(2)
									.html(data[i].userInfo.name);
							$("#tbody").children().eq(i % 10).children().eq(3)
									.html(data[i].userInfo.sex);
							$("#tbody").children().eq(i % 10).children().eq(4)
									.html(data[i].userInfo.phoneNumber);
							$("#tbody").children().eq(i % 10).children().eq(5)
									.html(data[i].account.points);
							$("#tbody").children().eq(i % 10).children().eq(6)
									.html(data[i].account.money);
							$("#tbody").children().eq(i % 10).children().eq(7)
									.html(parseInt(data[i].account.remainTime/3600)+"时"+
										  parseInt((data[i].account.remainTime%3600)/60)+"分"+
										  parseInt((data[i].account.remainTime%3600)%60)+"秒"
									);
							if (compare(data[i].member.memberEnd)) {
								$("#tbody").children().eq(i % 10).children()
										.eq(8).html("是");
							} else {
								$("#tbody").children().eq(i % 10).children()
										.eq(8).html("否");
							}
							$("#tbody").children().eq(n % 10).children().eq(9).children().eq(0).unbind('click').click(function(){
								$("input[name=modifyid]").val("");
								$("input[name=modifyname]").val("");
								$("#inlineRadio1").removeAttr("checked");
								$("#inlineRadio2").removeAttr("checked");
								$("input[name=modifyage]").val("");
								$("input[name=modifyphoneNumber]").val("");
								//
								$("input[name=modifyid]").val(data[n].id);
								$("input[name=modifyname]").val(data[n].userInfo.name);
								if(data[n].userInfo.sex=="男"){
									$("#inlineRadio1").prop("checked","checked");
								}else if(data[n].userInfo.sex=="女"){
									$("#inlineRadio2").prop("checked","checked");
								}
								$("input[name=modifyage]").val(data[n].userInfo.age);
								$("input[name=modifyphoneNumber]").val(data[n].userInfo.phoneNumber);
								$("#readyModify").click();
							});
							
							$("#tbody").children().eq(n % 10).children().eq(9).children().eq(1).unbind('click').click(function(){
								$("input[name=rechargeid]").val("");
								$("input[name=rechargename]").val("");
								$("input[name=recharge]").val("");
								$("input[name=rechargemoney]").val("");
								//
								$("input[name=rechargeid]").val(data[n].id);
								$("input[name=rechargename]").val(data[n].userInfo.name);
								$("input[name=recharge]").val(data[n].account.money);
								$("#readyAddMoney").click();
							});
							
						}
					}
					$("input[name=page]").val(page);
					$("#totalPage").html(Math.ceil(data.length/10));
					$("#lastPage").unbind('click').click(function() {//上一页
						page--;
						if (page < 1) {
							page = 1;
						}
						if ((page - 1) * 10 >= data.length) {
							page = page - 1;
						}
						selectAll();
					});

					$("#nextPage").unbind('click').click(function() {//下一页
						page++;
						if (page < 1) {
							page = 1;
						}
						if ((page - 1) * 10 >= data.length) {
							page = page - 1;
						}
						selectAll();
					});

					$("input[name=page]").unbind().bind('keyup',
							function(event) {//选定指定页数回车
								if (event.keyCode == "13") {
									var tempPage = page;//获取当前页数
									page = $("input[name=page]").val();//当前要跳的页数
									if (page < 1) {
										page = tempPage;
									}
									if ((page - 1) * 10 >= data.length) {
										page = tempPage;
									}
									selectAll();
								}
							});
				}
			});
		}
		
		function selectAny() {
			$.ajax({
				type : "get",
				data : {"keyword":$("input[name=keyword]").val(),
					"temp":$("select[name=selectTemp]").val()
					},  
				url : "user/selectAny",
				async : true,
				success : function(data) {
					for (var k = 0; k < 10; k++) {//清空之前显示的值
						$("#tbody").children().eq(k).children().eq(0).html("");
						$("#tbody").children().eq(k).children().eq(1).html("");
						$("#tbody").children().eq(k).children().eq(2).html("");
						$("#tbody").children().eq(k).children().eq(3).html("");
						$("#tbody").children().eq(k).children().eq(4).html("");
						$("#tbody").children().eq(k).children().eq(5).html("");
						$("#tbody").children().eq(k).children().eq(6).html("");
						$("#tbody").children().eq(k).children().eq(7).html("");
						$("#tbody").children().eq(k).children().eq(8).html("");
						$("#tbody").children().eq(k).children().eq(9).children().eq(0).unbind();
						$("#tbody").children().eq(k).children().eq(9).children().eq(1).unbind();
					}
					for ( var i in data) {//显示值
						const n = i;
						if (i >= (page - 1) * 10 && i < page * 10) {
							$("#tbody").children().eq(i % 10).children().eq(0)
									.html(data[i].id);
							$("#tbody").children().eq(i % 10).children().eq(1)
									.html(data[i].username);
							$("#tbody").children().eq(i % 10).children().eq(2)
									.html(data[i].userInfo.name);
							$("#tbody").children().eq(i % 10).children().eq(3)
									.html(data[i].userInfo.sex);
							$("#tbody").children().eq(i % 10).children().eq(4)
									.html(data[i].userInfo.phoneNumber);
							$("#tbody").children().eq(i % 10).children().eq(5)
									.html(data[i].account.points);
							$("#tbody").children().eq(i % 10).children().eq(6)
									.html(data[i].account.money);
							$("#tbody").children().eq(i % 10).children().eq(7)
									.html(parseInt(data[i].account.remainTime/3600)+"时"+
										  parseInt((data[i].account.remainTime%3600)/60)+"分"+
										  parseInt((data[i].account.remainTime%3600)%60)+"秒"
									);
							if (compare(data[i].member.memberEnd)) {
								$("#tbody").children().eq(i % 10).children()
										.eq(8).html("是");
							} else {
								$("#tbody").children().eq(i % 10).children()
										.eq(8).html("否");
							}
							$("#tbody").children().eq(n % 10).children().eq(9).children().eq(0).unbind('click').click(function(){
								$("input[name=modifyid]").val("");
								$("input[name=modifyname]").val("");
								$("#inlineRadio1").removeAttr("checked");
								$("#inlineRadio2").removeAttr("checked");
								$("input[name=modifyage]").val("");
								$("input[name=modifyphoneNumber]").val("");
								//
								$("input[name=modifyid]").val(data[n].id);
								$("input[name=modifyname]").val(data[n].userInfo.name);
								if(data[n].userInfo.sex=="男"){
									$("#inlineRadio1").prop("checked","checked");
								}else if(data[n].userInfo.sex=="女"){
									$("#inlineRadio2").prop("checked","checked");
								}
								$("input[name=modifyage]").val(data[n].userInfo.age);
								$("input[name=modifyphoneNumber]").val(data[n].userInfo.phoneNumber);
								$("#readyModify").click();
							});
							
							$("#tbody").children().eq(n % 10).children().eq(9).children().eq(1).unbind('click').click(function(){
								$("input[name=rechargeid]").val("");
								$("input[name=rechargename]").val("");
								$("input[name=recharge]").val("");
								$("input[name=rechargemoney]").val("");
								//
								$("input[name=rechargeid]").val(data[n].id);
								$("input[name=rechargename]").val(data[n].userInfo.name);
								$("input[name=recharge]").val(data[n].account.money);
								$("#readyAddMoney").click();
							});
							
						}
					}
					$("input[name=page]").val(page);
					$("#totalPage").html(Math.ceil(data.length/10));
					$("#lastPage").unbind('click').click(function() {//上一页
						page--;
						if (page < 1) {
							page = 1;
						}
						if ((page - 1) * 10 >= data.length) {
							page = page - 1;
						}
						selectAny();
					});

					$("#nextPage").unbind('click').click(function() {//下一页
						page++;
						if (page < 1) {
							page = 1;
						}
						if ((page - 1) * 10 >= data.length) {
							page = page - 1;
						}
						selectAny();
					});

					$("input[name=page]").unbind().bind('keyup',
							function(event) {//选定指定页数回车
								if (event.keyCode == "13") {
									var tempPage = page;//获取当前页数
									page = $("input[name=page]").val();//当前要跳的页数
									if (page < 1) {
										page = tempPage;
									}
									if ((page - 1) * 10 >= data.length) {
										page = tempPage;
									}
									selectAny();
								}
							});
				}
			});
		}

		function getDate() {
			var time = new Date();
			var day = ("0" + time.getDate()).slice(-2);
			var month = ("0" + (time.getMonth() + 1)).slice(-2);
			var today = time.getFullYear() + "-" + (month) + "-" + (day);
			return today;
		}
		function compare(a) {
			if (a != null) {//日期不为空
				var today = new Date();
				var myday = a.split("-");
				var myyear = myday[0];//用户的会员到期年份
				var mymonth = myday[1];//用户的会员到期月份
				var myday = myday[2];//用户的会员到期日期
				if (today.getFullYear() < myyear) {//当前年份小于到期年份
					return true;
				} else if (today.getFullYear() > myyear) {//当前年份大于到期年份
					return false;
				}
				
				if (today.getMonth() + 1 < mymonth) {
					return true;
				} else if (today.getMonth() + 1 > mymonth) {
					return false;
				}
					
				if (today.getDate() <= myday) {
					return true;
				}
				return false;
			}
			return false;//日期为空直接为false
		}

	}
</script>

</html>