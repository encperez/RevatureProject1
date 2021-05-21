package com.ex.reimbursement.pages;

import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.IndexController;
import io.javalin.Javalin;

import java.util.HashMap;

public class Home {

    HashMap<String,Object> context;

        public Home(Application app){
            context = (HashMap<String, Object>) app.getContext();
        }

        public void doPage() {
            Javalin javalinApp = (Javalin) context.get("JavalinApplication");
            javalinApp.get("/", ctx -> {
                ((IndexController) (context.get("IndexController"))).displayHomeScreen(ctx);
            });

        }
}
