package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.EmployeeController;
import com.ex.reimbursement.controllers.EmployeeLoginController;
import com.ex.reimbursement.controllers.EmployeeViewRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class EmployeeViewRequests {
    HashMap<String,Object> context;

    public EmployeeViewRequests(Application app) {
        context = (HashMap<String, Object>) app.getContext();
    }

    public void doPage() {
        Javalin javalinApp = (Javalin) context.get("JavalinApplication");
        javalinApp.get("/EmployeeViewRequests", ctx -> {
            ((EmployeeViewRequestController) (context.get("EmployeeViewRequestController"))).displayEmployeeViewRequest(ctx);
        });

        javalinApp.post("/EmployeeRequests", ctx ->{
            ((EmployeeController) (context.get("EmployeeController"))).getCurrentEmployeeRequests(ctx);
        });
    }

}
