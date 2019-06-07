/**
 * Created by wanyu on 2019/5/21.
 */
var APPROVE_QUERY_URL = "http://localhost:8080/approve/queryAll";
var APPROVE_AGREE_URL = "http://localhost:8080/approve/agree";

$(function () {
    $.ajax({
        url:APPROVE_QUERY_URL,
        type:"GET",
        dataType:"json",
        statusCode:{
            200:function (data) {
                var id = $("#approve>tbody>tr:last").attr('id');
                var pass =  "<td><font color='red'>"+'未通过'+"</font></td>";
                $(data).each(function (index) {
                    if(data[index].isapprove == "1"){
                        pass = "<td>"+'已通过'+"</td>";
                    }
                    id++;
                    var str = "<tr id='"+id+"'>" +
                        "<td>"+data[index].apid+"</td>"+
                        "<td>"+data[index].username+"</td>"+
                        "<td>"+data[index].telephone+"</td>"+pass+
                        "<td><button onclick='agreeReg("+data[index].apid+")'>"+'同意注册'+"</button></td>"
                        "</tr>"
                    $("#approve").append(str);
                    pass = "<td><font color='red'>"+'未通过'+"</font></td>";
                })
            }
        }
    })
});
function agreeReg(apid) {
    $.ajax({
        url:APPROVE_AGREE_URL + "/" +apid,
        type:"POST",
        statusCode:{
            200:function () {
                window.location = "approve.html";
            },
            409:function () {
                alert('审批失败!');
            }
        }
    })
}