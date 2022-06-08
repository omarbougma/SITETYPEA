package com.projecttypea.typea.dao;

import com.projecttypea.typea.bean.DonéesPro;
import com.projecttypea.typea.bean.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EtablissementDao extends JpaRepository<Etablissement, Long> {
    Etablissement findByNom(String nom);

}
