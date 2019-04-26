package com.hs.accounts.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="Accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        private String userId;
        private String userName;
        private String gender;
        private String status;
        private String accountType;
        private Date createdDate;
        private Date endDate;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "accounts_transaction", joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "id"))

    @OneToMany(mappedBy = "accounts")
        private List<Transactions> transactions;

    public Accounts() {
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
