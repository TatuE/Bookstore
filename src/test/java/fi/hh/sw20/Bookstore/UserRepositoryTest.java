package fi.hh.sw20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.sw20.Bookstore.domain.User;
import fi.hh.sw20.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
	String userPassword= "salasana";
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String userPwd = passwordEncoder.encode(userPassword);
	
	@Test
    public void findByNameShouldReturnUser() {
        User user = repository.findByUsername("admin");        
        assertThat(user.getId()).isNotNull();
        assertThat(user.getRole()).isEqualTo("ADMIN");
    }
    
    @Test
    public void createNewUser() {    	
    	User user = new User("testUser",userPwd,"test@user.com","USER");
    	repository.save(user);
    	assertThat(user.getId()).isNotNull();
    	User testUser = repository.findByUsername("testUser");
    	assertThat(testUser.getEmail()).isEqualTo("test@user.com");
    }
    
    @Test
    public void deleteUser() {
    	User user = new User("testUser2",userPwd,"test@user2.com","USER");
    	repository.save(user);	    	
    	repository.delete(user);	    	
    	User testUser2 = repository.findByUsername("testUser2");	        
        assertThat(testUser2).isNull();
    }

}
