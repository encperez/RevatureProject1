package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.EmployeeHomeController;
import com.ex.reimbursement.controllers.IndexController;
import com.ex.reimbursement.controllers.ManagerHomeController;
import io.javalin.Javalin;

import java.util.HashMap;

public class ManagerHome {
    HashMap<String,Object> context;

    public ManagerHome(Application app) {
        context = (HashMap<String, Object>) app.getContext();
    }

    public void doPage() {
        Javalin javalinApp = (Javalin) context.get("JavalinApplication");
        javalinApp.get("/ManagerHome", ctx -> {
            ((ManagerHomeController) (context.get("ManagerHomeController"))).displayHomeScreen(ctx);
        });

    }
}