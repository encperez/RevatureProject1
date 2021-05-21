package com.ex.reimbursement;

import com.ex.reimbursement.controllers.*;
import com.ex.reimbursement.daos.MongoDao;
import com.ex.reimbursement.pages.*;
import com.ex.reimbursement.pojos.Employee;
import com.ex.reimbursement.services.EmployeeService;
import com.ex.reimbursement.services.ManagerService;
import com.ex.reimbursement.services.RequestService;
import io.javalin.Javalin;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.ex.reimbursement.util.logging;

import java.util.HashMap;
import java.util.Map;

/**
 * Application that receives requests from an employee that
 *is handled by the managers
 * @author Enrique Perez
 */



public class Application extends AbstractApplication implements logging {
    private Javalin javalin;
    protected HashMap<String, Object> context = new HashMap<>();

    /**
     * Constructor for the application. sets the javalin app and calls init()
     */


    public Application(Javalin javalin){
        this.javalin = javalin;
        init();
    }

    /**
     * Sets up the javalin apps context for future screen in addition makes all the services
     * and controllers.
     */
    private void init(){

        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        Logger javalinLogger = Logger.getLogger("io.javalin.Javalin");
        Logger eclipseLogger = Logger.getLogger("org.eclipse.jetty");
        Logger thymeleafLogger = Logger.getLogger("org.thymeleaf");

        mongoLogger.setLevel(Level.FATAL);
        javalinLogger.setLevel(Level.FATAL);
        eclipseLogger.setLevel(Level.FATAL);
        thymeleafLogger.setLevel(Level.FATAL);

        javalinLogger.info("Info: Log4j2 Usage");
        javalinLogger.debug("Debug: Program has finished successfully");
        javalinLogger.error("Error: Program has errors");


        MongoDao mongoDao = new MongoDao("RevatureReimbursement");

        EmployeeService employeeService = new EmployeeService(mongoDao);
        EmployeeController employeeController = new EmployeeController(employeeService);

        RequestService requestService = new RequestService(mongoDao);
        RequestController requestController = new RequestController(requestService, employeeController);

        EmployeeLoginController employeeLoginController = new EmployeeLoginController(employeeController);
        EmployeeHomeController employeeHomeController = new EmployeeHomeController(employeeController);

        SubmitRequestController submitRequestController = new SubmitRequestController(employeeController, requestController);
        EmployeeViewRequestController employeeViewRequestController = new EmployeeViewRequestController(employeeController, requestController);

        ManagerService managerService = new ManagerService(mongoDao);
        ManagerController managerController = new ManagerController(managerService);

        ManagerLoginController managerLoginController = new ManagerLoginController(managerController);
        ManagerHomeController managerHomeController = new ManagerHomeController(managerController);

        IndexController indexController = new IndexController(employeeController, managerController);

        context.put("MongoDao", mongoDao);

        context.put("EmployeeService", employeeService);
        context.put("ManagerService", managerService);
        context.put("requestService", requestService);

        context.put("IndexController", indexController);

        context.put("EmployeeController", employeeController);
        context.put("EmployeeLoginController", employeeLoginController);
        context.put("EmployeeHomeController", employeeHomeController);
        context.put("SubmitRequestController", submitRequestController);
        context.put("ManagerController", managerController);
        context.put("EmployeeViewRequestController", employeeViewRequestController);
        context.put("ManagerLoginController", managerLoginController);
        context.put("ManagerHomeController", managerHomeController);

        context.put("RequestController", requestController);
        context.put("SubmitRequestController", submitRequestController);

        context.put("JavalinApplication", javalin);

    }
    /**
     * Runs the pages.
     */
    public void run(){
        (new Home(this)).doPage();
        (new EmployeeLogin(this)).doPage();
        (new EmployeeHome(this)).doPage();
        (new EmployeeData(this)).doPage();
        (new SubmitRequest(this)).doPage();
        (new EmployeeViewRequests(this)).doPage();
        (new ManagerLogin(this)).doPage();
        (new ManagerHome(this)).doPage();
    }

    public Map<String, Object> getContext() {
        return this.context;
    }

    @Override
    public void logData() {

    }
}
