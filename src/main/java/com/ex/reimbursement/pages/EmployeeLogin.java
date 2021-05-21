package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.EmployeeController;
import com.ex.reimbursement.controllers.EmployeeLoginController;
import com.ex.reimbursement.controllers.IndexController;
import io.javalin.Javalin;

import java.util.HashMap;

public class EmployeeLogin {

    HashMap<String,Object> context;


    public EmployeeLogin(Application app){
        context = (HashMap<String, Object>) app.getContext();
    }

    public void doPage() {
        Javalin javalinApp = (Javalin) context.get("JavalinApplication");

        javalinApp.get("/EmployeeLogin", ctx -> {
            ((EmployeeLoginController) (context.get("EmployeeLoginController"))).displayEmployeeLoginScreen(ctx);
        });
        javalinApp.post("/EmployeeLogin", ctx ->{
            ((EmployeeLoginController) (context.get("EmployeeLoginController"))).handleLoginAttempt(ctx);
        });

    }
}
