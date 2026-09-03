package com.empleado.invex.controller;

import com.empleado.invex.dto.EmployeeRequest;
import com.empleado.invex.dto.EmployeeResponse;
import com.empleado.invex.dto.EmployeeUpdateRequest;
import com.empleado.invex.service.EmployeesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeesController.class)
class EmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeesService employeeService;

    private EmployeeRequest requestDTO;
    private EmployeeResponse responseDTO;
    private List<EmployeeRequest> requestList;
    private List<EmployeeResponse> responseList;


    @BeforeEach
    void setUp() {

        LocalDate birthDate = LocalDate.of(1994, 5, 15);
        LocalDateTime createdAt = LocalDateTime.now();

        requestDTO = new EmployeeRequest(
                "Juan",
                "Carlos",
                "Pérez",
                "García",
                30,
                "M",
                birthDate,
                "Senior Developer",
                null,
                true
        );

        responseDTO = new EmployeeResponse(
                1L,
                "Juan",
                "Carlos",
                "Pérez",
                "García",
                30,
                "M",
                birthDate,
                "Senior Developer",
                createdAt,
                true
        );

        requestList = Arrays.asList(requestDTO);
        responseList = Arrays.asList(responseDTO);
    }


    @Test
    void getAllEmployees_ShouldReturnListOfEmployees() throws Exception {

        when(employeeService.findAll()).thenReturn(responseList);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Juan")))
                .andExpect(jsonPath(
                        "$[0].paternalLastName",
                        is("Pérez")
                ))
                .andExpect(jsonPath(
                        "$[0].maternalLastName",
                        is("García")
                ));

        verify(employeeService, times(1)).findAll();
    }


    @Test
    void getAllEmployees_ShouldReturnEmptyList_WhenNoEmployees()
            throws Exception {

        when(employeeService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(employeeService, times(1)).findAll();
    }


    @Test
    void getEmployeeById_ShouldReturnEmployee_WhenExists()
            throws Exception {

        when(employeeService.findById(1L))
                .thenReturn(responseDTO);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Juan")))
                .andExpect(jsonPath(
                        "$.paternalLastName",
                        is("Pérez")
                ))
                .andExpect(jsonPath(
                        "$.maternalLastName",
                        is("García")
                ));

        verify(employeeService, times(1)).findById(1L);
    }


    @Test
    void createEmployees_ShouldReturnCreatedEmployees()
            throws Exception {

        when(employeeService.create(anyList()))
                .thenReturn(responseList);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(requestList)
                        ))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath(
                        "$[0].firstName",
                        is("Juan")
                ))
                .andExpect(jsonPath(
                        "$[0].paternalLastName",
                        is("Pérez")
                ));

        verify(employeeService, times(1)).create(anyList());
    }


   


    @Test
    void updateEmployee_ShouldReturnUpdatedEmployee()
            throws Exception {

        EmployeeUpdateRequest updateRequest =
                new EmployeeUpdateRequest(
                        "Juan",
                        "Carlos",
                        "Pérez",
                        "García",
                        31,
                        "M",
                        LocalDate.of(1994, 5, 15),
                        "Lead Developer",
                        null,
                        true
                );

        EmployeeResponse updatedResponse =
                new EmployeeResponse(
                        1L,
                        "Juan",
                        "Carlos",
                        "Pérez",
                        "García",
                        31,
                        "M",
                        LocalDate.of(1994, 5, 15),
                        "Lead Developer",
                        LocalDateTime.now(),
                        true
                );

        when(employeeService.update(
                eq(1L),
                any(EmployeeUpdateRequest.class)
        )).thenReturn(updatedResponse);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(
                                        updateRequest
                                )
                        ))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath(
                        "$.position",
                        is("Lead Developer")
                ))
                .andExpect(jsonPath("$.age", is(31)));

        verify(employeeService, times(1)).update(
                eq(1L),
                any(EmployeeUpdateRequest.class)
        );
    }
 


    @Test
    void deleteEmployee_ShouldReturn204_WhenDeleted()
            throws Exception {

        doNothing()
                .when(employeeService)
                .delete(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).delete(1L);
    }


    @Test
    void searchEmployeesByName_ShouldReturnMatchingEmployees()
            throws Exception {

        String searchName = "Juan";

        when(employeeService.searchByName(searchName))
                .thenReturn(responseList);

        mockMvc.perform(get("/employees/search")
                        .param("name", searchName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath(
                        "$[0].firstName",
                        is("Juan")
                ));

        verify(employeeService, times(1))
                .searchByName(searchName);
    }



    @Test
    void searchEmployeesByName_ShouldReturn200_WhenNameExists()
            throws Exception {

        String searchName = "Pérez";

        when(employeeService.searchByName(searchName))
                .thenReturn(responseList);

        mockMvc.perform(get("/employees/search")
                        .param("name", searchName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath(
                        "$[0].paternalLastName",
                        is("Pérez")
                ));

        verify(employeeService, times(1))
                .searchByName(searchName);
    }
}