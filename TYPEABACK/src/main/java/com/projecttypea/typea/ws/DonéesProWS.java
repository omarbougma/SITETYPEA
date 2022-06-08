package com.projecttypea.typea.ws;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.projecttypea.typea.bean.DonéesPro;
import com.projecttypea.typea.bean.User;
import com.projecttypea.typea.service.DonéesProService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class DonéesProWS {

    @Autowired
    DonéesProService donéesProService;

    /*
     * @PutMapping("/user/updatedonnes/{id}")
     * public int updateCadre(@PathVariable Long id, @RequestBody DonéesPro
     * donnePro) {
     * return donéesProService.updateDonesPro(id, donnePro);
     * }
     */


    @Transactional
    @DeleteMapping("/deletedonnes/{id}")
    public void deleteById(@PathVariable Long id) {
        donéesProService.deleteById(id);
    }

    @GetMapping("/admin/donnéespro")
    public List<DonéesPro> findAll() {
        return donéesProService.findAll();
    }

    @PostMapping("/user/adddonéespro")
    public int addDonesPro(@RequestBody DonéesPro donne, HttpSession session) {
        return donéesProService.addDonesPro(donne, session);
    }

    @PutMapping("/user/updatedonnepro")
    public int save(@RequestBody DonéesPro donne, HttpSession session) {
        return donéesProService.save(donne, session);
    }




    @GetMapping("/admin/getdonne/{id}")
    public DonéesPro findByUserId(@PathVariable Long id) {
        return donéesProService.findByUserId(id);
    }

}