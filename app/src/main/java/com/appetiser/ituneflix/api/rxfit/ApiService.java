package com.appetiser.ituneflix.api.rxfit;

import com.appetiser.ituneflix.api.ApiConstants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    /**
     * Setting up retrofit api request
     */

    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            /**
             * Initializing http client
             * for api request timeout and added
             * interceptor, authenticator and host and ssl socket verifier
             */

            OkHttpClient client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new ApiInterceptor())
                    .authenticator(new ApiAuthenticator())
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .hostnameVerifier(ApiHostAndSocketVerifier.getHostnameVerifier())
                    .sslSocketFactory(ApiHostAndSocketVerifier.getUnsafeSSLFactory())
                    .build();

            /**
             * Initializing retrofit and
             * added gsonconverterfactory to convert
             * api response to GSON format
             */
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

        }

        return retrofit;
    }

}
