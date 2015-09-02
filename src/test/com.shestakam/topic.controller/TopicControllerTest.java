package com.shestakam.topic.controller;


import com.shestakam.topic.dao.TopicDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


/**
 * Created by shestakam on 2.9.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/dispatcherServlet-servlet.xml")
/*@SpringApplicationConfiguration(name = "dispatcherServlet-servlet.xml")*/
@WebAppConfiguration
public class TopicControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

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
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("antena")))
                .andExpect(jsonPath("$[1].name", is("mobile")))
                .andExpect(jsonPath("$[2].name", is("tv")));

  /*      .andExpect(MockRestRequestMatchers.jsonPath("$[0].id", is(this.bookmarkList.get(0).getId().intValue())))
                .andExpect(MockRestRequestMatchers.jsonPath("$[0].uri", is("http://bookmark.com/1/" + userName)))
                .andExpect(MockRestRequestMatchers.jsonPath("$[0].description", is("A description")))
                .andExpect(MockRestRequestMatchers.jsonPath("$[1].id", is(this.bookmarkList.get(1).getId().intValue())))
                .andExpect(MockRestRequestMatchers.jsonPath("$[1].uri", is("http://bookmark.com/2/" + userName)))
                .andExpect(MockRestRequestMatchers.jsonPath("$[1].description", is("A description")));*/
    }

    @Test
    public void notFound() throws Exception {
        mockMvc.perform(get("/topiccccs"))
                .andExpect(status().isNotFound());

    }


}
