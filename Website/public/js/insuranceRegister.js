//window.alert("Sup");
    var rootRef = firebase.database().ref().child("Insurance-Providers");
    var newID = 1;
    var IP_ID;
    var numberOfChildren = 0;
    rootRef.on("child_added", snap => {
        var ipid = snap.child("IP_ID").val();
        
        if (ipid == newID) {
            newID = ipid + 1; // keep incramenting to get a new id 
            numberOfChildren++;
            IP_ID = numberOfChildren;
        }
        else if (ipid != newID){
            numberOfChildren++;
            IP_ID = numberOfChildren;
        }
        
    })

///////////////////////////
var companyName = document.getElementById("companyName");
var email = document.getElementById("email");
var phone = document.getElementById("phoneNumber");
var address = document.getElementById("address");
var regBtn = document.getElementById("btnRegister");

regBtn.addEventListener("click", submitClick);

function submitClick() {
    IP_ID++;
    var database = firebase.database().ref();
    database.child("Insurance-Providers").child("IP"+IP_ID).child("Company_Name").set(companyName.value);
    database.child("Insurance-Providers").child("IP"+IP_ID).child("Email").set(email.value);
    database.child("Insurance-Providers").child("IP"+IP_ID).child("Phone").set(phone.value);
    database.child("Insurance-Providers").child("IP"+IP_ID).child("Address").set(address.value);
    database.child("Insurance-Providers").child("IP"+IP_ID).child("IP_ID").set(IP_ID);
    window.alert("Your details have been saved to the database " + companyName.value);
    localStorage.setItem('IP_ID', IP_ID); // saving user's gp id to local storage
    window.open('loggedIn.html','_self',false);
}
