package com.getyourgame.getyourgame.util;

import org.springframework.http.HttpMethod;

/**
 * Created by Guilherme on 14/09/2015.
 */
public class Webservice {
    private String servico;
    private HttpMethod metodo;
    private Boolean auth;

    public Webservice(){
    }

    public Webservice(String servico, HttpMethod metodo, Boolean auth){
        this.servico = servico;
        this.metodo = metodo;
        this.auth = auth;
    }

    public String getServico() {
        return servico;
    }

    public Boolean getAuth() {
        return auth;
    }

    public HttpMethod getMetodo() {
        return metodo;
    }

    public Webservice cadastro(){
        return new Webservice("register", HttpMethod.POST, false);
    }

    public Webservice login(){
        return new Webservice("login", HttpMethod.POST, false);
    }

    public Webservice buscarUsuario(int id_usuario){
        return new Webservice("usuario/"+id_usuario, HttpMethod.GET, true);
    }

    public Webservice atualizarUsuario(int id_usuario){
        return new Webservice("usuario/"+id_usuario, HttpMethod.PUT, false);
    }

    public Webservice estadoJogo(){
        return new Webservice("estado_jogo", HttpMethod.GET, false);
    }

    public Webservice buscaCadastros(){
        return new Webservice("cadastros", HttpMethod.GET, false);
    }
}
