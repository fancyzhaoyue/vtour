// 登录
$('#loginForm').validate({
	rules: {
		email: {
			required: true,
			email: true
		},
		password: 'required'
	},
	messages: {
		email: {
			required: '邮箱不能为空',
			email:'邮箱格式不正确'
		},
		password:'密码不能为空'
	},
	focusCleanup: true,
	focusInvalid: true,
	errorElement: "em",
	errorPlacement: function ( error, element ) {
		error.addClass( "help-block" );
		error.insertAfter( element );
	},
	highlight: function ( element, errorClass, validClass ) {
		$( element ).parents( ".form-group" ).addClass( "has-error" );
	},
	unhighlight: function (element, errorClass, validClass) {
		$( element ).parents( ".form-group" ).removeClass( "has-error" );
	}
});
$('.login-btn').click(function(){
	if($('#loginForm').valid()){
		$.post('/account/doLogin',$('#loginForm').serialize(), function(data){
		 	if(data.status == 0){
				window.location.href= "";
			}else{
				$('#loginErrorMsg').html(data.message).show();
			}
		})
	}
});
// 注册
$('#signupForm').validate({
	rules: {
		email: {
			required: true,
			email: true
		},
		nickName: 'required',
		password: 'required',
		agree: 'required'
	},
	messages: {
		email: {
			required: '邮箱不能为空',
			email: '邮箱格式不正确'
		},
		nickName: '昵称不能为空',
		password: '密码不能为空',
		agree: '请先同意VRena注册协议'
	},
	focusCleanup: true,
	focusInvalid: true,
	errorElement: "em",
	errorPlacement: function ( error, element ) {
		error.addClass( "help-block" );
		if ( element.prop( "type" ) === "checkbox" ) {
			error.insertAfter( element.parent( "label" ) );
		} else {
			error.insertAfter( element );
		}
	},
	highlight: function ( element, errorClass, validClass ) {
		$( element ).parents( ".form-group" ).addClass( "has-error" );
	},
	unhighlight: function (element, errorClass, validClass) {
		$( element ).parents( ".form-group" ).removeClass( "has-error" );
	}
});
$('.signup-btn').click(function(){
	if($('#signupForm').valid()){
		$.post('/account/doSignup',$('#signupForm').serialize(), function(data){
			if(data.status == 0){
				window.location.href= "/account/signup";
			}else{
				$('#signupErrorMsg').html(data.message).show();
			}
		})
	}
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


