package com.shestakam.inquiry.dao;

import com.shestakam.inquiry.entity.Inquiry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by shestakam on 1.9.15.
 */
public class HibernateInquiryDao implements InquiryDao {

    private  final static Logger logger = LogManager.getLogger(HibernateInquiryDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Long save(Inquiry inquiry) {
        logger.debug("save inquiry");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(inquiry);
        session.getTransaction().commit();
        session.close();
        return inquiry.getId();
    }

    public Inquiry get(Long id) {
        logger.debug("get inquiry by id. id = " + id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Inquiry inquiry = (Inquiry) session.get(Inquiry.class,id);
        session.getTransaction().commit();
        session.close();
        return inquiry;
    }

    public List<Inquiry> getAll() {
        logger.debug("get all inquiries");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from com.shestakam.inquiry.entity.Inquiry").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void delete(Long id) {
        logger.debug("delete inquire with id: " + id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Inquiry inquiry = (Inquiry) session.get(Inquiry.class, id);
        session.delete(inquiry);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Inquiry inquiry) {
        logger.debug("update inquiry with id : "+inquiry.getId());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(inquiry);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteInquiryByCustomerNameAndInquiryId(String customerName,Long inquiryId){
        logger.debug("delete inquire with id: " + inquiryId + " and customer name :" + customerName);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Inquiry inquiryForDelete = (Inquiry) session.createCriteria(Inquiry.class)
                .add(Restrictions.like("customerName", customerName))
                .add(Restrictions.like("id", inquiryId))
                .list()
                .get(0);
        session.delete(inquiryForDelete);
        session.getTransaction().commit();
        session.close();
    }

    public List<Inquiry> getInquiriesByCustomerName(String customerName) {
        logger.debug("get inquiries with customer name: " + customerName);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Inquiry> inquiryList =  session.createCriteria(Inquiry.class)
                .add(Restrictions.like("customerName", customerName))
                .list();
        session.getTransaction().commit();
        session.close();
        return inquiryList;
    }
}
