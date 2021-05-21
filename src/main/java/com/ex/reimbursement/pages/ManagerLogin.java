package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.*;
import io.javalin.Javalin;

import java.util.HashMap;

public class ManagerLogin {

    HashMap<String,Object> context;


    public ManagerLogin(Application app){
        context = (HashMap<String, Object>) app.getContext();
    }

    public void doPage() {
        Javalin javalinApp = (Javalin) context.get("JavalinApplication");

        javalinApp.get("/ManagerLogin", ctx -> {
            ((ManagerLoginController) (context.get("ManagerLoginController"))).displayManagerLoginScreen(ctx);
        });
        javalinApp.post("/ManagerLogin", ctx ->{
            ((ManagerLoginController) (context.get("ManagerLoginController"))).handleLoginAttempt(ctx);
        });

        javalinApp.post("/Manager", ctx -> {
            ((ManagerController) (context.get("ManagerController"))).getCurrentManager(ctx);
        });

    }
}
