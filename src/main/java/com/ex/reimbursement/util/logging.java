package com.ex.reimbursement.util;


import org.apache.logging.log4j.*;

public interface logging {

    org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();

    void logData();
}
