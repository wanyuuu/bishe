/**
 * Created by wanyu on 2019/5/30.
 */
var MYMEETING_QUERY_URL = "http://localhost:8080/meeting/queryMyMeeting";
$(function () {
    var uid = $.cookie('uid');
    $.ajax({
        url:MYMEETING_QUERY_URL + "/" +uid,
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
                        "<td>"+array[4]+"</td>"+
                        "</tr>";
                    $("#mymeeting").append(str);
                })
            }
        }
    })
});
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