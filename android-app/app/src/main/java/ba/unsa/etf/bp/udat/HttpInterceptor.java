package ba.unsa.etf.bp.udat;

import android.content.Context;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

        import java.io.IOException;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpRequest;
        import org.springframework.http.client.ClientHttpRequestExecution;
        import org.springframework.http.client.ClientHttpRequestInterceptor;
        import org.springframework.http.client.ClientHttpResponse;

public class HttpInterceptor implements ClientHttpRequestInterceptor {
    private static Context context;
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        String jwt = SessionService.getJwt(HttpInterceptor.context);
        if (jwt != null)
            headers.add("Authorization", "Bearer " + jwt);
        return execution.execute(request, body);
    }

    public static void setContext(Context context) {
        HttpInterceptor.context = context;
    }
}