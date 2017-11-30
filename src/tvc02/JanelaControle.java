/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tvc02;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private List<Mesa> mesas;

    private JList<Mesa> lstMesas = new JList<>(new DefaultListModel<>());
    public JList<Pedido> lstPedidos = new JList<>(new DefaultListModel<>());
    private JTextArea detalhes = new JTextArea();

    JButton criaMesa = new JButton("Abrir Mesa");
    JButton criaPedido = new JButton("Fazer Pedido");
    JButton excluiPedido = new JButton("Excluir Pedido");
    JButton fechaPedido = new JButton("Fechar Pedido");

    private JanelaPedido janelaPedido;

    JanelaControle() throws IOException, ParseException {
        super("Restaurante");
        this.mesas = new ArrayList<>();

        this.mesas = JanelaControle.this.le();

        JPanel principal = new JPanel(new GridLayout(2, 1));
        JPanel listas = new JPanel(new GridLayout(1, 3));
        listas.add(new JScrollPane(lstMesas));
        listas.add(new JScrollPane(lstPedidos));
        listas.add(new JScrollPane(detalhes));
        JPanel botao = new JPanel(new GridLayout(4, 1));
        botao.add(criaMesa);
        botao.add(criaPedido);
        botao.add(excluiPedido);
        botao.add(fechaPedido);
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
                    while (i < selecionado.getItens().size()) {
                        detalhes.setText(detalhes.getText() + selecionado.getItens().get(i).toString());
                        i++;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    detalhes.setText(detalhes.getText() + (selecionado.situação ? "Hora de abertura: " + sdf.format(selecionado.horaAberto) : "Hora de abertura :" + sdf.format(selecionado.horaAberto) + "\nHora de Fechamento: " + sdf.format(selecionado.horaFechado)));
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
                    try {
                        JanelaControle.this.escreve();
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaControle.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                    try {
                        JanelaControle.this.escreve();
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaControle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (selecionado != null) {
                        if (selecionado.getSituação()) {
                            janelaPedido = new JanelaPedido(selecionado.getItens(), JanelaControle.this);
                            lstPedidos.updateUI();
                            lstPedidos.clearSelection();
                            try {
                                JanelaControle.this.escreve();
                            } catch (IOException ex) {
                                Logger.getLogger(JanelaControle.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não é possivel editar um pedido fechado", "Pedido Fechado", 0);
                        }
                    } else {
                        mesas.get(mesas.indexOf(selecionada)).getPedidos().add(new Pedido(selecionada.getPedidos().size()));
                        lstPedidos.updateUI();
                        int index = selecionada.getPedidos().size() - 1;
                        lstPedidos.setSelectedIndex(index);
                        janelaPedido = new JanelaPedido(selecionada.getPedidos().get(index).getItens(), JanelaControle.this);
                        lstPedidos.updateUI();
                        janelaPedido.lstItem.updateUI();
                        lstPedidos.clearSelection();
                        janelaPedido.lstItem.clearSelection();
                    }
                }
                try {
                    JanelaControle.this.escreve();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaControle.class.getName()).log(Level.SEVERE, null, ex);
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
                    if (selecionado == null) {
                        JOptionPane.showMessageDialog(null, "Selecione um pedido!");

                    } else {
                        
                        selecionada.getPedidos().remove(selecionado);
                        lstPedidos.updateUI();
                        try {
                            JanelaControle.this.escreve();
                        } catch (IOException ex) {
                            Logger.getLogger(JanelaControle.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
                lstPedidos.updateUI();
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
                        try {
                            JanelaControle.this.escreve();
                        } catch (IOException ex) {
                            Logger.getLogger(JanelaControle.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
        try {
            JanelaControle.this.escreve();
        } catch (IOException ex) {
            Logger.getLogger(JanelaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void escreve() throws IOException {

        FileWriter arquivo = new FileWriter("saida.txt");
        PrintWriter escritor = new PrintWriter(arquivo);
        escritor.println("Total de mesas: " + mesas.size());
        FileWriter arquivo2 = new FileWriter("entrada.txt");
        PrintWriter escritor2 = new PrintWriter(arquivo2);
        escritor2.println(mesas.size());
        for (Mesa m : mesas) {
            escritor.println("Total de pedidos da mesa " + m.getNumMesa() + ": " + m.getPedidos().size());
            escritor2.println(m.getNumMesa());
            escritor2.println(m.getPedidos().size());
            for (Pedido p : m.getPedidos()) {
                escritor.println("Codigo: " + p.getNumPedido());
                escritor.println("Situação: " + (p.getSituação() ? "Aberto" : "Fechado"));
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                escritor.println("Abertura: " + sdf.format(p.getHoraAberto()));
                escritor.println("Fechamento: " + (p.getHoraFechado() == null ? " " : sdf.format(p.getHoraFechado())));
                escritor.println("Total de itens: " + p.getItens().size());
                escritor2.println(p.getNumPedido());
                escritor2.println(p.getSituação());
                escritor2.println(sdf.format(p.getHoraAberto()));
                escritor2.println((p.getHoraFechado() == null ? null : sdf.format(p.getHoraFechado())));
                escritor2.println(p.getItens().size());
                for (ItemPedido i : p.getItens()) {
                    escritor.println("Item: " + i.getNome());
                    escritor.println("Quantidade: " + i.getQuantidade());
                    escritor.println("Preço: " + i.getPreco());
                    escritor2.println(i.getNome());
                    escritor2.println(i.getQuantidade());
                    escritor2.println(i.getPreco());
                }
            }
        }
        arquivo.close();
        arquivo2.close();
    }

    public List<Mesa> le() throws IOException, ParseException {
        List<Mesa> listmesas = new ArrayList<Mesa>();;
        List<Pedido> listpedidos = null;
        List<ItemPedido> listitens = null;
        try {
            int mesas = 0, pedidos = 0, itens = 0;
            File arquivo = new File("entrada.txt");
            if (arquivo.exists() == false) {
                JOptionPane.showMessageDialog(null, "Sem arquivo de entrada!");
            } else {
                FileReader arq = new FileReader("entrada.txt");
                BufferedReader leitor = new BufferedReader(arq);
                String linha;
                linha = leitor.readLine();
                if (linha == null) {
                    JOptionPane.showMessageDialog(null, "Sem dados de entrada!");
                    return listmesas;
                } else {
                    mesas = Integer.parseInt(linha);
                    for (int i = 0; i < mesas; i++) {
                        linha = leitor.readLine();
                        Mesa m = new Mesa(Integer.parseInt(linha));
                        linha = leitor.readLine();
                        pedidos = Integer.parseInt(linha);
                        listpedidos = new ArrayList<Pedido>();
                        for (int j = 0; j < pedidos; j++) {
                            linha = leitor.readLine();
                            Pedido p = new Pedido(Integer.parseInt(linha));
                            linha = leitor.readLine();
                            p.setSituação(Boolean.parseBoolean(linha));
                            linha = leitor.readLine();
                            String aux[];
                            aux = linha.split(":");
                            Date d = new Date();
                            d.setHours(Integer.parseInt(aux[0]));
                            d.setMinutes(Integer.parseInt(aux[1]));
                            p.setHoraAberto(d);
                            if (!p.getSituação()) {
                                linha = leitor.readLine();
                                aux = linha.split(":");
                                d = new Date();
                                d.setHours(Integer.parseInt(aux[0]));
                                d.setMinutes(Integer.parseInt(aux[1]));
                                p.setHoraFechado(d);
                            } else {
                                linha = leitor.readLine();
                                p.setHoraFechado(null);
                            }
                            linha = leitor.readLine();
                            itens = Integer.parseInt(linha);
                            listitens = new ArrayList<ItemPedido>();
                            for (int k = 0; k < itens; k++) {
                                linha = leitor.readLine();
                                String nome = linha;
                                linha = leitor.readLine();
                                int quantidade = Integer.parseInt(linha);
                                linha = leitor.readLine();
                                Double preco = Double.parseDouble(linha);
                                ItemPedido ip = new ItemPedido(nome, quantidade, preco);
                                listitens.add(ip);
                                p.setItens(listitens);
                            }
                            listpedidos.add(p);
                            m.setPedidos(listpedidos);
                        }
                        listmesas.add(m);
                    }
                    arq.close();
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return listmesas;
    }
}
