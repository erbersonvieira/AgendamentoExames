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
public class FachadaRequerimento {
  private Conectar conn = null;
  public FachadaRequerimento(){
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
    // Requerimento
    public ArrayList<Requerimento> listagemGeralRequerimento() {
         ArrayList ob = new ArrayList();
       try {
           DAORequerimento meuDAO = new DAORequerimento(this.getConn());
           ob = meuDAO.list();
        } catch (Exception exception) {
            System.out.println("Listagem não localizada.");
      }
       return ob;
    }

    public int inserirRequerimento(Requerimento obj) {
       int retorno = 0;
       try {
        DAORequerimento meuDAO = new DAORequerimento(this.getConn());
        retorno = meuDAO.insert(obj);

      } catch (Exception exception){
         System.out.println("Inclusão Requerimento não realizada.");
      }
       return  retorno;
    }   
     
    
}
