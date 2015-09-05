package com.shestakam.inquiry.attribute.entity;

import com.shestakam.inquiry.entity.Inquiry;

/**
 * Created by shestakam on 1.9.15.
 */
public class InquiryAttribute {
    private Long id;
    private String name;
    private String value;
    private Inquiry inquiry;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inquiry getInquiry() {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
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


}
