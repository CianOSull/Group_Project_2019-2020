$(document).ready(function() {
    var rootRef = firebase.database().ref().child("Users");
    var userGpId = localStorage.getItem('gpId');
    
    rootRef.on("child_added", snap => {
        //alert(snap.val());
        var name = snap.child("Fname").val();
        var BCRes = snap.child("BreastCancerResult").val();
        var DRes = snap.child("DiabetesOutPut").val();
        var HRes = snap.child("HeartValue").val();
        var gpid = snap.child("GP_ID").val();
        var email = snap.child("Email").val();
        
        if (gpid == userGpId) { // getting only the objects that have id specific to the GP
        $("#table_body").append("<tr><td>" + name +"</td><td><a href = \"mailto:" + email + "\">"+ email +"</a></td>"+ PrepareForHTML(BCRes) + PrepareForHTML(DRes)+ PrepareForHTML(HRes));
        }
    });
    


});

function Click(aname, aemail, agpid){
    alert("Hello " + aname + " and your email address is " + aemail);
};

function Rounder(doubleInput){
    toReturn = doubleInput*100;
    toReturn = toReturn.toFixed(0);
    return toReturn;
}

function PrepareForHTML(doubleInput){
    doubleInput = Rounder(doubleInput);
    var toReturn = doubleInput;

    if(doubleInput>=50 && doubleInput<75){
        toReturn = "<td style=\"background-color:orange;\">" + toReturn;
    }else if(doubleInput>=75){
        toReturn = "<td style=\"background-color:red;\">" + toReturn;
    } else{
        toReturn = "<td>" + toReturn;
    }

    toReturn = toReturn + "</td>";

    return toReturn;
}