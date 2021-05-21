package com.ex.reimbursement.controllers;

import io.javalin.http.Context;

public class EmployeeUpdateController {
    EmployeeController employeeController;

    public EmployeeUpdateController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void displayEmployeeUpdateController(Context ctx){
        boolean validLogin = employeeController.validLogin(ctx);

        if (!validLogin){
            System.out.println("Failure");
            ctx.render("/public/EmployeeLogin.html");
        }
        else {
            System.out.println("Success");
            ctx.render("/public/EmployeeUpdateProfile.html");
        }
    }
}
