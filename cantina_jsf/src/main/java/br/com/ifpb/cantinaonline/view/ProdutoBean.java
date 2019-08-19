package br.com.ifpb.cantinaonline.view;

import br.com.ifpb.cantinaonline.model.Produto;
import br.com.ifpb.cantinaonline.model.dao.ProdutoDAOBD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.SQLException;
import java.util.List;


@ManagedBean
@ViewScoped
public class ProdutoBean {

    private Produto produto;
    private List<Produto> produtos;

    public String listarProdutos() throws SQLException, ClassNotFoundException {

        ProdutoDAOBD produtoDAOBD = new ProdutoDAOBD();
        this.produtos = produtoDAOBD.listarProduto();
        return "listagemProdutos.xhtml";

    }

    public List<Produto> getUsuarios() {
        return produtos;
    }



}
