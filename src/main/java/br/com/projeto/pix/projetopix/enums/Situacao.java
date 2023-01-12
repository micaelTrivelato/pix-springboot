package br.com.projeto.pix.projetopix.enums;

import lombok.Getter;

@Getter
public enum Situacao {

    INATIVO(0,"Inativo"),
    ATIVO(1,"Ativo");
    
    private Integer id;
    private String situacao;

    Situacao(Integer id, String situacao){
        this.id = id;
        this.situacao = situacao;
    }
}
