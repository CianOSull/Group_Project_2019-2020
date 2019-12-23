var exportBtn = document.getElementById("export");
var gpId = document.getElementById("gpId");
exportBtn.addEventListener("click", exportClick);


function exportClick(){
    window.alert("Exporting Patient data to Excel File...")
    var rootRef = firebase.database().ref().child("Patients");
    var csvContent = "data:text/csv;charset=utf-8,";
    csvContent+="Name, Email, GP_id, PID\r\n";
    var num = 1;
    rootRef.on("child_added", snap => {
        //alert(snap.val());
        var name = snap.child("Name").val();
        var email = snap.child("Email").val();
        var gpid = snap.child("GP_id").val();
        var pid = snap.child("PID").val();
        var rowlist = [[name, email, gpid, pid]];
        if (gpId.value == gpid) {
            csvContent += name+","+email+","+gpid+","+pid+"\r\n";

        }
        if (num == 4) {
    var encodedUri = encodeURI(csvContent);
    var link = document.createElement("a");
    link.setAttribute("href", encodedUri);
    link.setAttribute("download", "my_data.csv");
    document.body.appendChild(link); // Required for FF

    link.click(); // This will download the data file named "my_data.csv".
    }
        num++;
    });
    
   


}
               
