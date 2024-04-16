package com.acme.onlinedoctorapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.acme.onlinedoctorapp.model.Appointment;
import com.acme.onlinedoctorapp.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    void getAllAppointments() throws Exception {
        Appointment appointment = new Appointment("John Doe", "Dr. Smith", LocalDateTime.now());
        when(appointmentService.getAllAppointments()).thenReturn(Collections.singletonList(appointment));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/appointments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].doctorName").value("Dr. Smith"));
    }

    @Test
    void addAppointment() throws Exception {
        Appointment appointment = new Appointment("Jane Doe", "Dr. Johnson", LocalDateTime.now());
        when(appointmentService.addAppointment(appointment)).thenReturn(appointment);

        mockMvc.perform(MockMvcRequestBuilders.post("/appointments")
                .content(new ObjectMapper().writeValueAsString(appointment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientName").value("Jane Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doctorName").value("Dr. Johnson"));
    }
}
