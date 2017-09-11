package edu.berliner.week7challenge;


import edu.berliner.week7challenge.repositories.PersonUserRepository;
import edu.berliner.week7challenge.services.SSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private SSUserDetailsService userDetailsService;

    @Autowired
    private PersonUserRepository userRepo;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception
    {
        return new SSUserDetailsService(userRepo);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**","/img/**","/signup","/home").permitAll()
                .antMatchers("/addjob")
                    .hasAuthority("RECRUITER")
                .antMatchers("/addeducation2","/addexp2","/addskill2","/submiteducation","/submitexp","/submitperson","/submitskill")
                    .hasAnyAuthority("JOBSEEKER")
                .antMatchers("/addeducation","/addexp","/addskill")
                    .hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();
    }

}
