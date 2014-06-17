/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.Exame;
import Conexao.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Adm
 */
public class DAOExame {
    private Conectar co = Conectar.getInstance();
    private String sql = "",  sql1 = "";
    private PreparedStatement stmt = null;

    public DAOExame(Conectar conn) {
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
      String sql = "SELECT idExame, nomeExame from Exame order by nomeExame";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        Exame ex = new Exame();
        //populate(material, rs);
        ex.setIdExame(rs.getInt(1));
        ex.setNomeExame(rs.getString(2));
        list.add(ex);
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

    public int insert(Exame ex) throws SQLException {
    String sql;
    int a = 0;
    sql = "INSERT INTO Exame (idExame, nomeExame) VALUES (?, ?)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = co.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, ex.getIdExame());
      pstmt.setString(2, ex.getNomeExame());
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
