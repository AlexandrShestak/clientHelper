package com.shestakam.inquiry.attribute.entity;

/**
 * Created by shestakam on 1.9.15.
 */
public class InquiryAttribute {
    private Long id;
    private String name;
    private String value;
    private Long inquiryId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }
}
