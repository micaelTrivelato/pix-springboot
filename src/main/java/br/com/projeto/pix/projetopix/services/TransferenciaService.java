package br.com.projeto.pix.projetopix.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.pix.projetopix.dto.CarteiraDTO;
import br.com.projeto.pix.projetopix.dto.ChavesPIXDTO;
import br.com.projeto.pix.projetopix.dto.TransferenciaDTO;
import br.com.projeto.pix.projetopix.entities.Carteira;
import br.com.projeto.pix.projetopix.entities.Transferencia;
import br.com.projeto.pix.projetopix.enums.Situacao;
import br.com.projeto.pix.projetopix.repositories.CarteiraRepo;
import br.com.projeto.pix.projetopix.repositories.TransferenciaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepo transferenciaRepo;

    @Autowired
    CarteiraService carteiraService;

    @Autowired
    ChavesPixService chavesPixService;

    @Autowired
    CarteiraRepo carteiraRepo;

    public List<TransferenciaDTO> findByIdCarteiraPagadora(Long id) {
        List<Transferencia> listTransferencia = transferenciaRepo.findByIdCarteiraPagadora(id);
        List<TransferenciaDTO> listTransferenciaDTO = listTransferencia.stream().map(e -> new TransferenciaDTO(e))
                .collect(Collectors.toList());
        return listTransferenciaDTO;
    }

    public TransferenciaDTO realizarTransferencia(Long idCarteiraPagadora, String chavePix, Double valor) {
        ChavesPIXDTO chavesPIXDTORecebedor = chavesPixService.findByDescricao(chavePix);
        List<CarteiraDTO> carteiraDTORecebedor = carteiraService.findByChavePixId(chavesPIXDTORecebedor.getId());
        CarteiraDTO carteiraDTOPagador = carteiraService.findById(idCarteiraPagadora);

        verificaCarteira(carteiraDTOPagador, carteiraDTORecebedor.get(0), valor);

        Double valorFinalRecebedor = carteiraDTORecebedor.get(0).getSaldo() + valor;
        Double valorFinalPagador = carteiraDTOPagador.getSaldo() - valor;

        Carteira carteriaFinalRecebedor = new Carteira(carteiraDTORecebedor.get(0));
        carteriaFinalRecebedor.setSaldo(valorFinalRecebedor);

        Carteira carteriaFinalPagador = new Carteira(carteiraDTOPagador);
        carteriaFinalPagador.setSaldo(valorFinalPagador);

        carteiraRepo.save(carteriaFinalRecebedor);
        carteiraRepo.save(carteriaFinalPagador);

        Transferencia transferencia = new Transferencia();
        transferencia.setData(LocalDateTime.now());
        transferencia.setValor(valor);
        transferencia.setIdCarteiraPagadora(carteriaFinalPagador);
        transferencia.setIdCarteiraRecebedora(carteriaFinalRecebedor);
        Transferencia transferenciaRealizada = transferenciaRepo.save(transferencia);
        TransferenciaDTO transferenciaRealizadaDTO = new TransferenciaDTO(transferenciaRealizada);
        return transferenciaRealizadaDTO;

    }

    public void verificaCarteira(CarteiraDTO carteiraDTOPagador, CarteiraDTO carteiraDTORecebedor,
            Double valorTransferencia) {
        if (carteiraDTOPagador.getSituacao().equals(Situacao.INATIVO)) {
            throw new InvalidDataAccessResourceUsageException("A carteira pagadora não está ativa!");
        }
        if (carteiraDTOPagador.getChavePix().isEmpty()) {
            throw new InvalidDataAccessResourceUsageException(
                    "A carteira pagadora precisa ter pelo menos uma chave PIX cadastrada para efetuar transacoes!");
        }
        if (carteiraDTORecebedor.getId() == carteiraDTOPagador.getId()) {
            throw new InvalidDataAccessResourceUsageException(
                    "A conta pagadora possui idConta igual ao da conta recebedora");
        }
        if (!carteiraService.validaSaldo(carteiraDTOPagador.getId(), valorTransferencia)) {
            throw new InvalidDataAccessResourceUsageException("Saldo insuficiente");
        }
    }

}
