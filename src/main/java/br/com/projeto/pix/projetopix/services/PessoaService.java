package br.com.projeto.pix.projetopix.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.pix.projetopix.dto.PessoaDTO;
import br.com.projeto.pix.projetopix.entities.Pessoa;
import br.com.projeto.pix.projetopix.repositories.PessoaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    PessoaRepo pessoaRepo;

    public List<PessoaDTO> findAll() {
        List<Pessoa> listPessoa = pessoaRepo.findAll();
        List<PessoaDTO> listPessoaDTO = listPessoa.stream().map(e -> new PessoaDTO(e)).collect(Collectors.toList());
        return listPessoaDTO;
    }

    public PessoaDTO findById(Long id) {
        Pessoa pessoa = pessoaRepo.findById(id).orElseThrow(
            () -> new InvalidDataAccessResourceUsageException("Id não encontrado: " + id));
        PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
        return pessoaDTO;
    }

    public PessoaDTO save(Pessoa pessoa) {
        PessoaDTO pessoaDto = new PessoaDTO(pessoaRepo.save(pessoa));
        pessoaDto.setId(pessoa.getId());
        return pessoaDto;
    }
}
