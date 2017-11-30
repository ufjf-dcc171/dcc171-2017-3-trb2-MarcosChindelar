/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tvc02;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author M.Chin
 */
public class JanelaPedido extends JFrame {

    private List<ItemPedido> itens;
    public JList<ItemPedido> lstItem = new JList<ItemPedido>(new DefaultListModel<ItemPedido>());

    private final JLabel lbnomeItem = new JLabel("Item");
    private final JLabel lbquantItem = new JLabel("Quantidade");
    private final JLabel lbprecoItem = new JLabel("Preço");
    private final JTextField txtNomeItem = new JTextField(30);
    private final JTextField txtQuantItem = new JTextField(30);
    private final JTextField txtprecoItem = new JTextField(30);
    private final JButton btnAdd = new JButton("Adicionar Item");
    private JanelaControle janelaControle;

    public JanelaPedido(List<ItemPedido> get, JanelaControle aThis) throws HeadlessException {
        super("Itens");
        janelaControle = aThis;
        this.itens = get;
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        add(new JScrollPane(lstItem), BorderLayout.WEST);

        lstItem.setModel(new ItemPedidoListModel(itens));
        JPanel principal = new JPanel(new GridLayout(3, 3));
        principal.add(lbnomeItem);
        principal.add(txtNomeItem);
        principal.add(lbquantItem);
        principal.add(txtQuantItem);
        principal.add(lbprecoItem);
        principal.add(txtprecoItem);
        add(principal, BorderLayout.CENTER);
        add(btnAdd, BorderLayout.SOUTH);

        lstItem.setModel(new ItemPedidoListModel(itens));
        lstItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstItem.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ItemPedido selecionado = lstItem.getSelectedValue();
                if (selecionado != null) {
                    txtQuantItem.setText("" + selecionado.getQuantidade());
                    txtNomeItem.setText(selecionado.getNome());
                    txtprecoItem.setText("" + selecionado.getPreco());

                } else {
                    txtQuantItem.setText("");
                    txtNomeItem.setText("");
                    txtprecoItem.setText("");

                }
                
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemPedido selecionado = lstItem.getSelectedValue();
                if (selecionado != null) {
                    selecionado.setNome(txtNomeItem.getText());
                    selecionado.setQuantidade(Integer.parseInt(txtQuantItem.getText()));
                    selecionado.setPreco(Double.parseDouble(txtprecoItem.getText()));
                    janelaControle.getLstPedidos().updateUI();
                    lstItem.updateUI();
                    lstItem.clearSelection();
                    janelaControle.getLstPedidos().clearSelection();
                    try {
                        janelaControle.escreve();
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    ItemPedido i = new ItemPedido(txtNomeItem.getText(), Integer.parseInt(txtQuantItem.getText()), Double.parseDouble(txtprecoItem.getText()));
                    itens.add(i);
                    janelaControle.getLstPedidos().updateUI();
                    lstItem.updateUI();
                    lstItem.clearSelection();
                    janelaControle.getLstPedidos().clearSelection();
                }
                try {
                    janelaControle.escreve();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
                janelaControle.getLstPedidos().updateUI();
                lstItem.updateUI();
                lstItem.clearSelection();
                janelaControle.getLstPedidos().clearSelection();
            }
            
        });

    }

    public void setJanelaControle(JanelaControle janela) {
        this.janelaControle = janela;
    }
}
