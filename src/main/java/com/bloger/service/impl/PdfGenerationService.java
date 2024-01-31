package com.bloger.service.impl;
import com.bloger.entity.Post;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfGenerationService {

    public void generatePdf(List<Post> posts, ByteArrayOutputStream outputStream) throws DocumentException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("Post Data:"));

            for (Post post : posts) {
                document.add(new Paragraph("ID: " + post.getId()));
                document.add(new Paragraph("Content: " + post.getContent()));
                document.add(new Paragraph("Description: " + post.getDescription()));
                document.add(new Paragraph("Reply: " + post.getReply()));
                document.add(new Paragraph("Repost: " + post.getRepost()));
                document.add(new Paragraph("------------------------"));
            }
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }
}
