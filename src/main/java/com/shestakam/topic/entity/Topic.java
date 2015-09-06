package com.shestakam.topic.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shestakam.inquiry.entity.Inquiry;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shestakam on 1.9.15.
 */
public class Topic {
    private Long id;
    private String name;


    private Set<Inquiry> inquirySet = new HashSet<Inquiry>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Set<Inquiry> getInquirySet() {
        return inquirySet;
    }

    public void setInquirySet(Set<Inquiry> inquirySet) {
        this.inquirySet = inquirySet;
    }
}
