package model.service;

import commons.exceptions.BusinessException;
import configController.JPA;
import model.entity.GrantCondition;
import model.entity.LoanType;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

import static commons.ExceptionMessages.LoanTYPE_entity_exist;
import static commons.ExceptionMessages.Loan_Type_NotFound;

public class LoanTypeService {
    public static LoanTypeService loanTypeService = new LoanTypeService();

    private LoanTypeService() {
    }

    public static LoanTypeService getInstance() {
        return loanTypeService;
    }

    public LoanType persist(LoanType loanType) throws HibernateException, BusinessException {
        try {
            //todo kamel shavad
            EntityManager entityManager = JPA.getEntitymanager();
            entityManager.getTransaction().begin();
            entityManager.persist(loanType);
            entityManager.getTransaction().commit();
            entityManager.close();
            Logger.getLogger(String.valueOf(GrantCondition.class)).info("Loan Type created.");

        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(GrantCondition.class)).info("Loan Type dose not created.");
            throw new BusinessException(Loan_Type_NotFound);
        }
        return loanType;
    }

    public List<LoanType> findAll() throws HibernateException, BusinessException {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
            List<LoanType> loanTypeList = entityManager.createQuery("select person from loanType person").getResultList();
            entityManager.close();
            Logger.getLogger(String.valueOf(LoanType.class)).info("loan type find.");
            return loanTypeList;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(GrantCondition.class)).info("Loan Type dose not created.");
            throw new BusinessException(Loan_Type_NotFound);
        }
    }

    public List<LoanType> findById(long loanTypeId) throws HibernateException, BusinessException {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
            List<LoanType> loanTypeList =entityManager.createQuery("select  person from loanType person where person.loanTypeId=:LoanType_ID ").setParameter("LoanType_ID", loanTypeId).getResultList();
            entityManager.close();
            Logger.getLogger(String.valueOf(LoanType.class)).info("loan type find.");
            return loanTypeList;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(GrantCondition.class)).info("Loan Type dose not created.");
            throw new BusinessException(LoanTYPE_entity_exist);
        }
    }

//check.........................

    public static LoanType validate(String loanName, String interestRate) throws BusinessException {
        if (loanName.equals("") || interestRate.equals("")) {
            throw new BusinessException("loan type is not compeleted");
        } else {
            LoanType loanType = new LoanType(loanName, interestRate);
            return loanType;
        }
    }

}