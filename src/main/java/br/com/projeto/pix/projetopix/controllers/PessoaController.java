package br.com.projeto.pix.projetopix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.pix.projetopix.dto.PessoaDTO;
import br.com.projeto.pix.projetopix.entities.Pessoa;
import br.com.projeto.pix.projetopix.services.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        try {
            return ResponseEntity.ok().body(pessoaService.findAll());
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok().body(pessoaService.findById(id));
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PessoaDTO> save(@Valid @RequestBody Pessoa pessoa) throws MethodArgumentNotValidException {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoa));
    }
}
