package com.appetiser.ituneflix.api.rxfit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        /**
         * Prepare this for now but main functionality is
         *
         * This is very important to apps API Request functionality
         * It enables you to intercept the request and add another header
         * if user state is not logged in or not. In this app since the functionality
         * is search only.. so we return the original request.
         *
         * Nothing to add to intercepted request if:
         * a) Authorization value is empty because user is not logged in yet
         * b) There is already a header with updated Authorization value
         *
         */
        Request originalRequest = chain.request();
        return chain.proceed(originalRequest);
    }
}
