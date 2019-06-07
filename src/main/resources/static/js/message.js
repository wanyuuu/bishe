/**
 * Created by wanyu on 2019/5/18.
 */
var ADMEETING_QUERY_URL = "http://localhost:8080/meeting/queryAdMeeting";
var ADMEETING_JOIN_URL = "http://localhost:8080/meeting/joinAdMeeting";
$(function () {
    $.ajax({
        url:ADMEETING_QUERY_URL,
        type:"GET",
        dataType:"json",
        statusCode:{
            200:function (data) {
                var id = $("#message>tbody>tr:last").attr('id');
                $(data).each(function (index) {
                    var len = data[index].toString(); //传过来的是List 必须转为String
                    var array = len.split(',');
                    id++;
                    var str = "<tr id='"+id+"'>" +
                        "<td>"+array[0]+"</td>"+
                        "<td>"+array[1]+"</td>"+
                        "<td>"+array[2]+"</td>"+
                        "<td>"+timestampToTime(+array[3])+"</td>"+
                        "<td>"+timestampToTime(+array[4])+"</td>"+
                        "<td>"+array[5]+"</td>"+
                        "<td><button onclick='join("+array[0]+','+array[6]+','+array[3]+','+array[4]+")'>"+'参加'+"</button></td>"+
                        "</tr>";
                    $("#message").append(str);
                })
            }
        }
    })
});
function join(mid,mrid,starttime,endtime) {
    if(confirm('你确定参加吗？')){
        var did = $.cookie('did');
        var data = {
            mid:mid,
            mrid:mrid,
            starttime:timestampToTime(starttime),
            endtime:timestampToTime(endtime),
            uid:uid,
            did:did
        }
        var json = JSON.stringify(data);
        $.ajax({
            url:ADMEETING_JOIN_URL,
            type:"POST",
            data:json,
            contentType:"application/json",
            dataType:"json",
            statusCode:{
                200:function () {
                    alert('报名成功！');
                    window.location = "mymeeting.html";
                },
                409:function () {
                    alert('报名失败！');
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