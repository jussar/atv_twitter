/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.atividade.neo4j;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Jussara
 */
public class usuario {
    private String user;
    private String senha;
    private String email;    

    public usuario(String user, String senha, String email) {
        this.user = user;
        this.senha = senha;
        this.email = email;
        List<Publicacao> segue = new ArrayList<Publicacao>();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
