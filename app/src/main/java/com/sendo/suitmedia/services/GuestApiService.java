package com.sendo.suitmedia.services;

import com.sendo.suitmedia.models.Guest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GuestApiService {

    @GET("596dec7f0f000023032b8017")
    Call<List<Guest>> getGuest();
}
