package com.prjcadcliente.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.prjcadcliente.dominio.Cliente;
import com.prjcadcliente.persistencia.CRUDCliente;

public class GerenciarClientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeDoCliente;
	private JTextField txtEmail;
	private JLabel lblTelefone;
	private JTextField txtTelefone;
	private JLabel lblIdade;
	private JTextField txtIdade;
	private JTable tbClientesCadastrados;
	private Cliente cliente;
	private CRUDCliente crud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarClientes frame = new GerenciarClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciarClientes() {
		setTitle("Gerenciar Clientes");
		
		//vamos instanciar as classes Cliente e CRUD
		cliente = new Cliente();
		crud = new CRUDCliente();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeDoCliente = new JLabel("Nome do Cliente:");
		lblNomeDoCliente.setBounds(10, 11, 102, 14);
		contentPane.add(lblNomeDoCliente);
		
		txtNomeDoCliente = new JTextField();
		txtNomeDoCliente.setBounds(10, 27, 373, 20);
		contentPane.add(txtNomeDoCliente);
		txtNomeDoCliente.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 58, 48, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 77, 373, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 108, 69, 14);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(10, 129, 140, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(10, 155, 48, 14);
		contentPane.add(lblIdade);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(10, 172, 102, 20);
		contentPane.add(txtIdade);
		txtIdade.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Passar os dados do formulario para o objeto cliente
				cliente.setNome(txtNomeDoCliente.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				String retorno = crud.cadastrar(cliente);
				JOptionPane.showMessageDialog(null, retorno);
				txtNomeDoCliente.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
				
			}
		});
		btnCadastrar.setBounds(10, 203, 94, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Digite o Id do CLiente");
				
				//Passar os dados do formulario para o objeto cliente
				cliente.setNome(txtNomeDoCliente.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				cliente.setId(Integer.parseInt(id));
				String retorno = crud.atualizar(cliente);
				JOptionPane.showMessageDialog(null, retorno);
				txtNomeDoCliente.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
				id="0";
				
				
			}
		});
		btnAtualizar.setBounds(109, 203, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Digite o Id do cliente para apagar");
				
				cliente.setId(Integer.parseInt(id));
				
				JOptionPane.showMessageDialog(null, crud.deletar(cliente));
				
			}
		});
		btnDeletar.setBounds(203, 203, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(294, 203, 89, 23);
		contentPane.add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 237, 373, 192);
		contentPane.add(scrollPane);
		
		String[] colunas = {"Id","Nome","E-Mail","Telefone","Idade"};
		
		Object[][] dados = {
				{15,"Roberto","roberto@gmail.com","11",12},
				{21,"Vanessa","vanessa@gmail.com","11",32},
				{55,"Veronica","v@gmail.com","11",65},
				{95,"Tadeu","tadeu@gmail.com","11",42},
		
		};
		
		//Vamos construir o modelo de dados para exibir na tabela
		DefaultTableModel modelo = new DefaultTableModel(dados,colunas);
		
		tbClientesCadastrados = new JTable(modelo);
		scrollPane.setViewportView(tbClientesCadastrados);
	}
}
