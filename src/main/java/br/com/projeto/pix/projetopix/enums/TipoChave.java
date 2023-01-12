package br.com.projeto.pix.projetopix.enums;

import lombok.Getter;

@Getter
public enum TipoChave {
    
    CELULAR(0,"Celular"),
    EMAIL(1,"Email"),
    CPF(2,"CPF"),
    ALEATORIA(3,"Aleatoria");

    private Integer id;
    private String tipoChave;

    TipoChave(Integer id, String tipoChave){
        this.id = id;
        this.tipoChave = tipoChave;
    }
}
