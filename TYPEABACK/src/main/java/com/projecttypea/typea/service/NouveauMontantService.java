package com.projecttypea.typea.service;

import com.projecttypea.typea.bean.Manifestation;
import com.projecttypea.typea.bean.MissionStage;
import com.projecttypea.typea.bean.NouveauMontant;
import com.projecttypea.typea.bean.User;
import com.projecttypea.typea.dao.ManifestationDao;
import com.projecttypea.typea.dao.MissionStageDao;
import com.projecttypea.typea.dao.NouveauMontantDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class NouveauMontantService {

    @Autowired
    NouveauMontantDao nouveauMontantDao;

    @Autowired
    MissionStageDao missionStageDao;
    @Autowired
    UserService userService;


    public NouveauMontant findByMissionstageId(Long id) {
        return nouveauMontantDao.findByMissionstageId(id);
    }

    public NouveauMontant findByManifestationId(Long id) {
        return nouveauMontantDao.findByManifestationId(id);
    }

    @Autowired
    ManifestationDao manifDao;

    public int addMontantsMS(Long mStageId, NouveauMontant nvMontant) {
        MissionStage currentMS = missionStageDao.getById(mStageId);
        User currentuser = userService.findByEmail(currentMS.getUser().getEmail()); int newmontant= (int) (nvMontant.getNewautreMontant()+ nvMontant.getNewmTitre()+ nvMontant.getNewmHebergement()+nvMontant.getNewmFraisInscription());

        if (currentMS.getNvMnt() != null) {
            return -1;
        } else {
            nvMontant.setUser(currentuser);
            nvMontant.setMissionStage(currentMS);
            nvMontant.setNewMontant(newmontant);
            nouveauMontantDao.save(nvMontant);
            return 1;
        }
    }

    public int addMontantsM(Long manifId, NouveauMontant nvMontant) {

        Manifestation currentM = manifDao.getById(manifId);
        User currentuser = userService.findByEmail(currentM.getUser().getEmail());
        int newmontant= (int) (nvMontant.getNewautreMontant()+ nvMontant.getNewmTitre()+ nvMontant.getNewmHebergement()+nvMontant.getNewmFraisInscription());
        if (currentM.getNvMnt() != null) {
            return -1;
        } else {
            nvMontant.setUser(currentuser);
            nvMontant.setManifestation(currentM);
            nvMontant.setNewMontant(newmontant);
            nouveauMontantDao.save(nvMontant);
            return 1;
        }
    }



    public String montant_par_etab(String etab) {
        return nouveauMontantDao.montant_par_etab(etab);
    }
    public List<String> grafbar(String e1 , String e2 , String e3 , String e4 , String e5 , String e6 , String e7 , String e8 , String e9 , String e10 , String e11 , String e12 , String e13 )
    { List<String> NUMBERS= new ArrayList<>();

        NUMBERS.add(nouveauMontantDao.montant_par_etab(e1));
        System.out.println(NUMBERS.get(0));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e2));
        System.out.println(NUMBERS.get(1));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e3));
        System.out.println(NUMBERS.get(2));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e4));
        System.out.println(NUMBERS.get(3));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e5));
        System.out.println(NUMBERS.get(4));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e6));
        System.out.println(NUMBERS.get(5));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e7));
        System.out.println(NUMBERS.get(6));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e8));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e9));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e10));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e11));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e12));
        NUMBERS.add(nouveauMontantDao.montant_par_etab(e13));
        return NUMBERS;
    }
}
