package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	private Doctor d1;

	private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    /** TODO
     * Implement tests for each Entity class: Doctor, Patient, Room and Appointment.
     * Make sure you are as exhaustive as possible. Coverage is checked ;)
     */

    //
    // Doctor Entity Class
    //
    @Before
    void setUp(){
        d1 = new Doctor("Alvaro", "Valenzuela", 24, "alvarovgerena@gmail.com");
    }
    @Test
    void test_constructor() {
        assertEquals("Alvaro", d1.getFirstName());
        assertEquals("Valenzuela", d1.getLastName());
        assertEquals(30, d1.getAge());
        assertEquals("alvarovgerena@gmail.com", d1.getEmail());
    }
    @Test
    void test_get_id(){
        assertEquals(0, d1.getId()); // By default, id should be 0

        d1.setId(1);
        assertEquals(1, d1.getId());
    }
    @Test
    void test_equality(){
        Doctor sameDoctor = new Doctor("Alvaro", "Valenzuela", 24, "alvarovgerena@gmail.com");
        assertEquals(d1, sameDoctor);
    }
    @Test
    void test_inequality(){
        Doctor otherDoctor = new Doctor("Juan", "Alvarez", 30, "juan@prueba.com");
        assertNotEquals(d1, otherDoctor);
    }

    @Test
    void test_negative_age(){
        d1.setAge(-5);
        assertEquals(0, d1.getAge());
    }

    //
    // Patient Entity Class
    //
    @Before
    void setUp(){
        p1 = new Patient("Alvaro", "Valenzuela", 24, "alvarovgerena@gmail.com");
    }
    @Test
    void test_constructor() {
        assertEquals("Alvaro", p1.getFirstName());
        assertEquals("Valenzuela", p1.getLastName());
        assertEquals(30, p1.getAge());
        assertEquals("alvarovgerena@gmail.com", p1.getEmail());
    }
    @Test
    void test_get_id(){
        assertEquals(0, p1.getId()); // By default, id should be 0

        p1.setId(1);
        assertEquals(1, p1.getId());
    }
    @Test
    void test_equality(){
        Patient samePatient = new Patient("Alvaro", "Valenzuela", 24, "alvarovgerena@gmail.com");
        assertEquals(p1, samePatient);
    }
    @Test
    void test_inequality(){
        Patient otherPatient = new Patient("Juan", "Alvarez", 30, "juan@prueba.com");
        assertNotEquals(p1, otherPatient);
    }

    @Test
    void test_negative_age(){
        p1.setAge(-5);
        assertEquals(0, p1.getAge());
    }

    //
    // Room Entity Class
    //
    @Before
    void setUp(){
        r1 = new Room("Traumatology");
    }
    @Test
    void test_constructor(){
        assertEquals("Traumatology", r1.getRoomName());
    }
    @Test
    void test_equality(){
        Room sameRoom = new Room("Traumatology");
        assertEquals(r1, sameRoom);
    }
    @Test
    void test_inequality(){
        Room otherRoom = new Room("Nursing");
        assertNotEquals(r1, otherRoom);
    }

    //
    // Appointment Entity Class
    //
    @Before
    void setUp(){
        Patient patient1 = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        Patient patient2 = new Patient("Paulino", "Antunez", 37, "p.antunez@email.com");

        Doctor doctor1 = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        Doctor doctor2 = new Doctor ("Miren", "Iniesta", 24, "m.iniesta@hospital.accwe");

        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);

        a1 = new Appointment(patient1, doctor1, room, startsAt, finishesAt);
        a2 = new Appointment(patient2, doctor2, room, startsAt, finishesAt);
    }
    @Test
    void test_constructor(){
        assertEquals(patient1, a1.getPatient());
        assertEquals(doctor1, a1.getDoctor());
        assertEquals(room, a1.getRoom());
        assertEquals(startsAt, a1.getStartsAt());
        assertEquals(finishesAt, a1.getFinishesAt());
    }
    @Test
    void test_overlapping(){
        assertTrue(a1.overlaps(a2));
    }
    @Test
    void test_getters_and_setters(){

        // Change starting time in a1 to check if that setter is working correctly
        LocalDateTime newStartsAt = LocalDateTime.parse("14:00 19/10/2023", formatter);
        a1.setStartsAt(newStartsAt);
        assertEquals(newStartsAt, a1.getStartsAt());

        // Change patient, doctor and room in a1 and check if those setters are working correctly
        Patient newPatient = new Patient("Juan", "Benitez", 35, "juan@example.com");
        Doctor newDoctor = new Doctor("Ines", "Garcia", 40, "ines@example.com");
        Room newRoom = new Room("RX");
        a1.setPatient(newPatient);
        a1.setDoctor(newDoctor);
        a1.setRoom(newRoom);
        assertEquals(newPatient, a1.getPatient());
        assertEquals(newDoctor, a1.getDoctor());
        assertEquals(newRoom, a1.getRoom());

    }
}
