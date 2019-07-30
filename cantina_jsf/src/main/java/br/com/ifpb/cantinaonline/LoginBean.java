package br.com.ifpb.cantinaonline;

import br.com.ifpb.cantinaonline.model.Acesso;
import br.com.ifpb.cantinaonline.model.dao.UsuarioDAOBD;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class LoginBean {
    private String nomeUsuario;
    private String senha;
    public String efetuarLogin() throws SQLException, ClassNotFoundException {
        Acesso acesso = new Acesso(nomeUsuario,senha,"","");
        UsuarioDAOBD usuarioDAOBD = new UsuarioDAOBD();
        if(usuarioDAOBD.buscar(acesso) != null){
            System.out.println(usuarioDAOBD.buscar(acesso));
            return "principal.xhtml";
        } else {
            FacesMessage message = new FacesMessage("Login ou Senha incorretos", "Login ou Senha incorretos");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }


    }

    public String fazerLogout() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "login.xhtml";
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
