package com.shestakam.inquiry.dao;

import com.shestakam.inquiry.attribute.entity.InquiryAttribute;
import com.shestakam.inquiry.entity.Inquiry;
import com.shestakam.topic.entity.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

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

    public Inquiry getInquiryByCustomerNameAndInquiryId(String customerName, Long inquiryId) {
        logger.debug("delete inquire with id: " + inquiryId + " and customer name :" + customerName);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Inquiry inquiry = (Inquiry)  session.createCriteria(Inquiry.class)
                .add(Restrictions.like("customerName", customerName))
                .add(Restrictions.like("id", inquiryId))
                .list()
                .get(0);
        session.getTransaction().commit();
        session.close();
        return inquiry;
    }

    @Override
    public void saveInquiryWithTopicAndAttributes(Inquiry inquiry) {
        logger.debug("save inquiry with topic and attributes");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(inquiry);
        Long topicId = inquiry.getTopic().getId();
        Topic topic = (Topic)  session.get(Topic.class, topicId);
        inquiry.setTopic(topic);
        Set<InquiryAttribute> inquiryAttributes = inquiry.getInquiryAttributeSet();
        for(InquiryAttribute elem : inquiryAttributes){
            elem.setInquiry(inquiry);
            session.save(elem);
        }
        inquiry.setInquiryAttributeSet(inquiryAttributes);
        session.getTransaction().commit();
        session.close();
        return ;
    }

    @Override
    public void deleteInquiryWithAttributes(Long inquiryId) {
        logger.debug("delete inquiry with attributes");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Inquiry inquiry = (Inquiry) session.load(Inquiry.class,inquiryId);
        Set<InquiryAttribute> inquiryAttributes = inquiry.getInquiryAttributeSet();
        for(InquiryAttribute elem : inquiryAttributes){
            session.delete(elem);
        }
        session.delete(inquiry);
        session.getTransaction().commit();
        session.close();
        return ;
    }

    @Override
    public void updateInquiryWithTopicAndAtributes(Inquiry inquiry) {
        logger.debug("update inquiry with topic and attributes");
        Topic newTopic = inquiry.getTopic();
        Set<InquiryAttribute> newInquiryAttributes = inquiry.getInquiryAttributeSet();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(inquiry);
        Topic topic = (Topic)  session.get(Topic.class, newTopic.getId());
        inquiry.setTopic(topic);
        for(InquiryAttribute elem : newInquiryAttributes){
            elem.setInquiry(inquiry);
            session.save(elem);
        }
        inquiry.getInquiryAttributeSet().addAll(newInquiryAttributes);
        session.getTransaction().commit();
        session.close();
        return ;
    }
}
