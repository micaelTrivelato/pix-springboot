package br.com.projeto.pix.projetopix.dto;

import br.com.projeto.pix.projetopix.entities.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDTO {
    
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String status;
    private String login;
    private String telefone;

    public PessoaDTO(Pessoa pessoa){
        this.id = pessoa.getId();
        this.cpf = pessoa.getCpf();
        this.email = pessoa.getEmail();
        this.nome = pessoa.getNome();
        this.status = pessoa.getStatus();
        this.login = pessoa.getLogin();
        this.telefone = pessoa.getTelefone();
        //Não tem senha pois não devemos trazer senha em endpoint
    }
    public PessoaDTO(String naoEncontrado){
        this.cpf = naoEncontrado;
    }
}
