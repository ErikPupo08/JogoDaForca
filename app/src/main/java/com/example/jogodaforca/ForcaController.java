package com.example.jogodaforca;

import java.util.HashSet;
import java.util.Set;

public class ForcaController {

    private String palavraParaAdvinhar;//Palavra a ser advinhada
    private Set<Character> letrasUsadas;//Lista que conterá as letras que foram utilizadas sem itens repitidos


    private int qntErros = -1;//Quantidades de erros

    public ForcaController(String palavra) {
        letrasUsadas = new HashSet<Character>();
        this.palavraParaAdvinhar = palavra;
    }

    public int getQntErros() {
        return qntErros;
    }

    public void joga(Character letra){
        //caso o Set contenha a letra jogada, saimos da função
        if( letrasUsadas.contains( letra ) )
            return;
        letrasUsadas.add( letra );

        if(palavraParaAdvinhar.contains((letra.toString())))
            return;
        qntErros++;
    }

    public String getPalavraAteAgora(){
        String vizualizacao = "";
        for( char c : palavraParaAdvinhar.toCharArray() ){
            if( letrasUsadas.contains( c ) )
                vizualizacao += c;
            else
                vizualizacao += " ";
        }
        return vizualizacao;
    }

    public boolean isMorreu(){
        return getQntErros() == 5;
    }

    public boolean isGanhou(){
        return !getPalavraAteAgora().contains(" ");
    }

    public boolean isTerminou(){
        return isMorreu() || isGanhou();
    }
}
