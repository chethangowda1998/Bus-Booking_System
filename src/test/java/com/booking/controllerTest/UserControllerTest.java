package com.booking.controllerTest;

import com.booking.controller.UserController;
import com.booking.payload.UserDTO;
import com.booking.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private final UserService userService = mock(UserService.class);
    private final UserController userController = new UserController(userService);

    @Test
    public void testCreateUser() {
        // Create sample request parameters

        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String password = "secretpassword";
        String phoneNumber = "1234567890";
        // Simulate the absence of profileImage for this test
        MultipartFile profileImage = null;

        // Create a UserDTO instance to be returned by the mocked service
        UserDTO expectedCreatedUser = new UserDTO();
        expectedCreatedUser.setFirstName(firstName);
        expectedCreatedUser.setLastName(lastName);
        expectedCreatedUser.setEmail(email);
        expectedCreatedUser.setPassword(password);
        expectedCreatedUser.setPhoneNumber(phoneNumber);
        expectedCreatedUser.setProfileImage(profileImage);

        when(userService.createUser(any())).thenReturn(expectedCreatedUser);

        // Call the controller method
        ResponseEntity<UserDTO> response = userController.createUser(
                firstName, lastName, email, password, phoneNumber, profileImage
        );

        // Verify the service method was called with the expected parameters
        verify(userService).createUser(any());

        // Verify the response status code and the returned UserDTO
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedCreatedUser, response.getBody());
    }
}

