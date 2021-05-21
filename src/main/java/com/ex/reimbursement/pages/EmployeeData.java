package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.EmployeeController;
import com.ex.reimbursement.controllers.IndexController;
import io.javalin.Javalin;

import java.util.HashMap;

public class EmployeeData {

    HashMap<String, Object> context;

    public EmployeeData(Application app) {
        context = (HashMap<String, Object>) app.getContext();
    }

    public void doPage() {
        Javalin javalinApp = (Javalin) context.get("JavalinApplication");
        javalinApp.post("/Employee", ctx -> {
            ((EmployeeController) (context.get("EmployeeController"))).getCurrentEmployee(ctx);
        });

    }
}