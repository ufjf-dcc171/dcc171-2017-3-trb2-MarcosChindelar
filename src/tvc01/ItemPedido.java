/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tvc01;

/**
 *
 * @author M.Chin
 */
public class ItemPedido {

    public ItemPedido(String nome, int quantidade) {
        this.quantidade = quantidade;
        this.nome = nome;
    }

    int quantidade;
    float preço;
    String nome;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreço() {
        return preço;
    }

    public void setPreço(float preço) {
        this.preço = preço;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome+" x"+quantidade+"\n";
    }   
}
