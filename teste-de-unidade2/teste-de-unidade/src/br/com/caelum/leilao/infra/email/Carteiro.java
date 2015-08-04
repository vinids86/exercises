package br.com.caelum.leilao.infra.email;

import br.com.caelum.leilao.dominio.Leilao;

/**
 * Created by vinids86 on 01/08/15.
 */

public interface Carteiro {
    void envia(Leilao leilao);
}
