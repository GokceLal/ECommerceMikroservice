package com.example.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@Slf4j
public class ElasticSecurityConfig {
    /**
     * Spring security ortamda gelen istekleri yani filtrelemek için SecurityFilterChain e ihtiyaç duyar, eğer
     * bunu siz sağlamaz iseniz zaten kendisi bir tane yaratır ve sistemi bunun üzerinden yönetir
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /**
         * Bu alan tüm güvenlik işlemlerinin yönetildiği kısımdır.Burada hangi isteklerin kontrol edileceği
         * hangi isteklerin herkese açık olacağı belirlenir
         */

       // httpSecurity.authorizeRequests()
        //       .requestMatchers("/swagger-ui/**").permitAll() //bu adrese izin ver
        //              .anyRequest()
         //                     .authenticated();


        /**
         * Spring Boot 3.X sonrası config
         * Spring Security gelen istekleri ayrıştırmak ve yönetmek için HttpSecurity içinde
         * belli methodlarla işlemler yapar
         * 1- requestMatchers -> gelen isteklerden filtrelenecek olanları eklemek için kullanır.
         * 2- permitAll -> belirlenen isteklere erişimi aç.
         * 3- anyRequest -> olabilecek tüm istekler tüm end-point kullanımları anlamına gelir.
         * Dikkat burada konu olan kendinen önce belirlenen end-pointler dışında kalanları
         * dahil etmemektedir.
         * 4- authenticated -> belirlenen işsteklere erişimde oturum açmayı zorunlu kıl.
         */

        httpSecurity.authorizeHttpRequests(req->
                req.requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**" //** demek doc dan sonra gelen tüm endpointlere izin ver demek
                        ).permitAll()
                        .requestMatchers("/dev/v1/elastic/**").hasAuthority("AYSE_TEYZE")
                        .requestMatchers("/dev/v1/admin/**").hasAuthority("ADMIN")

                        .anyRequest()
                        .authenticated()
        );
        /**
         * _csrf kullanımı genel olarak MVC ve Web projelerinde kullanılır.
         *
         * WAF -> Web Application Firewall
         * genellikle, api gateway üzerinde aktif edilir ve saldırıları engellemek için kullanılır.
         *
         *
         * CSRF restAPI kullanımılarından kapatılır. Çünkü istek atmak için bir sayfaya erişmemize
         * gerek yoktur. Direkt olarak bir end-point e erişim sğlıyoruz.
         */
        //Spring boot 3.x Öncesi ->  httpSecurity.csrf().disable();
        httpSecurity.csrf(AbstractHttpConfigurer::disable);


        log.info("Tüm istekler buradan geçicek");
       // httpSecurity.addFilterBefore(getJwtAuthFilter(), UsernamePasswordAuthenticationFilter.class); //Banka güvenlik kapısı

        return httpSecurity.build();
    }
}
