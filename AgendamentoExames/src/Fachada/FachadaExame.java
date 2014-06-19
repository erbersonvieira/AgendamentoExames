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
public class FachadaExame {
  private Conectar conn = null;
  public FachadaExame(){
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

    
}
