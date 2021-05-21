package com.ex.reimbursement.daos;

import com.ex.reimbursement.pojos.Employee;
import com.ex.reimbursement.pojos.Manager;
import com.ex.reimbursement.pojos.reimbursementRequest;
import com.ex.reimbursement.util.logging;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Connects the MongoDB to the application.
 * @author Enrique Perez
 */


public class MongoDao implements logging {
    MongoCollection<Employee> Employees;
    MongoCollection<reimbursementRequest> Requests;
    MongoCollection<Manager> Managers;

    /**
     * Constructor for MongoDao and also connects to the Database and gets the collections.
     */


    public MongoDao(String dbName){
        try{
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/"+dbName);
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .retryWrites(true)
                    .codecRegistry(codecRegistry)
                    .build();
            MongoClient client = MongoClients.create(settings);
            System.out.println("success");
            MongoDatabase database = client.getDatabase(dbName);
            this.Employees = database.getCollection("Employee", Employee.class);
            this.Managers = database.getCollection("Manager", Manager.class);
            this.Requests = database.getCollection("reimbursementRequest", reimbursementRequest.class);
            MongoIterable<String> list = database.listCollectionNames();
            rootLogger.fatal("Database: " + dbName + " Is connected.");


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Getter/Setters
     */

    public MongoCollection<Employee> getEmployees(){
        return Employees;
    }

    public Employee getEmployee(String current) {
        return Employees.find(eq("username", current)).first();
    }

    public Manager getManager(String current) {
        return Managers.find(eq("username", current)).first();
    }

    /**
     * Updates the employee when functioning.
     */


    public void updateEmployee(String current, String newPassword){
        List<Bson> updates = new ArrayList<>();
        updates.add(Updates.set("username",current));
        updates.add(Updates.set("password",newPassword));

        Employees.findOneAndUpdate(eq("username",current),updates);

    }

    /**
     * Creates new employee Request with defaults in status and approvedBy
     */


    public void newRequest(reimbursementRequest request){

        rootLogger.fatal("New Request made for: " + request.getEmployee());
        Requests.insertOne(request);
    }

    public MongoCollection<reimbursementRequest> getReimbursementRequests(){
        return Requests;
    }

    public FindIterable<reimbursementRequest> getEmployeeReimbursementRequests(String current){

        return Requests.find(eq("employee", current));
    }

    /**
     * Currently returns null but is supposed to return the employees requests
     */


    public List<reimbursementRequest> getEmployeeReimbursementRequestsList(String current){

        List<reimbursementRequest> list = new ArrayList<>();
        try{
            //System.out.println(Requests.find(eq("employee", current)));
            list = Requests.find().into(new ArrayList<>());
        }
        catch(Exception e){
            System.out.println("I'm here");
        }
        return list;

    }

    @Override
    public void logData() {

    }
}
