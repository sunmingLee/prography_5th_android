package com.example.sunmin_project;

import android.content.ContentValues;
import android.os.Build;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class httpConnection {

    private HttpURLConnection conn;

    private HostnameVerifier TRUSTED_VERIFIER;
    private SSLSocketFactory TRUSTED_FACTORY;

    public void getResponse() {

        {
            URL urlObj;
            String response = "";
            int responseCode = -1;
            BufferedReader br = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {

                String url = "https://ghibliapi.herokuapp.com/films";
                urlObj = new URL(url);
                conn = (HttpURLConnection) urlObj.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "MyApplication/json; charset=utf-8");


                if (url.startsWith("https")) {
                    ((HttpsURLConnection) conn).setHostnameVerifier(getTrustedVerifier());
                    ((HttpsURLConnection) conn).setSSLSocketFactory(getTrustedFactory());
                }

                conn.setInstanceFollowRedirects(false);



                //outputStream.close();

                responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    inputStream = conn.getInputStream();
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }

                } else {
                    response = "";
                }
                HttpStore.getInstance().setResult(response);
                Log.d("HONG", "getResponse: "+response);

            } catch (Exception e) {
            } finally {
                if (br != null) try {
                    br.close();
                } catch (IOException e) {
                }
                if (conn != null) {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (Exception e) {
                    }

                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                        }
                    }
                    try {
                        InputStream errorStream = conn.getErrorStream();
                        if (errorStream != null) {
                            errorStream.close();
                        }
                    } catch (Exception e) {
                    }
                    conn.disconnect();
                    conn = null;
                }
            }

        }
    }

    private HostnameVerifier getTrustedVerifier() {
        if (TRUSTED_VERIFIER == null)
            TRUSTED_VERIFIER = new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    // TODO Auto-generated method stub
                    return true;
                }
            };

        return TRUSTED_VERIFIER;
    }

    /**
     * Configure HTTPS connection to trust all certificates
     * <p>
     * This method does nothing if the current request is not a HTTPS request
     *
     * @return this request
     * @throws 'HttpRequestException
     */
    private SSLSocketFactory getTrustedFactory() {
        if (TRUSTED_FACTORY == null) {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    // Intentionally left blank
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    try {
                        chain[0].checkValidity();
                    } catch (Exception e) {
                        throw new CertificateException("Certificate not valid or trusted.");
                    }
                }
            }};
            try {
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, trustAllCerts, new SecureRandom());
                TRUSTED_FACTORY = context.getSocketFactory();
            } catch (GeneralSecurityException e) {
            }
        }

        return TRUSTED_FACTORY;
    }
}
