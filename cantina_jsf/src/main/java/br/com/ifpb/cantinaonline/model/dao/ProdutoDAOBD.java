package br.com.ifpb.cantinaonline.model.dao;

import br.com.ifpb.cantinaonline.model.Acesso;
import br.com.ifpb.cantinaonline.model.AcessoProduto;
import br.com.ifpb.cantinaonline.model.Produto;
import br.com.ifpb.cantinaonline.model.conexaoBanco.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAOBD implements ProdutoDAO {

    private ConnectionFactory conexao;

    public ProdutoDAOBD() {
        conexao = new ConnectionFactory();

    }

    @Override
    public boolean adicionarProduto(Produto produto) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO produto(id,nome," +
                    "preco,quantidade) VALUES(?,?,?,?)");
            statement.setString(1,produto.getId());
            statement.setString(2,produto.getNome());
            statement.setDouble(3,produto.getPreco());
            statement.setInt(4,produto.getQuantidade());
            return statement.executeUpdate()>0;
        }
    }

    @Override
    public boolean removerProduto(Produto produto) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
            statement.setString(1, produto.getId());
            return statement.executeUpdate()>0;
        }
    }
    @Override
    public Produto buscarProduto(Produto produto) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM produto " +
                    "WHERE id =? AND nome =?");
            statement.setString(1,produto.getId());
            statement.setString(2, produto.getNome());
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Produto produto1 = new Produto(set.getString("id"),
                        set.getString("nome"),set.getDouble("preco"),
                        set.getInt("quantidade"));
                return produto1;
            }else{
                return null;
            }
        }
    }

    @Override
    public ArrayList listarProduto() throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM produto");
           ResultSet set = statement.executeQuery();
           ArrayList arrayList = new ArrayList();

            while (set.next()){
                String id = set.getString("id");
                String nome = set.getString("nome");
                double preco = set.getDouble("preco");
                Integer quantidade = set.getInt("quantidade");

                Produto produto = new Produto(id,nome,preco,quantidade);
                arrayList.add(produto);

            }

            return arrayList;

        }
    }
}
