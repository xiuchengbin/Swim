window.onload = function() {

					$(".zhuce").click(function() {
						$(".resgister").removeClass("cang");
						$("#cover").addClass("cover");
					});
					$(".close").click(function(){
						$(".btn-clean").click();
						$(".resgister").addClass("cang");
						$("#cover").removeClass("cover");
					});
					$(".btn-clean").click(function(){
						$("input[name=Resusername]").val("");
						$("input[name=Respassword]").val("");
						$("input[name=Respassword2]").val("");
					});
					
					$(".btn-resgister").click(function(){
						if($("input[name=Resusername]").val().length<5){
							alert("用户名至少5位");
						}else if($("input[name=Respassword]").val().length<5){
							alert("密码至少5位");
						}else if($("input[name=Respassword]").val()!=$("input[name=Respassword2]").val()){
							alert("俩次密码输入不一致");
							$("input[name=Respassword2]").val("");
						}else{
						$.ajax({
							   type : "get",
							   url : "user/resgister.do",
							   data : {"username":$("input[name=Resusername]").val(),
								   "password":$("input[name=Respassword]").val(),  
							   },  
							   async : true, // 同步调用
							   success : function(data) {
								   if(data=="用户名已被注册"){
									   alert(data);
									   $("input[name=Resusername]").val("");
								   }else{
									   alert("注册成功！您的账户id："+data);
									   $(".close").click();
								   }
								},
						      /*  error:function(data){
						    	   alert("注册失败");
						       } */
							});
						}
					});
				}

				/*layui.use('form', function() {
					var form = layui.form;
					form.verify({
						username : function(value, item) { //value：表单的值、item：表单的DOM对象
							if (value == null || value == "") {
								return '请输入您的用户名！';
							}
						},
						password : function(value, item) {
							if (value == null || value == "") {
								return '请输入您的账户密码！';
							}
						}
					});
					//监听提交
					form.on('submit(submitBut)', function(data) {
						return false;
					});
				});*/