package com.ajiranet.exception;

public class RouteNotExist extends Exception{
    public RouteNotExist() {
        super("ERROR: Route not found");
    }
}
