package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterAuditDaoStubImpl;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoStubImpl;
import com.sg.classroster.dto.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ClassRosterServiceLayerTest {

    private ClassRosterServiceLayer service;

    public ClassRosterServiceLayerTest() throws Exception {

        //ClassRosterDao dao = new ClassRosterDaoStubImpl();
        //ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();

        //service = new ClassRosterServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service =
                ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateStudent() throws Exception{

        Student student = new Student("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-January-2015");
        service.createStudent(student);
    }

    @Test
    public void testCreateStudentDuplicateId() throws Exception{

        //expect our test to throw an exception
        Student student = new Student("0001");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-January-2015");
        //service.createStudent(student);

        try {
            service.createStudent(student);
            fail("Expected ClassRosterDuplicateIdException was not thrown");
            //as soon as exception hits catch it is dead
        } catch (ClassRosterDuplicateIdException e) {
            return;
        }
    }

    @Test
    public void testCreateStudentInvalidData() throws Exception {

        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Smith");
        student.setCohort("Java-January-2015");

        try {
            service.createStudent(student);

            fail("Expected CLassRosterDataValidationException was not thrown");

        }catch (ClassRosterDataValidationException e) {
            return;
        }
    }

    @Test
    public void getAllStudents() throws Exception{

        assertEquals(1,service.getAllStudents().size());

    }

    @Test
    public void testGetStudent() throws Exception {

        Student student = service.getStudent("0001");
        assertNotNull(student);
        student = service.getStudent("9999");
        assertNull(student);
    }

    @Test
    public void testRemoveStudent() throws Exception{

        Student student = service.removeStudent("0001");
        assertNotNull(student);
        student = service.removeStudent("9999");
        assertNull(student);




    }
}