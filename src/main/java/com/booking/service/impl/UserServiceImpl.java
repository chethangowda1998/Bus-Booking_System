package com.booking.service.impl;

import com.booking.entities.User;
import com.booking.exception.ResourceNotFoundException;
import com.booking.payload.UserDTO;
import com.booking.repository.UserRepository;
import com.booking.service.UserService;
import com.booking.util.PdfUtil;
import com.itextpdf.text.DocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String uploadDirectory = "src/main/resources/static/user_profile_image/";

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        // Encode the password
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));

        MultipartFile profileImage = userDTO.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = saveProfileImage(profileImage);
            user.setProfilePicture(fileName);
        }

        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }
    private String saveProfileImage(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            String baseFileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
            String uniqueFileName = baseFileName + "_" + System.currentTimeMillis() + fileExtension;
            Path path = Paths.get(uploadDirectory + uniqueFileName);
            Files.write(path, bytes);

            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save profile image", e);
        }
    }
    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage.map(this::userToDto);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","Id",id));
                user.setId(userDTO.getId());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setPasswordHash(userDTO.getPassword());
                user.setPhoneNumber(userDTO.getPhoneNumber());
                user.setProfilePicture(userDTO.getProfilePicture());
        User saveduser = userRepository.save(user);
        return userToDto(saveduser);
    }

    @Override
    public void Deleteuser(long id) {
        userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("user", "Id", id));

    }

public void downloadUsersAsExcel(HttpServletResponse response) throws IOException {
    // Create a PageRequest to specify page number, page size, and sorting
    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

    // Get the first page of users
    Page<UserDTO> users = getAllUsers(pageable);

    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Users");

    // Create header row
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("ID");
    headerRow.createCell(1).setCellValue("First Name");
    headerRow.createCell(2).setCellValue("Last Name");
    headerRow.createCell(3).setCellValue("Email");
    headerRow.createCell(4).setCellValue("Phone Number");

    // Populate data rows
    int rowNum = 1;
    for (UserDTO user : users) {
        Row dataRow = sheet.createRow(rowNum++);
        dataRow.createCell(0).setCellValue(user.getId());
        dataRow.createCell(1).setCellValue(user.getFirstName());
        dataRow.createCell(2).setCellValue(user.getLastName());
        dataRow.createCell(3).setCellValue(user.getEmail());
        dataRow.createCell(4).setCellValue(user.getPhoneNumber());
    }

    // Set response headers
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

    // Write workbook data to response stream
    try (OutputStream outputStream = response.getOutputStream()) {
        workbook.write(outputStream);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } finally {
        workbook.close();
    }
}




    @Override
    public byte[] downloadUsersAsPdf() throws IOException, DocumentException {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Page<UserDTO> users = getAllUsers(pageable);
        List<UserDTO> userList = users.getContent();

        return PdfUtil.createPdf(userList);
    }







    private User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    private UserDTO userToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setProfilePicture(user.getProfilePicture());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }
}
