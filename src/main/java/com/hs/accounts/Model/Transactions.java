package com.hs.accounts.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="Transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    private Double receivedAmount;
    private Double totalAmount;
    private String transactionType;
    private String description;
    private String currency;
    String transactionRefId;
    private String operationType;
    private Double dues;
    private String CreatedBy;
    private String UpdatedBy;
    private Date CreatedAt;
    private Date UpdateAt;
    //String Transaction ref ID

//    @ManyToMany(mappedBy = "transactions")
@ManyToOne
@JoinColumn(name = "account_id", nullable = false)
@JsonBackReference
    private Accounts accounts;

    public Transactions() {
    }


    public Transactions(Date transactionDate, Double receivedAmount, Double totalAmount, String transactionType, String description, String currency, String transactionRefId, String operationType, Double dues, String createdBy, String updatedBy, Date createdAt, Date updateAt, Accounts accounts) {
        this.transactionDate = transactionDate;
        this.receivedAmount = receivedAmount;
        this.totalAmount = totalAmount;
        this.transactionType = transactionType;
        this.description = description;
        this.currency = currency;
        this.transactionRefId = transactionRefId;
        this.operationType = operationType;
        this.dues = dues;
        CreatedBy = createdBy;
        UpdatedBy = updatedBy;
        CreatedAt = createdAt;
        UpdateAt = updateAt;
        this.accounts = accounts;
    }


    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Date getUpdateAt() {
        return UpdateAt;
    }

    public void setUpdateAt(Date updateAt) {
        UpdateAt = updateAt;
    }

    public String getTransactionRefId() {
        return transactionRefId;
    }

    public void setTransactionRefId(String transactionRefId) {
        this.transactionRefId = transactionRefId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDues() {
        return dues;
    }

    public void setDues(Double dues) {
        this.dues = dues;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}
