package com.shestakam.topic.controller;

import com.shestakam.topic.dao.TopicDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    public String getListTopics() throws IOException {
        logger.debug("get all topics list");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(topicDao.getAll());
        logger.debug(topicDao.getAll());
        return jsonString;
    }
}
