
function postUser() {
    var request = new XMLHttpRequest();
    request.onload = function() {
        if(this.readyState == 4 && this.status == 200){
          var response = JSON.parse(this.responseText);
             document.getElementById("Manager").innerText = "Hello, " + response.name;
        }
    };
        request.open("POST", "/Manager", true)
        request.send();

}