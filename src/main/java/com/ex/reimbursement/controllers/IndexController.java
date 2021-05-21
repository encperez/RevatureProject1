package com.ex.reimbursement.controllers;

import io.javalin.http.Context;

public class IndexController {
    private EmployeeController employeeController;
    private ManagerController managerController;

    public IndexController(EmployeeController employeeController, ManagerController managerController) {
        this.employeeController = employeeController;
        this.managerController = managerController;
    }

    public void displayHomeScreen(Context ctx){

        employeeController.logout();
        managerController.logout();

        ctx.render("/public/index.html");
    }
}
