package ba.unsa.etf.bp.udat.filters;

/**
 * Created by Edin on 09.11.2017..
 */

import ba.unsa.etf.bp.udat.models.User;
import ba.unsa.etf.bp.udat.repositories.UserRepository;
import ba.unsa.etf.bp.udat.services.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static UserRepository userRepository;
    static final String HEADER_CORS = "Access-Control-Allow-Origin";
    static final String ALLOWED_ORIGIN = "*";

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        res.addHeader(HEADER_CORS, ALLOWED_ORIGIN);

        ServletContext servletContext = req.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        userRepository = webApplicationContext.getBean(UserRepository.class);
        AccountCredentials creds;
        if(!req.getMethod().equals("POST")) {
            creds = new AccountCredentials();
            creds.setUsername("");
            creds.setPassword("");
        } else {
            creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);
        }
        User user = userRepository.findByUsername(creds.getUsername());

        String password = creds.getPassword().toString();
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();

            for (int i = 0; i < digest.length; i++)
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (user != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        }

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        sb.toString(),
                        grantedAuthorities
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        TokenAuthenticationService
                .addAuthentication(req, res, auth.getName());
    }
}
