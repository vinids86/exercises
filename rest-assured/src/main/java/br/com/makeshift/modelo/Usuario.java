package br.com.makeshift.modelo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vinids86 on 02/08/15.
 */

@XmlRootElement
public class Usuario {

    private long id;
    private String nome;
    private String email;

    public Usuario() {}

    public Usuario(long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(String nome, String email) {
        this(0l, nome, email);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (getId() != usuario.getId()) return false;
        if (getNome() != null ? !getNome().equals(usuario.getNome()) : usuario.getNome() != null) return false;
        return !(getEmail() != null ? !getEmail().equals(usuario.getEmail()) : usuario.getEmail() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}