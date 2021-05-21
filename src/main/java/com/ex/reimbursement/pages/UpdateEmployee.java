package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.EmployeeController;
import com.ex.reimbursement.controllers.EmployeeUpdateController;
import io.javalin.Javalin;

import java.util.HashMap;

public class UpdateEmployee {
    private HashMap<String,Object> context;

    public UpdateEmployee(Application app) {
        context = (HashMap<String, Object>) app.getContext();
    }


    public void doPage() {
        Javalin javalinApp = (Javalin) context.get("JavalinApplication");
        javalinApp.get("/UpdateEmployee", ctx->{
            ((EmployeeUpdateController) (context.get("EmployeeUpdateController"))).displayEmployeeUpdateController(ctx); });
        javalinApp.post("/UpdateEmployee",ctx->{
            ((EmployeeController) context.get("EmployeeController")).UpdateProfile(ctx); });
    }
}
