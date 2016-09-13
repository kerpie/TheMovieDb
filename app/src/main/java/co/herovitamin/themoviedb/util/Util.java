package co.herovitamin.themoviedb.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import co.herovitamin.themoviedb.network.API;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {

    public final static String mBaseURL = "http://api.themoviedb.org/3/";

    public static API createNetworkClient(){
        Retrofit mRetrofit = new Retrofit.Builder()
                                .baseUrl(mBaseURL)
                                .addConverterFactory(
                                        GsonConverterFactory.create(
                                                new GsonBuilder()
                                                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                                        .create()
                                        )
                                )
                                .build();

        return mRetrofit.create(API.class);
    }
}