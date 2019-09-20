package com.hs.accounts.Commons;

import java.util.Date;

public class TransactionRestDTO {
    Double   totalAmount;
    String   accountNoUUID;
    Double   receivedAmount;
    String   description;
    String   operationType;
    String   transactionType;
    String shareDescription;
    String shareAccountNo;
    Integer sharePercent;
    String transactionRefId;
    private String CreatedBy;
    private String UpdatedBy;
    private Date CreatedAt;
    private Date UpdateAt;
    private boolean flag;


    // String transation reffId

    public TransactionRestDTO() {
    }


    public TransactionRestDTO(Double totalAmount, String accountNoUUID, Double receivedAmount, String description, String operationType, String transactionType, String shareDescription, String shareAccountNo, Integer sharePercent, String transactionRefId, String createdBy, String updatedBy, Date createdAt, Date updateAt, boolean flag) {
        this.totalAmount = totalAmount;
        this.accountNoUUID = accountNoUUID;
        this.receivedAmount = receivedAmount;
        this.description = description;
        this.operationType = operationType;
        this.transactionType = transactionType;
        this.shareDescription = shareDescription;
        this.shareAccountNo = shareAccountNo;
        this.sharePercent = sharePercent;
        this.transactionRefId = transactionRefId;
        CreatedBy = createdBy;
        UpdatedBy = updatedBy;
        CreatedAt = createdAt;
        UpdateAt = updateAt;
        this.flag = flag;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAccountNoUUID() {
        return accountNoUUID;
    }

    public void setAccountNoUUID(String accountNoUUID) {
        this.accountNoUUID = accountNoUUID;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getShareDescription() {
        return shareDescription;
    }

    public void setShareDescription(String shareDescription) {
        this.shareDescription = shareDescription;
    }

    public String getShareAccountNo() {
        return shareAccountNo;
    }

    public void setShareAccountNo(String shareAccountNo) {
        this.shareAccountNo = shareAccountNo;
    }

    public Integer getSharePercent() {
        return sharePercent;
    }

    public void setSharePercent(Integer sharePercent) {
        this.sharePercent = sharePercent;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
