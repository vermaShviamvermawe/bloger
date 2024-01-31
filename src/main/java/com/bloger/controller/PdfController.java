package com.bloger.controller;
import com.bloger.entity.Post;
import com.bloger.repository.PostRepository;
import com.bloger.service.impl.PdfGenerationService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class PdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private PostRepository postRepository; // Assuming you have a PostRepository

    @GetMapping("/export-pdf")
    public ResponseEntity<Resource> exportToPdf() {
        List<Post> posts = postRepository.findAll(); // Fetch posts from the database

        try {
            // Generate PDF content as a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            pdfGenerationService.generatePdf(posts, byteArrayOutputStream);

            // Convert byte array to ByteArrayResource
            ByteArrayResource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());

            // Set headers to prompt download
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=posts.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

            // Return a ResponseEntity with the resource and headers
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (DocumentException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return ResponseEntity.status(500).body(null);
        }
    }
}
