/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tvc02;

/**
 *
 * @author M.Chin
 */
public class ItemPedido {
    
    int quantidade;
    Double preco;
    String nome;


    public ItemPedido(String nome, int quantidade,Double valor) {
        this.quantidade = quantidade;
        this.nome = nome;
        this.preco = valor;
    }
    
    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double valor) {
        this.preco = valor;
    }

    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome+" x"+quantidade+" R$ "+preco+"\n";
        
    }   
}
