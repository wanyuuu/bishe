/**
 * Created by wanyu on 2019/5/22.
 */
var ROOM_QUERY_URL = "http://localhost:8080/administrator/queryRoom";
var ROOM_ADD_URL = "http://localhost:8080/administrator/addRoom";
var ROOM_DELETE_URL = "http://localhost:8080/administrator/deleteRoom";
$(function () {
    $.ajax({
        url:ROOM_QUERY_URL,
        type:"GET",
        dataType:"json",
        statusCode:{
            200:function (data) {
                var id = $("#room>tbody>tr:last").attr('id');
                $(data).each(function (index) {
                    id++;
                    var str = "<tr id='"+id+"'>" +
                        "<td>"+data[index].mrid+"</td>"+
                        "<td>"+data[index].mrname+"</td>"+
                        "<td>"+data[index].maddress+"</td>"+
                        "<td>"+data[index].mcapacity+"</td>"+
                        "<td><button onclick='deleteRoom("+data[index].mrid+")'>"+'删除'+"</button></td>"+
                        "</tr>"
                    $("#room").append(str);
                })
            }
        }
    })
});
function addRoom() {
    $("#showform").show();
    // $("#close").click(function () {
    //     $("#showform").hide();
    // })
    $("#save").click(function () {
        var data = JSON.stringify($("#mroom").serializeObject());
        $.ajax({
            url:ROOM_ADD_URL,
            contentType:"application/json",
            type:"POST",
            data:data,
            dataType:"json",
            statusCode:{
                200:function () {
                    window.location = "room.html";
                },
                409:function () {
                    alert('添加失败');
                }
            }

        })
    })
}
function deleteRoom(mrid) {
    if(confirm("你确定删除吗?")){
        $.ajax({
            url:ROOM_DELETE_URL + "/" + mrid,
            type:"POST",
            statusCode:{
                200:function () {
                    window.location = "room.html";
                },
                409:function () {
                    alert('该会议室仍有会议');
                }
            }
        })
    }else {

    }
}