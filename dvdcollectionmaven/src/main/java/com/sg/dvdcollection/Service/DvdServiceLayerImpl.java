package com.sg.dvdcollection.Service;

import com.sg.dvdcollection.dao.DvdAuditDao;
import com.sg.dvdcollection.dao.DvdDao;
import com.sg.dvdcollection.dao.DvdPersistenceException;
import com.sg.dvdcollection.dto.Dvd;

import java.util.List;

public class DvdServiceLayerImpl implements DvdServiceLayer{

    DvdDao dao;
    DvdAuditDao auditDao;

    public DvdServiceLayerImpl(DvdDao dao, DvdAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }


    @Override
    public void createDvd(Dvd dvd) throws DvdDuplicateTitleException, DvdDataValidationException, DvdPersistenceException {

        // First check to see if there is already a student
        // associated with the given student's id
        // If so, we're all done here -
        // throw a ClassRosterDuplicateIdException
        if (dao.getDvd(dvd.getTitle()) != null) {
            throw new DvdDuplicateTitleException(
                    "ERROR: Could not create student.  Student Id "
                            + dvd.getTitle()
                            + " already exists");
        }

        // Now validate all the fields on the given Student object.
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateDvdData(dvd);

        // We passed all our business rules checks so go ahead
        // and persist the Student object
        dao.addDvd(dvd.getTitle(), dvd);

        // The student was successfully created, now write to the audit log
        //auditDao.writeAuditEntry(
                //"Dvd " + dvd.getTitle() + " CREATED.");

    }

    @Override
    public List<Dvd> getAllDvds() throws DvdPersistenceException {
        return dao.getAllDvds();
    }

    @Override
    public Dvd getDvd(String dvdTitle) throws DvdPersistenceException {
        return dao.getDvd(dvdTitle);
    }

    @Override
    public Dvd removeDvd(String dvdTitle) throws DvdPersistenceException {
        Dvd removedDvd = dao.removeDvd(dvdTitle);
        // Write to audit log
        //auditDao.writeAuditEntry("Dvd " + dvdTitle + " REMOVED.");
        return removedDvd;
    }

    @Override
    public Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdPersistenceException {
        return dao.addDvd(dvd.getTitle(),dvd);
    }

    private void validateDvdData(Dvd dvd) throws
            DvdDataValidationException {

        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getDirectorName() == null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getUserRating() == null
                || dvd.getUserRating().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0
                || dvd.getRatingMppa() == null
                || dvd.getRatingMppa().trim().length() == 0
                || dvd.getLocalDateReleaseDate() == null) {

            throw new DvdDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }

}
