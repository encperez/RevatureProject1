package com.ex.reimbursement.controllers;

import io.javalin.http.Context;

public class ManagerLoginController {
    ManagerController managerController;

    public ManagerLoginController(ManagerController managerController) {
        this.managerController = managerController;
    }

    public void displayManagerLoginScreen(Context ctx){
        ctx.render("/public/ManagerLogin.html");
    }

    public void handleLoginAttempt(Context ctx){
        boolean validLogin = managerController.validLogin(ctx);

        if (!validLogin){
            System.out.println("Failure");
            ctx.render("/public/ManagerLogin.html");
        }
        else {
            System.out.println("Success");
            ctx.render("/public/ManagerHome.html");
        }
    }

}