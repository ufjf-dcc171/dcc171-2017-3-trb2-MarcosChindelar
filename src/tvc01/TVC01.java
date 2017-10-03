package tvc01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

public class TVC01 {

    public static void main(String[] args) {

        JanelaControle janela = new JanelaControle(getSampleData());
        janela.setSize(430, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(false);
        
    }

    private static List<Mesa> getSampleData() {

        ItemPedido i1 = new ItemPedido("Cervaja", 5);
        ItemPedido i2 = new ItemPedido("Pastel", 10);
        ItemPedido i3 = new ItemPedido("Coca-Cola", 2);
        ItemPedido i4 = new ItemPedido("Porção de Kibe", 1);
        List<ItemPedido> itens1 = new ArrayList<ItemPedido>();
        List<ItemPedido> itens2 = new ArrayList<ItemPedido>();
        itens1.add(i1);itens1.add(i2);
        itens2.add(i3);itens2.add(i4);
        
        List<Pedido> pedidos1 = new ArrayList<Pedido>();
        List<Pedido> pedidos2 = new ArrayList<Pedido>();
        Pedido p1 = new Pedido(0);
        Pedido p2 = new Pedido(0);
        p1.setItens(itens1);       
        p2.setItens(itens2);
        pedidos1.add(p1);
        pedidos2.add(p2);
        pedidos2.add(p1);
           
        List<Mesa> mesas = new ArrayList<Mesa>();           
        Mesa m1 = new Mesa(0);
        Mesa m2 = new Mesa(0);
        m1.setPedidos(pedidos1);
        m2.setPedidos(pedidos2);
        mesas.add(m1);
        mesas.add(m2);
        
        return mesas;
    }

}
