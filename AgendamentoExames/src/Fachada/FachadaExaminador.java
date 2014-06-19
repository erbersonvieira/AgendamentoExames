/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fachada;
import DAO.*;
import Beans.*;
import Conexao.*;
import java.util.*;
import java.sql.SQLException;

/**
 *
 * @author Adm
 */
public class FachadaExaminador {
  private Conectar conn = null;
  public FachadaExaminador(){
        this.conn = Conectar.getInstance();
    }

    /**
     * @return the conn
     */
    public Conectar getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Conectar conn) {
        this.conn = conn;
    }

    // Início dos Métodos da Fachada.
        

    // Examinador
        public ArrayList<Examinador> listagemGeralExaminador() {
         ArrayList ob = new ArrayList();
       try {
           DAOExaminador meuDAO = new DAOExaminador(this.getConn());
           ob = meuDAO.list();
        } catch (Exception exception) {
            System.out.println("Listagem examinadores não localizada.");
      }
       return ob;
    }

    public int inserirExaminador(Examinador obj) {
       int retorno = 0;
       try {
        DAOExaminador meuDAO = new DAOExaminador(this.getConn());
        retorno = meuDAO.insert(obj);

      } catch (Exception exception){
         System.out.println("Inclusão Examinador não realizada.");
      }
       return  retorno;
    }   
   
    
}
