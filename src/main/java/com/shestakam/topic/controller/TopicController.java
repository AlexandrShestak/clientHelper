package com.shestakam.topic.controller;

import com.shestakam.topic.dao.TopicDao;
import com.shestakam.topic.entity.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by shestakam on 1.9.15.
 */
@RestController
public class TopicController {

    private  final static Logger logger = LogManager.getLogger(TopicController.class);
    private TopicDao topicDao;

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @RequestMapping(value = "/topics",method = RequestMethod.GET)
    public List<Topic> getListTopics() {
        logger.debug("get all topics list");

        //String sonString = mapper.writeValueAsString(topicDao.getAll);
        return topicDao.getAll();
    }
}
