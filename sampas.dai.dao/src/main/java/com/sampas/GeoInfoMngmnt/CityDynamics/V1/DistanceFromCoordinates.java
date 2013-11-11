package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

public class DistanceFromCoordinates {
    private static double earthRadius = 6371.2; 

    public static double getDistanceFromCoordinatesDeg ( double lon1, double lat1, double lon2, double lat2 ) {
        double a1 = lon1 * 3.1415926535897932385 / 180.0;
        double b1 = lat1 * 3.1415926535897932385 / 180.0;
        double a2 = lon2 * 3.1415926535897932385 / 180.0;
        double b2 = lat2 * 3.1415926535897932385 / 180.0;
        double distance = arccos ( cos ( a1 - a2 ) * cos ( b1 ) * cos ( b2 ) + sin ( b1 ) * sin ( b2 ) ) * earthRadius;
       
        return distance;
    }
    // 
    public static double getDistanceFromCoordinatesRad ( double lon1, double lat1, double lon2, double lat2 ) {
        double a1 = lon1;
        double b1 = lat1;
        double a2 = lon2;
        double b2 = lat2;
        double distance = arccos ( cos ( a1 - a2 ) * cos ( b1 ) * cos ( b2 ) + sin ( b1 ) * sin ( b2 ) ) * earthRadius;
        // Km distance
        return distance;
    }

    private static double cos (double a ) {
        return Math.cos ( a );
    }

    private static double sin ( double a ) {
        return Math.sin ( a );
    }

    private static double arccos ( double a ) {
        return Math.acos ( a );
    }
}
