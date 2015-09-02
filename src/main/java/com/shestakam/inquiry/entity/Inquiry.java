package com.shestakam.inquiry.entity;

import com.shestakam.inquiry.attribute.entity.TopicAttribute;
import com.shestakam.topic.entity.Topic;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shestakam on 1.9.15.
 */
public class Inquiry {
    private Long id;
    private String description;
    private Date creationDate;
    private String customerName;
    private Topic topic;
    private Set<TopicAttribute> topicAttributeSet = new HashSet<TopicAttribute>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<TopicAttribute> getTopicAttributeSet() {
        return topicAttributeSet;
    }

    public void setTopicAttributeSet(Set<TopicAttribute> topicAttributeSet) {
        this.topicAttributeSet = topicAttributeSet;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
