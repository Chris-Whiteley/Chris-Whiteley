/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.videoondemand.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chris
 */
public class ParentalControlServiceTest {

    private static class MockMovieService implements MovieService {

        @Override
        public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {

            switch (movieId) {
                case "Bambie":
                    return "U";
                case "Pulp Fiction":
                    return "18";
                case "Something Went Wrong":
                    throw new TechnicalFailureException();
                default:
                    throw new TitleNotFoundException();
            }
        }

    }

    private static MovieService movieService;
    private static ParentalControlService instance;

    public ParentalControlServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        movieService = new MockMovieService();
        instance = new ParentalControlServiceImpl(movieService);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of canWatchMovie method, of class ParentalControlService.
     */
    @Test
    public void testCanWatchMovie1() throws Exception {
        System.out.println("canWatchMovie1");
        String customerParentelControlLevel = "U";
        String movieId = "Bambie";
        assertTrue(instance.canWatchMovie(customerParentelControlLevel, movieId));
    }

    /**
     * Test of canWatchMovie method, of class ParentalControlService.
     */
    @Test
    public void testCanWatchMovie2() throws Exception {
        System.out.println("canWatchMovie2");
        String customerParentelControlLevel = "15";
        String movieId = "Pulp Fiction";
        assertFalse(instance.canWatchMovie(customerParentelControlLevel, movieId));
    }

    /**
     * Test of canWatchMovie method, of class ParentalControlService.
     */
    @Test
    public void testCanWatchMovie3() throws Exception {
        System.out.println("canWatchMovie3");
        String customerParentelControlLevel = "18";
        String movieId = "Pulp Fiction";
        assertTrue(instance.canWatchMovie(customerParentelControlLevel, movieId));
    }

    /**
     * Test of canWatchMovie method, of class ParentalControlService.
     */
    @Test
    public void testCanWatchMovie4() throws Exception {
        System.out.println("canWatchMovie4");
        String customerParentelControlLevel = "PG";
        String movieId = "Something Went Wrong";  // this is used to simulate TechnicalFailureException in MovieService.getParentalControlLevel
        assertFalse(instance.canWatchMovie(customerParentelControlLevel, movieId));
    }

    @Test(expected = TitleNotFoundException.class)
    public void voidtestCanWatchMovie5() throws Exception {
        System.out.println("canWatchMovie5");
        String customerParentelControlLevel = "12";
        String movieId = "Unknown";
        instance.canWatchMovie(customerParentelControlLevel, movieId);
    }
}
