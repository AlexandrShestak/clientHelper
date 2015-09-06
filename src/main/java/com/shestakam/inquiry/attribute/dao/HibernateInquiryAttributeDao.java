package com.shestakam.inquiry.attribute.dao;

import com.shestakam.inquiry.attribute.entity.InquiryAttribute;
import com.shestakam.inquiry.attribute.entity.InquiryAttribute;
import com.shestakam.inquiry.entity.Inquiry;
import com.shestakam.topic.entity.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


import java.util.List;

/**
 * Created by shestakam on 6.9.15.
 */
public class HibernateInquiryAttributeDao implements InquiruAttributeDao {

    private  final static Logger logger = LogManager.getLogger(HibernateInquiryAttributeDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public Long save(InquiryAttribute inquiryAttribute) {
        logger.debug("save inquiry attribute");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(inquiryAttribute);
        session.getTransaction().commit();
        session.close();
        return inquiryAttribute.getId();
    }

    @Override
    public InquiryAttribute get(Long id) {
        logger.debug("get inquiry attribute by id. id = " + id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        InquiryAttribute inquiryAttribute = (InquiryAttribute)session.get(InquiryAttribute.class,id);
        session.getTransaction().commit();
        session.close();
        return inquiryAttribute;
    }

    @Override
    public List<InquiryAttribute> getAll() {
        logger.debug("get all inquiry attributes");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from com.shestakam.inquiry.attribute.entity.InquiryAttribute").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void delete(Long id) {
        logger.debug("delete inquire attribute with id: " + id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        InquiryAttribute inquiryAttribute = (InquiryAttribute)session.get(InquiryAttribute.class,id);
        session.delete(inquiryAttribute);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(InquiryAttribute inquiryAttribute) {
        logger.debug("update inquiry attribute with id : " + inquiryAttribute.getId());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(inquiryAttribute);
        session.getTransaction().commit();
        session.close();

    }
}
