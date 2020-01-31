package com.example.demo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class PdfApplication {


    public static void main(String[] args) {

        SpringApplication.run(PdfApplication.class, args);

    }

}
