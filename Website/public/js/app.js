//window.alert("Sup");
    var rootRef = firebase.database().ref().child("GP");
    var newID = 1;
    var GP_id;
    var numberOfChildren = 0;
    rootRef.on("child_added", snap => {
        var gpid = snap.child("GP_id").val();
        
        if (gpid == newID) {
            newID = gpid + 1; // keep incramenting to get a new id 
            numberOfChildren++;
            GP_id = numberOfChildren;
        }
        else if (gpid != newID){
            numberOfChildren++;
            GP_id = numberOfChildren;
        }
        
    })

///////////////////////////
var fName = document.getElementById("firstName");
var lName = document.getElementById("lastName");
var email = document.getElementById("email");
var phone = document.getElementById("phoneNumber");
var address = document.getElementById("address");
var role = document.getElementById("occupation");
var regBtn = document.getElementById("btnRegister");
var age = document.getElementById("age");

regBtn.addEventListener("click", submitClick);

function submitClick() {
    GP_id++;
    var database = firebase.database().ref();
    database.child("GP").child("gp"+GP_id).child("Name").set(fName.value + " " + lName.value);
    database.child("GP").child("gp"+GP_id).child("Email").set(email.value);
    database.child("GP").child("gp"+GP_id).child("Phone").set(phone.value);
    database.child("GP").child("gp"+GP_id).child("Address").set(address.value);
    database.child("GP").child("gp"+GP_id).child("Age").set(age.value);
    database.child("GP").child("gp"+GP_id).child("GP_id").set(GP_id);
    database.child("GP").child("gp"+GP_id).child("Role").set(role.value);
    window.alert("Your details have been saved to the database " + fName.value);
    localStorage.setItem('gpId', GP_id); // saving user's gp id to local storage
    window.open('loggedIn.html','_self',false);
}