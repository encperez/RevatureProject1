
function validateForm(){
    let title = document.getElementById("requestTitle").value;
    if(title == ""){
        alert("Please put a title for your request!");
        return false;
    }

    let details = document.getElementById("details").value;
    if(details == ""){
        alert("Please put in some details for your request!");
        return false;
    }

    let amount = document.getElementById("amount").value;
    if(isNaN(amount) || amount < 0){
        alert("Please put in  a number that's bigger than 0!");
        return false;
    }
}
