package br.com.ifpb.cantinaonline.model.dao;


import br.com.ifpb.cantinaonline.model.Usuario;
import br.com.ifpb.cantinaonline.model.conexaoBanco.ConnectionFactory;

import java.sql.*;


public class UsuarioDAOBD implements UsuarioDAO {

    private ConnectionFactory conexao;

    public UsuarioDAOBD() {
        conexao = new ConnectionFactory();

    }
    @Override
    public boolean adicionar(Usuario usuario) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO usuario(nomecompleto,nomeusuario," +
                    "idade,email,senha,telefone,funcao,cidade,bairro,rua,numero) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,usuario.getNomeCompleto());
            statement.setString(2,usuario.getNomeUsuario());
            statement.setInt(3, usuario.getIdade());
            statement.setString(4,usuario.getEmail());
            statement.setString(5, usuario.getSenha());
            statement.setString(6, usuario.getTelefone());
            statement.setString(7, usuario.getFuncao());
            statement.setString(8, usuario.getEndereco().getCidade());
            statement.setString(9,usuario.getEndereco().getBairro());
            statement.setString(10, usuario.getEndereco().getRua());
            statement.setInt(11, usuario.getEndereco().getNumero());
            return statement.executeUpdate()>0;
        }

    }

    @Override
    public boolean remover(Usuario usuario) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM usuario WHERE id = ?");
            statement.setString(1, usuario.getNomeUsuario());
            return statement.executeUpdate()>0;
        }
    }


}
