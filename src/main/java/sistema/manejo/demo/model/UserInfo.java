package sistema.manejo.demo.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;


public class UserInfo extends User implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int idUser;
	private List<Role> roles;

	public UserInfo(int idUser, String username, String password, List<Role> roles) {
	    super(username, password, roles.stream()
	        .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getName()))
	        .collect(Collectors.toList()));
	    this.setIdUser(idUser);
	    this.setRoles(roles);
	}
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> role) {
		this.roles = role;
	}
}
