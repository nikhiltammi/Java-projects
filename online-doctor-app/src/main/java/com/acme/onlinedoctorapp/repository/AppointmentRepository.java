package com.acme.onlinedoctorapp.repository;

import com.acme.onlinedoctorapp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientName(String patientName);
    List<Appointment> findByDoctorName(String doctorName);
    List<Appointment> findByAppointmentTimeBetween(LocalDateTime start, LocalDateTime end);
}
