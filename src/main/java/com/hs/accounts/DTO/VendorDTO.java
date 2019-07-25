package com.hs.accounts.DTO;

import java.util.Date;

public class VendorDTO {
    private Long id;
    private String name;
    private String accountNo;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer sharePercent;
    private String CreatedBy;
    private String UpdatedBy;
    private Date CreatedAt;
    private Date UpdateAt;

    public VendorDTO() {
    }

    public VendorDTO(Long id, String name, String accountNo, String description, Date startDate, Date endDate, Integer sharePercent, String createdBy, String updatedBy, Date createdAt, Date updateAt) {
        this.id = id;
        this.name = name;
        this.accountNo = accountNo;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sharePercent = sharePercent;
        CreatedBy = createdBy;
        UpdatedBy = updatedBy;
        CreatedAt = createdAt;
        UpdateAt = updateAt;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSharePercent() {
        return sharePercent;
    }

    public void setSharePercent(Integer sharePercent) {
        this.sharePercent = sharePercent;
    }
}
