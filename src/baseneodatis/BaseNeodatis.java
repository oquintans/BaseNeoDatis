/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseneodatis;

/**
 *
 * @author oracle
 */
public class BaseNeodatis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Metodos m = new Metodos();
      // m.step2();
        //  m.amosar_deportes();
      //  m.actualiza_por_nome_xogador("luis", "paco");
        //m.consulta_xogadores();
        //m.xogadoresDeporte("tennis");
        m.devoltar_equipos_con_xogadores_menos_dunha_cantidade(1600);
        //m.prueba();
    }

}
