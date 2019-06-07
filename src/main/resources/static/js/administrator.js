/**
 * Created by wanyu on 2019/5/20.
 */
var uid = $.cookie('uid');
function userInfo() {
    if(uid != undefined){
        window.location = "userInfo.html";
    }
}
function uppassword() {
    if(uid != undefined){
        window.location = "password.html";
    }
}
function orderMeeting() {
    if(uid != undefined){
        window.location = "bookRoom.html";
    }
}
function myMeeting() {
    if(uid != undefined){
        window.location = "mymeeting.html";
    }
}
function myOrder() {
    if(uid != undefined){
        window.location = "mybook.html";
    }
}
function userlist() {
    if(uid == undefined){
        window.location = "userlist.html";
    }
}
function department() {
    if(uid == undefined){
        window.location = "department.html";
    }
}
function approve() {
    if(uid == undefined){
        window.location = "approve.html";
    }
}
function room() {
    if(uid == undefined){
        window.location = "room.html";
    }
}