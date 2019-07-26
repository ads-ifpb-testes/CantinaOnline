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
                    "datanascimento,email,cidade,bairro,rua,numero,senha,telefone,funcao) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,usuario.getNomeCompleto());
            statement.setString(2,usuario.getNomeUsuario());
            statement.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            statement.setString(4,usuario.getEmail());
            statement.setString(5,usuario.getEndereco().getCidade());
            statement.setString(6,usuario.getEndereco().getBairro());
            statement.setString(7,usuario.getEndereco().getRua());
            statement.setString(8,usuario.getEndereco().getNumero());
            statement.setString(9,usuario.getSenha());
            statement.setString(10,usuario.getTelefone());
            statement.setString(11, usuario.getFuncao());
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
