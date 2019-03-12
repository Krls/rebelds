package com.rebeld.identification.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rebeld.identification.config.Application;
import com.rebeld.identification.domain.RebeldInformation;
import com.rebeld.identification.exception.RebeldException;
import com.rebeld.identification.service.RebeldService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class RebeldControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private RebeldService rebeldService;
	
	@Test
	public void registerRebeldOk() throws Exception {

		when(rebeldService.registerRebeld(Mockito.any())).thenReturn(true);
		
		List<RebeldInformation> info = generateTestRebeldsInformation();

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		mvc.perform(post("/api/registerRebeld")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(info)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void registerRebeldKoByInternalError() throws Exception {

		when(rebeldService.registerRebeld(Mockito.any())).thenThrow(RebeldException.class);
		
		List<RebeldInformation> info = generateTestRebeldsInformation();

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		mvc.perform(post("/api/registerRebeld")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(info)))
		.andExpect(status().isInternalServerError());
	}
	
	private List<RebeldInformation> generateTestRebeldsInformation() {
		RebeldInformation rebeld1 = new RebeldInformation("Carles", "Terra");
		RebeldInformation rebeld2 = new RebeldInformation("Laia", "Mart");

		List<RebeldInformation> info = new ArrayList<RebeldInformation>();
		info.add(rebeld1);
		info.add(rebeld2);
		return info;
	}
}
