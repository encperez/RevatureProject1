package com.ex.reimbursement.services;

import com.ex.reimbursement.daos.MongoDao;
import com.ex.reimbursement.pojos.Employee;
import com.mongodb.client.MongoCollection;

public class EmployeeService {
    private MongoDao dao;

    public EmployeeService(MongoDao dao){
        this.dao = dao;
        dao.getEmployees();
    }

    public MongoCollection<Employee> getEmployees(){
        return dao.getEmployees();
    }

    public MongoDao getDao(){
        return dao;
    }
}
