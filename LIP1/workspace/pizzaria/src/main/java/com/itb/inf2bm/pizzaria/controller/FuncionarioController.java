package com.itb.inf2bm.pizzaria.controller;

import com.itb.inf2bm.pizzaria.exceptions.BadRequest;
import com.itb.inf2bm.pizzaria.model.Categoria;
import com.itb.inf2bm.pizzaria.services.CategoriaService;
import com.itb.inf2bm.pizzaria.services.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


// @Controller              // Exclusivo para web

@RestController             // Exclusivo para api's
@RequestMapping("/api/v1/funcionario")
public class FuncionarioController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public FuncionarioController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    // ResponseEntity : Tipo especial de resposta (Uma forma de retornar qualquer tipo de model (Entity)
    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> listarTodasCategorias() {

        return ResponseEntity.ok().body(categoriaService.listarTodasCategorias());
    }
    @PostMapping("/categoria")
    @Transactional
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria){
        categoria.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/categoria").toUriString());
        return ResponseEntity.created(uri).body(categoriaService.salvarCategoria(categoria));
    }
    @GetMapping("/categoria/{id}")
    public  ResponseEntity<Categoria> listarcategoriaPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(categoriaService.listarCategoriaPorId(Long.parseLong(id)));
        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

    }

    // @PutMapping: Complementação da url principal, utilizado exclusivamente para UPDATE
    @PutMapping("/categoria/{id}")
    @Transactional
    public ResponseEntity<Categoria> atualizarCategoria(@RequestBody Categoria categoria, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(categoriaService.atualizarCategoria(categoria , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

    }
    // @DeleteMapping: Complementação da url principal, utilizado exclusivamente para EXCLUSÃO FÍSICA
    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<Object> deletarCategoria(@PathVariable(value = "id") String id){
        try{
            if(categoriaService.deletarCategoria(Long.parseLong(id))) {
            return ResponseEntity.ok().body("Categoria com o id " + id + " excluida com sucesso");}

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da categoria com o id" + id);

    }
    @PutMapping("/deletlogic/categoria/{id}")
    @Transactional
    public ResponseEntity<Categoria> deletarLogicCategoria(@RequestBody Categoria categoria, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(categoriaService.deletarLogicCategoria(categoria, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}

