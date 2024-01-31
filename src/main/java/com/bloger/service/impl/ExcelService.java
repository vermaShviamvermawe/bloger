package com.bloger.service.impl;

import com.bloger.entity.Post;
import com.bloger.repository.PostRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
@Autowired
 private   PostRepository postRepository ;

    public void generateExcel() throws IOException {
        List<Post>posts= postRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Posts");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Content");
            headerRow.createCell(2).setCellValue("Description");
            headerRow.createCell(3).setCellValue("Reply");
            headerRow.createCell(4).setCellValue("Repost");

            // Populate data
            int rowNum = 1;
            for (Post post : posts) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(post.getId());
                row.createCell(1).setCellValue(post.getContent());
                row.createCell(2).setCellValue(post.getDescription());
                row.createCell(3).setCellValue(post.getReply());
                row.createCell(4).setCellValue(post.getRepost());
            }

            // Write the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream("posted.xlsx")) {
                workbook.write(fileOut);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
