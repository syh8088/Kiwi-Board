package kiwi.board.common.config.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Setter
public class AppUserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private Long id;

	private String name;

	private String phone;

	private String email;

	private String manager;

	private String managerEmail;

	private String managerPhone;

	private String adminYn;

	private List<SimpleGrantedAuthority> authorities;
						 
	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;

	private boolean enabled = true;
}
