package com.projecttypea.typea.dao;

import com.projecttypea.typea.bean.NouveauMontant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NouveauMontantDao extends JpaRepository<NouveauMontant, Long> {

    NouveauMontant findByManifestationId(Long id);
    NouveauMontant findByMissionstageId(Long id);
    @Query(value = "SELECT SUM(nv.newMontant) FROM NouveauMontant  nv,Etablissement e,DonéesPro d, User  u WHERE  nv.user.id = u.id AND d.user.id = u.id AND d.etablissement.id = e.id  AND e.nom like :etab ")
    String montant_par_etab(@Param("etab") String etab);

}
