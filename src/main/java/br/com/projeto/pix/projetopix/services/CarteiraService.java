package br.com.projeto.pix.projetopix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.projeto.pix.projetopix.dto.CarteiraDTO;
import br.com.projeto.pix.projetopix.dto.ChavesPIXDTO;
import br.com.projeto.pix.projetopix.entities.Carteira;
import br.com.projeto.pix.projetopix.entities.ChavesPIX;
import br.com.projeto.pix.projetopix.enums.Situacao;
import br.com.projeto.pix.projetopix.repositories.CarteiraRepo;
import br.com.projeto.pix.projetopix.repositories.ChavesPIXRepo;
import br.com.projeto.pix.projetopix.repositories.PessoaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarteiraService {

    @Autowired
    CarteiraRepo carteiraRepo;

    @Autowired
    PessoaRepo pessoaRepo;

    @Autowired
    ChavesPIXRepo chavesPIXRepo;

    public List<CarteiraDTO> findAll() {
        List<Carteira> listCarteira = carteiraRepo.findAll();
        List<CarteiraDTO> listCarteiraDTO = listCarteira.stream().map(e -> new CarteiraDTO(e))
                .collect(Collectors.toList());
        return listCarteiraDTO;
    }

    public CarteiraDTO findById(Long id) {
        Optional<Carteira> carteira = carteiraRepo.findById(id);
        if (carteira.isPresent()) {
            CarteiraDTO carteiraDTO = new CarteiraDTO(carteira.get());
            return carteiraDTO;
        }
        throw new InvalidDataAccessResourceUsageException("Carteira não encontrada");
    }

    public CarteiraDTO save(Carteira carteira) {
        pessoaRepo.findById(carteira.getIdPessoa().getId()).orElseThrow(
                () -> new InvalidDataAccessResourceUsageException(
                        "IdPessoa não encontrado: " + carteira.getIdPessoa().getId()));
        List<Carteira> listVerificaCarteira = carteiraRepo.findByIdPessoa(carteira.getIdPessoa().getId());
        if (listVerificaCarteira.isEmpty()) {
            carteira.setSituacao(Situacao.ATIVO);
            CarteiraDTO carteiraDTO = new CarteiraDTO(carteiraRepo.save(carteira));
            carteiraDTO.setId(carteira.getId());
            return carteiraDTO;
        } else {
            Carteira carteiraUpdate = listVerificaCarteira.get(0);
            carteiraUpdate.setSituacao(Situacao.ATIVO);
            CarteiraDTO carteiraDTO = new CarteiraDTO(carteiraRepo.save(carteiraUpdate));
            carteiraDTO.setId(carteiraUpdate.getId());
            return carteiraDTO;
        }

    }

    public void addPixToCarteira(Long idConta, ChavesPIXDTO chavesPIXDTO) {
        Optional<Carteira> carteira = carteiraRepo.findById(idConta);
        List<ChavesPIX> listChavePixDTO = carteira.get().getChavePix();
        listChavePixDTO.add(new ChavesPIX(chavesPIXDTO));
        carteira.get().setChavePix(listChavePixDTO);
        carteiraRepo.save(carteira.get());
    }

    public Boolean validaSaldo(Long idCarteiraPagadora, Double valor) {
        Optional<Carteira> carteira = carteiraRepo.findById(idCarteiraPagadora);
        if (carteira.isPresent()) {
            Double saldoAtual = carteira.get().getSaldo();
            Double valorFinal = saldoAtual - valor;
            if (valorFinal >= 0) {
                return true;
            } else {
                return false;
            }

        }
        throw new InvalidDataAccessResourceUsageException("Carteira não encontrada: ");
    }

    public List<CarteiraDTO> findByChavePixId(Long chavePixId) {
        List<Carteira> listCarteira = carteiraRepo.findByChavePix(chavePixId);
        if (!listCarteira.isEmpty()) {
            List<CarteiraDTO> listCarteiraDTO = listCarteira.stream().map(e -> new CarteiraDTO(e))
                    .collect(Collectors.toList());
            return listCarteiraDTO;
        }
        throw new InvalidDataAccessResourceUsageException(
                "Carteira Recebedora não encontrada para a chave pix informada");
    }

    public String deletePixByIdConta(Long idConta, String chavePix) {
        if (carteiraRepo.existsById(idConta)) {
            CarteiraDTO carteiraDTO = findById(idConta);
            carteiraDTO.getChavePix().removeIf(e -> e.getDescricao().equals(chavePix));
            Carteira carteiraToRemove = new Carteira(carteiraDTO);
            save(carteiraToRemove);
        } else {
            throw new EntityNotFoundException("IdConta nao encontrado " + idConta);
        }
        return "Chave PIX deletada da carteira com sucesso!";
    }

    public void inativaConta(Long idConta) {
        CarteiraDTO carteiraDTO = new CarteiraDTO();
        if (carteiraRepo.existsById(idConta)) {
            carteiraDTO = findById(idConta);
            carteiraDTO.setSituacao(Situacao.INATIVO);
            carteiraRepo.save(new Carteira(carteiraDTO));
        } else {
            throw new EntityNotFoundException("IdConta nao encontrado " + idConta);
        }
    }
}
