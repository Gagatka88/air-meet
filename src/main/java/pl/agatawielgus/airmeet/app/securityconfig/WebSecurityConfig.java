package pl.agatawielgus.airmeet.app.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
    DataSource securityDataSource;

    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource).
                usersByUsernameQuery("select username,password, enabled from users where username=?").
                authoritiesByUsernameQuery("select username, authority from authorities where username=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll();

        http
                .authorizeRequests()
                .antMatchers("/loggedin/**")
                .access("hasRole('USER')");

        http
                .authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");



        http
                .authorizeRequests()
                .and()
                .formLogin()
                .loginProcessingUrl("/authenticateTheUser")
                .loginPage("/login")
                .defaultSuccessUrl("/loggedin", true)
                .permitAll()
                .and()
                .logout()
                .permitAll();


    }
}

