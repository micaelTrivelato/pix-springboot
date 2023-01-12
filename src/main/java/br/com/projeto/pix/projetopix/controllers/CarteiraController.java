package br.com.projeto.pix.projetopix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.pix.projetopix.dto.CarteiraDTO;
import br.com.projeto.pix.projetopix.entities.Carteira;
import br.com.projeto.pix.projetopix.services.CarteiraService;
import br.com.projeto.pix.projetopix.services.ChavesPixService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/carteira")
public class CarteiraController {
    
    @Autowired
    ChavesPixService chavesPixService;
    @Autowired
    CarteiraService carteiraService;

    @GetMapping
    public ResponseEntity<List<CarteiraDTO>> findAll() {
        try {
            return ResponseEntity.ok().body(carteiraService.findAll());
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraDTO> findById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok().body(carteiraService.findById(id));
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CarteiraDTO> save(@Valid @RequestBody Carteira carteira) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carteiraService.save(carteira));
    }

    @DeleteMapping("/{idConta}/{chavePix}")
    @ResponseBody
    public ResponseEntity<String> deletePixByIdConta(@PathVariable("idConta") long idConta, @PathVariable("chavePix") String chavePix) {
        String chavePixDeletadacarteira = carteiraService.deletePixByIdConta(idConta, chavePix);
        if(chavePixDeletadacarteira.equals("Chave PIX deletada da carteira com sucesso!")){
            return ResponseEntity.status(HttpStatus.OK).body(chavesPixService.deleteByDescricao(chavePix));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(chavePixDeletadacarteira);
    } 
    
    @PutMapping("/{idConta}")
    @ResponseBody
    public ResponseEntity<String> inativaConta(@PathVariable("idConta") long idConta) {
        carteiraService.inativaConta(idConta);
        return ResponseEntity.status(HttpStatus.OK).body("Conta inativada com Sucesso!");
    } 

    
}