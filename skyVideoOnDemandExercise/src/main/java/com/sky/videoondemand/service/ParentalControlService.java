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
public interface ParentalControlService {

    boolean  canWatchMovie(String customerParentalControlLevel, String movieId) throws TitleNotFoundException;

}
