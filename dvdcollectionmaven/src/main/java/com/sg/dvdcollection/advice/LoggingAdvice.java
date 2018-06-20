package com.sg.dvdcollection.advice;

import com.sg.dvdcollection.dao.DvdAuditDao;
import com.sg.dvdcollection.dao.DvdPersistenceException;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {

    DvdAuditDao auditDao;

    public LoggingAdvice(DvdAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (DvdPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
