// 登录
$('.login-btn').click(function(){
	$.post('/login',{email:$('#email').val(),password:$('#password').val()}, function(data){
	 	if(data.status == 0){
			window.location.href= "";
		}else{
			$('#loginErrorMsg').html(data.message).show();
		}
	})
});
// 注册
$('.signup-btn').click(function(){
	$.post('/signup',$('#signupForm').serialize(), function(data){
		if(data.status == 0){
			window.location.href= "/register";
		}else{
			$('#signupErrorMsg').html(data.message).show();
		}
	})
});

//修改用户资料
$('.profile-save-btn').click(function(){
	$.post('/set/doProfile',$('#profileForm').serialize(), function(data){
		if(data.status == 0){
			alert("保存成功");
		}else{
			alert(data.message);
		}
	})
});


