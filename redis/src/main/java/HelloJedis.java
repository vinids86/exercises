import redis.clients.jedis.Jedis;

/**
 * Created by vinids86 on 31/07/15.
 */
public class HelloJedis {
    public static void main (String ... args) {
        String dataDoSorteio1 = "04-09-2013";
        String numeroDoSorteio1 = "10, 11, 18, 42, 55, 56";
        String chave1 = String.format("resultado:%s:megasena", dataDoSorteio1);

        String dataDoSorteio2 = "07-09-2013";
        String numeroDoSorteio2 = "2, 21, 30, 35, 45, 50";
        String chave2 = String.format("resultado:%s:megasena", dataDoSorteio2);

        String dataDoSorteio3 = "21-09-2013";
        String numeroDoSorteio3 = "2, 13, 24, 41, 42, 44";
        String chave3 = String.format("resultado:%s:megasena", dataDoSorteio3);

        String dataDoSorteio4 = "02-10-2013";
        String numeroDoSorteio4 = "7, 15, 20, 23, 30, 41";
        String chave4 = String.format("resultado:%s:megasena", dataDoSorteio4);

        Jedis jedis = new Jedis("localhost");
        String resultado = jedis.mset(
                chave1, numeroDoSorteio1,
                chave2, numeroDoSorteio2,
                chave3, numeroDoSorteio3,
                chave4, numeroDoSorteio4
        );

        System.out.println(resultado);

        System.out.println(jedis.mget(chave1, chave2));
        System.out.println(jedis.strlen(chave1));
    }
}
