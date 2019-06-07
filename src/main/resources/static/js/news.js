/**
 * Created by wanyu on 2019/5/17.
 */
var NEWS_QUERY_URL = "http://localhost:8080/administrator/queryNews";
var NEWS_ADD_URL = "http://localhost:8080/administrator/publishNews";
$(function () {
    $.ajax({
        url:NEWS_QUERY_URL,
        type:"GET",
        dataType:"json",
        statusCode:{
            200:function (data) {
                var id = $("#news > ul > li:last").attr('id');
                $(data).each(function (index) {
                    id++;
                    var str = "<li value='" + data[id - 1].nid + "'>" + data[id - 1 ].posttime + "&nbsp;" + data[id - 1].newspaper + "</li>";
                    $("#news > ul").append("</br>" + str);
                })
            }
        }
    })
});
function postnews() {
    $("#form").show();
    $("#save").click(function () {
        var data = JSON.stringify($("#postnews").serializeObject());
        $.ajax({
            url:NEWS_ADD_URL,
            contentType:"application/json",
            type:"POST",
            data:data,
            dataType:"json",
            statusCode:{
                200:function () {
                    window.location = "news.html";
                },
                409:function () {
                    alert('添加失败');
                }
            }

        })
    })
}