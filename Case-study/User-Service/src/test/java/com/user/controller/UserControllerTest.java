package com.user.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.dto.UserDto;
import com.user.entity.UserRole;
import com.user.service.UserService;


@WebMvcTest
class UserControllerTest {

	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testPostStudent() throws JsonProcessingException, Exception {
		UserDto requestDto = new UserDto();
		requestDto.setName("name");
		requestDto.setEmailId("email");
		requestDto.setRole(UserRole.ADMIN);
		
		when(userService.save(requestDto)).thenReturn(requestDto);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/user")
							.content(mapper.writeValueAsString(requestDto))
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}

}
