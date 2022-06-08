package com.projecttypea.typea.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.projecttypea.typea.bean.Documents;
import com.projecttypea.typea.service.DocumentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentsWS {
    @Autowired
    DocumentsService documentsService;

    @PostMapping("/user/add_document/manifestation/{manifId}")
    public int storeDocumentManifestation(@PathVariable Long manifId, @RequestParam("file") MultipartFile document)
            throws IOException {
        return documentsService.storeDocumentManifestation(manifId, document);
    }

    @PostMapping("/user/add_document/missionstage/{missionId}")
    public int storeDocumentMission(@PathVariable Long missionId, MultipartFile document)
            throws IOException {
        return documentsService.storeDocumentMission(missionId, document);
    }

    @GetMapping("/admin/retrievedoc/{docName}")
    public ResponseEntity<byte[]> retrieve(@PathVariable String docName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        byte[] docDownload = documentsService.retrieve(docName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(docDownload);
    }

    @Transactional
    @DeleteMapping("/user/deletedocument/{id}")
    public void deleteById(@PathVariable Long id) {
        documentsService.deleteById(id);
    }

    @GetMapping("/documents")
    public List<Documents> findAll() {
        return documentsService.findAll();
    }

    @PostMapping("/user/add_documentMST/{missionId}")
    public int storeDocument(@PathVariable Long missionId,
            @RequestParam(value = "filecin", required = false) MultipartFile document,
            @RequestParam(value = "fileA", required = false) MultipartFile document1,
            @RequestParam(value = "fileB", required = false) MultipartFile document2,
            @RequestParam(value = "fileC", required = false) MultipartFile document3,
            @RequestParam(value = "fileD", required = false) MultipartFile document4,
            @RequestParam(value = "fileE", required = false) MultipartFile document5,
            @RequestParam(value = "fileF", required = false) MultipartFile document6)
            throws IOException {
        return documentsService.storeDocumentMissionStage(missionId, document, document1, document2, document3,
                document4,
                document5,
                document6);
    }

    @PostMapping("/user/add_documentM/{manifId}")
    public int storeDocumentManif(@PathVariable Long manifId,
            @RequestParam(value = "filecin", required = false) MultipartFile document,
            @RequestParam(value = "fileA", required = false) MultipartFile document1,
            @RequestParam(value = "fileB", required = false) MultipartFile document2,
            @RequestParam(value = "fileC", required = false) MultipartFile document3,
            @RequestParam(value = "fileD", required = false) MultipartFile document4,
            @RequestParam(value = "fileE", required = false) MultipartFile document5,
            @RequestParam(value = "fileF", required = false) MultipartFile document6)
            throws IOException {
        return documentsService.storeDocumentManifestation(manifId, document, document1, document2, document3,
                document4,
                document5,
                document6);
    }

    @GetMapping("/admin/viewdocs/{mStageId}")
    public Map<String, String> findAllByMStageId(@PathVariable Long mStageId) {
        return documentsService.findAllBDocsyMStageId(mStageId);
    }

    @GetMapping("/admin/viewdocsmanif/{manifId}")
    public Map<String, String> findAllBDocsyManifId(@PathVariable Long manifId) {
        return documentsService.findAllBDocsyManifId(manifId);
    }

}
