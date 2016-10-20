package com.yourfitonline.githubclient2;

import android.app.DownloadManager;
import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by evgenyshumakov on 19.10.16.
 */

public class ServiceGenerator {


    public static final String API_BASE_URL = "https://api.github.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <T> T createService(Class<T> serviceClass){
        return createService(serviceClass,null,null);
    }

    public static<T> T createService(Class<T> sericeClass, String login, String password){
        if(login != null && password != null){
            String credentials = login + ":" + password;

            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization",basic)
                            .header("Accept", "application/json")
                            .method(original.method(),original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(sericeClass);
    }


}
