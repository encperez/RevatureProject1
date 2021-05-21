package com.ex.reimbursement.controllers;

import com.ex.reimbursement.pojos.Employee;
import com.ex.reimbursement.pojos.reimbursementRequest;
import com.ex.reimbursement.services.EmployeeService;
import com.ex.reimbursement.services.RequestService;
import io.javalin.http.Context;

public class RequestController {
    RequestService requestService;
    EmployeeController employeeController;

    public RequestController(RequestService requestService, EmployeeController employeeController) {
        this.requestService = requestService;
        this.employeeController = employeeController;
    }

    public void CreateRequest(Context ctx){
        String username = employeeController.getCurrentEmployeeUsername();
        String title  = ctx.formParam("requestTitle");
        String details = ctx.formParam("details");
        String amountString  = ctx.formParam("amount");

        reimbursementRequest request = new reimbursementRequest(username, title, details, amountString, "pending", "Not yet approved");
        requestService.newRequest(request);
    }
}
