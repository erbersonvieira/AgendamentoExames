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
public class Fachada {
  private Conectar conn = null;
  public Fachada(){
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
    
    // Exame
        public ArrayList<Exame> listagemGeralExame() {
         ArrayList ob = new ArrayList();
       try {
           DAOExame meuDAO = new DAOExame(this.getConn());
           ob = meuDAO.list();
        } catch (Exception exception) {
            System.out.println("Listagem exames não localizada.");
      }
       return ob;
    }

    public int inserirExame(Exame obj) {
       int retorno = 0;
       try {
        DAOExame meuDAO = new DAOExame(this.getConn());
        retorno = meuDAO.insert(obj);

      } catch (Exception exception){
         System.out.println("Inclusão Exame não realizada.");
      }
       return  retorno;
    }   

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
