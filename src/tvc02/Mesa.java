/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tvc02;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M.Chin
 */
public class Mesa {
    
    int numMesa;
    private List<Pedido> pedidos;
    
    public Mesa() {
        this.pedidos= new ArrayList<>();
    }

    public Mesa(int mesa) {
        this.pedidos = new ArrayList<>();
        this.numMesa = mesa;
    }
        
    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Mesa "+numMesa;
    }
    
}
