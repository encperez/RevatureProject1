package com.ex.reimbursement.services;

import com.ex.reimbursement.daos.MongoDao;

public class ManagerService {
    private MongoDao dao;

    public ManagerService(MongoDao dao){ this.dao = dao; }

    public MongoDao getDao() {
        return dao;
    }
}
