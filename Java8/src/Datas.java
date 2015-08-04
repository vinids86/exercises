import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by vinids86 on 04/08/15.
 */
public class Datas {

    public static void main(String... args) {
        LocalDate hoje = LocalDate.now();
        System.out.println(hoje);

        LocalDate olimpiadasRio = LocalDate.of(2016, Month.JUNE, 5);

        int anos = olimpiadasRio.getYear() - hoje.getYear();

        System.out.println(anos);

        Period periodo = Period.between(hoje, olimpiadasRio);

        LocalDate proximasOlimpiadas = olimpiadasRio.plusYears(4);

        System.out.println(proximasOlimpiadas);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String valorFormatado = proximasOlimpiadas.format(formatador);
        System.out.println(valorFormatado);

        DateTimeFormatter formatadorComHoras = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

        LocalDateTime agora = LocalDateTime.now();
        System.out.println(agora.format(formatadorComHoras));

        LocalTime intervalo = LocalTime.of(15, 30);
        System.out.println(intervalo);
        
    }
}
