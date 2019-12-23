$(document).ready(function() {
    var rootRef = firebase.database().ref().child("Users");
    var userGpId = localStorage.getItem('gpId');
    
    rootRef.on("child_added", snap => {
        //alert(snap.val());
        var fname = snap.child("Fname").val();
        var lname = snap.child("Lname").val();
        var age = snap.child("Age").val();
        var address = snap.child("Address").val();
        var email = snap.child("Email").val();
        var gpid = snap.child("GP_ID").val();
        var ipid = snap.child("IP_ID").val();
        //var pid = snap.child("PID").val();
        
        if (gpid == userGpId) { // getting only the objects that have id specific to the GP
        $("#table_body").append("<tr><td>" + ipid +"</td><td>" + fname +"</td><td>" + lname + "</td><td>" + age + "</td><td>" + address + "</td><td>" + email + "</td>");
        }
    });
    


});

function Click(aname, aemail, agpid){
    alert("Hello " + aname + " and your email address is " + aemail);
};