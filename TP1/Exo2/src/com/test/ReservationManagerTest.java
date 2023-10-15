package com.test;

import com.example.dao.*;
import com.example.entity.*;
import com.example.service.IParkingPlaceManager;
import com.example.service.ReservationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class ReservationManagerTest {
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
    private ReservationManager reservationManager;
    private IParkingPlaceManager iParkingPlaceManager;

    @BeforeEach
    public void setUp()
    {
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

        reservationStatus = ReservationStatus.CONFIRMED;
        start_date = new Date(2023,10,10);
        end_date = new Date(2023,10,15);

        reservation = new Reservation();
        reservation.setStatus(reservationStatus);
        reservation.setStartTime(start_date);
        reservation.setEndTime(end_date);
        reservation.setReservationId(1);
        reservation.setUser(user);
        reservation.setParkingPlace(parkingPlace);

        reservationDao = Mockito.mock(ReservationDao.class);
        reservationDao.setConn(con);

        iParkingPlaceManager = Mockito.mock(IParkingPlaceManager.class);
        reservationManager = new ReservationManager();
        reservationManager.setReservationDao(reservationDao);
        reservationManager.setiPlaceManager(iParkingPlaceManager);
    }

    @Test
    public void createReservationTest(){

        when(iParkingPlaceManager.isAvailable(1,start_date,end_date)).thenReturn(true);

        reservationManager.createReservation(reservation);

        Mockito.verify(reservationDao, times(1)).insertReservation(reservation);
        Mockito.verify(iParkingPlaceManager, times(1)).updateStatus(1, PlaceStatus.RESERVED);
    }

    @Test
    public void cancelReservationTest() {
        when(reservationDao.getReservationById(1)).thenReturn(reservation);

        reservationManager.cancelReservation(1);

        Mockito.verify(reservationDao, times(1)).updateReservationStatus(1, ReservationStatus.CANCELLED);
    }


}