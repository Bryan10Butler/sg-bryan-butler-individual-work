package com.sg.vendingmachine.dao;

public interface VendingMachineAuditDao {

    //write entries to the log file
    public void writeAuditLog(String entry)
            throws VendingMachinePersistenceException;

}
