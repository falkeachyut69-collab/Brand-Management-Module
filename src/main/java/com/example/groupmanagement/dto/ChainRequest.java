package com.example.groupmanagement.dto;

public class ChainRequest {

    private String companyName;
    private String gstNumber;
    private Long groupId;

    public String getCompanyName() {
        return companyName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public Long getGroupId() {
        return groupId;
    }
}