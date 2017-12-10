package ba.unsa.etf.bp.udat.services;



import ba.unsa.etf.bp.udat.models.User;
import ba.unsa.etf.bp.udat.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import java.io.IOException;
import javax.json.JsonObjectBuilder;
import javax.json.JsonObject;
import javax.json.Json;



public class TokenAuthenticationService {

    private static UserRepository userRepository;
    private static Logger logger = Logger.getLogger(TokenAuthenticationService.class.getName());

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String FIELD_NAME_TOKEN = "jwt";
    static final String FIELD_NAME_ROLE = "role";
    static final String FIELD_NAME_USERNAME = "username";
    static final String HEADER_STRING = "Authorization";

    public static User findUserByToken(String token) {
        String username = parseJwt(token);

        if (username == null) {
            return null;
        }

        return userRepository.findByUsername(username);
    }

    public static String parseJwt(String token) {
        if (token != null) {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
        }

        return null;
    }
    public static void addAuthentication(HttpServletRequest req,
                                         HttpServletResponse res, String username) throws IOException{
        if (userRepository == null) {
            ServletContext servletContext = req.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userRepository = webApplicationContext.getBean(UserRepository.class);
        }
        User user = userRepository.findByUsername(username);
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add(FIELD_NAME_USERNAME, username)
                .add(FIELD_NAME_TOKEN, JWT)
                .add(FIELD_NAME_ROLE, user.getRole().getName());

        JsonObject responseObj = objectBuilder.build();
        logger.info(responseObj.toString());
        res.getWriter().write(responseObj.toString());
    }

    public static Authentication getAuthentication(HttpServletRequest request) {

        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        userRepository = webApplicationContext.getBean(UserRepository.class);

        String userReq = parseJwt(request.getHeader(HEADER_STRING));

        User user = userRepository.findByUsername(userReq);

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (user != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        }
        else {
            userReq = null;
        }

        return userReq != null ? new UsernamePasswordAuthenticationToken(userReq, null, grantedAuthorities) : null;
    }
}
