package model.service;

import commons.exceptions.BusinessException;
import configController.JPA;
import model.entity.GrantCondition;
import model.entity.LoanType;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

import static commons.ExceptionMessages.GrandCondition_NotFound;
import static commons.ExceptionMessages.THE_CONDITIONS_IS_NOT_TRUE;

public class GrantConditionalService {
    private static  GrantConditionalService grantConditionalService = new GrantConditionalService();

    private GrantConditionalService() {
    }

    public static GrantConditionalService getInstance() {
        return grantConditionalService;
    }

    public GrantCondition persist(LoanType loanType,GrantCondition grantCondition) throws BusinessException {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
           entityManager.getTransaction().begin();
//            LoanTypeService.getInstance().persist(loanType);
//            for (GrantCondition grantCondition: grantConditions){
//                grantCondition.getLoanType().setLoanTypeId(loanType.getLoanTypeId());
//            }
            grantCondition.setLoanType(loanType);
           entityManager.persist(grantCondition);
            entityManager.getTransaction().commit();
            entityManager.close();
            Logger.getLogger(String.valueOf(GrantCondition.class)).info("Grant Conditions created.");
            return grantCondition;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(GrantCondition.class)).info("Grant Conditions dose not created.");
            throw new BusinessException(THE_CONDITIONS_IS_NOT_TRUE);
        }
    }

    public static List<GrantCondition> finByName(String name) throws BusinessException {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
            //  validate();
            List<GrantCondition> grantConditionList = entityManager.createQuery("select grantCondition from GrantCondition grantCondition left join fetch grantCondition.loanType loan where grantCondition.name=:name").setParameter("name", name).getResultList();
            entityManager.close();
            Logger.getLogger(String.valueOf(LoanType.class)).info("Grant Conditions find.");
            return grantConditionList;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(LoanType.class)).info("Grant Conditions dose not find.");
            e.printStackTrace();
            throw new BusinessException(GrandCondition_NotFound);
        }
    }

    public static List<GrantCondition> findByLoanId(long loanTypeId) throws BusinessException {
        try {
            EntityManager entityManager = JPA.getEntitymanager();
            List<GrantCondition> grantConditionList = entityManager.createQuery("select grantCondition from GrantCondition grantCondition left join fetch grantCondition.loanType loan where grantCondition.loanType.loanTypeId=:LoanType_ID").setParameter("LoanType_ID", loanTypeId).getResultList();
            entityManager.close();
            Logger.getLogger(String.valueOf(GrantCondition.class)).info("Grant Conditions  find.");
            return grantConditionList;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(LoanType.class)).info("Grant Conditions dose not find.");
            e.printStackTrace();
            throw new BusinessException(GrandCondition_NotFound);
        }
    }

    //check........................................
//    public static void create(List<GrantCondition> grantConditionList, String loanTypeName, String interestRate) throws BusinessException {
//        LoanType loanType = new LoanType(loanTypeName, interestRate, grantConditionList);
//        GrantConditionalService.getInstance().persist(loanType, grantConditionList);
//    }

    public static boolean validate(GrantCondition grantCondition) throws BusinessException {
        if (grantCondition.getMinDuration() >= grantCondition.getMaxDuration()) {
            throw new BusinessException("حداقل زمان باید کمتر از حداکثر باشد.");
        }
        if (grantCondition.getMinAmount().compareTo(grantCondition.getMaxAmount()) > 0) {
            throw new BusinessException("حداقل مبلغ باید کمتر از حداکثر باشد.");
        }

        List<GrantCondition> findGrantConditional = GrantConditionalService.finByName(grantCondition.getName());
        if (findGrantConditional == null) {
            throw new BusinessException("نام شرط اعطا ذکر شده تکراری است.");
        }

        return true;
    }
}


