package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {
	@Autowired
	private MockMvc mockMvc;

    //User CRUD Tests
	@Test
	public void testGetUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user")
						.param("username", "testUser"))
				.andExpect(status().isOk());
	}
    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .param("username", "testUser")
                        .param("password", "1234"))

                .andExpect(status().isOk());
    }
//    @Test
//    public void testUpdateUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.put("/user")
//                        .param("username", "testUser")
//                        .param("password", "aaaaaaaaaaa"))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void testDeleteUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/user")
//                        .param("username", "testUser")
//                        .param("password", "1234"))
//                .andExpect(status().isOk());
//    }
}
