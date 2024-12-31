package com.example.monitor.api;

import com.example.monitor.Earthquake;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {

    public interface Service {
        @GET("all_hour.geojson")
        Call<EarthquakeJSONResponse> getEarthquakes();

        //@POST("earthquakes")
        //Call<EarthquakeJSONResponse> createEarthquake(@Body Earthquake earthquake);

        //@PUT("earthquakes/{id}")
        //Call<EarthquakeJSONResponse> updateEarthquake(@Path("id") int id, @Body Earthquake earthquake);
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    private Service service;

    private static final ApiClient apiClient = new ApiClient();

    public static ApiClient getInstance(){

        return apiClient;
    }

    private ApiClient(){

    }

    public Service getService(){
        if(service==null){
            service = retrofit.create(Service.class);
        }
        return service;
    }
}
