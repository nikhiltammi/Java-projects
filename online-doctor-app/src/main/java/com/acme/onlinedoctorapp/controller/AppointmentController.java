package com.acme.onlinedoctorapp.controller;

import com.acme.onlinedoctorapp.model.Appointment;
import com.acme.onlinedoctorapp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        Appointment newAppointment = appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }
}
