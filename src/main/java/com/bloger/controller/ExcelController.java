package com.bloger.controller;

import com.bloger.entity.Post;
import com.bloger.repository.PostRepository;
import com.bloger.service.impl.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private PostRepository postRepository; // Assuming you have a PostRepository

    @GetMapping("/export-excel")
    public String exportToExcel() throws IOException {


            excelService.generateExcel();
            return "Excel file exported successfully!";

    }
}
