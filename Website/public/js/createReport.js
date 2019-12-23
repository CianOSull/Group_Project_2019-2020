$(document).ready(function() {
    var rootRef = firebase.database().ref().child("Users");
    //var userGpId = localStorage.getItem('gpId');
    var userIpId = localStorage.getItem('IP_ID');
    var patientsCount = 0;
    var breastAboveFifty = 0;
    var diabetesAboveFifty = 0;
    var heartAboveFifty = 0;
    var breastBelowFifty = 0;
    var diabetesBelowFifty = 0;
    var heartBelowFifty = 0;
    var myDiv = document.getElementById("myDIV");
    var breastScore = 0;
    var diabetesScore = 0;
    var heartScore = 0;
    rootRef.on("child_added", snap => {
        //alert(snap.val());
        var name = snap.child("Fname").val();
        var BCRes = snap.child("BreastCancerResult").val();
        var DRes = snap.child("DiabetesOutPut").val();
        var HRes = snap.child("HeartValue").val();
        var ipid = snap.child("IP_ID").val();
        var email = snap.child("Email").val();
        
        if (ipid == userIpId) { // getting only the objects that have id specific to the GP
            patientsCount++;
            breastScore = breastScore+BCRes;
            
            diabetesScore = diabetesScore+DRes;
            heartScore = heartScore+HRes;

            if (BCRes > 0.5) {
                breastAboveFifty++;
            }if (DRes > 0.5) {
                diabetesAboveFifty++;

            }if (HRes > 0.5) {
                heartAboveFifty++;
        
            }if (BCRes < 0.5) {
                breastBelowFifty++;
                
            }if (DRes < 0.5) {
                diabetesBelowFifty++;
                
            }if (HRes < 0.5) {
                heartBelowFifty++;
                
            }
        }
myDiv.innerHTML = "<tr><td>Risk</td><td>No. of Patients</td><td>No. of patients &gt; 50%</td><td>No. of patients &lt; 50%</td><td>Average Score</td></tr><tr><td>Breast Cancer</td><td>"+ patientsCount + "</td><td>" + breastAboveFifty +"</td><td>" + breastBelowFifty +"</td><td>" + (breastScore/patientsCount) +"</td></tr><tr><td>Diabetes</td><td>"+ patientsCount + "</td><td>" + diabetesAboveFifty +"</td><td>" + diabetesBelowFifty +"</td><td>" + (diabetesScore/patientsCount) +"</td></tr><tr><td>Heart</td><td>"+ patientsCount + "</td><td>" + heartAboveFifty +"</td><td>" + heartBelowFifty +"</td><td>" + (heartScore/patientsCount) +"</td></tr>";

        
    });
                       
  

});
function Rounder(doubleInput){
    toReturn = doubleInput*100;
    toReturn = toReturn.toFixed(0);
    return toReturn;
}
