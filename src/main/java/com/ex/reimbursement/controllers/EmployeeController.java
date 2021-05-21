package com.ex.reimbursement.controllers;

import com.ex.reimbursement.pojos.Employee;
import com.ex.reimbursement.pojos.reimbursementRequest;
import com.ex.reimbursement.services.EmployeeService;
import com.ex.reimbursement.util.logging;
import io.javalin.http.Context;



public class EmployeeController implements logging {
    public EmployeeService employeeService;
    private String currentEmployee;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public boolean validLogin(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        if(username == null || password == null){
            return false;
        }
        Employee employee = employeeService.getDao().getEmployee(username);
        if(employee == null){
            return false;
        }

        if(password.equals(employee.getPassword())){
            rootLogger.fatal("Username: " + username + " Has logged in.");
            currentEmployee = username;
            return true;
        }
        return false;
    }

    public boolean logCheck(Context ctx){
        if (currentEmployee != null)
            return true;
        else
            return false;
    }

    public void getCurrentEmployee(Context ctx) {
        Employee employee = employeeService.getDao().getEmployee(currentEmployee);
        try {
            ctx.json(employee);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void UpdateProfile(Context ctx){
        Employee employee = employeeService.getDao().getEmployee(currentEmployee);
        String newPassword = ctx.formParam("password");
        employeeService.getDao().updateEmployee(currentEmployee, newPassword);
        ctx.redirect("/EmployeeUpdate");
    }

    public void setCurrentEmployee(String current){
        this.currentEmployee = current;
    }


    public void getCurrentEmployeeRequests(Context ctx){
       // FindIterable<reimbursementRequest> requests = employeeService.getDao().getEmployeeReimbursementRequests(currentEmployee);
   //     JSONArray response = new JSONArray();
     //   System.out.println(requests);
      //  for (reimbursementRequest request : requests){
        //    System.out.println(request.toString());
       //     response.put(request);
    //    }
        System.out.println(currentEmployee);
        System.out.println(employeeService.getDao().getEmployeeReimbursementRequestsList(currentEmployee));
        ctx.json(employeeService.getDao().getEmployeeReimbursementRequestsList(currentEmployee));

    }

    public void UpdateEmployee(Context ctx){

    }

    @Override
    public void logData() { }


    public String getCurrentEmployeeUsername(){
        return currentEmployee;
    }

    public void logout(){
        currentEmployee = null;
    }
}
