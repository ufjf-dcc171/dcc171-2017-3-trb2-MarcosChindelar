/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tvc02;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author M.Chin
 */
public class ItemPedidoListModel implements ListModel<ItemPedido> {

    private final List<ItemPedido> itens;
    private final List<ListDataListener> dataListeners;

    public ItemPedidoListModel(List<ItemPedido> itens) {
        this.itens = itens;
        this.dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return itens.size();
    }

    @Override
    public ItemPedido getElementAt(int index) {
        return itens.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.dataListeners.remove(l);
    }

    @Override
    public String toString() {
        return ""+itens;
    }

}
