package com.booking.service;

import com.booking.payload.UserDTO;
import com.itextpdf.text.DocumentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {

    public UserDTO createUser(UserDTO userDTO);

    Page<UserDTO> getAllUsers(Pageable pageable);

   public UserDTO updateUser(Long id, UserDTO userDTO);

    void Deleteuser(long id);


    void downloadUsersAsExcel(HttpServletResponse response) throws IOException;

   byte[] downloadUsersAsPdf() throws IOException, DocumentException;


}
