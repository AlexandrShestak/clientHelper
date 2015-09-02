package com.shestakam.inquiry.controller;

import com.shestakam.inquiry.dao.InquiryDao;
import com.shestakam.inquiry.entity.Inquiry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.shestakam.topic.dao.TopicDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

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
        //inquiries = inquiryDao.getInquiryByCustomerName(customerName);
        return inquiries;
    }


    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.GET)
    public Inquiry getInquiryListForCustomerByInquiryId() {
        logger.debug("get all inquiries for customer by inquiry id");
        Inquiry inquiries = new Inquiry();
        //inquiries = inquiryDao.getInquiryByCustomerNameAndInquiryId(customerName);
        return inquiries;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries",method = RequestMethod.POST)
    public void createInquiry() {
        logger.debug("create inquiry");
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        //inquiries = inquiryDao.getInquiryByCustomerName(customerName);
        return ;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.PUT)
    public void updateInquiry(@PathVariable String customerName , @PathVariable Long inquiryId) {
        logger.debug("update inquiry  with id: " + inquiryId + " and customer: " + customerName);
        Inquiry inquiries = new Inquiry();
        //inquiries = inquiryDao.getInquiryByCustomerNameAndInquiryId(customerName);
        return ;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.DELETE)
    public void deleteInquiry(@PathVariable String customerName,@PathVariable Long inquiryId) {
        logger.debug("delete inquiry with id: " + inquiryId + " and customer: " + customerName);
        inquiryDao.deleteInquiryByCustomerNameAndInquiryId(customerName,inquiryId);
        return ;
    }

}
