/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.Candidato;
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
public class DAOCandidato {

    private Conectar co = Conectar.getInstance();
    private String sql = "",  sql1 = "";
    private PreparedStatement stmt = null;

    public DAOCandidato(Conectar conn) {
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
      String sql = "SELECT idCandidato, nomeCandidato, cpfCandidato, sexo from Candidato order by nomeCandidato";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        Candidato c = new Candidato();
        //populate(material, rs);
        c.setIdCandidato(rs.getInt(1));
        c.setNomeCandidato(rs.getString(2));
        c.setCpfCandidato(rs.getString(3));
        c.setSexoCandidato(rs.getString(4));
        list.add(c);
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

    public int insert(Candidato c) throws SQLException {
    String sql;
    int a = 0;
    sql = "INSERT INTO Candidato (idCandidato, nomeCandidato, cpfCandidato, sexo) VALUES (?, ?, ?, ?)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = co.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, c.getIdCandidato());
      pstmt.setString(2, c.getNomeCandidato());
      pstmt.setString(3, c.getCpfCandidato());
      pstmt.setString(4, c.getSexoCandidato());
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
