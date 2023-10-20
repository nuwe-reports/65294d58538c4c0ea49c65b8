
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.format.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;



/** TODO
 * Implement all the unit test in its corresponding class.
 * Make sure to be as exhaustive as possible. Coverage is checked ;)
 */

//
// Tests for Doctor Controller
//
@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Doctor doctor1;
    private Doctor doctor2;
    @Before
    void setUp(){
        doctor1 = new Doctor("Alvaro", "Valenzuela", 24, "alvarovgerena@gmail.com");
        doctor2 = new Doctor("Juan", "García", 30, "juan@doctor.com");
    }
    @Test
    void shouldGetAllDoctors() throws Exception{
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctors.add(doctor1);
        doctors.add(doctor2);

        when(doctorRepository.findAll()).thenReturn(doctors);
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk());
    }
    @Test
    void shouldGetDoctorById() throws Exception{
        doctor1.setId(1);

        Optional<Doctor> opt = Optional.of(doctor1);

        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(doctor1.getId());
        assertThat(doctor1.getId()).isEqualTo(1);

        when(doctorRepository.findById(doctor1.getId())).thenReturn(opt);
        mockMvc.perform(get("/api/doctors/" + doctor1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetAnyDoctorById() throws Exception{
        long id = 31;
        mockMvc.perform(get("/api/doctors/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateDoctor() throws Exception{
        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor1)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteDoctorById() throws Exception{
        doctor1.setId(1);

        Optional<Doctor> opt = Optional.of(doctor1);

        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(doctor1.getId());
        assertThat(doctor1.getId()).isEqualTo(1);

        when(doctorRepository.findById(doctor1.getId())).thenReturn(opt);
        mockMvc.perform(delete("/api/doctors/" + doctor1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteDoctor() throws Exception{
        long id = 31;
        mockMvc.perform(delete("/api/doctors/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllDoctors() throws Exception{
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk());
    }
}
//
// Tests for Patient Controller
//
@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Patient patient1;
    private Patient patient2;

    @Before
    void setUp(){
        patient1 = new Patient("Alvaro", "Valenzuela", 24, "alvarovgerena@gmail.com");
        patient2 = new Patient("Juan", "García", 30, "juan@doctor.com");
    }
    @Test
    void shouldGetAllPatients() throws Exception{
        List<Patient> patients = new ArrayList<Patient>();
        patients.add(patient1);
        patients.add(patient2);

        when(patientRepository.findAll()).thenReturn(patients);
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk());
    }
    @Test
    void shouldGetPatientById() throws Exception{
        patient1.setId(1);

        Optional<Patient> opt = Optional.of(patient1);

        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(patient1.getId());
        assertThat(patient1.getId()).isEqualTo(1);

        when(patientRepository.findById(patient1.getId())).thenReturn(opt);
        mockMvc.perform(get("/api/patients/" + patient1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetAnyPatientById() throws Exception{
        long id = 31;
        mockMvc.perform(get("/api/patients/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreatePatient() throws Exception{
        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeletePatientById() throws Exception{
        patient1.setId(1);

        Optional<Patient> opt = Optional.of(patient1);

        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(patient1.getId());
        assertThat(patient1.getId()).isEqualTo(1);

        when(patientRepository.findById(patient1.getId())).thenReturn(opt);
        mockMvc.perform(delete("/api/patients/" + patient1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeletePatient() throws Exception{
        long id = 31;
        mockMvc.perform(delete("/api/patients/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllPatients() throws Exception{
        mockMvc.perform(delete("/api/patients"))
                .andExpect(status().isOk());
    }

}

//
// Tests for Room Controller
//
@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Room room1;
    private Room room2;

    @Before
    void setUp(){
        room1 = new Room("Dermatology");
        room2 = new Room("Traumatology");
    }
    @Test
    void shouldGetAllRooms() throws Exception{
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(room1);
        rooms.add(room2);

        when(roomRepository.findAll()).thenReturn(rooms);
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk());
    }
    @Test
    void shouldGetRoomById() throws Exception{

        Optional<Room> opt = Optional.of(room1);

        assertThat(opt).isPresent();
        assertThat(opt.get().getRoomName()).isEqualTo(room1.getRoomName());
        assertThat(room1.getRoomName()).isEqualTo("Dermatology");

        when(roomRepository.findByRoomName(room1.getRoomName())).thenReturn(opt);
        mockMvc.perform(get("/api/rooms/" + room1.getRoomName()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetRoomById() throws Exception{
        long roomName = "RX";
        mockMvc.perform(get("/api/rooms/" + roomName))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateRoom() throws Exception{
        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room1)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteRoomById() throws Exception{

        Optional<Patient> opt = Optional.of(room1);

        assertThat(opt).isPresent();
        assertThat(opt.get().getRoomName()).isEqualTo(room1.getRoomName());
        assertThat(room1.getRoomName()).isEqualTo("Dermatology");

        when(roomRepository.findByRoomName(room1.getRoomName())).thenReturn(opt);
        mockMvc.perform(delete("/api/rooms/" + room1.getRoomName()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteRoom() throws Exception{
        long roomName = "RX";
        mockMvc.perform(delete("/api/rooms/" + roomName))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllRooms() throws Exception{
        mockMvc.perform(delete("/api/rooms"))
                .andExpect(status().isOk());
    }

}
