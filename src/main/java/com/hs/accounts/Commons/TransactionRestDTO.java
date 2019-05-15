package com.hs.accounts.Commons;

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

    public TransactionRestDTO() {
    }

    public TransactionRestDTO(Double totalAmount, String accountNoUUID, Double receivedAmount, String description, String operationType, String transactionType, String shareDescription, String shareAccountNo, Integer sharePercent) {
        this.totalAmount = totalAmount;
        this.accountNoUUID = accountNoUUID;
        this.receivedAmount = receivedAmount;
        this.description = description;
        this.operationType = operationType;
        this.transactionType = transactionType;
        this.shareDescription = shareDescription;
        this.shareAccountNo = shareAccountNo;
        this.sharePercent = sharePercent;
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
}
