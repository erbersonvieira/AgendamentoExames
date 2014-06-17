/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.Examinador;
import Conexao.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Adm
 */
public class DAOExaminador {
    
    private Conectar co = Conectar.getInstance();
    private String sql = "",  sql1 = "";
    private PreparedStatement stmt = null;

    public DAOExaminador(Conectar conn) {
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
      String sql = "SELECT idExaminador, nomeExaminador, cpfExaminador from Examinador order by nomeExaminador";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        Examinador exam = new Examinador();
        //populate(material, rs);
        exam.setIdExaminador(rs.getInt(1));
        exam.setNomeExaminador(rs.getString(2));
        exam.setCpfExaminador(rs.getString(3));
        list.add(exam);
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

    public int insert(Examinador exam) throws SQLException {
    String sql;
    int a = 0;
    sql = "INSERT INTO Examinador (idExaminador, nomeExaminador, cpfExaminador) VALUES (?, ?, ?)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = co.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, exam.getIdExaminador());
      pstmt.setString(2, exam.getNomeExaminador());
      pstmt.setString(3, exam.getCpfExaminador());
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
