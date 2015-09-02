package com.shestakam.inquiry.dao;

import com.shestakam.db.GenericDao;
import com.shestakam.inquiry.entity.Inquiry;

import java.util.List;

/**
 * Created by shestakam on 1.9.15.
 */
public interface InquiryDao  extends GenericDao<Inquiry> {
    void deleteInquiryByCustomerNameAndInquiryId(String customerName,Long inquiryId);
    List<Inquiry> getInquiriesByCustomerName(String customerName);
}
