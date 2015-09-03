package com.shestakam.inquiry.controller;

import com.shestakam.TestUtil;
import com.shestakam.inquiry.attribute.entity.InquiryAttribute;
import com.shestakam.inquiry.dao.InquiryDao;
import com.shestakam.topic.dao.TopicDao;
import com.shestakam.topic.entity.Topic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by shestakam on 3.9.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/dispatcherServlet-servlet.xml")
/*@SpringApplicationConfiguration(name = "dispatcherServlet-servlet.xml")*/
@WebAppConfiguration
public class InquiryControllerTest {

    private static final String customerName = "MigelXoce";
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private InquiryDao inquiryDao;

    private TopicDao topicDao;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

   /* @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }*/

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    public void setInquiryDao(InquiryDao inquiryDao) {
        this.inquiryDao = inquiryDao;
    }

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Test
    public void getInquiryForCustomerTest() throws Exception {
        mockMvc.perform(get("/customers/"+customerName+"/inquiries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].description", is("change tariff plan from red to extra")))
                .andExpect(jsonPath("$[0].creationDate", is("2015-09-12")))
                .andExpect(jsonPath("$[0].customerName", is("MigelXoce")))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].description", is("install a temporary lock for 3 weeks")))
                .andExpect(jsonPath("$[1].creationDate", is("2015-01-12")))
                .andExpect(jsonPath("$[1].customerName", is("MigelXoce")));
    }

    @Test
    public void getInquiryForCustomerByInquiryIdTest() throws Exception {
        mockMvc.perform(get("/customers/" + customerName + "/inquiries/" + 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.description", is("change tariff plan from red to extra")))
                .andExpect(jsonPath("$.creationDate", is("2015-09-12")))
                .andExpect(jsonPath("$.customerName", is("MigelXoce")));
    }


    @Test
    public void createInquiryTest() throws Exception{

        Topic topic = new Topic();
        topic.setId(1L);
        topic.setName("problems with balance");
        InquiryAttribute inquiryAttribute = new InquiryAttribute();
        inquiryAttribute.setId(1L);
        inquiryAttribute.setName("bug");
        inquiryAttribute.setValue("some buggg");
        Set<InquiryAttribute> attributes = new HashSet<>();
        attributes.add(inquiryAttribute);

       /* String topicJson = json(topic);
        String attributesJson = json(attributes);*/
        this.mockMvc.perform(post("/customers/" + customerName + "/inquiries")
                .contentType(contentType)
                .content(TestUtil.convertObjectToJsonBytes(topic))
                .content(TestUtil.convertObjectToJsonBytes(attributes)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteInquiryTest()  throws Exception {
        this.mockMvc.perform(delete("/customers/"+customerName+"/inquiries/"+2))
                .andExpect(status().isOk());
    }



}