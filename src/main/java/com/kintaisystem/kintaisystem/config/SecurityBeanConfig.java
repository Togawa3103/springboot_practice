package com.kintaisystem.kintaisystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeanConfig {
	//フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			return bCryptPasswordEncoder;
		}
		
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.authorizeHttpRequests((requests) -> requests
					.requestMatchers("/", "/home").permitAll()
					.anyRequest().authenticated()
				)
				.formLogin((form) -> form 
					//ログイン処理のパス
	              .loginProcessingUrl("/login")
	                //ログインページ
	              .loginPage("/login")
	                //ログインエラー時の遷移先 ※パラメーターに「error」を付与
	              .failureUrl("/login?error")
	                //ログイン成功時の遷移先
	              .defaultSuccessUrl("/index", true)
					.permitAll()
				)
				.logout((logout) -> logout.permitAll());

			return http.build();
		}
}
