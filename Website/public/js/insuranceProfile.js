$(document).ready(function() {
    var rootRef = firebase.database().ref().child("Insurance-Providers");
    var insuranceID = localStorage.getItem('IP_ID');
    var myDiv = document.getElementById("myDIV");
    
    rootRef.on("child_added", snap => {
        //alert(snap.val());
        var company_name = snap.child("Company_Name").val();
        var address = snap.child("Address").val();
        var email = snap.child("Email").val();
        var phone = snap.child("Phone").val();
        var ipid = snap.child("IP_ID").val();
        if (ipid == insuranceID) { // getting only the objects that have id specific to the GP
        myDiv.innerHTML = "<h2>Company Profile</h2><br><h4>Company Name:</h4><p>" +company_name+"</p><h4>Address:</h4><p>"+address +"</p><h4>Email:</h4><p>"+email+"</p><h4>Phone:</h4><p>"+phone+"</p>";

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