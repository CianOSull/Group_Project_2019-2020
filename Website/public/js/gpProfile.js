$(document).ready(function() {
    var rootRef = firebase.database().ref().child("GP");
    var insuranceID = localStorage.getItem('IP_ID');
    var gpId = localStorage.getItem('gpId');
    var myDiv = document.getElementById("myDIV");
    
    rootRef.on("child_added", snap => {
        //alert(snap.val());
        var name = snap.child("Name").val();
        var address = snap.child("Address").val();
        var email = snap.child("Email").val();
        var phone = snap.child("Phone").val();
        var gpid = snap.child("GP_id").val();
        var age = snap.child("Age").val();
        
        if (gpid == gpId) { // getting only the objects that have id specific to the GP
        myDiv.innerHTML = "<h2>Your Profile</h2><br><h4>Name:</h4><p>" +name+"</p><h4>Phone:</h4><p>"+phone +"</p><h4>Age:</h4><p>"+age +"</p><h4>Email:</h4><p>"+email +"</p><h4>Address:</h4><p>"+address+"</p>";

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