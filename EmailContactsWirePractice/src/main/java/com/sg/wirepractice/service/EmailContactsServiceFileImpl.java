package com.sg.wirepractice.service;

import com.sg.wirepractice.dao.EmailContactsAuditDao;
import com.sg.wirepractice.dao.EmailContactsDao;

public class EmailContactsServiceFileImpl implements EmailContactsService {

    //properties
    EmailContactsDao myDao;
    EmailContactsAuditDao auditDao;

    //constructor
    public EmailContactsServiceFileImpl (EmailContactsDao myDao, EmailContactsAuditDao auditDao) {
        this.myDao = myDao;
        this.auditDao = auditDao;
    }

    public void test() {


    }

}
