package br.com.projeto.pix.projetopix.entities;

import java.time.LocalDateTime;
import br.com.projeto.pix.projetopix.dto.TransferenciaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="transferencia_id_seq", initialValue=2, allocationSize=100)
public class Transferencia {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transferencia_id_seq")
    private Long id;

    @OneToOne
    @JoinColumn(name = "idCarteiraPagadora",nullable=false, referencedColumnName = "id")
    private Carteira idCarteiraPagadora;

    @OneToOne
    @JoinColumn(name = "idCarteiraRecebedora",nullable=false, referencedColumnName = "id")
    private Carteira idCarteiraRecebedora;

    private Double valor;
    private LocalDateTime data;

    public Transferencia(TransferenciaDTO transferenciaDto){
        this.id = transferenciaDto.getId();
        this.idCarteiraPagadora = transferenciaDto.getIdCarteiraPagadora();
        this.idCarteiraRecebedora = transferenciaDto.getIdCarteiraRecebedora();
        this.valor = transferenciaDto.getValor();
        this.data = transferenciaDto.getData();
    }
}
