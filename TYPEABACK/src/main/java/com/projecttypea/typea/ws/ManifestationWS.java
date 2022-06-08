package com.projecttypea.typea.ws;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.projecttypea.typea.bean.DonéesPro;
import com.projecttypea.typea.bean.Manifestation;
import com.projecttypea.typea.bean.Soutien;
import com.projecttypea.typea.bean.User;
import com.projecttypea.typea.security.enums.DemandesState;
import com.projecttypea.typea.service.ManifestationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManifestationWS {

    @Autowired
    ManifestationService manifestationService;

    @PostMapping("/user/manifestationadd")
    public Long ajoutManifestation(@RequestBody Manifestation manif, HttpSession session) {
        return manifestationService.ajoutManifestation(manif, session);
    }

    @PutMapping("/user/updatemanif/{id}")
    public int updateManifestation(@PathVariable Long id, @RequestBody Manifestation manifestation) {
        return manifestationService.updateManifestation(id, manifestation);
    }

    @Transactional
    @DeleteMapping("/deletemanif/{id}")
    public void deleteById(Long id) {
        manifestationService.deleteById(id);
    }

    @GetMapping("/admin/manifestations")
    public List<Manifestation> findAll() {
        return manifestationService.findAll();
    }

    @PostMapping("/user/addmanifestation")
    public int addManifestation(@Valid @RequestBody Manifestation manifestation, HttpSession session) {
        return manifestationService.addManifestation(manifestation, session);
    }

    @GetMapping("/user/getmanifestations")
    public List<Manifestation> findAllByUserEmail(HttpSession session) {
        return manifestationService.findAllByUserEmail(session);
    }

    @GetMapping("/admin/getmanifbyid/{id}")
    public Manifestation getById(@PathVariable Long id) {
        return manifestationService.getById(id);
    }

    @GetMapping("/admin/getuserbymanif/{manifId}")
    public User getCurrentUser(@PathVariable Long manifId) {
        return manifestationService.getCurrentUser(manifId);
    }

    @GetMapping("/admin/getdonnebymanif/{manifId}")
    public DonéesPro getCurrentDonne(@PathVariable Long manifId) {
        return manifestationService.getCurrentDonne(manifId);
    }

    @GetMapping("/admin/getsoutienbymanif/{manifId}")
    public Soutien getSoutienByMStage(@PathVariable Long manifId) {
        return manifestationService.getSoutienByMStage(manifId);
    }

    public int manifAccepted(Long manifId, String toMail, String body, String subject) {
        return manifestationService.manifAccepted(manifId, toMail, body, subject);
    }

    @GetMapping("/admin/refusemanif/{manifId}")
    public int manifRefused(@PathVariable Long manifId) {
        return manifestationService.manifRefused(manifId);
    }

    @GetMapping("/admin/findallmanifs/{state}")
    public List<Manifestation> findAllByState(@PathVariable DemandesState state) {
        return manifestationService.findAllByState(state);
    }

}
