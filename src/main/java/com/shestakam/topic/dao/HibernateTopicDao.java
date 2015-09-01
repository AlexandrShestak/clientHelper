package com.shestakam.topic.dao;

import com.shestakam.topic.entity.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by shestakam on 1.9.15.
 */
public class HibernateTopicDao implements TopicDao {

    private  final static Logger logger = LogManager.getLogger(HibernateTopicDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Long save(Topic topic) {
        logger.debug("save topic");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(topic);
        session.getTransaction().commit();
        session.close();
        return topic.getId();
    }

    public Topic get(Long id) {
        logger.debug("get topic by id. id = " + id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Topic topic = (Topic) session.get(Topic.class,id);
        session.getTransaction().commit();
        session.close();
        return topic;
    }

    public List<Topic> getAll() {
        logger.debug("get all topics");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from com.shestakam.topic.entity.Topic").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void delete(Long id) {
        logger.debug("delete topic with id: " + id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Topic topic = (Topic) session.get(Topic.class,id);
        session.delete(topic);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Topic topic) {
        logger.debug("update topic with id : "+topic.getId());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(topic);
        session.getTransaction().commit();
        session.close();
    }
}
