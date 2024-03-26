package com.booking;

import com.booking.entities.User;
import com.booking.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.class)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    @Order(1)
    public void userRepositorySavesUser() {
        // Given
        User user = User.builder().build();
        user.setFirstName("chethan");
        user.setLastName("gowda");


        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(user.getId()).isNotNull(); // Assuming your ID generation strategy assigns a non-null ID
        assertThat(user.getId()).isGreaterThan(0L);
    }
    @Test
    @Order(2)
    public void UserRepositoryupdateUser(){

        User user = userRepository.findById(2L).get();
        user.setFirstName("chithra");
        User save = userRepository.save(user);
        assertThat(save.getFirstName()).isNotNull(); // Assuming your ID generation strategy assigns a non-null ID
        assertThat(save.getFirstName()).isEqualTo("chithra");

    }
    @Test
    @Order(3)
    public void getUsersById(){

        User user = userRepository.findById(2l).get();

        assertThat(user.getId()).isGreaterThan(0l);

    }
    @Test
    @Order(4)
    public void getAllUser(){
        List<User> users=userRepository.findAll();
        assertThat(users.size()).isGreaterThan(0);

    }
    @Test
@Order(5)
    public void DeleteUser(){
        User user = userRepository.findById(1l).get();
        userRepository.delete(user);
        User User1= null;
        Optional<User> user1 =userRepository.findByfirstName("chithra");
if(user1.isPresent()){
    User Delete = user1.get();
    assertThat(Delete).isNotNull();
}


    }
}
