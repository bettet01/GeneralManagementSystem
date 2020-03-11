/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dao;

/**
 *
 * @author briannaschladweiler
 */
public class GmsDaoException extends Exception {

    public GmsDaoException(String message) {
        super(message);
    }

    public GmsDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
