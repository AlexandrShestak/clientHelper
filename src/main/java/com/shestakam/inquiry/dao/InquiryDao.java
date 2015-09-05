package com.shestakam.inquiry.dao;

import com.shestakam.db.GenericDao;
import com.shestakam.inquiry.attribute.entity.InquiryAttribute;
import com.shestakam.inquiry.entity.Inquiry;
import com.shestakam.topic.entity.Topic;

import java.util.List;
import java.util.Set;

/**
 * Created by shestakam on 1.9.15.
 */
public interface InquiryDao  extends GenericDao<Inquiry> {
    void deleteInquiryByCustomerNameAndInquiryId(String customerName,Long inquiryId);
    List<Inquiry> getInquiriesByCustomerName(String customerName);
    Inquiry getInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId);
    void saveInquiryWithTopicAndAttributes(Inquiry inquiry);
    void deleteInquiryWithAttributes(Long inquiryId);
    void updateInquiryWithTopicAndAtributes(Inquiry inquiry);
}
