package sistema.manejo.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	
	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Value("${security.jwt.grant-type}")
	private String grantType;

	@Value("${security.jwt.scope-read}")
	private String scopeRead;

	@Value("${security.jwt.scope-write}")
	private String scopeWrite = "write";

	@Value("${security.jwt.resource-ids}")
	private String resourceIds;
	
	@Value("${security.jwt.token-validity-seconds}")
	private String tokenValiditySeconds;
	
	@Value("${security.jwt.token-refresh-validity-seconds}")
	private String refreshTokenValiditySeconds;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	 @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.inMemory()
	            .withClient(clientId)
	            .secret(bcrypt.encode(clientSecret))
	            .authorizedGrantTypes(grantType, "refresh_token")
	            .scopes(scopeRead, scopeWrite).resourceIds(resourceIds)
	            .accessTokenValiditySeconds(Integer.parseInt(tokenValiditySeconds))
	            .refreshTokenValiditySeconds(Integer.parseInt(refreshTokenValiditySeconds));
	    }

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

			enhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter));
			endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter).tokenEnhancer(enhancerChain)
					.reuseRefreshTokens(false)// false
					.authenticationManager(authenticationManager);
		}

	    @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }

	    @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        converter.setSigningKey("your-secret-key"); // Clave para firmar los JWT
	        return converter;
	    }
}
