
var USER_LOGIN_URL = "http://localhost:8080/user/login";
var USER_REG_URL = "http://localhost:8080/user/register";
var ADMI_LOGIN_URL = "http://localhost:8080/administrator/login";
$(function () {
    $("#login").click(function () {
        var shenfen = $("#shenfen").val();
        if(shenfen == "user"){
            var json = JSON.stringify($("#user").serializeObject());
            $.ajax({
                url:USER_LOGIN_URL,
                type:"POST",
                data:json,
                contentType:"application/json",
                dataType:"json",
                statusCode:{
                    200:function (data) {
                        var date = new Date();
                        var time = new Date(date);
                        time.setHours(time.getHours() + 12);
                        $.cookie("uid",data.uid,{'expires':time});
                        $.cookie("did",data.did,{'expires':time});
                        window.location = "index.html";
                    },
                    409:function () {
                        alert('密码错误！');
                    },
                    500:function () {
                        alert('该用户不存在！');
                    },
                    501:function (data) {
                        alert('注册仍未被审批！')
                    }
                }
            })
        }else if(shenfen == "administrator"){
            var username = $("#username").val();
            var password = $("#password").val();
            var data = {
                username:username,
                password:password
            }
            var json = JSON.stringify(data);
            $.ajax({
                url:ADMI_LOGIN_URL,
                contentType:"application/json",
                data:json,
                type:"POST",
                statusCode:{
                    200:function () {
                        $.cookie('uid','',{ expires: -1 });
                        window.location = "index.html";
                    }
                }
            })
        }

    });
    
    $("#register").click(function () {
        if($("#username").val() == "请输入用户名" || $("#username").val() == "用户名不能为空，最大长度为11" ){
            alert('用户名不能为空');
            return false;
        }
        if($("#telephone").val() == "请输入手机号"){
            alert('手机号不能为空');
            return false;
        }
        if($("#telephone").val() == "请正确输入手机号"){
            alert('手机号格式错误，请重新输入');
            return false;
        }
        if($("#email").val() == "请输入邮箱地址" ){
            alert('邮箱不能为空');
            return false;
        }
        if($("#email").val() == "请正确输入邮箱地址" ){
            alert('邮箱地址格式错误，请重新输入');
            return false;
        }
        if($("#password").val() != $("#repassword").val()){
            alert('两次密码输入不一致，请重新输入！');
            return false;
        }

        var json = JSON.stringify($("#user").serializeObject());
        $.ajax({
            url:USER_REG_URL,
            type:"POST",
            data:json,
            contentType:"application/json",
            dataType:"json",
            statusCode:{
                200:function () {
                    alert('注册成功')
                    window.location = "login.html";
                },
                409:function () {
                    alert('该用户已注册，请更换用户名')
                }
            }
        })
    })
});
