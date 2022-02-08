package com.gis.medfind.RepositoryTests;
 
import static org.assertj.core.api.Assertions.assertThat;

import com.gis.medfind.entity.User;
import com.gis.medfind.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;
     
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("kkmichaelstarkk@gmail.com");
        user.setPassword("@michael0958267");
        user.setFirstName("Kaleab");
        user.setLastName("Kindu");
        
        User savedUser = repo.save(user);
        
        User existUser = entityManager.find(User.class, savedUser.getId());
        
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
        
    }
}