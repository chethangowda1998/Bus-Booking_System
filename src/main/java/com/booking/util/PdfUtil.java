package com.booking.util;

import com.booking.payload.UserDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfUtil {

    public static byte[] createPdf(List<UserDTO> users) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        for (UserDTO user : users) {
            document.add(new Paragraph("ID: " + user.getId()));
            document.add(new Paragraph("First Name: " + user.getFirstName()));
            document.add(new Paragraph("Last Name: " + user.getLastName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Phone Number: " + user.getPhoneNumber()));
            document.add(new Paragraph("\n")); // Add a new line between users
        }

        document.close();

        return baos.toByteArray();
    }
}
