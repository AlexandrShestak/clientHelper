package com.shestakam.inquiry.controller;

import com.shestakam.inquiry.attribute.entity.InquiryAttribute;
import com.shestakam.inquiry.dao.InquiryDao;
import com.shestakam.inquiry.entity.Inquiry;
import com.shestakam.topic.entity.Topic;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by shestakam on 3.9.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/dispatcherServlet-servlet.xml","/daoContext.xml"})
@WebAppConfiguration
public class InquiryControllerTest {

    private static final String customerName = "MigelXoce";
    private static final String customerNameToDeleteInquiry = "shevKaterina";
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private InquiryDao inquiryDao;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
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
        mockMvc.perform(get("/customers/" + customerName + "/inquiries/" + 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.description", is("install a temporary lock for 3 weeks")))
                .andExpect(jsonPath("$.creationDate", is("2015-01-12")))
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
        Inquiry inquiry = new Inquiry();
        inquiry.setCustomerName("TratataTatat");
        inquiry.setDescription("tratattatatata");
        inquiry.setInquiryAttributeSet(attributes);
        inquiry.setTopic(topic);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(inquiry);
        //String jsonString = "{\"id\":null,\"description\":\"tratattatatata\",\"creationDate\":null,\"customerName\":\"TratataTatat\",\"topic\":{\"id\":1,\"name\":\"problems with balance\"},\"inquiryAttributeSet\":[{\"id\":1,\"name\":\"bug\",\"value\":\"some buggg\",\"inquiry\":null}]}";
        int sizeBefore = inquiryDao.getAll().size();
        this.mockMvc.perform(post("/customers/" + customerName + "/inquiries")
                .contentType(contentType)
                .content(jsonString))
                .andExpect(status().isOk());
        List<Inquiry> inquiryList = inquiryDao.getAll();
        int sizeAfter =  inquiryList.size();
        Long inquiryId = inquiryList.get(inquiryList.size()-1).getId();
        inquiryDao.deleteInquiryWithAttributes(inquiryId);
        Assert.assertEquals(sizeBefore+1,sizeAfter);
    }

    @Test
    public void updateInquiryTest() throws Exception {
        Inquiry inquiry = inquiryDao.get(4L);
        inquiry.setDescription("add unlimited calls for one week");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(inquiry);
       // String jsonString = "{\"id\":4,\"description\":\"add unlimited calls for one week\",\"creationDate\":\"2015-06-11\",\"customerName\":\"BarakNigrian\",\"topic\":{\"id\":4,\"name\":\"add a service\"},\"inquiryAttributeSet\":[]}";
        int sizeBefore = inquiryDao.getAll().size();
        this.mockMvc.perform(put("/customers/" + customerName + "/inquiries/" + 2)
                .contentType(contentType)
                .content(jsonString))
                .andExpect(status().isOk());
        int sizeAfter =  inquiryDao.getAll().size();
        Assert.assertEquals(sizeBefore, sizeAfter);
        Inquiry inquiryAfter = inquiryDao.get(4L);
        Assert.assertEquals("add unlimited calls for one week",inquiryAfter.getDescription());
        inquiry.setDescription("add unlimited calls");
        jsonString = mapper.writeValueAsString(inquiry);
        //jsonString = "{\"id\":4,\"description\":\"add unlimited calls\",\"creationDate\":\"2015-06-11\",\"customerName\":\"BarakNigrian\",\"topic\":{\"id\":4,\"name\":\"add a service\"},\"inquiryAttributeSet\":[]}}";
        this.mockMvc.perform(put("/customers/" + customerName + "/inquiries/" + 2)
                .contentType(contentType)
                .content(jsonString))
                .andExpect(status().isOk());
        Assert.assertEquals("add unlimited calls", inquiryDao.get(4L).getDescription());
    }

    @Test
    public void deleteInquiryTest()  throws Exception {
        // i don't know how to return in previous state after delete, because id is changed if insert after delete
        //data to return in previous state after test
       /* Inquiry  inquiry = inquiryDao.get(6L);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(inquiry);*/

        int sizeBefore = inquiryDao.getAll().size();
        this.mockMvc.perform(delete("/customers/" + customerNameToDeleteInquiry + "/inquiries/" + 6))
                .andExpect(status().isOk());
        int sizeAfter = inquiryDao.getAll().size();
        Assert.assertEquals(sizeBefore - 1, sizeAfter);

        /*this.mockMvc.perform(post("/customers/" + customerName + "/inquiries")
                .contentType(contentType)
                .content(jsonString))
                .andExpect(status().isOk());
        Assert.assertEquals(sizeBefore,inquiryDao.getAll().size());*/
    }
}
