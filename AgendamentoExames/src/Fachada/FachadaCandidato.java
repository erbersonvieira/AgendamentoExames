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
public class FachadaCandidato {
  private Conectar conn = null;
  public FachadaCandidato(){
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
    // Candidato
        public ArrayList<Candidato> listagemGeralCandidato() {
         ArrayList ob = new ArrayList();
       try {
           DAOCandidato meuDAO = new DAOCandidato(this.getConn());
           ob = meuDAO.list();
        } catch (Exception exception) {
            System.out.println("Listagem Candidatos não localizada.");
      }
       return ob;
    }

    public int inserirCandidato(Candidato obj) {
       int retorno = 0;
       try {
        DAOCandidato meuDAO = new DAOCandidato(this.getConn());
        retorno = meuDAO.insert(obj);

      } catch (Exception exception){
         System.out.println("Inclusão Candidato não realizada.");
      }
       return  retorno;
    }   
    
}
