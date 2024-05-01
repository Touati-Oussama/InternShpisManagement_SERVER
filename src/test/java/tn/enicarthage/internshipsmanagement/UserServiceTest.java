package tn.enicarthage.internshipsmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.springframework.boot.test.context.SpringBootTest;
import tn.enicarthage.internshipsmanagement.entities.Department;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.UserRepos;
import tn.enicarthage.internshipsmanagement.response.UserDTO;
import tn.enicarthage.internshipsmanagement.security.AuthenticationService;
import tn.enicarthage.internshipsmanagement.security.UserRequest;
import tn.enicarthage.internshipsmanagement.services.UserService;
import tn.enicarthage.internshipsmanagement.services.UserServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepos userRepository;

    @InjectMocks
    private UserServiceImp userService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setNom("Lahmer");
        user.setPrenom("Hamza");
        user.setUsername("lahmer_hamza");
        user.setPassword("123456");
        user.setRole(ERole.ETUDIANT);
        user.setEmail("lahmer.hamza@gmail.com");
        user.setDepartment(new Department(1,"Informatique", null));
        user.setTelephone("12345678");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("touati_oussama");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.getUser(1L);

        assertEquals(user.getUsername(), foundUser.getUsername());
    }



    @Test
    void getAllEtudiants() {

        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        when(userRepository.findByRole(ERole.ETUDIANT)).thenReturn(userList);
        List<User> foundUsers = userService.getEtudiants();
        assertEquals(8, foundUsers.size());
    }



    @Test
    void findByEmail() {
        User user = new User();
        user.setUserId(1L);
        user.setEmail("oussama.touati.178@gmail.com");
        when(userRepository.findByEmail("oussama.touati.178@gmail.com")).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findByEmail("oussama.touati.178@gmail.com");
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void findByUsername() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("touati_oussama");
        when(userRepository.findByUsername("touati_oussama")).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findByUsername("touati_oussama");
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
    }


}