package com.shestakam.topic.controller;


import com.shestakam.topic.dao.TopicDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Created by shestakam on 2.9.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/dispatcherServlet-servlet.xml")
/*@SpringApplicationConfiguration(name = "dispatcherServlet-servlet.xml")*/
@WebAppConfiguration
public class TopicControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TopicDao topicDao;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Test
    public void getAllTopics() throws Exception {
        mockMvc.perform(get("/topics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[4].name", is("remove service")))
                .andExpect(jsonPath("$[1].name", is("change tariff plan")))
                .andExpect(jsonPath("$[2].name", is("install a temporary lock")))
                .andExpect(jsonPath("$[0].name", is("problems with balance")))
                .andExpect(jsonPath("$[5].name", is("forgotten PIN")))
                .andExpect(jsonPath("$[3].name", is("add a service")));
    }

    @Test
    public void notFound() throws Exception {
        mockMvc.perform(get("/topiccccs"))
                .andExpect(status().isNotFound());

    }


}
