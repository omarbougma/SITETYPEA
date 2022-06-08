package com.projecttypea.typea.ws;

import com.projecttypea.typea.service.formulaire;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController

public class FORMULAIREWS {

    @GetMapping("/user/raport/{id}")
    public ResponseEntity<String> generateReport(@PathVariable long id) throws FileNotFoundException, JRException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        String path = formulairee.exportReport(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(path);
    }

    @GetMapping("/admin/raportlettremission/{id}")
    public ResponseEntity<String> exportLettremission(@PathVariable long id) throws FileNotFoundException, JRException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        String path = formulairee.exportLettremission(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(path);
    }

    @GetMapping("/user/raportmission/{id}")
    public ResponseEntity<String> exportReportMission(@PathVariable long id) throws FileNotFoundException, JRException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        String path = formulairee.exportReportMission(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(path);
    }

    @GetMapping("/admin/raportlettremanif/{id}")
    public ResponseEntity<String> exportLettremanif(@PathVariable long id) throws FileNotFoundException, JRException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        String path = formulairee.exportLettremanif(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(path);
    }

    @GetMapping("/admin/raportNVmontantmanif/{id}")
    public ResponseEntity<String> exportNvmontantmanif(@PathVariable long id)
            throws FileNotFoundException, JRException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        String path = formulairee.exportNvmontantmanif(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(path);
    }

    @GetMapping("/admin/raportNvmontantmis/{id}")
    public ResponseEntity<String> exportNvmontantmission(@PathVariable long id)
            throws FileNotFoundException, JRException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        String path = formulairee.exportNvmontantmission(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(path);
    }

    @Autowired
    private formulaire formulairee;

}
