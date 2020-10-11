package model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "loanType")
@Table
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanTypeId;

    @Column(columnDefinition = "varchar2(50)", nullable = false)
    private String loanName;

    @Column(columnDefinition = "varchar2(20)", nullable = false)
    private String interestRate;

    @OneToMany(mappedBy = "loanType", cascade = CascadeType.PERSIST)
    private List<GrantCondition> grantConditions = new ArrayList<GrantCondition>();


//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "FK")


    public LoanType() {

    }

    public LoanType(String loanName, String interestRate, List<GrantCondition> grantConditions) {
        this.loanName = loanName;
        this.interestRate = interestRate;
        this.grantConditions = grantConditions;
    }

    public LoanType(String loanName, String interestRate) {
        this.loanName = loanName;
        this.interestRate = interestRate;
    }

    public LoanType(Long loanTypeId) {
        this.loanTypeId = loanTypeId;

    }

    public Long getLoanTypeId() {
        return loanTypeId;
    }

    public LoanType setLoanTypeId(Long loanTypeId) {
        this.loanTypeId = loanTypeId;
        return this;
    }

    public String getLoanName() {
        return loanName;
    }

    public LoanType setLoanName(String loanName) {
        this.loanName = loanName;
        return this;
    }


    public String getInterestRate() {
        return interestRate;
    }

    public LoanType setInterestRate(String interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public List<GrantCondition> getGrantConditions() {
        return grantConditions;
    }

    public LoanType setGrantConditions(List<GrantCondition> grantConditions) {
        this.grantConditions = grantConditions;
        return this;
    }
}
