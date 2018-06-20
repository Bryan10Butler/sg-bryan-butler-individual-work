package service;

import dao.EmailContactsDao;
import dao.EmailContactsDaoInMemoryImpl;

public class EmailContactsServiceImpl implements EmailContactsService{

    //By adding this property we are stating
    //that the service layer is dependent on the dao
    private EmailContactsDao dao;

    // This Constructor allows any class that creates an instance of the service
    // to pass in the values for the service dependencies
    // This allows for dependency injection
    public EmailContactsServiceImpl (EmailContactsDao dao) {
        this.dao = dao;
    }
}
