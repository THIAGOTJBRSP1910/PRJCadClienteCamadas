package com.prjcadcliente.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prjcadcliente.dominio.Cliente;

/**
 * <b>CRUDCliente</b><br>
 * Essa classe permite manipular as informa�oes do cliente. Aqui voce
 * encontrar� os seguintes comandos:
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
	
	//Declara��o das instancias de comunica��o com o banco
	//de dados
	private Connection con = null;//Estabelecer a comunica��o com o banco de dados
	private ResultSet rs = null;//Guardar os retornos dos selects no banco de dados
	private PreparedStatement pst = null;//Executa as consultas de SQL
	
	
	public String cadastrar(Cliente cliente) {
		
		String msg = "";
		
		//Cria��o dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");//Realiza a conex�o do driver
			
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
				msg = "N�o foi possivel cadastrar";
			
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
				String msg = "";
		
		//Cria��o dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");//Realiza a conex�o do driver
			
			String consulta = "UPDATE tbcliente SET nome=?,email=?,telefone=?,idade=? WHERE id=?";//Trazer o resultado da tabela
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			pst.setInt(5, cliente.getId());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Atualizado com sucesso";
			else
				msg = "N�o foi possivel atualizar";
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar atualizar: "+ex.getMessage();//mostrar mensagem de erro
		}
		catch(Exception e) {
			msg = "Erro inesperado:"+e.getMessage();
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}//quando terminar de processar o banco, ele fecha
		}
		
		
		return msg;
	}
	
	public String deletar(Cliente cliente) {
			String msg = "";
		
		//Cria��o dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");//Realiza a conex�o do driver
			
			String consulta = "DELETE FROM tbcliente WHERE id=?";//Trazer o resultado da tabela
			
			pst = con.prepareStatement(consulta);
			
			pst.setInt(1, cliente.getId());
			
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Deletado com sucesso";
			else
				msg = "N�o foi possivel deletar";
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar deletar: "+ex.getMessage();//mostrar mensagem de erro
		}
		catch(Exception e) {
			msg = "Erro inesperado:"+e.getMessage();
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}//quando terminar de processar o banco, ele fecha
		}
		
		
		return msg;
	}
	
	public List<Cliente> PesquisarPorNome(String nome) {
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			//carregar o drive de comunica��o com o banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciador de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");
			
			//Vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbcliente where nome=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, nome);
			
			//Vamos executar a consulta e guardar o resultado na variavel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que est� no rs e adiciona-lo
			 * a lista de clientes para, ent�o retorna-la
			 */
			while(rs.next()) {
				lista.add(new Cliente(rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			}//Fim do while
			
		}//Fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		
		return lista;
	}
	
	public Cliente PesquisarPorId(int id) {
			Cliente cliente = new Cliente();//array � um do lado do outro com"," lista � um embaixo do outro
		
		try {
			//carregar o drive de comunica��o com o banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciador de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");
			
			//Vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbcliente where id=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setInt(1,id);
			
			//Vamos executar a consulta e guardar o resultado na variavel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que est� no rs e adiciona-lo
			 * a lista de clientes para, ent�o retorna-la
			 */
			if(rs.next()) {
				cliente.setId(rs.getInt(0));
				cliente.setNome(rs.getString(1));
				cliente.setEmail(rs.getString(2));
				cliente.setTelefone(rs.getString(3));
				cliente.setIdade(rs.getInt(4));
			}
		}//Fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		
		return cliente;
	}
	
	public List<Cliente> PesquisarTodos() {
			List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			//carregar o drive de comunica��o com o banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciador de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");
			
			//Vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbcliente";
			
			pst = con.prepareStatement(consulta);
			
			
			//Vamos executar a consulta e guardar o resultado na variavel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que est� no rs e adiciona-lo
			 * a lista de clientes para, ent�o retorna-la
			 */
			while(rs.next()) {
				lista.add(new Cliente(rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			}//Fim do while
			
		}//Fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		
		return lista;
	}
}
