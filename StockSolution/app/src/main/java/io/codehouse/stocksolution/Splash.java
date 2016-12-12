package io.codehouse.stocksolution;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import io.codehouse.stocksolution.Services.SyncService;
import io.codehouse.stocksolution.api.ApiEndpoints;
import io.codehouse.stocksolution.api.ApiLocation;
import io.codehouse.stocksolution.db.*;
import io.codehouse.stocksolution.db.User;
import io.codehouse.stocksolution.models.*;
import io.codehouse.stocksolution.models.Expenses;
import io.codehouse.stocksolution.models.Income;
import io.codehouse.stocksolution.models.ProductCategories;
import io.codehouse.stocksolution.models.Products;
import io.codehouse.stocksolution.models.Stock;
import io.codehouse.stocksolution.models.StockSessions;
import io.codehouse.stocksolution.models.Stores;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        if (!isMyServiceRunning(SyncService.class)) {
            //Start it
            Intent intent = new Intent(getApplicationContext(),
                    SyncService.class);
            startService(intent);
        } else {
        }

        Splash.loadData task = new Splash.loadData();
        task.execute();


    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /*

         function _get()
     {
        $this->response($this->db->get('expenses',array('id'=>$data['server_id']))->result_array(), 200);
     }

     function _get()
     {
        $this->response($this->db->get('income',array('id'=>$data['server_id']))->result_array(), 200);
     }

    function _get()
     {
        $this->response($this->db->get('products',array('id'=>$data['server_id']))->result_array(), 200);
     }
          function _get()
     {
        $this->response($this->db->get('product_categories',array('id'=>$data['server_id']))->result_array(), 200);
     }
          function _get()
     {
        $this->response($this->db->get('stock_details',array('id'=>$data['server_id']))->result_array(), 200);
     }
          function _get()
     {
        $this->response($this->db->get('stock_session',array('id'=>$data['server_id']))->result_array(), 200);
     }
          function _get()
     {
        $this->response($this->db->get('stores',array('id'=>$data['server_id']))->result_array(), 200);
     }
          function _get()
     {
        $this->response($this->db->get('users',array('id'=>$data['server_id']))->result_array(), 200);
     }

     */


    void getStores()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);
        Call<List<io.codehouse.stocksolution.models.Stores>> data = endpoints.list_stores();
        data.enqueue(new Callback<List<Stores>>() {
            @Override
            public void onResponse(Response<List<Stores>> response, Retrofit retrofit) {
                Realm realm = Realm.getDefaultInstance();
                List<Stores>  storesList = response.body();
                for (int i= 0; i<storesList.size(); i++)
                {
                    Stores currentStore = storesList.get(i);
                    RealmResults<io.codehouse.stocksolution.db.Stores> realmResults = realm.where(io.codehouse.stocksolution.db.Stores.class).equalTo("server_id",currentStore.getId()).findAll();
                    if(realmResults.isEmpty())
                    {
                        //New
                        io.codehouse.stocksolution.db.Stores stores = new io.codehouse.stocksolution.db.Stores();
                        realm.beginTransaction();
                        stores.setId(realm.where(io.codehouse.stocksolution.db.Stores.class).max("id").intValue() + 1);
                        stores.setServer_id(currentStore.getId());
                        stores.setLast_visit(currentStore.getLast_visit());
                        stores.setLocation(currentStore.getLocation());
                        stores.setName(currentStore.getName());
                        stores.setNext_visit(currentStore.getNext_visit());
                        stores.setTelephone(currentStore.getTelephone());
                        realm.copyToRealmOrUpdate(stores);
                        realm.commitTransaction();
                    }
                    else
                    {
                        //exists
                        io.codehouse.stocksolution.db.Stores stores = realm.where(io.codehouse.stocksolution.db.Stores.class).equalTo("server_id",currentStore.getId()).findFirst();
                        realm.beginTransaction();
                        stores.setLast_visit(currentStore.getLast_visit());
                        stores.setLocation(currentStore.getLocation());
                        stores.setName(currentStore.getName());
                        stores.setNext_visit(currentStore.getNext_visit());
                        stores.setTelephone(currentStore.getTelephone());
                        realm.copyToRealmOrUpdate(stores);
                        realm.commitTransaction();
                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }
        });

    }
    void  getStockSessions()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);
        Call<List<io.codehouse.stocksolution.models.StockSessions>> data = endpoints.list_stock_sessions();
        data.enqueue(new Callback<List<StockSessions>>() {
            @Override
            public void onResponse(Response<List<StockSessions>> response, Retrofit retrofit) {
                Realm realm = Realm.getDefaultInstance();
                List<StockSessions> stockSessionsList = response.body();
                for (int i= 0; i< stockSessionsList.size(); i++)
                {
                    StockSessions currentStockSessions = stockSessionsList.get(i);
                    RealmResults<io.codehouse.stocksolution.db.StockSessions> realmResults = realm.where(io.codehouse.stocksolution.db.StockSessions.class).equalTo("server_id",currentStockSessions.getId()).findAll();
                    if(realmResults.isEmpty())
                    {
                        //New
                        io.codehouse.stocksolution.db.StockSessions stockSessions = new io.codehouse.stocksolution.db.StockSessions();
                        realm.beginTransaction();
                        stockSessions.setId(realm.where(io.codehouse.stocksolution.db.Stock.class).max("id").intValue() + 1);
                        stockSessions.setServer_id(currentStockSessions.getId());
                        stockSessions.setDate_closed(currentStockSessions.getDate_closed());
                        stockSessions.setDate_opened(currentStockSessions.getDate_opened());
                        //TODO check if there is a store offline with the same id and put there
                        stockSessions.setId_store(0);
                        stockSessions.setId_store_server(currentStockSessions.getId_store());
                        //TODO check if there is a store offline with the same id and put there
                        stockSessions.setId_user_closed(0);
                        stockSessions.setId_user_closed_server(currentStockSessions.getId_user_closed());
                        //TODO check if there is a store offline with the same id and put there
                        stockSessions.setId_user_opened(0);
                        stockSessions.setId_user_opened_server(currentStockSessions.getId_user_opened());
                        realm.copyToRealmOrUpdate(stockSessions);
                        realm.commitTransaction();
                    }
                    else
                    {
                        //Exist
                        io.codehouse.stocksolution.db.StockSessions stockSessions = realm.where(io.codehouse.stocksolution.db.StockSessions.class).equalTo("server_id",currentStockSessions.getId()).findFirst();
                        realm.beginTransaction();
                        stockSessions.setDate_closed(currentStockSessions.getDate_closed());
                        stockSessions.setDate_opened(currentStockSessions.getDate_opened());
                        //TODO check if there is a store offline with the same id and put there
                        stockSessions.setId_store(0);
                        stockSessions.setId_store_server(currentStockSessions.getId_store());
                        //TODO check if there is a store offline with the same id and put there
                        stockSessions.setId_user_closed(0);
                        stockSessions.setId_user_closed_server(currentStockSessions.getId_user_closed());
                        //TODO check if there is a store offline with the same id and put there
                        stockSessions.setId_user_opened(0);
                        stockSessions.setId_user_opened_server(currentStockSessions.getId_user_opened());
                        realm.copyToRealmOrUpdate(stockSessions);
                        realm.commitTransaction();

                    }


                }


            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }
        });

    }
    void getStockDetails()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);
        Call<List<io.codehouse.stocksolution.models.Stock>> data = endpoints.list_stock_details();
        data.enqueue(new Callback<List<io.codehouse.stocksolution.models.Stock>>() {
            @Override
            public void onResponse(Response<List<Stock>> response, Retrofit retrofit) {

                List<Stock> stockList = response.body();
                Realm realm = Realm.getDefaultInstance();
                for (int i = 0; i<stockList.size();i++)
                {
                    Stock currentStock = stockList.get(i);
                    RealmResults<io.codehouse.stocksolution.db.Stock> realmResults = realm.where(io.codehouse.stocksolution.db.Stock.class).equalTo("server_id",currentStock.getId()).findAll();
                    if(realmResults.isEmpty())
                    {
                        io.codehouse.stocksolution.db.Stock stock = new io.codehouse.stocksolution.db.Stock();
                        realm.beginTransaction();
                        stock.setId(realm.where(io.codehouse.stocksolution.db.Stock.class).max("id").intValue() + 1);
                        stock.setServer_id(currentStock.getId());
                        stock.setId_product(0);
                        stock.setId_product_server(currentStock.getId_product());
                        stock.setId_stock_session(0);
                        stock.setId_stock_session_server(currentStock.getId_stock_session());
                        stock.setId_user(0);
                        stock.setId_user_server(currentStock.getId_user());
                        stock.setPurchasing_price(currentStock.getPurchasing_price());
                        stock.setQuantity(currentStock.getQuantity());
                        stock.setTimestamp(currentStock.getTimestamp());
                        realm.copyToRealmOrUpdate(stock);
                        realm.commitTransaction();
                    }
                    else
                    {
                        io.codehouse.stocksolution.db.Stock stock = realm.where(io.codehouse.stocksolution.db.Stock.class).equalTo("server_id",currentStock.getId()).findFirst();
                        realm.beginTransaction();
                        stock.setId_product(0);
                        stock.setId_product_server(currentStock.getId_product());
                        stock.setId_stock_session(0);
                        stock.setId_stock_session_server(currentStock.getId_stock_session());
                        stock.setId_user(0);
                        stock.setId_user_server(currentStock.getId_user());
                        stock.setPurchasing_price(currentStock.getPurchasing_price());
                        stock.setQuantity(currentStock.getQuantity());
                        stock.setTimestamp(currentStock.getTimestamp());
                        realm.copyToRealmOrUpdate(stock);
                        realm.commitTransaction();

                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }
        });

    }
    void getProductCategories()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);
        Call<List<io.codehouse.stocksolution.models.ProductCategories>> data = endpoints.list_product_categories();
        data.enqueue(new Callback<List<ProductCategories>>() {
            @Override
            public void onResponse(Response<List<ProductCategories>> response, Retrofit retrofit) {

                List<ProductCategories> productCategoriesList = response.body();
                Realm realm = Realm.getDefaultInstance();

                for(int i=0; i<productCategoriesList.size(); i++)
                {
                    ProductCategories currentProductCategories = productCategoriesList.get(i);
                    RealmResults<io.codehouse.stocksolution.db.ProductCategories> realmResults = realm.where(io.codehouse.stocksolution.db.ProductCategories.class).equalTo("server_id",currentProductCategories.getId()).findAll();
                    if(realmResults.isEmpty())
                    {
                        io.codehouse.stocksolution.db.ProductCategories productCategories = new io.codehouse.stocksolution.db.ProductCategories();
                        realm.beginTransaction();
                        productCategories.setId(realm.where(io.codehouse.stocksolution.db.ProductCategories.class).max("id").intValue() +1);
                        productCategories.setServer_id(currentProductCategories.getId());
                        productCategories.setId_user_server(currentProductCategories.getId_user());
                        productCategories.setId_user(0);
                        productCategories.setId_store(0);
                        productCategories.setId_store_server(currentProductCategories.getId_store());
                        productCategories.setName(currentProductCategories.getName());
                        realm.copyToRealmOrUpdate(productCategories);
                        realm.commitTransaction();
                    }
                    else
                    {
                        io.codehouse.stocksolution.db.ProductCategories productCategories = realm.where(io.codehouse.stocksolution.db.ProductCategories.class).equalTo("server_id",currentProductCategories.getId()).findFirst();
                        realm.beginTransaction();
                        productCategories.setId_user_server(currentProductCategories.getId_user());
                        productCategories.setId_user(0);
                        productCategories.setId_store(0);
                        productCategories.setId_store_server(currentProductCategories.getId_store());
                        productCategories.setName(currentProductCategories.getName());
                        realm.copyToRealmOrUpdate(productCategories);
                        realm.commitTransaction();
                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }
        });

    }
    void getProducts()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);
        Call<List<io.codehouse.stocksolution.models.Products>> data = endpoints.list_products();
        data.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Response<List<Products>> response, Retrofit retrofit) {
                List<Products> productsList = response.body();
                Realm realm = Realm.getDefaultInstance();
                for (int i = 0; i<productsList.size();i++)
                {
                    Products currentProduct = productsList.get(i);
                    RealmResults<io.codehouse.stocksolution.db.Products> realmResults = realm.where(io.codehouse.stocksolution.db.Products.class).equalTo("server_id",currentProduct.getId()).findAll();
                    if(realmResults.isEmpty())
                    {
                        io.codehouse.stocksolution.db.Products products = new io.codehouse.stocksolution.db.Products();
                        realm.beginTransaction();
                        products.setId(realm.where(io.codehouse.stocksolution.db.Products.class).max("id").intValue() + 1);
                        products.setServer_id(currentProduct.getId());
                        products.setName(currentProduct.getName());
                        products.setId_product_category(0);
                        products.setId_product_category_server(currentProduct.getId_product_category());
                        products.setMeasurement(currentProduct.getMeasurement());
                        realm.copyToRealmOrUpdate(products);
                        realm.commitTransaction();
                    }
                    else
                    {
                        io.codehouse.stocksolution.db.Products products = realm.where(io.codehouse.stocksolution.db.Products.class).equalTo("server_id",currentProduct.getId()).findFirst();
                        realm.beginTransaction();
                        products.setName(currentProduct.getName());
                        products.setId_product_category(0);
                        products.setId_product_category_server(currentProduct.getId_product_category());
                        products.setMeasurement(currentProduct.getMeasurement());
                        realm.copyToRealmOrUpdate(products);
                        realm.commitTransaction();

                    }

                }

            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }
        });

    }
    void getIncome()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);
        Call<List<io.codehouse.stocksolution.models.Income>> data = endpoints.list_income();
        data.enqueue(new Callback<List<Income>>() {
            @Override
            public void onResponse(Response<List<Income>> response, Retrofit retrofit) {
                List<Income> incomeList = response.body();
                Realm realm = Realm.getDefaultInstance();
                for (int i = 0; i<incomeList.size();i++)
                {
                    Income currentIncome = incomeList.get(i);
                    RealmResults<io.codehouse.stocksolution.db.Income> realmResults = realm.where(io.codehouse.stocksolution.db.Income.class).equalTo("server_id",currentIncome.getId()).findAll();
                    if(realmResults.isEmpty())
                    {
                        io.codehouse.stocksolution.db.Income income = new io.codehouse.stocksolution.db.Income();
                        realm.beginTransaction();
                        income.setId(realm.where(io.codehouse.stocksolution.db.Income.class).max("id").intValue() + 1);
                        income.setId_product(0);
                        income.setId_server(currentIncome.getId());
                        income.setId_product_server(currentIncome.getId_product());
                        income.setId_stock_session(0);
                        income.setId_stock_session_server(currentIncome.getId_stock_session());
                        income.setId_user(0);
                        income.setId_user_server(currentIncome.getId_user());
                        income.setNarration(currentIncome.getNarration());
                        income.setPrice(currentIncome.getPrice());
                        income.setQuantity(currentIncome.getQuantity());
                        income.setTimestamp(currentIncome.getTimestamp());
                        realm.copyToRealmOrUpdate(income);
                        realm.commitTransaction();
                    }
                    else
                    {
                        io.codehouse.stocksolution.db.Income income = realm.where(io.codehouse.stocksolution.db.Income.class).equalTo("server_id",currentIncome.getId()).findFirst();
                        realm.beginTransaction();
                        income.setId_product(0);
                        income.setId_product_server(currentIncome.getId_product());
                        income.setId_stock_session(0);
                        income.setId_stock_session_server(currentIncome.getId_stock_session());
                        income.setId_user(0);
                        income.setId_user_server(currentIncome.getId_user());
                        income.setNarration(currentIncome.getNarration());
                        income.setPrice(currentIncome.getPrice());
                        income.setQuantity(currentIncome.getQuantity());
                        income.setTimestamp(currentIncome.getTimestamp());
                        realm.copyToRealmOrUpdate(income);
                        realm.commitTransaction();

                    }

                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }
        });

    }
    void getExpenses()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getApi())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);
        Call<List<io.codehouse.stocksolution.models.Expenses>> data = endpoints.list_expenses();
        data.enqueue(new Callback<List<Expenses>>() {
            @Override
            public void onResponse(Response<List<Expenses>> response, Retrofit retrofit) {
                List<Expenses> expensesList = response.body();
                Realm realm = Realm.getDefaultInstance();
                for (int i = 0; i<expensesList.size();i++)
                {
                    Expenses currentExpenses = expensesList.get(i);
                    RealmResults<io.codehouse.stocksolution.db.Expenses> realmResults = realm.where(io.codehouse.stocksolution.db.Expenses.class).equalTo("server_id",currentExpenses.getId()).findAll();
                    if(realmResults.isEmpty())
                    {
                        io.codehouse.stocksolution.db.Expenses expenses = new io.codehouse.stocksolution.db.Expenses();
                        realm.beginTransaction();
                        expenses.setId(realm.where(io.codehouse.stocksolution.db.Expenses.class).max("id").intValue() + 1);
                        expenses.setServer_id(currentExpenses.getId());
                        expenses.setId_user(0);
                        expenses.setId_user_server(currentExpenses.getId_user());
                        expenses.setTimestamp(currentExpenses.getTimestamp());
                        expenses.setNarration(currentExpenses.getNarration());
                        expenses.setCost(currentExpenses.getCost());
                        expenses.setExpense(currentExpenses.getExpense());
                        expenses.setId_stock_session(0);
                        expenses.setId_stock_session_server(currentExpenses.getId_stock_session());
                        realm.copyToRealmOrUpdate(expenses);
                        realm.commitTransaction();
                    }
                    else
                    {
                        io.codehouse.stocksolution.db.Expenses expenses = realm.where(io.codehouse.stocksolution.db.Expenses.class).equalTo("server_id",currentExpenses.getId()).findFirst();
                        realm.beginTransaction();
                        expenses.setId(realm.where(io.codehouse.stocksolution.db.Expenses.class).max("id").intValue() + 1);
                        expenses.setServer_id(currentExpenses.getId());
                        expenses.setId_user(0);
                        expenses.setId_user_server(currentExpenses.getId_user());
                        expenses.setTimestamp(currentExpenses.getTimestamp());
                        expenses.setNarration(currentExpenses.getNarration());
                        expenses.setCost(currentExpenses.getCost());
                        expenses.setExpense(currentExpenses.getExpense());
                        expenses.setId_stock_session(0);
                        expenses.setId_stock_session_server(currentExpenses.getId_stock_session());
                        realm.copyToRealmOrUpdate(expenses);
                        realm.commitTransaction();

                    }

                }


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    private class loadData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            getStockDetails();
            getExpenses();
            getIncome();
            getStores();
            getStockSessions();
            getProductCategories();
            getProducts();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Realm realm = Realm.getDefaultInstance();
            RealmResults<User> users = realm.where(User.class).findAll();


            if(users.isEmpty())
            {
                //login
                final Intent intent = new Intent(Splash.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
            else
            {
                //home
                final Intent intent = new Intent(Splash.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();

            }

        }
    }


}
