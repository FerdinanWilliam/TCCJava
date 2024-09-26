package com.fiebtcc.barbersclub.barbersclub.controller;

import com.fiebtcc.barbersclub.barbersclub.exceptions.BadRequest;
import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import com.fiebtcc.barbersclub.barbersclub.services.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionario")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping("/admin")
    public ResponseEntity<List<Admin>> listarTodosAdmins(){
        return ResponseEntity.ok().body(adminService.listarTodasAdmin());
    }
    @PostMapping("/admin")
    @Transactional
    public ResponseEntity<Admin> salvarAdmin(@RequestBody Admin admin){
        admin.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/admin").toUriString());
        return ResponseEntity.created(uri).body(adminService.salvarAdmin(admin));
    }
    @GetMapping("/admin/{id}")
    public  ResponseEntity<Admin> listarAdminPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(adminService.listarAdminPorId(Long.parseLong(id)));
        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Object> deletarAdmin(@PathVariable(value = "id") String id){
        try{
            if(adminService.deletarAdmin(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Admin com o id " + id + " excluida com sucesso");}

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da admin com o id" + id);

    }
    @PutMapping("/admin/{id}")
    @Transactional
    public ResponseEntity<Admin> atualizarAdmin(@RequestBody Admin admin, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(adminService.atualizarAdmin(admin , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @PutMapping("/deletlogic/admin/{id}")
    @Transactional
    public ResponseEntity<Admin> deletarLogicAdmin(@RequestBody Admin admin, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(adminService.deletarLogicAdmin(admin, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}


