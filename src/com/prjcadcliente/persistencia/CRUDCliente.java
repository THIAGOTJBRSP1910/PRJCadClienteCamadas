package com.prjcadcliente.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.prjcadcliente.dominio.Cliente;

/**
 * <b>CRUDCliente</b><br>
 * Essa classe permite manipular as informaçoes do cliente. Aqui voce
 * encontrará os seguintes comandos:
 * <ul>
 *  <li>Cadastro</li>
 *  <li>Pesquisar por nome e por id</li>
 *  <li>Atualizar</li>
 *  <li>Deletar</li>
 *  </ul>
 * @author thiago.jbezerra
 *
 */

public class CRUDCliente {
	
	//Declaração das instancias de comunicação com o banco
	//de dados
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	
	public String cadastrar(Cliente cliente) {
		
		String msg = "";
		
		//Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");//Realiza a conexão do driver
			
			String consulta = "INSERT INTO tbcliente(nome,email,telefone,idade)values(?,?,?,?)";//Trazer o resultado da tabela
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Cadastro realizo com sucesso";
			else
				msg = "Não foi possivel cadastrar";
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar cadastrar: "+ex.getMessage();//mostrar mensagem de erro
		}
		catch(Exception e) {
			msg = "Erro inesperado:"+e.getMessage();
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}//quando terminar de processar o banco, ele fecha
		}
		
		
		return msg;
	}
	
	public String atualizar(Cliente cliente) {
		return null;
	}
	
	public String deletar(Cliente cliente) {
		return null;
	}
	
	public List<Cliente> PesquisarPorNome(String nome) {
		return null;
	}
	
	public Cliente PesquisarPorId(int id) {
		return null;
	}
	
	public List<Cliente> PesquisarTodos() {
		return null;
	}
}
