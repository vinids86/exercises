package br.com.caelum.leilao.infra.dao;

import br.com.caelum.leilao.dominio.Pagamento;

/**
 * Created by vinids86 on 01/08/15.
 */

public interface RepositorioDePagamentos {
    void salva(Pagamento pagamento);
}
