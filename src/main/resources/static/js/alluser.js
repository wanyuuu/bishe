/**
 * Created by wanyu on 2019/5/21.
 */
var ALLUSER_QUERY_URL = "http://localhost:8080/administrator/queryAllUser";
var USER_DELETE_URL = "http://localhost:8080/administrator/deleteUser";
$(function () {
    $.ajax({
        url:ALLUSER_QUERY_URL,
        type:"GET",
        dataType:"json",
        statusCode:{
            200:function (data) {
                var id = $("#alluser>tbody>tr:last").attr('id');
                $(data).each(function (index) {
                    id++;
                    var str = "<tr id='"+id+"'>" +
                        "<td>"+data[index].username+"</td>"+
                        "<td>"+data[index].telephone+"</td>"+
                        "<td>"+data[index].email+"</td>"+
                        "<td>"+data[index].regtime+"</td>"+
                       "<td><button onclick='deleteUser("+data[index].uid+")'>"+'删除'+"</button></td>"
                        "</tr>"
                    $("#alluser").append(str);
                })
            }
        }
    })
});
function deleteUser(uid) {
    
    if(confirm("你确定删除吗?")){
        $.ajax({
            url:USER_DELETE_URL + "/" + uid,
            type:"POST",
            statusCode:{
                200:function () {
                    window.location = "userlist.html";
                },
                409:function () {
                    alert('删除失败！');
                }
            }
        })
    }else {

    }
}