/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseneodatis;

import java.util.Date;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author oracle
 */
public class Metodos {

    private String ODB_NAME = "baseneodatis";
    private ODB odb = null;

    public void step2() throws Exception {
// Create instance
        Sport volleyball = new Sport("volley-ball");
        Sport tennis = new Sport("tennis");

// Create 8 players
        Player player1 = new Player("olivier", new Date(), volleyball, 1000);
        Player player2 = new Player("pierre", new Date(), volleyball, 1500);
        Player player3 = new Player("elohim", new Date(), volleyball, 2000);
        Player player4 = new Player("minh", new Date(), volleyball, 1300);
        Player player5 = new Player("luis", new Date(), tennis, 1600);
        Player player6 = new Player("carlos", new Date(), tennis, 2000);
        Player player7 = new Player("luis", new Date(), tennis, 1500);
        Player player8 = new Player("jose", new Date(), tennis, 3000);

// Create 4 teams
        Team team1 = new Team("Paris");
        Team team2 = new Team("Montpellier");
        Team team3 = new Team("Bordeux");
        Team team4 = new Team("Lion");

// Set players for teams
        team1.addPlayer(player1);
        team1.addPlayer(player2);
        team2.addPlayer(player3);
        team2.addPlayer(player4);
        team3.addPlayer(player5);
        team3.addPlayer(player6);
        team4.addPlayer(player7);
        team4.addPlayer(player8);

// Then create game2 for the two teams
        Game game1 = new Game(new Date(), volleyball, team1, team2);
        Game game2 = new Game(new Date(), tennis, team3, team4);

        try {
// Open the database
            odb = ODBFactory.open(ODB_NAME);
// Store the object
            odb.store(game1);
            odb.store(game2);
        } finally {
            if (odb != null) {
// Close the database
                odb.close();

            }
        }
    }

    public void amosar_deportes() {

        odb = ODBFactory.open(ODB_NAME);
        IQuery q = new CriteriaQuery(Sport.class);
        Objects<Sport> objs = odb.getObjects(q);

        while (objs.hasNext()) {
            System.out.println(objs.next().getName());
        }

        odb.close();
    }

    public void consulta_xogadores() {
        odb = ODBFactory.open(ODB_NAME);
        IQuery q = new CriteriaQuery(Player.class);
        Objects<Player> objs = odb.getObjects(q);
        int i = 0;
        while (objs.hasNext()) {
            i++;
            Player p = objs.next();
            System.out.println("Player " + i);
            System.out.println("Nombre: " + p.getName());
            System.out.println("Deporte: " + p.getFavoriteSport());
            System.out.println("Salario: " + p.getSalario());
        }

        odb.close();

    }

    public void actualiza_por_nome_xogador(String nome_antigo, String nome_novo) {
        odb = ODBFactory.open(ODB_NAME);
        IQuery q = new CriteriaQuery(Player.class, Where.equal("name", nome_antigo));
        Objects<Player> objs = odb.getObjects(q);
        while (objs.hasNext()) {
            Player p = objs.next();
            p.setName(nome_novo);
            odb.store(p);
        }
        odb.close();
    }

    public void xogadoresDeporte(String deporte) {
        odb = ODBFactory.open(ODB_NAME);
        IQuery q = new CriteriaQuery(Player.class, Where.equal("favoriteSport.name", deporte));
        Objects<Player> objs = odb.getObjects(q);
        System.out.println("Deporte: " + deporte);
        while (objs.hasNext()) {
            Player p = objs.next();
            System.out.println("Jugador: " + p.getName());
        }
        odb.close();
    }

    public void devoltar_equipos_con_xogadores_menos_dunha_cantidade(int cantidade) {
        odb = ODBFactory.open(ODB_NAME);
       
        
        odb.close();

    }

    public void prueba() {
        odb = ODBFactory.open(ODB_NAME);
        IQuery q = new CriteriaQuery(Team.class);
        Objects<Team> objs = odb.getObjects(q);
        while (objs.hasNext()) {
            Team t = objs.next();
            System.out.println(t.getName());
            for (int i = 0; i < t.getPlayers().size(); i++) {
                System.out.println((Player) (t.getPlayers().get(i)));
            }

        }
        odb.close();
    }
}
