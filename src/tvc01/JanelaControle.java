/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tvc01;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.geometry.VerticalDirection;
import javafx.scene.layout.Border;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author M.Chin
 */
public class JanelaControle extends JFrame {

    private List<Mesa> mesas = new ArrayList<Mesa>();

    private JList<Mesa> lstMesas = new JList<Mesa>(new DefaultListModel<Mesa>());
    private JList<Pedido> lstPedidos = new JList<Pedido>(new DefaultListModel<Pedido>());
    private JTextArea detalhes = new JTextArea ();
    
    JButton criaMesa = new JButton("Abrir Mesa");
    JButton criaPedido = new JButton("Fazer Pedido");
    JButton excluiPedido = new JButton("Excluir Pedido");
    JButton fechaPedido = new JButton("Fechar Pedido");

    private JanelaPedido janelaPedido;

    JanelaControle(List<Mesa> sampleData) {
        super("Restaurante");
        this.mesas = sampleData;
       
        JPanel principal = new JPanel(new GridLayout(2,1));
        JPanel listas = new JPanel(new GridLayout(1,3));
        listas.add(lstMesas);listas.add(lstPedidos);listas.add(detalhes);
        JPanel botao = new JPanel(new GridLayout(4,1));
        botao.add(criaMesa);botao.add(criaPedido);botao.add(excluiPedido);botao.add(fechaPedido);
        add(principal);
        principal.add(listas);
        principal.add(botao);
        detalhes.setEditable(false);     
        
        
        lstMesas.setModel(new MesaListModel(mesas));
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstMesas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if (selecionada != null) {
                    detalhes.setText("");
                    lstPedidos.setModel(new PedidoListModel(selecionada.getPedidos()));
                } else {
                    lstPedidos.setModel(new DefaultListModel<>());
                }
            }
        });
        lstPedidos.addListSelectionListener(new ListSelectionListener() {
            @Override
           public void valueChanged(ListSelectionEvent e) {
                Pedido selecionado = lstPedidos.getSelectedValue();
                if (selecionado != null) {
                    detalhes.setText("");
                    int i = 0;
                    while(i<selecionado.getItens().size()){
                        detalhes.setText(detalhes.getText()+selecionado.getItens().get(i).toString());
                        i++;
                    }   
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        detalhes.setText(detalhes.getText()+(selecionado.situação? "Hora de abertura: "+sdf.format(selecionado.horaAberto) : "Hora de abertura :"+sdf.format(selecionado.horaAberto) +"\nHora de Fechamento: "+sdf.format(selecionado.horaFechado)));
                } else {
                    lstPedidos.setModel(new DefaultListModel<>());
                }
            }
        });

        criaMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cod = JOptionPane.showInputDialog("Insira o Numero da Mesa");
                if (cod != null && !cod.isEmpty()) {
                    Mesa m = new Mesa();
                    m.setNumMesa(Integer.parseInt(cod));
                    mesas.add(m);
                    lstMesas.updateUI();
                }
            }
        });
        criaPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if (selecionada == null) {
                    JOptionPane.showMessageDialog(null, "É preciso selecionar uma mesa", "Mesa invalida", 0);
                } else {
                    Pedido selecionado = lstPedidos.getSelectedValue();
                    if (selecionado != null) {
                        if (selecionado.getSituação()) {
                            janelaPedido = new JanelaPedido(selecionado.getItens(), JanelaControle.this);
                            
                            lstPedidos.updateUI();
                        } else {
                            JOptionPane.showMessageDialog(null, "Não é possivel editar um pedido fechado", "Pedido Fechado", 0);
                        }
                    } else {
                        mesas.get(mesas.indexOf(selecionada)).getPedidos().add(new Pedido(selecionada.getPedidos().size()));
                        lstPedidos.updateUI();
                        int index = selecionada.getPedidos().size() - 1;
                        lstPedidos.setSelectedIndex(index);
                        janelaPedido = new JanelaPedido(selecionada.getPedidos().get(index).getItens(), JanelaControle.this);
                    }

                }
            }

        }
        );
        excluiPedido.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if (selecionada == null) {
                    JOptionPane.showMessageDialog(null, "Selecione uma mesa!");
                } else {
                    Pedido selecionado = lstPedidos.getSelectedValue();
                    if (selecionado != null) {
                        selecionada.getPedidos().remove(selecionado);
                        lstPedidos.updateUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um pedido!");
                    }
                }
            }
        }
        );
        fechaPedido.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if (selecionada == null) {
                    JOptionPane.showMessageDialog(null, "Selecione uma mesa!");
                } else {
                    Pedido selecionado = lstPedidos.getSelectedValue();
                    if (selecionado != null) {
                        selecionado.situação = false;
                        selecionado.horaFechado = new Date();
                        detalhes.updateUI();
                        lstPedidos.updateUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um pedido!");
                    }
                }
            }
        }
        );

    }

    public void adicionaPedido(Pedido p) {

        lstMesas.getSelectedValue().getPedidos().add(p);
        lstMesas.updateUI();
        lstPedidos.updateUI();
        janelaPedido.setVisible(false);
    }
    
      public JList<Mesa> getLstMesas() {
        return lstMesas;
    }

    public void setLstMesas(JList<Mesa> lstMesas) {
        this.lstMesas = lstMesas;
    }

    public JList<Pedido> getLstPedidos() {
        return lstPedidos;
    }

    public void setLstPedidos(JList<Pedido> lstPedidos) {
        this.lstPedidos = lstPedidos;
    }

}
