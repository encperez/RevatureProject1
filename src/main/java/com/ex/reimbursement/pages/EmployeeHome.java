package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.EmployeeHomeController;
import com.ex.reimbursement.controllers.IndexController;
import io.javalin.Javalin;

import java.util.HashMap;

public class EmployeeHome {
    HashMap<String,Object> context;

    public EmployeeHome(Application app) {
        context = (HashMap<String, Object>) app.getContext();
    }

    public void doPage() {
        Javalin javalinApp = (Javalin) context.get("JavalinApplication");
        javalinApp.get("/EmployeeHome", ctx -> {
            ((EmployeeHomeController) (context.get("EmployeeHomeController"))).displayHomeScreen(ctx);
        });

    }
}
