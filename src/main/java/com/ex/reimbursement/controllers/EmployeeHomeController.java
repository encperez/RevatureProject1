package com.ex.reimbursement.controllers;

import io.javalin.http.Context;

/**
 *  handle the display for the employee home page
 */
public class EmployeeHomeController {

    EmployeeController employeeController;

    /**
     * Constructor for EmployeeHome
     * */
    public EmployeeHomeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    /**
     * Displays employee home if employee is logged in.
     */

    public void displayHomeScreen(Context ctx){
        boolean logCheck = employeeController.logCheck(ctx);
        if(logCheck)
            ctx.render("/public/EmployeeHome.html");
        else
            ctx.redirect("/EmployeeLogin");
    }
}
