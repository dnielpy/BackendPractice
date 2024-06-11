package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
    @Test
    public void testUpdateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/user")
                        .param("username", "testUser")
                        .param("password", "1234"))
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user")
                        .param("username", "testUser")
                        .param("password", "1234"))
                .andExpect(status().isOk());
    }
    
    //Lists CRUD Tests
    @Test
    public void testCreateList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/list")
                        .param("username", "testUser")
                        .param("tittle", "testTitle"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list")
                        .param("tittle", "testTitle"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/list")
                        .param("username", "testUser")
                        .param("tittle", "testTitle"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/list")
                        .param("tittle", "testTitle"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/list/addList")
                        .param("username", "testUser")
                        .param("list_tittle", "testListTitle")
                        .param("note_tittle", "testNoteTitle"))
                .andExpect(status().isOk());
    }


    //Notes CRUD Tests
    @Test
    public void testCreateNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/notes")
                        .param("username", "testUser")
                        .param("tittle", "testTitle")
                        .param("note", "testNote")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/notes")
                        .param("tittle", "testTitle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/notes")
                        .param("tittle", "testTitle")
                        .param("note", "updatedNote")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/notes")
                        .param("tittle", "testTitle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

