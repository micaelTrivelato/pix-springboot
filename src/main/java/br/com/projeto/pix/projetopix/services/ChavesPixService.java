package br.com.projeto.pix.projetopix.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.pix.projetopix.dto.CarteiraDTO;
import br.com.projeto.pix.projetopix.dto.ChavesPIXDTO;
import br.com.projeto.pix.projetopix.entities.ChavesPIX;
import br.com.projeto.pix.projetopix.enums.Situacao;
import br.com.projeto.pix.projetopix.repositories.CarteiraRepo;
import br.com.projeto.pix.projetopix.repositories.ChavesPIXRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChavesPixService {

    @Autowired
    ChavesPIXRepo chavesPIXRepo;

    @Autowired
    CarteiraService carteiraService;

    @Autowired
    CarteiraRepo carteiraRepo;

    public List<ChavesPIXDTO> findAll() {
        List<ChavesPIX> listChavesPIX = chavesPIXRepo.findAll();
        List<ChavesPIXDTO> listChavesPIXDTO = listChavesPIX.stream().map(e -> new ChavesPIXDTO(e)).collect(Collectors.toList());
        return listChavesPIXDTO;
    }

    public ChavesPIXDTO findById(Long id) {
        ChavesPIX chavesPIX= chavesPIXRepo.findById(id).orElseThrow(
            () -> new InvalidDataAccessResourceUsageException("Id não encontrado: " + id));
            ChavesPIXDTO chavesPIXDTO = new ChavesPIXDTO(chavesPIX);
        return chavesPIXDTO;
    }

    public ChavesPIXDTO save(Long idConta, ChavesPIX chavesPIX) {
        
        if (carteiraRepo.existsById(idConta)) {
            CarteiraDTO carteiraDTO = carteiraService.findById(idConta);
            if (carteiraDTO.getSituacao().equals(Situacao.INATIVO)) {
                throw new EntityNotFoundException("Carteira inativa!");
            }
        } else {
            throw new EntityNotFoundException("Carteira não existe!");
        }
        ChavesPIXDTO chavesPIXDTO = new ChavesPIXDTO(chavesPIXRepo.save(chavesPIX));
        chavesPIXDTO.setId(chavesPIX.getId());
        carteiraService.addPixToCarteira(idConta, chavesPIXDTO);
        return chavesPIXDTO;  
    }

    public ChavesPIXDTO findByDescricao(String chavePix) {
        List<ChavesPIX> listChavesPIX = chavesPIXRepo.findByDescricao(chavePix);
        if(!listChavesPIX.isEmpty()){
            List<ChavesPIXDTO> listChavesPIXDTO = listChavesPIX.stream().map(e -> new ChavesPIXDTO(e)).collect(Collectors.toList());
            return listChavesPIXDTO.get(0);
        }
        throw new InvalidDataAccessResourceUsageException("Chave PIX não encontrada: ");
    }

    public String deleteByDescricao(String chavePix) {
        if(!chavesPIXRepo.findByDescricao(chavePix).isEmpty()){
            chavesPIXRepo.deleteByDescricao(chavePix);
        }else{
            throw new EntityNotFoundException("Chave PIX não encontrada: " + chavePix);
        }
        return "Chave PIX deletada com sucesso!";
    }
}
