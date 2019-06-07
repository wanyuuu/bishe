/**
 * Created by wanyu on 2019/5/21.
 */
var DEPARTMENT_QUERY_URL = "http://localhost:8080/administrator/queryDepartment";
var DEPARTMENT_DELETE_URL = "http://localhost:8080/administrator/deleteDepartment";
var DEPARTMENT_UPDATE_URL = "http://localhost:8080/administrator/updateDepartment";
var DEPARTMENT_ADD_URL = "http://localhost:8080/administrator/addDepartment";
$(function () {
    $.ajax({
        url:DEPARTMENT_QUERY_URL,
        type:"GET",
        dataType:"json",
        statusCode:{
            200:function (data) {
                var id = $("#alldepartment>tbody>tr:last").attr('id');
                $(data).each(function (index) {
                    id++;
                    var str = "<tr id='"+id+"'>" +
                        "<td>"+data[index].did+"</td>"+
                        "<td>"+data[index].dname+"</td>"+
                        "<td>"+data[index].dnumber+"</td>"+
                        "<td>"+data[index].dinformation+"</td>"+
                        "<td><button onclick='updateDepartment("+data[index].did+")'>"+'修改'+"</button></td>"+
                    "<td><button onclick='deleteDepartment("+data[index].did+")'>"+'删除'+"</button></td>"+
                    "</tr>"
                    $("#alldepartment").append(str);
                })
            }
        }
    })
});
function addDepartment() {
    $("#form").show();
    // $("#close").click(function () {
    //     $("#form").hide();
    // })
    $("#save").click(function () {
        var data = JSON.stringify($("#department").serializeObject());
        $.ajax({
            url:DEPARTMENT_ADD_URL,
            contentType:"application/json",
            type:"POST",
            data:data,
            dataType:"json",
            statusCode:{
                200:function () {
                    window.location = "department.html";
                },
                409:function () {
                    alert('添加失败');
                }
            }
            
        })
    })
}
function deleteDepartment(did) {

    if(confirm("你确定删除吗?")){
        $.ajax({
            url:DEPARTMENT_DELETE_URL + "/" + did,
            type:"POST",
            statusCode:{
                200:function () {
                    window.location = "department.html";
                },
                500:function () {
                    alert('该部门仍有员工');
                }
            }
        })
    }else {

    }
}
function updateDepartment(did) {
    $("#form").show();
    $("#did").val(did);
    // $("#close").click(function () {
    //     $("#form").hide();
    // })
    $("#save").click(function () {
        var data = JSON.stringify($("#department").serializeObject());
        $.ajax({
            url:DEPARTMENT_UPDATE_URL,
            contentType:"application/json",
            type:"POST",
            data:data,
            dataType:"json",
            statusCode:{
                200:function () {
                    alert('修改成功');
                    window.location = "department.html";
                },
                409:function () {
                    alert('修改失败，部门名已存在');
                }
            }
        })
    })
}