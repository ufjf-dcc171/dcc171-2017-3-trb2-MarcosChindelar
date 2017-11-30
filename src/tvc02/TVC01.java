package tvc02;

import java.awt.Frame;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;

public class TVC01 {

    public static void main(String[] args) throws IOException, ParseException {

        JanelaControle janela = new JanelaControle();
        janela.setSize(800, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(false);
        
    }

    /*private static List<Mesa> getSampleData() {
       
        ItemPedido i1 = new ItemPedido("Cervaja", 5, 9.60);
        ItemPedido i2 = new ItemPedido("Pastel", 10,8.0);
        ItemPedido i3 = new ItemPedido("Coca-Cola",2 ,5.50);
        ItemPedido i4 = new ItemPedido("Porção de Kibe", 1,15.00);
        List<ItemPedido> itens1 = new ArrayList<ItemPedido>();
        List<ItemPedido> itens2 = new ArrayList<ItemPedido>();
        itens1.add(i1);itens1.add(i2);
        itens2.add(i3);itens2.add(i4);
        
        List<Pedido> pedidos1 = new ArrayList<Pedido>();
        List<Pedido> pedidos2 = new ArrayList<Pedido>();
        Pedido p1 = new Pedido(0);
        Pedido p2 = new Pedido(1);
        p1.setItens(itens1);       
        p2.setItens(itens2);
        pedidos1.add(p1);
        pedidos2.add(p2);
                
        List<Mesa> mesas = new ArrayList<Mesa>();           
        Mesa m1 = new Mesa(0);
        Mesa m2 = new Mesa(1);
        m1.setPedidos(pedidos1);
        m2.setPedidos(pedidos2);
        mesas.add(m1);
        mesas.add(m2);
        
        return mesas;
    }
*/
}
