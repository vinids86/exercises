package br.com.caelum.leilao.infra.relogio;

import java.util.Calendar;

/**
 * Created by vinids86 on 02/08/15.
 */
public class RelogioDoSistema implements Relogio {
    @Override
    public Calendar hoje() {
        return Calendar.getInstance();
    }
}
