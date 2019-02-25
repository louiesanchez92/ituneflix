package com.appetiser.ituneflix.api.rxfit;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class ApiAuthenticator implements Authenticator {

    /**
     * API Authenticator is very important
     * It detects if the request has an expired oauth token
     * so in this class we can renew the oauth token here.
     *
     * But in this app, this is not useful since no oauth token
     * is receive from the api. but for future use. I'll just put it here.
     *
     * @param route
     * @param response
     * @return
     * @throws IOException
     */

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        return null;
    }
}
