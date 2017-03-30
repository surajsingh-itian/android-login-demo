package com.phyder.myhelper.net;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created on 24/03/17.
 *
 * @author Suraj Singh
 *         Phyder Solutions Private Limited, a division of, Cyber Manager Software Services Private Limited
 */

public interface LoginService {

    @POST("login")
    Call<LoginResponse> login(@NonNull @Body LoginRequest request);
}
