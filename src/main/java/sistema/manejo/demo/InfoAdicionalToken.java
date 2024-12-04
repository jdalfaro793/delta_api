package sistema.manejo.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import sistema.manejo.demo.model.Role;
import sistema.manejo.demo.model.User;
import sistema.manejo.demo.model.UserInfo;
import sistema.manejo.demo.service.UserService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	private static final Logger log = LoggerFactory.getLogger(InfoAdicionalToken.class);

	@Autowired
	UserService usuarioService;
	
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
		
		try {
			Map<String, Object> info = new HashMap<>();
			
			List<Role> roles=  (List<Role>)  info.get("roles");
			
			
			List<String> activeRoleNamesList = roles.stream().filter(rol->rol.getStatus()==1).map(Role::getName).collect(Collectors.toList());
			
			UserInfo usuarioInfo = (UserInfo)authentication.getPrincipal();
			
			if(usuarioInfo != null) {
				User usuario = usuarioService.findUserId(usuarioInfo.getIdUser());
				info.put("id_user", usuario.getId());
		        info.put("roles",activeRoleNamesList);

				
			} else {
				log.error("Usuario no valido: {}", authentication.getName());
				info.put("mensaje", "Usted no tiene permiso");
			}
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		} catch (Exception e) {
			log.error("Error al obtener información adicional del usuario " + authentication.getName() + ": " + e.getMessage());
		}
			
		return accessToken;
	}

	
}
