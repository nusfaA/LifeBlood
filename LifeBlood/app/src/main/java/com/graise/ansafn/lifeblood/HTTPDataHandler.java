package com.graise.ansafn.lifeblood;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by ansaf.n on 2/23/2018.
 */

public class HTTPDataHandler {
    static String stream = null;

    public HTTPDataHandler() {

    }

    public String GetHTTPData(String urlstring) {
        try {
            URL url = new URL(urlstring);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

            //connection status
            if (urlconnection.getResponseCode() == 200) {
                InputStream in = new BufferedInputStream(urlconnection.getInputStream());

                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = r.readLine()) != null) {
                    sb.append(line);
                    stream = sb.toString();
                    urlconnection.disconnect();
                }

            } else {

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }

    public void PostHTTPData(String urlstring, String json) {
        try {
            URL url = new URL(urlstring);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

            urlconnection.setRequestMethod("POST");
            urlconnection.setDoOutput(true);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlconnection.setFixedLengthStreamingMode(length);
            urlconnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlconnection.connect();
            try (OutputStream os = urlconnection.getOutputStream()) {
                os.write(out);
            }

            InputStream response = urlconnection.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PutHTTPData(String urlstring, String newValue) {
        try {
            URL url = new URL(urlstring);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

            urlconnection.setRequestMethod("PUT");
            urlconnection.setDoOutput(true);

            byte[] out = newValue.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlconnection.setFixedLengthStreamingMode(length);
            urlconnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlconnection.connect();
            try (OutputStream os = urlconnection.getOutputStream()) {
                os.write(out);
            }

            InputStream response = urlconnection.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DeleteHTTPData(String urlstring, String json) {
        try {
            URL url = new URL(urlstring);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

            urlconnection.setRequestMethod("DELETE");
            urlconnection.setDoOutput(true);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlconnection.setFixedLengthStreamingMode(length);
            urlconnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlconnection.connect();
            try (OutputStream os = urlconnection.getOutputStream()) {
                os.write(out);
            }

            InputStream response = urlconnection.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
