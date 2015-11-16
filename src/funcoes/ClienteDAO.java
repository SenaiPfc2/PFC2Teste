package funcoes;


import atributos.Cliente;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteDAO {
    
    public static void CadCliente(Cliente cli){
        
        PreparedStatement stmt;
        try {   
            String sql = ("INSERT INTO tabcliente(usuario_id_usuario, tabContato_id_contato,empresa,cnpj,setor,contato)  VALUES(?,?,?,?,?,?)");
            stmt = Conexao.getConnection().prepareStatement(sql);      
  
                stmt.setInt(1, 3);
                stmt.setInt(2, cli.getIdContato());
                stmt.setString(3, cli.getEmpresa());
                stmt.setString(4, cli.getCnpj());
                stmt.setString(5, cli.getSetor());
                stmt.setString(6, cli.getContato());
                              
                stmt.executeUpdate();
                stmt.close();  

            } catch (SQLException ex) {      
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException("Erro ao Cadastrar Cliente: ",ex);       
            }
    }
    
    public static ArrayList CarregaCliente(int id) {
        
        Statement stmt;
        ArrayList<Cliente> cliente = new ArrayList<Cliente>();
        
        try {            
            String Sql = "SELECT * FROM tabcliente cli\n" +
                        "INNER JOIN tabtel tel\n" +
                        "INNER JOIN tabemail email\n" +
                        "INNER JOIN tabcontato cont\n" +
                        "ON cont.id_contato = cli.tabContato_id_contato AND\n" +
                        "cont.id_contato = tel.contato_id AND\n" +
                        "id_contato = email.contato_id_contato where cli.idcliente = '"+ id +"';";
            
            ResultSet rs;            
            stmt = Conexao.getConnection().createStatement();            
            rs = stmt.executeQuery(Sql); 
            
            while(rs.next()){
                Cliente c = new Cliente();
                
                c.setId(rs.getInt("idcliente"));
                c.setEmpresa((rs.getString("empresa")));
                c.setCnpj(rs.getString("cnpj"));
                c.setContato(rs.getString("contato"));
                c.setSetor(rs.getString("setor"));  
                c.setTel(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setIdContato(rs.getInt("tabContato_id_contato"));
                c.setId(id); 
                cliente.add(c);                
            }            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {      
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir os dados do Cliente: ", ex);    
        }    
        return cliente;
    }
    
    public static int idContato(int id) {
        
        Statement stmt;
        Cliente c = new Cliente();
        
        try {            
            String Sql = "SELECT  tabContato_id_contato FROM tabcliente WHERE idcliente = '"+ id +"';";
            
            ResultSet rs;            
            stmt = Conexao.getConnection().createStatement();            
            rs = stmt.executeQuery(Sql); 
            
            while(rs.next()){                                
                c.setIdContato(rs.getInt("tabContato_id_contato"));                             
            }            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {      
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir os dados do Cliente: ", ex);    
        }    
        return c.getIdContato();
    }
    
    public static void ExcluirCliente(int id){
        
        CallableStatement stmt;
        try {   
            stmt = Conexao.getConnection().prepareCall("{call ExcluirCliente(?)}");
            
            stmt.setInt(1, id);
            
            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {      
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao excluir os dados do Cliente: ",ex);    
        }
    }
    
    public static void UpdateCliente(Cliente cli, int id){
        
        PreparedStatement stmt;
        
        try {   
            String sql = ("UPDATE tabcliente SET empresa='" + cli.getEmpresa() 
                        + "' , cnpj='"+ cli.getCnpj() +"', setor='"+cli.getSetor()
                        +"', contato='"+cli.getContato()+"' where idCliente = '" + id + "';");
            
            stmt = Conexao.getConnection().prepareStatement(sql);                             
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {      
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Alterar os dados do Cliente: ",ex);    
        }
    }
    
    public static ArrayList<Cliente> ListarCliente(){
        
        Statement stmt;
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        
        try {            
            String Sql = "SELECT * FROM tabcliente cli\n" +
                        "INNER JOIN tabtel tel\n" +
                        "INNER JOIN tabemail email\n" +
                        "INNER JOIN tabcontato cont\n" +
                        "ON cont.id_contato = cli.tabContato_id_contato AND\n" +
                        "cont.id_contato = tel.contato_id AND\n" +
                        "id_contato = email.contato_id_contato;";

            ResultSet rs;
            
            stmt = Conexao.getConnection().createStatement();
            
            rs = stmt.executeQuery(Sql); 
            
            while(rs.next()){
                Cliente c = new Cliente();
                
                c.setId(rs.getInt("idcliente"));
                c.setEmpresa((rs.getString("empresa")));
                c.setCnpj(rs.getString("cnpj"));
                c.setContato(rs.getString("contato"));
                c.setSetor(rs.getString("setor"));                 
                c.setTel(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));               
                clientes.add(c);                
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao Listar os dados do Clientes: ",ex);
        }
        return clientes;
    }    
}
