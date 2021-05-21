     function postRequests() {
            var request = new XMLHttpRequest();
            request.onreadystatechange = function() {
                if(this.readyState == 4 && this.status == 200){
                    var response = JSON.parse(this.responseText);
                    console.log(response);
                    //document.getElementById("requests").innerText = response;

                      for(var req of response){
                        var status = document.createElement("P");
                        var amount = document.createElement("P");//Create a paragraph
                        var title = document.createElement("P");//date of the object
                        var details = document.createElement("P");//body of the request

                        status.id = "pending";
                        amount.id = "amount";
                        title.id = "title";
                        details.id = "details";
                        title.innerText = "Title: " + req.title;
                        amount.innerText = "Amount: " + req.amount;
                        body.innerText = "Details: " + req.body;
                        status.innerText = "Status: " + req.status;

                        document.getElementById("requests").appendChild(date);
                        document.getElementById("requests").appendChild(pending);
                        document.getElementById("requests").appendChild(amount);
                        document.getElementById("requests").appendChild(body);
                    }
                }
            };
            request.open("POST", "/EmployeeRequests", true);
            request.send();
        }
        postRequests();

