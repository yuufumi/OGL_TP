package com.test;

import com.example.dao.*;
import com.example.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReservationDaoTest {
    private DatabaseConnection dbc;
    private Connection con;
    private User user;
    private UserDao userDao;
    private Parking parking;
    private ParkingDao parkingDao;
    private ParkingPlace parkingPlace;
    private ParkingPlaceDao parkingPlaceDao;
    private ReservationDao reservationDao;
    private PlaceStatus placeStatus;
    private ReservationStatus reservationStatus;
    private Reservation reservation;
    private Date start_date;
    private Date end_date;
    @BeforeEach
    public void setUp(){
        dbc = new DatabaseConnection("sa", "","org.h2.Driver","jdbc:h2:mem:test");
        con = dbc.connect();
        dbc.createDb(con);

        user = new User();
        user.setEmail("youcefBenali@gmail.com");
        user.setUserId(1);
        user.setName("Youcef Benali");
        user.setPhone("066666666");

        userDao = new UserDao();
        userDao.setConn(con);
        userDao.insertUser(user);

        parking = new Parking();
        parking.setParkingId(1);
        parking.setName("a");
        parking.setCapacity(20);
        parking.setAddress("aaa");

        parkingDao = new ParkingDao();
        parkingDao.setConn(con);
        parkingDao.insertParking(parking);

        placeStatus = PlaceStatus.AVAILABLE;
        parkingPlace = new ParkingPlace();
        parkingPlace.setPlaceStatus(placeStatus);
        parkingPlace.setParking(parking);
        parkingPlace.setIdPlace(1);
        parkingPlace.setPlaceName("Alger");

        parkingPlaceDao = new ParkingPlaceDao();
        parkingPlaceDao.setConn(con);
        parkingPlaceDao.insertParkingPlace(parkingPlace);

        reservationStatus = ReservationStatus.PENDING;
        start_date = new Date(2023,10,10);
        end_date = new Date(2023,10,15);

        reservation = new Reservation();
        reservation.setStatus(reservationStatus);
        reservation.setStartTime(start_date);
        reservation.setEndTime(end_date);
        reservation.setReservationId(1);
        reservation.setUser(user);
        reservation.setParkingPlace(parkingPlace);

        reservationDao = new ReservationDao();
        reservationDao.setConn(con);
        reservationDao.insertReservation(reservation);
    }
    @Test
    public void insertReservationStatusTest(){
        assertEquals(reservationDao.getReservationById(1).getUser().getUserId(),1);
    }
    @Test
    public void getReservationByIdTest(){
        reservationDao = Mockito.mock(ReservationDao.class);
        when(reservationDao.getReservationById(1)).thenReturn(reservation);
        Reservation expected = reservationDao.getReservationById(1);
        assertEquals(end_date,expected.getEndTime());
    }
    @Test
    public void UpdateReservationStatusTest(){
        reservationDao.updateReservationStatus(1,ReservationStatus.CANCELLED);
        assertEquals(reservationDao.getReservationById(1).getStatus(),ReservationStatus.CANCELLED);
    }

}