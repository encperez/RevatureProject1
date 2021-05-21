package com.ex.reimbursement.controllers;

import io.javalin.http.Context;

public class ManagerHomeController {
    ManagerController managerController;

    public ManagerHomeController(ManagerController managerController) {
        this.managerController = managerController;
    }

    public void displayHomeScreen(Context ctx){
        boolean logCheck = managerController.logCheck(ctx);
        if(logCheck)
            ctx.render("/public/ManagerHome.html");
        else
            ctx.redirect("/ManagerLogin");
    }
}