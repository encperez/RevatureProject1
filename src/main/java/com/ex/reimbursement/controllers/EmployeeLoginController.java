package com.ex.reimbursement.controllers;

import io.javalin.http.Context;

public class EmployeeLoginController {
    EmployeeController employeeController;

    public EmployeeLoginController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void displayEmployeeLoginScreen(Context ctx){
        ctx.render("/public/EmployeeLogin.html");
    }

    public void handleLoginAttempt(Context ctx){
        boolean validLogin = employeeController.validLogin(ctx);

        if (!validLogin){
            System.out.println("Failure");
            ctx.render("/public/EmployeeLogin.html");
        }
        else {
            System.out.println("Success");
            ctx.render("/public/EmployeeHome.html");
        }
    }

}
