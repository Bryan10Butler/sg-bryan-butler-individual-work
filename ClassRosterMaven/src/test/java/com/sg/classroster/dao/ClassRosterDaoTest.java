package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClassRosterDaoTest {

    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    @Before

    //out setup ran "known good state
    public void setUp() throws Exception {

        List<Student> studentList = dao.getAllStudents();
        for (Student student : studentList) {
            dao.removeStudent(student.getStudentId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddGetStudent() throws Exception {

        //create new student
        Student student = new Student("0001");
        student.setFirstName("Joe");
        student.setLastName("Smith");
        student.setCohort("Java-May-2000");

        //passing student into DAO
        dao.addStudent(student.getStudentId(), student);

        //declare another student object
        //associate with student id 0001
        //got the student back out from the DAO
        Student fromDao = dao.getStudent(student.getStudentId());

        //assert that everything comes out correctly
        assertEquals(student, fromDao);
    }

    @Test
    public void testGetAllStudents() throws Exception {

        //create student 1
        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");

        //add student to dao
        dao.addStudent(student1.getStudentId(),student1);

        //create student 2
        Student student2 = new Student("0002");
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setCohort(".NET-May-2000");

        //add student to dao
        dao.addStudent(student2.getStudentId(),student2);

        //ask dao for both students
        //size tells you the size
        assertEquals(2,dao.getAllStudents().size());
    }

    @Test
    public void testRemoveStudent() throws Exception{

        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");

        //add student to dao
        dao.addStudent(student1.getStudentId(),student1);

        //create student 2
        Student student2 = new Student("0002");
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setCohort(".NET-May-2000");

        //add student to dao
        dao.addStudent(student2.getStudentId(),student2);


        dao.removeStudent(student1.getStudentId());
        assertEquals(1,dao.getAllStudents().size());
        //assert whatever answer we get back is null
        //because we are removing student 1
        assertNull(dao.getStudent(student1.getStudentId()));

        dao.removeStudent(student2.getStudentId());
        //removed all students
        assertEquals(0,dao.getAllStudents().size());
        //assert whatever answer we get back is null
        //because we are removing student 1
        assertNull(dao.getStudent(student2.getStudentId()));

    }
}