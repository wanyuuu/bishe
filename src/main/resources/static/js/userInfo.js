/**
 * Created by wanyu on 2019/5/18.
 */
var USERINFO_QUERY_URL = "http://localhost:8080/user/queryUserInfo";
var USERINFO_UPDATE_URL = "http://localhost:8080/user/updateUserInfo";
$(function () {
    var uid = $.cookie("uid");
    $.ajax({
        url:USERINFO_QUERY_URL + "/" + uid,
        type:"GET",
        dataType:"json",
        statusCode:{
            200:function (data) {
                var len = data.responseText;
                var array = len.split(",");
                $("#uid").val(array[0]);
                $("#username").val(array[1]);
                $("#telephone").val(array[2]);
                $("#email").val(array[3]);
                //设置text等于xxx的选中
                $("#department").find("option:contains("+array[4]+")").attr("selected",true);
            }
        }
    });
    $("#keep").click(function () {
        var uid = $("#uid").val();
        var username = $("#username").val();
        var telephone = $("#telephone").val();
        var email = $("#email").val();
        var did = $("#department").val();
        var data = {
            uid:uid,
            username:username,
            telephone:telephone,
            email:email,
            did:did
        }
        var json = JSON.stringify(data);
        $.ajax({
            url:USERINFO_UPDATE_URL,
            data:json,
            contentType:"application/json",
            type:"POST",
            dataType:"text",
            statusCode:{
                409:function (data) {
                    if(data.responseText == ""){
                        alert('修改失败');
                    }else {
                        alert(data.responseText);
                    }

                }
            }
        })
    })
})