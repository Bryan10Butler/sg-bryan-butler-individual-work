package com.sg.dvdcollection.dao;

public interface DvdAuditDao {

    public void writeAuditEntry(String entry) throws DvdPersistenceException;

}
