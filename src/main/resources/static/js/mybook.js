/**
 * Created by wanyu on 2019/5/22.
 */
var MEETING_QUERY_URL = "http://localhost:8080/meeting/querySelfMeeting";
var MEETING_DELETE_URL = "http://localhost:8080/meeting/deleteMeeting";
$(function () {
    var uid = $.cookie('uid');
    $.ajax({
        url:MEETING_QUERY_URL + "/" +uid,
        type:"POST",
        dataType:"json",
        statusCode:{
            200:function (data) {
                var id = $("#mybook>tbody>tr:last").attr('id');
                $(data).each(function (index) {
                    var len = data[index].toString(); //传过来的是List 必须转为String
                    var array = len.split(',');
                    id++;
                    var str = "<tr id='"+id+"'>" +
                        "<td>"+array[0]+"</td>"+
                        "<td>"+array[1]+"</td>"+
                        "<td>"+timestampToTime(+array[2])+"</td>"+
                        "<td>"+timestampToTime(+array[3])+"</td>"+
                        "<td>"+timestampToTime(+array[4])+"</td>"+
                        "<td>"+array[5]+"</td>"+
                        "<td><button onclick='deleteMeeting("+array[0]+")'>"+'取消会议'+"</button></td>"+
                        "</tr>";
                    $("#mybook").append(str);
                })
            }
        }
    })
})
function deleteMeeting(bid) {
        if(confirm("你确定取消吗?")){
            $.ajax({
                url:MEETING_DELETE_URL + "/" + bid,
                type:"POST",
                statusCode:{
                    200:function () {
                        window.location = "mybook.html";
                    },
                    500:function () {
                        alert('取消失败');
                    }
                }
            })
        }else {

        }
}
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s;
}