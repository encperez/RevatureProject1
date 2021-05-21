package com.ex.reimbursement.controllers;

import io.javalin.http.Context;

public class SubmitRequestController {
    EmployeeController employeeController;
    RequestController requestController;


    public SubmitRequestController(EmployeeController employeeController, RequestController requestController) {
        this.employeeController = employeeController;
        this.requestController = requestController;
    }

    public void displaySubmitRequestScreen(Context ctx){
        boolean logCheck = employeeController.logCheck(ctx);
        if(logCheck)
            ctx.render("/public/SubmitRequest.html");
        else
            ctx.redirect("/EmployeeLogin");
    }

    public void submitRequest(Context ctx){
        requestController.CreateRequest(ctx);
        ctx.render("/public/SubmitRequest.html");
    }
}
