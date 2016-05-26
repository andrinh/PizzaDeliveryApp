package com.rhinoactive.pizzadeliveryapp.networking;

import com.google.gson.Gson;
import com.rhinoactive.pizzadeliveryapp.model.Order;
import com.rhinoactive.pizzadeliveryapp.utils.Constants;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Matt on 5/25/16.
 */
public class PizzaHTTPClient {
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    static Gson gson = new Gson();

    public static Call isServerRunning() throws Exception {
        OkHttpClient client = configureHTTPClient();

        Request request = new Request.Builder()
                .url(Constants.SERVER_URL)
                .get()
                .build();

        return client.newCall(request);
    }

    public static Call orderPizza(Order order) throws Exception {

        OkHttpClient client = configureHTTPClient();

        Request request = new Request.Builder()
                .url(Constants.SERVER_URL + "order")
                .post(RequestBody.create(JSON, gson.toJson(order)))
                .build();

        return client.newCall(request);
    }

    private static final HttpUrl API_URL = HttpUrl.parse(Constants.SERVER_URL);

    private static HttpUrl requestUrl(String endpoint, HashMap<String, String> params) {
        HttpUrl.Builder tmp =  API_URL.newBuilder()
                .setPathSegment(0, endpoint);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            tmp.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return tmp.build();
    }


    private static SSLContext setCertContext() throws Exception {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);
        return ctx;
    }

    private static OkHttpClient configureHTTPClient() throws Exception {
        SSLContext ctx = setCertContext();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(ctx.getSocketFactory())
                .build();

        return client;
    }


    private static class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }
}
