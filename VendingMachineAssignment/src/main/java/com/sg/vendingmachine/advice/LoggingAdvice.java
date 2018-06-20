package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineItemUnavailableException;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {

    VendingMachineAuditDao auditDao;

    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    //join point holds information about the method that triggered the call
    //Which method is this?
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditLog(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    //join point holds information about the method that triggered the call
    //Which method is this?
    //print item of most importance
    //code contained in the advice will run only after the pointcut "purchase item" has thrown an exception
    public void logAfterThrowing(JoinPoint error, Throwable ex){
        Object[] args = error.getArgs();
        //get execution
        String auditEntry = error.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditLog(auditEntry + " Error Message: " + ex.getMessage());
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }

    }
}