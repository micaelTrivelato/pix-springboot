package br.com.projeto.pix.projetopix.controllers;

import java.util.List;

import br.com.projeto.pix.projetopix.dto.TransferenciaDTO;
import br.com.projeto.pix.projetopix.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/transferencia")
public class TransferenciaController {
    
    @Autowired
    TransferenciaService transferenciaService;

    @GetMapping("/{idConta}")
    public ResponseEntity<List<TransferenciaDTO>> findByIdCarteiraPagadora(@PathVariable("idConta") long id) {
        return ResponseEntity.ok().body(transferenciaService.findByIdCarteiraPagadora(id));
    }

    @PostMapping("")
    public ResponseEntity<TransferenciaDTO> realizarTransferencia(@RequestParam Long idCarteiraPagadora, @RequestParam String chavePix, @RequestParam Double valor) {
        return ResponseEntity.ok().body(transferenciaService.realizarTransferencia(idCarteiraPagadora, chavePix, valor));
    }
}
