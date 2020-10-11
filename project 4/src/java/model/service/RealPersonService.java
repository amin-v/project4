package model.service;

import commons.exceptions.BusinessException;
import configController.JPA;
import model.entity.RealPerson;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static commons.ExceptionMessages.REPETETIVE_NATIONAL_CODE;
import static commons.ExceptionMessages.RealCustomer_Entity_NOT_FOUND;

public class RealPersonService {
    private static RealPersonService realPersonService = new RealPersonService();

    private RealPersonService() {
    }

    public static RealPersonService getInstance() {
        return realPersonService;
    }

    public RealPerson persist(RealPerson person) throws Exception {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
            entityManager.close();
            Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer created.");
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer dose not created.");
            throw new BusinessException(REPETETIVE_NATIONAL_CODE);
        }
        return person;
    }

    public RealPerson update(RealPerson realPerson) throws Exception {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
            RealPerson hibernatePerson = entityManager.find(RealPerson.class, realPerson.getRealPersonId());
            hibernatePerson.setBirthDate(realPerson.getBirthDate());
            hibernatePerson.setFamily(realPerson.getFamily());
            hibernatePerson.setFatherName(realPerson.getFatherName());
            hibernatePerson.setNationalCode(realPerson.getNationalCode());
            entityManager.getTransaction().begin();
            entityManager.persist(hibernatePerson);
            entityManager.getTransaction().commit();
            Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer updated.");
            entityManager.close();
            return hibernatePerson;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer dose not updated.");
            e.printStackTrace();
            throw new BusinessException(REPETETIVE_NATIONAL_CODE);
        }
    }


    public RealPerson delete(RealPerson realPerson) throws Exception {
        EntityManager entityManager = JPA.getEntitymanager();
        entityManager.getTransaction().begin();
        RealPerson hibernatePerson = entityManager.find(RealPerson.class, realPerson.getRealPersonId());
        entityManager.remove(hibernatePerson);
        entityManager.getTransaction().commit();
        entityManager.close();
        return hibernatePerson;
    }

    public List<RealPerson> findAll() throws Exception {
        EntityManager entityManager = JPA.getEntitymanager();
        List<RealPerson> realPersonList = entityManager.createQuery("select  person  from realPerson person").getResultList();
        entityManager.close();
        return realPersonList;
    }


    public List<RealPerson> findById(Long id) throws Exception {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
           List<RealPerson> realPersonList = entityManager.createQuery("select  person from realPerson person where  person.id=:id").setParameter("id",id).getResultList();
            Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer retrieved.");
            entityManager.close();
            return realPersonList;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer dose not retrieved.");
            e.printStackTrace();
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        }
    }

    public List<RealPerson> findByName(String name) throws Exception {
        EntityManager entityManager = JPA.getEntitymanager();
        List<RealPerson> realPersonList = entityManager.createQuery("select  person  from realPerson person where person.name=:name").setParameter("name", name).getResultList();
        Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer retrieved.");
        entityManager.close();
        return realPersonList;
    }

    public List<RealPerson> findByFamily(String family) throws Exception {
        EntityManager entityManager = JPA.getEntitymanager();
        List<RealPerson> realPersonList = entityManager.createQuery("select  person  from realPerson person where person.family=:family").setParameter("family", family).getResultList();
        Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer retrieved.");
        entityManager.close();
        return realPersonList;
    }

    public List<RealPerson> findByNationalCode(String nationalCode) throws Exception {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
            List<RealPerson> realPersonList=entityManager.createQuery("select  person  from realPerson person where person.nationalCode=:nationalCode").setParameter("nationalCode", nationalCode).getResultList();
            entityManager.close();
            Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer retrieved.");
            return realPersonList;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(RealPerson.class)).info("real customer dose not retrieved.");
            e.printStackTrace();
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        }
    }

    public List<RealPerson> conditionalFind(String where, Map<String,Object> parameterList) throws Exception {
        EntityManager entityManager=JPA.getEntitymanager();
        Query query=entityManager.createQuery("select person from realPerson person "+where);
        Set<String> keys=parameterList.keySet();
        for (String key : keys) {
            query.setParameter(key,parameterList.get(key));
        }
        List<RealPerson> realPersonList=query.getResultList();
        return realPersonList;
    }

}
