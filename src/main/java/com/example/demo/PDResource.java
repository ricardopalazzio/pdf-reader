package com.example.demo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTree;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class PDResource {

    @Value("classpath:pdf_1.pdf")
    Resource resourceFile;
    @Value("classpath:filled.pdf")
    Resource resourceFileFilled;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]>  getPDF() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "pdf_1.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(resourceFile.getInputStream().readAllBytes(), headers, HttpStatus.OK);

        return response;
    }


    @GetMapping("/read")
    public ResponseEntity<byte[]> read() throws IOException {
       PDDocument document = PDDocument.load(resourceFileFilled.getInputStream());

            document.getClass();

            if (!document.isEncrypted()) {

                PDDocumentCatalog catalog = document.getDocumentCatalog();
                PDAcroForm form = catalog.getAcroForm();
                PDFieldTree fields = form.getFieldTree();


                fields.forEach(pdField -> {
                    Object value = pdField.getValueAsString();
                    String name = pdField.getFullyQualifiedName();
                    System.out.print(name);
                    System.out.print(" = ");
                    System.out.print(value);
                    System.out.println();
                });

            }

        return ResponseEntity.ok("Ok".getBytes());
    }

}
