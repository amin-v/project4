package model.service;

import commons.exceptions.BusinessException;
import configController.JPA;
import model.entity.GrantCondition;
import model.entity.LoanFile;
import model.entity.LoanType;
import model.entity.RealPerson;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import static commons.ExceptionMessages.customerNumer_RealPerson;

public class LoanFileService {
    private static LoanFileService loanFileService = new LoanFileService();

    private LoanFileService() {
    }

    public static LoanFileService getInstance() {
        return loanFileService;
    }


    public LoanFile persist(LoanType loanType, RealPerson realPerson, LoanFile loanFile) throws HibernateException, BusinessException {
        try {
            validate(loanFile.getAmount(), loanFile.getDuration(), loanFile.getLoanType().getLoanTypeId());
            EntityManager entityManager = JPA.getEntitymanager();
            entityManager.getTransaction().begin();
            loanFile.setLoanType(loanType);
            loanFile.setRealPerson(realPerson);
            entityManager.persist(loanFile);
            entityManager.getTransaction().commit();
            entityManager.close();
            Logger.getLogger(String.valueOf(LoanFile.class)).info("loan file created.");
            return loanFile;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(LoanFile.class)).info("loan file dose not created.");
            throw new BusinessException(customerNumer_RealPerson);

        }
    }

    public static List<LoanFile> findById(long realPersonId) throws BusinessException {
        try {
        EntityManager entityManager = JPA.getEntitymanager();
        List<LoanFile> loanFileList = entityManager.createQuery("select loanFile from LoanFile loanFile LEFT join fetch loanFile.realPerson loan where loanFile.realPerson.realPersonId=: realPersonId").setParameter("realPersonId", realPersonId).getResultList();
        entityManager.close();
        Logger.getLogger(String.valueOf(LoanFile.class)).info("Loan File  find.");
        return loanFileList;
        } catch (HibernateException e) {
            Logger.getLogger(String.valueOf(LoanType.class)).info("LoanFile dose not find.");
            e.printStackTrace();
            throw new BusinessException(customerNumer_RealPerson);
        }
    }



    //check..................................................
    public static LoanType validate(String amount, String duration, long loanTypeId) throws BusinessException {
        List<GrantCondition> grantConditionList = GrantConditionalService.findByLoanId(loanTypeId);
        for (GrantCondition grantCondition : grantConditionList) {
            if ((Long.parseLong(duration) < grantCondition.getMaxDuration()) && (Long.parseLong(duration) > grantCondition.getMinDuration())) {
                if ((new BigDecimal(amount).compareTo(grantCondition.getMaxAmount())) <= 0 && (new BigDecimal(amount).compareTo(grantCondition.getMinAmount()) >= 0)) {
                    return grantCondition.getLoanType().setLoanTypeId(loanTypeId);
                }
            }
        }
        throw new BusinessException("در وارد کردن مدت و مبلغ قرار داد دقت فرمایید...");
    }
}