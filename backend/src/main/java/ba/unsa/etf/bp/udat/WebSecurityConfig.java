package ba.unsa.etf.bp.udat;

/**
 * Created by Edin on 09.11.2017..
 */

import ba.unsa.etf.bp.udat.filters.JWTAuthenticationFilter;
import ba.unsa.etf.bp.udat.filters.JWTLoginFilter;
import ba.unsa.etf.bp.udat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_USER = "USER";

    @Autowired
    private UserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                //.antMatchers("/**").permitAll();
               // .antMatchers("/**").authenticated()
                /*.antMatchers("/").hasAnyRole(ROLE_ADMIN,ROLE_USER)
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()*/
                .antMatchers("/import/**").hasRole(ROLE_ADMIN)
               /* .antMatchers("/enrollment/**").permitAll()//hasAnyRole(ROLE_ADMIN,ROLE_USER)
                .antMatchers("/exams/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers("/attendance/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers("/academic_years/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers("/courses/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers("/departments/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers("/lecturers/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers("/semesters/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers("/times/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers("/prediction/**").permitAll()//hasAnyRole(ROLE_ADMIN, ROLE_USER)*/
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

}
