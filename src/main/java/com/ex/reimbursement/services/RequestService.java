package com.ex.reimbursement.services;

import com.ex.reimbursement.daos.MongoDao;
import com.ex.reimbursement.pojos.reimbursementRequest;

public class RequestService {
    private MongoDao dao;

    public RequestService(MongoDao dao) {
        this.dao = dao;
        dao.getReimbursementRequests();
    }

    public void newRequest(reimbursementRequest request){
        dao.newRequest(request);
    }

}
