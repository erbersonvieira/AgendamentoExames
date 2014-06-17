/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Adm
 */
public class Conectar {
    
     private static boolean DEBUG = false;
  private Connection connection;

  private final String driver = "org.gjt.mm.mysql.Driver";

 // Parametros de Produção - Tauceti.

  private static Conectar rep = new Conectar();


  private Conectar() { }

  public static  synchronized Conectar getInstance(){
      if (rep == null){
          rep = new Conectar();
      }
      return rep;
  }

  public static Connection getConnection() throws SQLException {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbexames"+"?user=root&password=root");
    }
    catch (SQLException sex) {
       throw new SQLException(sex.getMessage());
     }
    return connection;
  }
  public static void devolveconexao(Connection connection) throws SQLException {
   try {
     connection.close();
   }
   catch (Exception ex) {
        throw new SQLException(ex.getMessage());
   }

  }
    
}
