/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tvc02;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author M.Chin
 */
public class Pedido {
    
    int numPedido;
    boolean situação;
    Date horaAberto;
    Date horaFechado;
    private List<ItemPedido> itens;
    
    Pedido(int numero){
        this.numPedido = numero;
        this.itens = new ArrayList<ItemPedido>();
        horaAberto=new Date();
        
        situação = true;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }
    
    
    
    public Date getHoraAberto() {
        return horaAberto;
    }

    public void setHoraAberto(Date horaAberto) {
        this.horaAberto = horaAberto;
    }

    public Date getHoraFechado() {
        return horaFechado;
    }

    public void setHoraFechado(Date horaFechado) {
        this.horaFechado = horaFechado;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public boolean getSituação() {
        return situação;
    }

    public void setSituação(boolean situação) {
        this.situação = situação;
    }
  
    @Override
    public String toString() {
        return "Pedido: "+numPedido;
    }
       
}
