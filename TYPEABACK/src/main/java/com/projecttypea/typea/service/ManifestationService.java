package com.projecttypea.typea.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.projecttypea.typea.bean.DonéesPro;
import com.projecttypea.typea.bean.Manifestation;
import com.projecttypea.typea.bean.Soutien;
import com.projecttypea.typea.bean.User;
import com.projecttypea.typea.dao.ManifestationDao;
import com.projecttypea.typea.dao.UserDao;
import com.projecttypea.typea.security.enums.DemandesState;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManifestationService {

    @Autowired
    ManifestationDao manifestationDao;

    @Autowired
    UserDao userDao;

    @Autowired
    SoutienService soutienService;

    @Autowired
    DemandeService demandeService;

    public Manifestation getById(Long id) {
        return manifestationDao.getById(id);
    }

    public Optional<Manifestation> findById(Long id) {
        return manifestationDao.findById(id);
    }

    public List<Manifestation> findAll() {
        return manifestationDao.findAll();
    }

    public Long ajoutManifestation(Manifestation manif, HttpSession session) {
        try {
            User currentUser = userDao.findByEmail((String) (session.getAttribute("session")));
            if (currentUser.getDonne() != null) {
                addManifestation(manif, session);
                Long manifId = manif.getId();
                soutienService.addSoutienManifestation(manifId, manif.getSoutien(),session);
                return manif.getId();
            } else {
                return Long.valueOf(-2);
            }
        } catch (Exception e) {
            return Long.valueOf(-1);
        }
    }

    public int addManifestation(Manifestation manif, HttpSession session) {
        User currentUser = userDao.findByEmail((String) session.getAttribute("session"));
        manif.setUser(currentUser);
        manif.setDemandeType("Manifestation");
        manif.getSoutien().setEtat(1);
        manif.setState(DemandesState.IDLE);
        manifestationDao.save(manif);
        return 1;
    }

    public int updateManifestation(Long id, Manifestation manifestation) {
        Manifestation currentManif = manifestationDao.getById(id);
        if (currentManif == null) {
            return -1;
        } else {
            currentManif.setDateDebut(manifestation.getDateDebut());
            currentManif.setDateDepart(manifestation.getDateDepart());
            currentManif.setDateFin(manifestation.getDateFin());
            currentManif.setDateRetour(manifestation.getDateRetour());
            currentManif.setNatureParticiaton(manifestation.getNatureParticiaton());
            currentManif.setPays(manifestation.getPays());
            currentManif.setSoutien(manifestation.getSoutien());
            currentManif.setTitreManifestation(manifestation.getTitreManifestation());
            currentManif.setTitreParticipation(manifestation.getTitreParticipation());
            currentManif.setVille(manifestation.getVille());
            currentManif.setDocuments(manifestation.getDocuments());
            manifestationDao.save(currentManif);
            return 1;
        }
    }

    public void deleteById(Long id) {
        manifestationDao.deleteById(id);
    }

    public List<Manifestation> findAllByUserEmail(HttpSession session) {
        String email = (String) session.getAttribute("session");
        return manifestationDao.findAllByUserEmail(email);
    }

    public int manifAccepted(Long manifId, String toMail, String body, String subject) {
        Manifestation currentManif = manifestationDao.getById(manifId);
        if (currentManif.getState() == DemandesState.APPROVED
                || currentManif.getState() == DemandesState.REFUSED) {
            return -1;
        } else {
            currentManif.getSoutien().setEtat(0);
            currentManif.setState(DemandesState.APPROVED);
            manifestationDao.save(currentManif);
            demandeService.sendSimpleMail(toMail, body, subject);
            return 1;
        }
    }

    public int manifRefused(Long manifId) {
        Manifestation currentManif = manifestationDao.getById(manifId);
        if (currentManif.getState() == DemandesState.APPROVED
                || currentManif.getState() == DemandesState.REFUSED) {
            return -1;
        } else {
            currentManif.getSoutien().setEtat(-1);
            currentManif.setState(DemandesState.REFUSED);
            manifestationDao.save(currentManif);
            return 1;
        }
    }

    public User getCurrentUser(Long manifId) {
        Manifestation currentManifestation = getById(manifId);
        User currentUser = currentManifestation.getUser();
        return currentUser;
    }

    public DonéesPro getCurrentDonne(Long manifId) {
        Manifestation currentManifestation = getById(manifId);
        User currentUser = currentManifestation.getUser();
        DonéesPro currentDonnePro = currentUser.getDonne();
        return currentDonnePro;
    }

    public Soutien getSoutienByMStage(Long manifId) {
        Manifestation currentManifestation = getById(manifId);
        Soutien currentSoutien = currentManifestation.getSoutien();
        return currentSoutien;
    }

    public List<Manifestation> findAllByState(DemandesState state) {
        return manifestationDao.findAllByState(state);
    }

}