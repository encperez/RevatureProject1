package com.ex.reimbursement.controllers;

import com.ex.reimbursement.pojos.Employee;
import com.ex.reimbursement.pojos.Manager;
import com.ex.reimbursement.services.EmployeeService;
import com.ex.reimbursement.services.ManagerService;
import com.ex.reimbursement.util.logging;
import io.javalin.http.Context;
import org.apache.logging.log4j.Level;

public class ManagerController implements logging {
    ManagerService managerService;
    private String currentManager = null;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    public boolean validLogin(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        if(username == null || password == null){
            return false;
        }
        Manager manager = managerService.getDao().getManager(username);
        if(manager == null){
            return false;
        }

        if(password.equals(manager.getPassword())){
            rootLogger.fatal("Manager: " + username + " Has logged in.");
            currentManager = username;
            return true;
        }
        return false;
    }

    public boolean logCheck(Context ctx){
        if (currentManager != null)
            return true;
        else
            return false;
    }

    public void getCurrentManager(Context ctx) {
        Manager manager = managerService.getDao().getManager(currentManager);
        try {
            ctx.json(manager);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
    public void setCurrentEmployee(String current){
        this.currentManager = current;
    }



    @Override
    public void logData() { }


    public String getCurrentEmployeeUsername(){
        return currentManager;
    }

    public void logout(){
        currentManager = null;
    }

}
