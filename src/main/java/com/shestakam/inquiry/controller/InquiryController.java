package com.shestakam.inquiry.controller;

import com.shestakam.inquiry.attribute.entity.InquiryAttribute;
import com.shestakam.inquiry.dao.InquiryDao;
import com.shestakam.inquiry.entity.Inquiry;
import com.shestakam.topic.entity.Topic;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.Set;

/**
 * Created by shestakam on 2.9.15.
 */
@RestController
public class InquiryController {
    private  final static Logger logger = LogManager.getLogger(InquiryController.class);
    private InquiryDao inquiryDao;

    public void setInquiryDao(InquiryDao inquiryDao) {
        this.inquiryDao = inquiryDao;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries",method = RequestMethod.GET)
    public List<Inquiry> getInquiryListForCustomer(@PathVariable String customerName) {
        logger.debug("get all inquiries for customer ");
        List<Inquiry> inquiries = inquiryDao.getInquiriesByCustomerName(customerName);
        return inquiries;
    }


    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.GET)
    public Inquiry getInquiryListForCustomerByInquiryId(@PathVariable String customerName ,
                                                        @PathVariable Long inquiryId) {
        logger.debug("get all inquiries for customer by inquiry id");
        Inquiry inquiry = inquiryDao.getInquiryByCustomerNameAndInquiryId(customerName, inquiryId);
        return inquiry;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries",method = RequestMethod.POST)
    public void createInquiry(@PathVariable String customerName,
                              @RequestBody String description,
                              @RequestBody Topic topic,
                              @RequestBody Set<InquiryAttribute> attributes ) {
        logger.debug("create inquiry for customer: " + customerName);
        Inquiry inquiry = new Inquiry();
        inquiry.setCustomerName(customerName);
        inquiry.setCreationDate(new java.sql.Date(System.currentTimeMillis()));
        inquiry.setDescription(description);
        inquiry.setTopic(topic);
        inquiry.setInquiryAttributeSet(attributes);
        return ;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.PUT)
    public void updateInquiry(@PathVariable String customerName ,
                              @PathVariable Long inquiryId,
                              @RequestBody String description,
                              @RequestBody Topic topic,
                              @RequestBody Set<InquiryAttribute> attributes) {
        logger.debug("update inquiry  with id: " + inquiryId + " and customer: " + customerName);
        Inquiry inquiry = inquiryDao.getInquiryByCustomerNameAndInquiryId(customerName, inquiryId);
        inquiry.setDescription(description);
        inquiry.setTopic(topic);
        inquiry.getInquiryAttributeSet().addAll(attributes);
        return ;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.DELETE)
    public void deleteInquiry(@PathVariable String customerName,@PathVariable Long inquiryId) {
        logger.debug("delete inquiry with id: " + inquiryId + " and customer: " + customerName);
        inquiryDao.deleteInquiryByCustomerNameAndInquiryId(customerName,inquiryId);
        return ;
    }

}