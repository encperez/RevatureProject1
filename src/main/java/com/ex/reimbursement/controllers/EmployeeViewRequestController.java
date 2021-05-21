package com.ex.reimbursement.controllers;

import io.javalin.http.Context;

public class EmployeeViewRequestController {
    EmployeeController employeeController;
    RequestController requestController;

    public EmployeeViewRequestController(EmployeeController employeeController, RequestController requestController) {
        this.employeeController = employeeController;
        this.requestController = requestController;
    }

    public void displayEmployeeViewRequest(Context ctx){
        boolean logCheck = employeeController.logCheck(ctx);
        if(logCheck) {
            ctx.render("/public/EmployeeViewRequests.html");
        }
        else
            ctx.redirect("/EmployeeLogin");
    }
}
