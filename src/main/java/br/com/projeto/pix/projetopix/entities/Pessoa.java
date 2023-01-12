package br.com.projeto.pix.projetopix.entities;

import br.com.projeto.pix.Constants.Regex;
import br.com.projeto.pix.projetopix.dto.PessoaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="pessoa_id_seq", initialValue=3, allocationSize=100)
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_id_seq")
    private Long id;

    @NotNull @NotEmpty @NotBlank
    private String cpf;
    private String nome;
    
    @Email(message = "O Email não é válido.", regexp = Regex.EMAIL)
    @NotEmpty(message = "Email não pode ser vazio.")
    private String email;
    private String telefone;
    private String status;
    private String login;
    @JsonIgnore
    private String senha;

    public Pessoa(PessoaDTO pessoa){
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.status = pessoa.getStatus();
        this.login = pessoa.getLogin();
        this.telefone = pessoa.getTelefone();
        this.cpf = pessoa.getCpf();
        this.email = pessoa.getEmail();
        //Não tem senha pois não devemos trazer senha em endpoint
    }
}
