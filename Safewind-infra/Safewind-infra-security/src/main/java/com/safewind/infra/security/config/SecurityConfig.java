package com.safewind.infra.security.config;

import com.safewind.infra.security.aspect.PermitAllUrlProperties;
import com.safewind.infra.security.filter.JwtAuthenticationTokenFilter;
import com.safewind.infra.security.handle.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-25  09:55
 * @Description: 安全配置类
 * 这里是spring security的核心，快速了解security的一个入口
 * @EnableMethodSecurity：启用方法级别的安全控制，支持 @PreAuthorize 和 @PostAuthorize
 */
//@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class SecurityConfig {

    /**
     * 自定义用户认证逻辑
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 认证失败处理类
     */
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 退出处理类
     */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * 跨域配置
     *
     * @see com.safewind.common.config.WebMvcConfig
     */
    @Autowired
    private CorsFilter corsFilter;

    /**
     * token认证过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 允许访问的url
     */
    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    /**
     * anyRequest()        |   匹配所有请求路径，通常用于设置全局访问规则。
     * access(String)      |   根据传入的 Spring EL 表达式判断是否允许访问。
     * anonymous()         |   允许匿名用户（未登录用户）访问指定资源。
     * denyAll()           |   拒绝所有用户的访问请求，常用于禁止访问某些敏感资源。
     * fullyAuthenticated()|   用户必须通过完整登录认证（非 remember-me 自动登录）才能访问。
     * hasAnyAuthority(String...) |   如果用户拥有任意一个指定的权限（Authority），则允许访问。
     * hasAnyRole(String...)      |   如果用户拥有任意一个指定的角色（Role），则允许访问。
     * hasAuthority(String)       |   如果用户拥有指定的权限（Authority），则允许访问。
     * hasIpAddress(String)       |   如果用户的 IP 地址匹配指定的 IP 或 CIDR 范围，则允许访问。
     * hasRole(String)            |   如果用户拥有指定的角色（Role），则允许访问。
     * permitAll()                |   允许所有用户访问（无论是否登录）。
     * rememberMe()               |   允许通过 remember-me 功能自动登录的用户访问。
     * authenticated()            |   用户登录后即可访问，不区分 remember-me。
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // CSRF禁用，因为不使用session
                .csrf(csrf -> csrf.disable())
                // 禁用HTTP响应标头
                .headers((headersCustomizer) -> {
                    headersCustomizer.cacheControl(cache -> cache.disable()).frameOptions(options -> options.sameOrigin());
                })
                // 认证失败处理类
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
                // 基于token，所以不需要session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 注解标记允许匿名访问的url
                .authorizeHttpRequests((requests) -> {
                    // 匿名注解访问
                    permitAllUrlProperties.getPermitAllUrl().forEach(url -> requests.requestMatchers(url).permitAll());
                    // 对于登录login 注册register 验证码captchaImage 允许匿名访问
                    requests.requestMatchers("/user/login", "/register", "/captchaImage").permitAll()
                            // 静态资源，可匿名访问
                            //.requestMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
                            // 除上面外的所有请求全部需要鉴权认证
                            .anyRequest().authenticated();
                })
                // 添加Logout filter
                .logout(logout -> logout.logoutUrl("/user/logout").logoutSuccessHandler(logoutSuccessHandler))
                // 添加JWT filter
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 添加CORS filter
                .addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class)
                .addFilterBefore(corsFilter, LogoutFilter.class)
                .build();
    }

    /**
     * 身份验证实现
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // 自定义用户认证逻辑
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // 自定义加密算法
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
