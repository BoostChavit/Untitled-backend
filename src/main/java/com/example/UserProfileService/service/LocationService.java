package com.example.UserProfileService.service;

import com.example.UserProfileService.model.LocationResponse;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class LocationService {
    private DatabaseReader dbReader;
    private String path = "./src/main/resources/GeoLite2-City_20231024/GeoLite2-City.mmdb";

    public LocationService() throws IOException {
        File database = new File(path);
        dbReader = new DatabaseReader.Builder(database).build();
    }

    // เอา ip มาหาที่อยู่ปัจจุบัน
    public LocationResponse getLocation(String ip)
            throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String cityName = response.getCity().getName();
        String country = response.getCountry().getName();
        String latitude =
                response.getLocation().getLatitude().toString();
        String longitude =
                response.getLocation().getLongitude().toString();
        return new LocationResponse(ip, cityName, country, latitude, longitude);
    }

    // คำนวณระยะห่างระหว่าง 2 คนจาก ip
    // คืนค่าเป็นกิโลเมตร
    private double calDistance(String ip, String ip2) throws IOException, GeoIp2Exception {

        InetAddress ipAddress2 = InetAddress.getByName(ip2);
        CityResponse response2 = dbReader.city(ipAddress2);

        Double lat2 =
                response2.getLocation().getLatitude();
        Double lon2 =
                response2.getLocation().getLongitude();



        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        Double lat1 =
                response.getLocation().getLatitude();
        Double lon1 =
                response.getLocation().getLongitude();

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers
        double r = 6371;

        // calculate the result
        return(c * r);
    }
}
