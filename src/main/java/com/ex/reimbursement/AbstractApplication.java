package com.ex.reimbursement;

import io.javalin.Javalin;

import java.util.Map;

public abstract class AbstractApplication {
    protected Map<String, Object> context;
    private Javalin javalin;
}
