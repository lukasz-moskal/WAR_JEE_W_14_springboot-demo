package pl.coderslab.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authentication
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                    "select u.username, u.password, u.enabled from users u where u.username = ?");

            /*.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER")
            .and()
            .withUser("user").password(passwordEncoder().encode("user")).roles("USER");*/

        /*auth.inMemoryAuthentication()
            .withUser("admin").password("{noop}admin").roles("ADMIN", "USER")
            .and()
            .withUser("user").password("{noop}user").roles("USER");*/
    }

    // authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            //.csrf().disable()
            .authorizeRequests()
            //.antMatchers("/**").authenticated()
            .antMatchers("/login").permitAll()
            .antMatchers("/book/list").authenticated()
            .anyRequest().hasRole("ADMIN")
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/book/list").and()
            .logout().logoutSuccessUrl("/login?test");

    }
}
