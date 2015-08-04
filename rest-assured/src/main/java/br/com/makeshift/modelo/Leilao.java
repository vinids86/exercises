package br.com.makeshift.modelo;

/**
 * Created by vinids86 on 02/08/15.
 */

public class Leilao {

    private Long id;
    private String nome;
    private Double valorInicial;
    private Usuario usuario;
    private boolean usado;

    public Leilao() {}

    public Leilao(Long id, String nome, Double valorInicial, Usuario usuario, boolean usado) {
        this.id = id;
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.usuario = usuario;
        this.usado = usado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
}