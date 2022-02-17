package com.student.crudactivity.dao;

import com.student.crudactivity.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    public StudentDAOImpl(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    public List<Student> findAll(){

    Session currentSession =entityManager.unwrap(Session.class);

    Query<Student> theQuery=currentSession.createQuery("from Student",Student.class);

    List<Student> students=theQuery.getResultList();

    return students;

    }
    public Student findById(int id){

        Session currentSession =entityManager.unwrap(Session.class);

        Student theStudent=currentSession.get(Student.class,id);

        return theStudent;
    }

    public void save(Student student){
        Session currentSession =entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(student);

    }

    public void deleteById(int theId){

        Session currentSession =entityManager.unwrap(Session.class);
        Query theQuery=currentSession.createQuery("delete from Student where id= :StudentId");
        theQuery.setParameter("StudentId",theId);
        theQuery.executeUpdate();

    }
}
