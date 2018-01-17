package ba.unsa.etf.bp.udat;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ba.unsa.etf.bp.udat.ba.unsa.etf.bp.udat.models.LoginRequestData;
import ba.unsa.etf.bp.udat.ba.unsa.etf.bp.udat.models.LoginResponseData;

public class LoginRequestTask extends AsyncTask<LoginRequestData, Integer, LoginResponseData> {

    public interface OnLoginRequestDone {
        public void onDone (LoginResponseData responseData);
    }
    private OnLoginRequestDone caller;
    public LoginRequestTask(OnLoginRequestDone caller) {
        this.caller = caller;
    }
    @Override
    protected LoginResponseData doInBackground(LoginRequestData... data) {
        try {
            String loginUrl = "http://192.168.0.13:8090/login";
            RestTemplate restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            String res = restTemplate.postForObject(loginUrl, data[0], String.class);
            JSONObject responseObj = new JSONObject(res);
            return new LoginResponseData(responseObj.getString("role"), responseObj.getString("jwt"));
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(LoginResponseData response) {
        super.onPostExecute(response);
        caller.onDone(response);
    }
}
