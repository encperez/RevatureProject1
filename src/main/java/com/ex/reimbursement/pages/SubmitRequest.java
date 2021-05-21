package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.EmployeeLoginController;
import com.ex.reimbursement.controllers.SubmitRequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class SubmitRequest {
    HashMap<String,Object> context;

    public SubmitRequest(Application app) {
        this.context = (HashMap<String, Object>) app.getContext();
    }

    public void doPage() {
        Javalin javalinApp = (Javalin) context.get("JavalinApplication");

        javalinApp.get("/SubmitRequest", ctx -> {
            ((SubmitRequestController) (context.get("SubmitRequestController"))).displaySubmitRequestScreen(ctx);
        });
        javalinApp.post("/SubmitRequest", ctx ->{
            ((SubmitRequestController) (context.get("SubmitRequestController"))).submitRequest(ctx);
        });

    }
}
