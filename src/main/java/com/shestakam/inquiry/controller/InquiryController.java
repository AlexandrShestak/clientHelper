package com.shestakam.inquiry.controller;

import com.shestakam.inquiry.dao.InquiryDao;
import com.shestakam.inquiry.entity.Inquiry;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
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
    public String getInquiryListForCustomer(@PathVariable String customerName) throws IOException {
        logger.debug("get all inquiries for customer ");
        List<Inquiry> inquiries = inquiryDao.getInquiriesByCustomerName(customerName);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(inquiries);
        return jsonString;
    }


    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.GET)
    public String getInquiryListForCustomerByInquiryId(@PathVariable String customerName ,
                                                        @PathVariable Long inquiryId) throws IOException {
        logger.debug("get all inquiries for customer by inquiry id");
        Inquiry inquiry = inquiryDao.getInquiryByCustomerNameAndInquiryId(customerName, inquiryId);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(inquiry);
        return jsonString;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries",method = RequestMethod.POST)
    public void createInquiry(@PathVariable String customerName,
                              @RequestBody Inquiry inquiry) {
        logger.debug("create inquiry for customer: " + customerName);
        inquiry.setCreationDate(new java.sql.Date(System.currentTimeMillis()));
        inquiryDao.saveInquiryWithTopicAndAttributes(inquiry);
        return ;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.PUT)
    public void updateInquiry(@PathVariable String customerName ,
                              @PathVariable Long inquiryId,
                              @RequestBody Inquiry inquiry) {
        logger.debug("update inquiry  with id: " + inquiry);
        inquiryDao.updateInquiryWithTopicAndAttributes(inquiry);
        return ;
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}",method = RequestMethod.DELETE)
    public void deleteInquiry(@PathVariable String customerName,@PathVariable Long inquiryId) {
        logger.debug("delete inquiry with id: " + inquiryId + " and customer: " + customerName);
        inquiryDao.deleteInquiryByCustomerNameAndInquiryId(customerName,inquiryId);
        return ;
    }

}
