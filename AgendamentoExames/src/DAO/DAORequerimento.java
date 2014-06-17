/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Adm
 */

 
import Conexao.Conectar;
//import Controlador.*;
import Beans.Requerimento;

import java.sql.*;
import javax.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ricardoricrob
 */

public class DAORequerimento {

    private Conectar co = Conectar.getInstance();
    private String sql = "",  sql1 = "";
    private PreparedStatement stmt = null;

    public DAORequerimento(Conectar conn) {
        this.co = conn;
    }

    public ArrayList list() throws SQLException {
      
    ArrayList list = new ArrayList();
    list.clear();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = co.getConnection();
      String sql = "SELECT idRequerimento, nomeRequerimento from requerimento order by nomeRequerimento";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        Requerimento r = new Requerimento();
        //populate(material, rs);
        r.setIdRequerimento(rs.getInt(1));
        r.setNomeRequerimento(rs.getString(2));
        list.add(r);
      }
      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      rs.close();
      pstmt.close();
      conn.rollback();
      e.printStackTrace();
    } finally {
    	co.devolveconexao(conn);
    }
    return list;
  }

    public int insert(Requerimento r) throws SQLException {
    String sql;
    int a = 0;
    sql = "INSERT INTO requerimento (idRequerimento, nomeRequerimento) VALUES (?, ?)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = co.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, r.getIdRequerimento());
      pstmt.setString(2, r.getNomeRequerimento());
      a = pstmt.executeUpdate();
      pstmt.close();
//      conn.commit();
//      JOptionPane.showMessageDialog(null,"Registro Gravado com Sucesso");
      return a;
    } catch (SQLException sqle) {

      sqle.printStackTrace();
      throw sqle;
    } finally {
    	co.devolveconexao(conn);
    }
  }   
}
