package com.netlight.myapi;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    private Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void all() throws Exception {
        mockMvc.perform(get("/employees")).
                andExpect(status().isOk());
    }

    @Test
    void one() throws Exception {
        mockMvc.perform(get("/employees/1")).
                andExpect(status().isOk());
    }

    @Test
    void newEmployee() throws Exception {
        mockMvc.perform(post("/employees").
                contentType(MediaType.APPLICATION_JSON).
                content(gson.toJson(new Employee("first","last","role")))).
                andExpect(status().isCreated());
    }

    @Test
    void replaceEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}