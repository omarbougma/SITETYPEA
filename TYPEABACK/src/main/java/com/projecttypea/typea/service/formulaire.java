package com.projecttypea.typea.service;

import com.projecttypea.typea.bean.*;
import com.projecttypea.typea.dao.ManifestationDao;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class formulaire {
    String home = System.getProperty("user.home");

    public String exportReport(long id) throws FileNotFoundException, JRException {

        Manifestation manifestation = getById(id);
        User user = userService.getById(manifestation.getUser().getId());
        DonéesPro donéesPro = donéesProService.findByUser(user);

        Soutien soutien = soutienService.getById(manifestation.getSoutien().getId());
        File file = ResourceUtils.getFile("classpath:formulaire.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
                Collections.singleton(manifestation));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nom", user.getNom());
        parameters.put("prenom", user.getPrenom());
        parameters.put("telephone", user.getTelephone());
        parameters.put("email", user.getEmail());

        parameters.put("id", manifestation.getId());
        parameters.put("titremanifestation", manifestation.getTitreManifestation());
        parameters.put("titreparticipation", manifestation.getTitreParticipation());
        parameters.put("pays", manifestation.getPays());
        parameters.put("datedebut", manifestation.getDateDebut());
        parameters.put("datefin", manifestation.getDateFin());
        parameters.put("datedepert", manifestation.getDateDepart());
        parameters.put("dateretour", manifestation.getDateRetour());
        parameters.put("ville", manifestation.getVille());

        parameters.put("niveau", donéesPro.getNiveau());
        parameters.put("grade", donéesPro.getGrade());
        parameters.put("ced", donéesPro.getCed());
        parameters.put("etablissement", donéesPro.getEtablissement().getNom());
        parameters.put("entitérecherche", donéesPro.getEntiteRecherche());
        parameters.put("respoentité", donéesPro.getRespoEntite());

        parameters.put("nature", soutien.getNature());
        parameters.put("mtitretransport", soutien.getmTitreTransport());
        parameters.put("mhebergement", soutien.getmHebergement());
        parameters.put("mtotal", soutien.getMontant());
        parameters.put("mfraisinscription", soutien.getmFraisInscription());
        parameters.put("montant autre", soutien.getmAutre());
        parameters.put("isbénf", soutien.getIsBenfTypeA());
        parameters.put("devise", soutien.getDevise());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        String path = home + "/Downloads/" + user.getNom() + manifestation.getId() + "manifestation.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return path;

    }

    public String exportNvmontantmanif(long id) throws FileNotFoundException, JRException {

        try {
            Manifestation manifestation = getById(id);
            User user = userService.getById(manifestation.getUser().getId());
            DonéesPro donéesPro = donéesProService.findByUser(user);
            NouveauMontant nouveauMontant = nouveauMontantService.findByManifestationId(id);

            Soutien soutien = soutienService.getById(manifestation.getSoutien().getId());
            File file = ResourceUtils.getFile("classpath:Nvmontantmanif.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
                    Collections.singleton(manifestation));
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nom", user.getNom());
            parameters.put("prenom", user.getPrenom());
            parameters.put("telephone", user.getTelephone());
            parameters.put("email", user.getEmail());

            parameters.put("id", manifestation.getId());
            parameters.put("titremanifestation", manifestation.getTitreManifestation());
            parameters.put("titreparticipation", manifestation.getTitreParticipation());
            parameters.put("pays", manifestation.getPays());
            parameters.put("datedebut", manifestation.getDateDebut());
            parameters.put("datefin", manifestation.getDateFin());
            parameters.put("datedepert", manifestation.getDateDepart());
            parameters.put("dateretour", manifestation.getDateRetour());
            parameters.put("ville", manifestation.getVille());

            parameters.put("niveau", donéesPro.getNiveau());
            parameters.put("grade", donéesPro.getGrade());
            parameters.put("ced", donéesPro.getCed());
            parameters.put("etablissement", donéesPro.getEtablissement().getNom());
            parameters.put("entitérecherche", donéesPro.getEntiteRecherche());
            parameters.put("respoentité", donéesPro.getRespoEntite());

            parameters.put("nature", soutien.getNature());
            parameters.put("mtitretransport", soutien.getmTitreTransport());
            parameters.put("mhebergement", soutien.getmHebergement());
            parameters.put("mtotal", soutien.getMontant());
            parameters.put("mfraisinscription", soutien.getmFraisInscription());
            parameters.put("montant autre", soutien.getmAutre());
            parameters.put("isbénf", soutien.getIsBenfTypeA());
            parameters.put("devise", soutien.getDevise());

            parameters.put("mtitretransported", nouveauMontant.getNewmTitre());
            parameters.put("mhebergemented", nouveauMontant.getNewmHebergement());
            parameters.put("mtotaled", nouveauMontant.getNewMontant());
            parameters.put("montant autreed", nouveauMontant.getNewautreMontant());

            parameters.put("mfraisinscriptioned", nouveauMontant.getNewmFraisInscription());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            String path = home + "/Downloads/" + user.getNom() + manifestation.getId()
                    + "nouveau montants manifestation.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, path);

            return path;

        } catch (Exception e) {
            return "erreur";
        }

    }

    public String exportNvmontantmission(long id) throws FileNotFoundException, JRException {

        MissionStage mission = missionStageService.getById(id);
        User user = userService.getById(mission.getUser().getId());
        DonéesPro donéesPro = donéesProService.findByUser(user);

        NouveauMontant nouveauMontant = nouveauMontantService.findByMissionstageId(id);
        Soutien soutien = soutienService.getById(mission.getSoutien().getId());
        File file = ResourceUtils.getFile("classpath:Nvmontantmis.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton(mission));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nom", user.getNom());
        parameters.put("prenom", user.getPrenom());
        parameters.put("telephone", user.getTelephone());
        parameters.put("email", user.getEmail());

        parameters.put("id", mission.getId());

        parameters.put("titreparticipation", mission.getObjetMission());
        parameters.put("pays", mission.getPays());
        parameters.put("datedebut", mission.getDateDebut());
        parameters.put("datefin", mission.getDateFin());
        parameters.put("datedepert", mission.getDateDepart());
        parameters.put("dateretour", mission.getDateRetour());
        parameters.put("ville", mission.getVille());

        parameters.put("niveau", donéesPro.getNiveau());
        parameters.put("grade", donéesPro.getGrade());
        parameters.put("ced", donéesPro.getCed());
        parameters.put("etablissement", donéesPro.getEtablissement().getNom());
        parameters.put("entitérecherche", donéesPro.getEntiteRecherche());
        parameters.put("respoentité", donéesPro.getRespoEntite());

        parameters.put("nature", soutien.getNature());
        parameters.put("mtitretransport", soutien.getmTitreTransport());
        parameters.put("mhebergement", soutien.getmHebergement());
        parameters.put("mtotal", soutien.getMontant());
        parameters.put("mfraisinscription", soutien.getmFraisInscription());
        parameters.put("montant autre", soutien.getmAutre());
        parameters.put("isbénf", soutien.getIsBenfTypeA());
        parameters.put("devise", soutien.getDevise());

        parameters.put("mtitretransported", nouveauMontant.getNewmTitre());
        parameters.put("mhebeed", nouveauMontant.getNewmHebergement());
        parameters.put("mtotaled", nouveauMontant.getNewMontant());
        parameters.put("mfraised", nouveauMontant.getNewmFraisInscription());
        parameters.put("mautreed", nouveauMontant.getNewautreMontant());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String path = home + "/Downloads/" + user.getNom() + mission.getId() + "nouveau montants mission.pdf";

        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return path;

    }

    public String exportReportMission(long id) throws FileNotFoundException, JRException {


            MissionStage mission = missionStageService.getById(id);
            User user = userService.getById(mission.getUser().getId());
            DonéesPro donéesPro = donéesProService.findByUser(user);
            Cadre cadre = cadreService.findByMissionstage(mission);

            Soutien soutien = soutienService.getById(mission.getSoutien().getId());
            File file = ResourceUtils.getFile("classpath:formmission.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton(mission));
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nom", user.getNom());
            parameters.put("prenom", user.getPrenom());
            parameters.put("telephone", user.getTelephone());
            parameters.put("email", user.getEmail());

            parameters.put("id", mission.getId());

            parameters.put("titreparticipation", mission.getObjetMission());
            parameters.put("pays", mission.getPays());
            parameters.put("datedebut", mission.getDateDebut());
            parameters.put("datefin", mission.getDateFin());
            parameters.put("datedepert", mission.getDateDepart());
            parameters.put("dateretour", mission.getDateRetour());
            parameters.put("ville", mission.getVille());

            parameters.put("niveau", donéesPro.getNiveau());
            parameters.put("grade", donéesPro.getGrade());
            parameters.put("ced", donéesPro.getCed());
            parameters.put("etablissement", donéesPro.getEtablissement().getNom());
            parameters.put("entitérecherche", donéesPro.getEntiteRecherche());
            parameters.put("respoentité", donéesPro.getRespoEntite());

            parameters.put("nature", soutien.getNature());
            parameters.put("mtitretransport", soutien.getmTitreTransport());
            parameters.put("mhebergement", soutien.getmHebergement());
            parameters.put("mtotal", soutien.getMontant());
            parameters.put("mfraisinscription", soutien.getmFraisInscription());
            parameters.put("montant autre", soutien.getmAutre());
            parameters.put("isbénf", soutien.getIsBenfTypeA());
            parameters.put("devise", soutien.getDevise());

            parameters.put("1", cadre.getTitreCadre());
            parameters.put("3", cadre.getRespoMarDuProjet());
            parameters.put("4", cadre.getPartenaireEtranger());
            parameters.put("2", cadre.getTitreProjet());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            String path = home + "/Downloads/" + user.getNom() + mission.getId() + "mission stage.pdf";

            JasperExportManager.exportReportToPdfFile(jasperPrint, path);

            return path;

    }

    public String exportLettremanif(long id) throws FileNotFoundException, JRException {
        Manifestation manifestation = manifestationDao.getById(id);
        NouveauMontant nouveauMontant = nouveauMontantService.findByManifestationId(id);
        User user= userService.getById(manifestation.getUser().getId());
        File file = ResourceUtils.getFile("classpath:Lettremanif.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton(manifestation));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("titremanifestation", manifestation.getTitreManifestation());
        parameters.put("ville", manifestation.getVille());
        parameters.put("datedebut", manifestation.getDateDebut());
        parameters.put("datefin", manifestation.getDateFin());
        parameters.put("montant", nouveauMontant.getNewMontant());
        if(user.getGender().equals("male"))
        {parameters.put("gender", "Monsieur");

        }else{
            parameters.put("gender", "Madame");
        }
        parameters.put("nom",user.getNom());
        parameters.put("penom",user.getPrenom());


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        String path = home + "/Downloads/" + manifestation.getId() + "lettre manifestation.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return path;

    }

    public String exportLettremission(long id) throws FileNotFoundException, JRException {
        MissionStage mission = missionStageService.getById(id);
        NouveauMontant nouveauMontant = nouveauMontantService.findByMissionstageId(id);
        User user= userService.getById(mission.getUser().getId());
        File file = ResourceUtils.getFile("classpath:Lettremission.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton(mission));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("titremanifestation", mission.getObjetMission());
        parameters.put("ville", mission.getVille());
        parameters.put("datedebut", mission.getDateDebut());
        parameters.put("datefin", mission.getDateFin());
        parameters.put("montant", nouveauMontant.getNewMontant());
        if(user.getGender().equals("male"))
        {parameters.put("gender", "Monsieur");

        }else{
            parameters.put("gender", "Madame");
        }
        parameters.put("nom",user.getNom());
        parameters.put("penom",user.getPrenom());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        String path = home + "/Downloads/" + mission.getId() + "lettre mission.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return path;

    }

    public Manifestation getById(Long aLong) {
        return manifestationDao.getById(aLong);
    }

    @Autowired
    private ManifestationDao manifestationDao;
    @Autowired
    private DonéesProService donéesProService;
    @Autowired
    private UserService userService;
    @Autowired
    private SoutienService soutienService;
    @Autowired
    private MissionStageService missionStageService;
    @Autowired
    private CadreService cadreService;
    @Autowired
    private NouveauMontantService nouveauMontantService;
}
