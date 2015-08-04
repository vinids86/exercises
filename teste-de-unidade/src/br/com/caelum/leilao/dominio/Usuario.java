package br.com.caelum.leilao.dominio;

public class Usuario {

	private int id;
	private String nome;
	
	public Usuario(String nome) {
		this(0, nome);
	}

	public Usuario(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Usuario usuario = (Usuario) o;

		if (getId() != usuario.getId()) return false;
		return !(getNome() != null ? !getNome().equals(usuario.getNome()) : usuario.getNome() != null);

	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
		return result;
	}
}
