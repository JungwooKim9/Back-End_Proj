package com.jung9.shop.config;

import com.jung9.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/img/**").permitAll() // CSS, JavaScript, 이미지 파일에 대한 접근 허용
                .antMatchers("/secure/**").authenticated() // /secure/** 경로에 대해서만 인증 필요
                .anyRequest().permitAll()
//                .and().csrf().disable();
                .and() // 이전 구성과 새로운 구성을 연결하는 and() 메서드
//                .authorizeRequests() // URL 접근 권한 설정
//                .antMatchers("/admin/**").hasRole("ADMIN") // "/admin/**" 패턴에 대한 접근은 "ADMIN" 역할이 필요
//                .antMatchers("/user/**").hasRole("USER") // "/user/**" 패턴에 대한 접근은 "USER" 역할이 필요
//                .anyRequest().permitAll()
//                .and()
                .formLogin() // 로그인 설정 추가
                .loginPage("/member/login") // 로그인 페이지 지정
                .loginProcessingUrl("/member/login")
                .passwordParameter("memberPassword")   // 모델명이 "password"인 경우 생략 가능
                .usernameParameter("memberEmail")   // 모델명이 "username"인 경우 생략 가능 username
                .defaultSuccessUrl("/", true) // 로그인 성공 후 이동할 경로 설정
                .permitAll() // 로그인 페이지에 접근 권한 허용
                .and()
                .logout() // 로그아웃 설정 추가
                .logoutUrl("/logout") // 로그아웃 URL을 지정: 안해준 경우 th:href="@{/logout}" 이런 식으로 경로 지정?
                .logoutSuccessUrl("/") // 로그아웃 성공 후 리다이렉트할 URL 지정
                .invalidateHttpSession(true) // 세션 무효화 설정
                .deleteCookies("JSESSIONID") // 로그아웃 시 삭제할 쿠키 설정 (선택 사항)
                .permitAll(); // 로그아웃 페이지에 접근 권한 허용
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 직접 구현한 UserDetailsService를 사용하도록 설정한다.
        auth.userDetailsService(memberService);
    }


}
