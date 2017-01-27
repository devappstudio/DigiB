package dev.mobile.digibanq.endpoints;

import dev.mobile.digibanq.api.*;
import retrofit.Call;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Field;
import retrofit.http.POST;

/**
 * Created by finxl-isaac on 12/4/16.
 */

public interface ApiEndpoints {
    @FormUrlEncoded
    @POST("register")
    Call<UserDetails> register(@Field("full_name") String first, @Field("dob") String dob,@Field("phone") String phone, @Field("email") String email, @Field("password") String password,@Field("code") String code);
    @FormUrlEncoded
    @POST("login")
    Call<UserDetails> login(@Field("phone") String phone,@Field("password") String password);
    @POST("forgot_password")
    Call<UserDetails> forgot_password(@Field("email") String email,@Field("code") String code);
    //
    @POST("new_password")
    Call<UserDetails> new_password(@Field("email") String email,@Field("password") String password);

}
