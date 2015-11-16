package funcoes;

import atributos.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContatosDAO {

    public static int CadContato() {
        int id = 0;
        PreparedStatement stmt;

        try {
            String sql = ("INSERT INTO tabcontato() VALUES();");
            stmt = Conexao.getConnection().prepareStatement(sql);

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return id;
    }

    public static void CadTel(int id, String tel) {
        PreparedStatement stmt;

        try {
            String sql = ("INSERT INTO tabtel(contato_id, telefone) VALUES(?,?)");
            stmt = Conexao.getConnection().prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.setString(2, tel);
            System.out.println(tel);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public static void CadEmail(int id, String email) {
        PreparedStatement stmt;
        try {
            String sql = ("INSERT INTO tabemail(contato_id_contato,email) VALUES(?,?)");
            stmt = Conexao.getConnection().prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.setString(2, email);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public static void CadEndereco(Endereco endereco) {
        
        PreparedStatement stmt;
        try {
            
            String sql = ("INSERT INTO TABENDERECO(cep,rua,numero,bairro,cidade,estado,pais,cod_contato) "
                        + "VALUES (?,?,?,?,?,?,?,?);");

            stmt = Conexao.getConnection().prepareStatement(sql);
            
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getRua());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.setString(7, endereco.getPais());
            stmt.setInt(8, endereco.getIdContato());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public static void ExcluirContato(int id) {

        PreparedStatement stmt;
        try {
            String sql = ("DELETE FROM  tabcontato WHERE idcontato = ?");
            stmt = Conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir o Contato do Cliente: ", ex);
        }
    }

    public static void ExcluirTel(int id) {

        PreparedStatement stmt;
        try {
            String sql = ("DELETE FROM  tabTel WHERE contato_id = ?");
            stmt = Conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir o Telefone do Cliente: ", ex);
        }
    }

    public static void ExcluirEmail(int id) {

        PreparedStatement stmt;
        try {
            String sql = ("DELETE FROM  tabEmail WHERE contato_id_contato = ?");
            stmt = Conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir o Email do Cliente: ", ex);
        }
    }
    
    public static void ExcluirEndereco(int id) {

        PreparedStatement stmt;
        try {
            String sql = ("DELETE FROM  tabEndereco WHERE Contato_id_contato = ?");
            stmt = Conexao.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir o Endereço do Cliente: ", ex);
        }
    }

    public static void UpdateTel(int id, String tel) {

        PreparedStatement stmt;

        try {
            String sql = ("UPDATE tabTel SET telefone='" + tel
                    + "' where contato_id = '" + id + "';"); //precisa alterar: caso mais de um telefone cadastrado desse mesmo contato, dessa forma vai alterar todos os tels. criar metodo para verificar qual tel é pra alterar. teremos um and na condicao para o update

            stmt = Conexao.getConnection().prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Alterar o Tel do cliente: ", ex);
        }
    }

    public static void UpdateEmail(int id, String email) {

        PreparedStatement stmt;

        try {
            String sql = ("UPDATE tabEmail SET email='" + email
                    + "' where contato_id_contato = '" + id + "';");//mesma coisa aqui

            stmt = Conexao.getConnection().prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Alterar o Email do cliente: ", ex);
        }
    }
    
    public static void UpdateEndereco(int id, Endereco endereco) {

        PreparedStatement stmt;

        try {
            String sql = ("UPDATE tabEndereco SET cep='" + endereco.getCep() 
                        + "' rua = '" +endereco.getRua()
                        + "' numero='" + endereco.getNumero() 
                        + "' bairro = '" +endereco.getBairro()
                        + "' cidade= '" + endereco.getCidade() 
                        +"' estado ='" + endereco.getEstado() 
                        + "' pais= '" + endereco.getPais()
                        + "' where Contato_id_contato = '" + id + "';");

            stmt = Conexao.getConnection().prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Alterar o Endereço do cliente: ", ex);
        }
    }

}
