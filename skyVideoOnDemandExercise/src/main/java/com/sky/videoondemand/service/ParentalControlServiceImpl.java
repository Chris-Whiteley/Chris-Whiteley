/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.videoondemand.service;

/**
 *
 * @author Chris
 */
public class ParentalControlServiceImpl implements ParentalControlService {

    private MovieService movieService;

    private enum ParentalControlLevelEnum {

        U, PG, Cert12, Cert15, Cert18
    };

    public ParentalControlServiceImpl(MovieService ms) {
        movieService = ms;
    }

    @Override
    public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws TitleNotFoundException {
        boolean result = false;
        try {
            ParentalControlLevelEnum customerLevel = toEnum(customerParentalControlLevel);
            ParentalControlLevelEnum movieLevel = toEnum(movieService.getParentalControlLevel(movieId));
            result = (customerLevel.compareTo(movieLevel) >= 0);
        } catch (TechnicalFailureException ex) {
        }

        return result;
    }

    private ParentalControlLevelEnum toEnum(String parentalControlLevel) {
        String level = parentalControlLevel;
        if (level.startsWith("1")) {
            level = "Cert" + level;
        }

        return ParentalControlLevelEnum.valueOf(level);
    }

}
