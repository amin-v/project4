package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "LoanFile")
@Table
public class LoanFile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long loanFileId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "realPersonId", nullable = false)
    private RealPerson realPerson;


    @Column(columnDefinition = "varchar2(30)")
    private String duration;

    @Column(columnDefinition = "varchar2(30)")
    private String amount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "loanTypeId", nullable = false)
    LoanType loanType;

    public LoanFile() {
    }

    public LoanFile(String duration, String amount) {
        this.duration = duration;
        this.amount = amount;
    }

    public long getLoanFileId() {
        return loanFileId;
    }

    public void setLoanFileId(long loanFileId) {
        this.loanFileId = loanFileId;
    }

    public RealPerson getRealPerson() {
        return realPerson;
    }

    public void setRealPerson(RealPerson realPerson) {
        this.realPerson = realPerson;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
