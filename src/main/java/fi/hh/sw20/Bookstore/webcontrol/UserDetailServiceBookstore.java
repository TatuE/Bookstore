package fi.hh.sw20.Bookstore.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.hh.sw20.Bookstore.domain.User;
import fi.hh.sw20.Bookstore.domain.UserRepository;

@Service
public class UserDetailServiceBookstore implements UserDetailsService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserDetailServiceBookstore(UserRepository userRepository){		
		this.userRepository = userRepository;		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User currentUser = userRepository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPassword(),
				AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;				
	}
}
