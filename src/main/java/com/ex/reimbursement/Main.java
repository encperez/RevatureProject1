package com.ex.reimbursement;

import io.javalin.Javalin;
import org.apache.log4j.BasicConfigurator;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        Javalin javalinApp = Javalin.create(config -> {config.addStaticFiles("/public");})
                .start(7777);
        Application app = new Application(javalinApp);
        app.run();
        //app.get("/", ctx -> ctx.result("Hello World"));

    }
}
