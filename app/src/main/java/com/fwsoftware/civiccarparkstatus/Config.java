package com.fwsoftware.civiccarparkstatus;

import android.Manifest;

public class Config {
    public static final String CAR_PARK_URL;
    public static final String CAR_PARK_URL_SIMPLE;
    public static final String[] PERMISSIONS;

    static {
        CAR_PARK_URL ="https://at.govt.nz/umbraco/Surface/ParkingAvailabilitySurface/ParkingAvailabilityResult?carparkIdParam=civic,%20downtown,%20victoria%20st";
        CAR_PARK_URL_SIMPLE ="https://at.govt.nz/umbraco/Surface/ParkingAvailabilitySurface/ParkingAvailabilityResult?carparkIdParam=civic, downtown, victoria st&categoryParam=short-term";
        PERMISSIONS = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE
        };
    }
    public static  String getCarParkUrl(boolean simple){
        if(simple)
            return CAR_PARK_URL_SIMPLE;
        return CAR_PARK_URL;
    }
}
