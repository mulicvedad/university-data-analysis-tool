package ba.unsa.etf.bp.udat;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpRequestTask extends AsyncTask<String, Integer, float[]> {
    public interface OnRequestDone {
        void onDone(float[] response, ChartHelper.ChartType chartType);
    }

    private OnRequestDone caller;
    private ChartHelper.ChartType chartType;

    public HttpRequestTask(OnRequestDone caller, ChartHelper.ChartType chartType) {
        this.caller = caller;
        this.chartType = chartType;
    }
    @Override
    protected float[] doInBackground(String... params) {
        try {
            String baseUrl = "http://192.168.0.13:8090";
            float[] response = new float[4];
            for (int i=0; i<params.length; i++) {
                String url =  baseUrl + params[i];
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                response[i] = restTemplate.getForObject(url, Float.class);
            }

            return response;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(float[] response) {
        super.onPostExecute(response);
        if (this.chartType == ChartHelper.ChartType.ATTENDANCE_PERCENTAGE)
            response[1] = 100 - response[0];
        caller.onDone(response, chartType);
    }
}
