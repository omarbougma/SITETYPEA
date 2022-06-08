package com.projecttypea.typea.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.projecttypea.typea.bean.DonéesPro;
import com.projecttypea.typea.bean.Etablissement;
import com.projecttypea.typea.bean.User;
import com.projecttypea.typea.dao.DonéesProDao;
import com.projecttypea.typea.dao.EtablissementDao;
import com.projecttypea.typea.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonéesProService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @Autowired
    DonéesProDao donéesProDao;
    @Autowired
    EtablissementService etablissementService;
    @Autowired
    private EtablissementDao etablissementDao;

    public List<DonéesPro> findAll() {
        return donéesProDao.findAll();
    }

    public DonéesPro findByUser(User user) {
        return donéesProDao.findByUser(user);
    }

    public DonéesPro findByUserId(Long id) {
        return donéesProDao.findByUserId(id);
    }

    public int addDonesPro(DonéesPro donne, HttpSession session) {
        User currentUser = userDao.findByEmail((String) session.getAttribute("session"));
        System.out.println(session.getAttribute("session"));

        if (currentUser.getDonne() != null) {

            return 1;

        } else if (currentUser.getDonne() == null) {
            currentUser.setDonne(donne);
            etablissementService.save(donne.getEtablissement());
            donne.setEtablissement(donne.getEtablissement());
            donne.setUser(currentUser);
            donéesProDao.save(donne);

            return -1;
        } else {
            return -3;
        }
    }

    public int savee(DonéesPro donéesPro) {
        donéesProDao.save(donéesPro);
        return 1;
    }

    public int save(DonéesPro donne, HttpSession session) {
        User currentUser = userDao.findByEmail((String) session.getAttribute("session"));
        DonéesPro savedDonne = findByUser(currentUser);
        etablissementDao.save(donne.getEtablissement());
        if (donne.getEtablissement() != null) {
            savedDonne.setEtablissement(donne.getEtablissement());
        }
        if (donne.getCed() != null) {
            savedDonne.setCed(donne.getCed());
        }
        if (donne.getEntiteRecherche() != null) {
            savedDonne.setEntiteRecherche(donne.getEntiteRecherche());
        }
        if (donne.getGrade() != null) {
            savedDonne.setGrade(donne.getGrade());
        }
        if (donne.getNiveau() != null) {
            savedDonne.setNiveau(donne.getNiveau());
        }
        if (donne.getRespoEntite() != null) {
            savedDonne.setRespoEntite(donne.getRespoEntite());
        }
        donéesProDao.save(savedDonne);
        donne.setUser(currentUser);
        return 1;
    }

    /*
     * public int updateDonesPro(Long id, DonéesPro donnePro) {
     * DonéesPro currentDonnes = donéesProDao.getById(id);
     * if (currentDonnes == null) {
     * return -1;
     * } else {
     * currentDonnes.setCed(donnePro.getCed());
     * currentDonnes.setEntiteRecherche(donnePro.getEntiteRecherche());
     * currentDonnes.setEtablissement(donnePro.getEtablissement());
     * currentDonnes.setGrade(donnePro.getGrade());
     * currentDonnes.setNiveau(donnePro.getNiveau());
     * currentDonnes.setRespoEntite(donnePro.getRespoEntite());
     * donéesProDao.save(currentDonnes);
     * return 1;
     * }
     * }
     */
    public void deleteById(Long id) {
        donéesProDao.deleteById(id);
    }

}