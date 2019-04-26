package com.hs.accounts.Commons;

public class AccountRestDTO {
    private String id;
    private String name;
    private String gender;
    private String accountType;

    public AccountRestDTO() {
    }

    public AccountRestDTO(String patientId, String patientName, String gender, String accountType) {
        this.id = patientId;
        this.name = patientName;
        this.gender = gender;
        this.accountType = accountType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
