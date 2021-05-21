import com.ex.reimbursement.Application;
import com.ex.reimbursement.controllers.*;
import com.ex.reimbursement.daos.MongoDao;
import com.ex.reimbursement.pages.EmployeeHome;
import com.ex.reimbursement.pages.Home;
import com.ex.reimbursement.services.EmployeeService;
import com.ex.reimbursement.services.ManagerService;
import com.ex.reimbursement.services.RequestService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeLoginTest{
    HashMap<String, Object> context = new HashMap<>();
    static Javalin javalin;
    public Application test;
    private Context ctx = mock(Context.class);


    @Before
    public void initTest(){
        javalin = Javalin.create(config -> {
        }).start(6005);
        test = new Application(javalin);

        MongoDao dao = new MongoDao("RevatureReimbursement");
        EmployeeService employeeService = new EmployeeService(dao);
        EmployeeController employeeController = new EmployeeController(employeeService);

        RequestService requestService = new RequestService(dao);
        RequestController requestController = new RequestController(requestService, employeeController);

        EmployeeLoginController employeeLoginController = new EmployeeLoginController(employeeController);
        EmployeeHomeController employeeHomeController = new EmployeeHomeController(employeeController);

        SubmitRequestController submitRequestController = new SubmitRequestController(employeeController, requestController);
        EmployeeViewRequestController employeeViewRequestController = new EmployeeViewRequestController(employeeController, requestController);
        ManagerService managerService = new ManagerService(dao);
        ManagerController managerController = new ManagerController(managerService);

        IndexController indexController = new IndexController(employeeController, managerController);

        ManagerLoginController managerLoginController = new ManagerLoginController(managerController);
        ManagerHomeController managerHomeController = new ManagerHomeController(managerController);



        context.put("MongoDao", dao);

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

        context.put("RequestController", requestController);
        context.put("SubmitRequestController", submitRequestController);

        context.put("ManagerLoginController", managerLoginController);
        context.put("ManagerHomeController", managerHomeController);

    }
    @After
    public void stop(){
        javalin.stop();
    }

    @Test
    public void doPageTest(){

     test.run();
    }

    @Test
    public void validLoginTest(){
        when( ctx.formParam("username") ).thenReturn("user");
        when( ctx.formParam("password") ).thenReturn("password");

        ((EmployeeController) context.get("EmployeeController")).validLogin(ctx);
    }

    @Test
    public void handleLoginTest(){
        when( ctx.formParam("username") ).thenReturn("user");
        when( ctx.formParam("password") ).thenReturn("password");

        ((EmployeeLoginController) (context.get("EmployeeLoginController"))).handleLoginAttempt(ctx);
    }

    @Test
    public void handleLoginTestFail(){
        when( ctx.formParam("username") ).thenReturn("user");
        when( ctx.formParam("password") ).thenReturn("");

        ((EmployeeLoginController) (context.get("EmployeeLoginController"))).handleLoginAttempt(ctx);
    }

    @Test
    public void displayHomeScreenTest(){
        when( ctx.formParam("username") ).thenReturn("user");
        when( ctx.formParam("password") ).thenReturn("password");

        ((EmployeeLoginController) (context.get("EmployeeLoginController"))).handleLoginAttempt(ctx);
        ((EmployeeHomeController) (context.get("EmployeeHomeController"))).displayHomeScreen(ctx);
    }

    @Test
    public void getCurrentEmployeeTest(){
        when( ctx.formParam("username") ).thenReturn("user");
        when( ctx.formParam("password") ).thenReturn("password");

        ((EmployeeLoginController) (context.get("EmployeeLoginController"))).handleLoginAttempt(ctx);
        ((EmployeeHomeController) (context.get("EmployeeHomeController"))).displayHomeScreen(ctx);
        ((EmployeeController) (context.get("EmployeeController"))).getCurrentEmployee(ctx);
    }

    @Test
    public void createReimbursementRequestTest(){
        ((EmployeeController) (context.get("EmployeeController"))).setCurrentEmployee("user");

        when( ctx.formParam("requestTitle") ).thenReturn("Test Title");
        when( ctx.formParam("details") ).thenReturn("These are a test");
        when( ctx.formParam("amount")).thenReturn("3000");

        ((SubmitRequestController) (context.get("SubmitRequestController"))).displaySubmitRequestScreen(ctx);
        ((SubmitRequestController) (context.get("SubmitRequestController"))).submitRequest(ctx);
    }
}