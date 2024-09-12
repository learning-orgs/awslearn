package com.learn.aws.controller;

import com.learn.aws.dto.Employee;
import com.learn.aws.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testViewHomePage() throws Exception {
        Employee emp1 = new Employee(1, "John", "Doe");
        Employee emp2 = new Employee(2, "Jane", "Doe");

        when(employeeServiceImpl.getAllEmployee()).thenReturn(Arrays.asList(emp1, emp2));

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("allemplist"))
                .andExpect(model().attribute("allemplist", Arrays.asList(emp1, emp2)));
    }

//    @Test
//    public void testAddNewEmployee() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/addnew"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("newemployee"))
//                .andExpect(model().attributeExists("employee"))
//                .andExpect(model().attribute("employee", new Employee()));
//    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee(1, "John", "Doe");

        mockMvc.perform(MockMvcRequestBuilders.post("/save")
                        .param("id", "1")
                        .param("firstName", "John")
                        .param("lastName", "Doe"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        // Verify the service method was called
        // Use Mockito.verify if needed
    }

    @Test
    public void testUpdateForm() throws Exception {
        Employee employee = new Employee(1, "John", "Doe");

        when(employeeServiceImpl.getById(1)).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/showFormForUpdate/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update"))
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attribute("employee", employee));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deleteEmployee/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        // Verify the service method was called
        // Use Mockito.verify if needed
    }
}
