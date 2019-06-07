/**
 * Created by wanyu on 2019/5/23.
 */
var USEDROOM_QUERY_URL = "http://localhost:8080/meeting/queryUsedroom";
var CREATE_MEETING_URL = "http://localhost:8080/meeting/createMeeting";
$(function () {
    $("#showroom").click(function () {
        $("#usedRoom tbody").html("");
        var starttime =  $("#starttime").val();
        var endtime = $("#endtime").val();
        $.ajax({
            url:USEDROOM_QUERY_URL +"/" + starttime +"/" + endtime,
            type:"POST",
            dataType:"json",
            statusCode:{
                200:function (data) {
                    var id = $("#usedRoom>tbody>tr:last").attr('id');
                    $(data).each(function (index) {
                        id++;
                        var str = "<tr id='"+id+"'>" +
                        "<td>"+data[index].mrid+"</td>"+
                        "<td>"+data[index].mrname+"</td>"+
                        "<td>"+data[index].maddress+"</td>"+
                        "<td>"+data[index].mcapacity+"</td>"+
                         "<td><button onclick='addRoom("+data[index].mrid+")'>"+'选择'+"</button></td>";
                            $("#usedRoom").append(str);
                    })
                }
            }
        })
    });
    $("#save").click(function () {
        var mname = $("#mname").val();
        var mnumber = $("#mnumber").val();
        var mrid = $("#mrid").val();
        var information = $("#information").val();
        var uid = $.cookie('uid');
        var did = $("#department").val();
        var starttime =  $("#starttime").val();
        var endtime = $("#endtime").val();
        var json = {
            mname:mname,
            mrid:mrid,
            starttime:'',
            endtime:'',
            mnumber:mnumber,
            uid:uid,
            did:did,
            information:information
        }
        var data = JSON.stringify(json);
        $.ajax({
            url:CREATE_MEETING_URL +"/" + starttime +"/" + endtime,
            contentType:"application/json",
            type:"POST",
            data:data,
            dataType:"json",
            statusCode:{
                200:function () {
                    alert('创建会议成功');
                    if(uid == undefined){
                        window.location = "message.html";
                        return;
                    }
                    window.location = "mybook.html";
                },
                409:function () {
                    alert('创建会议失败');
                }
            }
        })
    })
});
function addRoom(mrid) {
    $("#mrid").val(mrid);
}