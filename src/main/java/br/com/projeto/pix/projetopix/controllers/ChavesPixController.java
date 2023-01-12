package br.com.projeto.pix.projetopix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.pix.projetopix.dto.ChavesPIXDTO;
import br.com.projeto.pix.projetopix.entities.ChavesPIX;
import br.com.projeto.pix.projetopix.services.ChavesPixService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/chaves-pix")
public class ChavesPixController {

    @Autowired
    ChavesPixService chavesPixService;

    @GetMapping
    public ResponseEntity<List<ChavesPIXDTO>> findAll() {
        try {
            return ResponseEntity.ok().body(chavesPixService.findAll());
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChavesPIXDTO> findById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok().body(chavesPixService.findById(id));
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{idConta}")
    @ResponseBody
    public ResponseEntity<ChavesPIXDTO> save(@Valid @PathVariable("idConta") long idConta, @RequestBody ChavesPIX chavesPix) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chavesPixService.save(idConta, chavesPix));
    }
    
}
