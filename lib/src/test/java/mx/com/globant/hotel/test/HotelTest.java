package mx.com.globant.hotel.test;

import org.junit.jupiter.api.Test;

import mx.com.globant.hotel.entities.RoomType;
import mx.com.globant.hotel.repository.RoomTypeRepository;
import mx.com.globant.hotel.rest.RoomTypeController;
import mx.com.globant.hotel.service.RoomTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.json.JSONObject;



@AutoConfigureMockMvc
@ContextConfiguration(classes = {RoomTypeService.class,RoomTypeController.class})
@WebMvcTest
public class HotelTest {	
	@Autowired
	private MockMvc mockMvc;	
	@MockBean
	RoomTypeRepository roomTypeRepository;        
	
    @Test
    void testCreateRoomType() throws Exception{
    	
    	JSONObject premium1 = new JSONObject().put("name","Premium S1");
	    JSONObject premium2 = new JSONObject().put("name","Premium S2");
	    JSONObject vip = new JSONObject().put("name","VIP");
	    JSONObject standar = new JSONObject().put("name","standar");    	
    	
    	MockHttpServletRequest req = new MockHttpServletRequest();
    	req.addParameter("name", "Premium S1");       
    	 
    	 ResultActions result1 = mockMvc.perform(MockMvcRequestBuilders.post("/api/roomtype")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(premium1.toString()))
    			 .andExpectAll(status().isCreated());
    	 
    	 ResultActions result2 = mockMvc.perform(MockMvcRequestBuilders.post("/api/roomtype")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(premium2.toString()))
    			 .andExpectAll(status().isCreated());
    	 
    	 ResultActions result3 = mockMvc.perform(MockMvcRequestBuilders.post("/api/roomtype")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(vip.toString()))
    			 .andExpectAll(status().isCreated());
    	 
    	 ResultActions result4 = mockMvc.perform(MockMvcRequestBuilders.post("/api/roomtype")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(standar.toString()))
    			 .andExpectAll(status().isCreated());
    	 
    	 ResultActions result5 = mockMvc.perform(MockMvcRequestBuilders
        		 .get("/api/roomtype/consultaId/800")
       			 .contentType(MediaType.APPLICATION_JSON))
       			 .andExpectAll(status().isNotFound());
    	 
    	 ResultActions result6 = mockMvc.perform(MockMvcRequestBuilders
        		 .get("/api/roomtype/")
       			 .contentType(MediaType.APPLICATION_JSON))
       			 .andExpectAll(status().isNotFound());
    			 
    	}    
}

@Configuration
class DataSourceConfig2 {
	
	@Bean
	public DataSource datasource() {
		
		return DataSourceBuilder.create()
		          .driverClassName("org.postgresql.Driver")
		          .url("jdbc:postgresql://localhost:5432/postgres")
		          .username("postgres")
		          .password("Satelite14")
		          .build();		
	}
}
