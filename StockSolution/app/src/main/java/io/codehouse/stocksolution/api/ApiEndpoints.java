package io.codehouse.stocksolution.api;


import java.util.List;

import io.codehouse.stocksolution.models.Expenses;
import io.codehouse.stocksolution.models.Income;
import io.codehouse.stocksolution.models.ProductCategories;
import io.codehouse.stocksolution.models.Products;
import io.codehouse.stocksolution.models.Stock;
import io.codehouse.stocksolution.models.StockSessions;
import io.codehouse.stocksolution.models.Stores;
import io.codehouse.stocksolution.models.User;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by finxl-isaac on 12/4/16.
 */

public interface ApiEndpoints {
    @FormUrlEncoded
    @POST("register")
    Call<User> register(@Field("full_name") String first, @Field("username") String username,@Field("password") String password);
    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("username") String username, @Field("password") String password);
    //
    @GET("list_expenses")
    Call<List<Expenses>> list_expenses();

    @GET("list_income")
    Call<List<Income>> list_income();

    @GET("list_products")
    Call<List<Products>> list_products();

    @GET("list_product_categories")
    Call<List<ProductCategories>> list_product_categories();
    @GET("list_stock_details")
    Call<List<Stock>> list_stock_details();
    @GET("list_stock_sessions")
    Call<List<StockSessions>> list_stock_sessions();
    @GET("list_stores")
    Call<List<Stores>> list_stores();
    @GET("list_users")
    Call<List<User>> list_users();

}
