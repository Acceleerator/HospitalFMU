/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Kino
 */
public class dadosDAO {
    private static final dadosDAO instance;
    
    static {
        instance = new dadosDAO();
    }
    
    public dadosDAO() {}
    
    public static dadosDAO getInstance() {
        return instance;
    }
    
    public usuario checkLogin(usuario login){
        PreparedStatement pstms = null;
        int updateQuery = 0;
        
        usuario dadosLogin = new usuario();
        Connection conn = conexao.getConnection();
        boolean retorno = true;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE login = '" + login.getLogin() + "' AND senha = '" + login.getSenha() + "'");
            if (rs.next()) {
                dadosLogin = loadDadosLogin(login, rs);
            } else {
                login = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return login;
    }
    
    public List<usuario> readUsuarios() throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<usuario> usuarios = new ArrayList<>();
        Connection conn = conexao.getConnection();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM usuarios");
            rs = stmt.executeQuery();
            while (rs.next()){
                usuario dadosUser = new usuario();
                loadDadosLogin(dadosUser, rs);
                usuarios.add(dadosUser);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            conn.close();
        }
        return usuarios;
    }
    
    public usuario searchLoginByID(usuario login) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        usuario dadosLogin = new usuario();
        Connection conn = conexao.getConnection();
        try {
            stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id_user = '" + login.getId_user()+ "'");
            rs = stmt.executeQuery();
            if (rs.next()) {
                dadosLogin = loadDadosLogin(login, rs);
            } else {
                login = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return login;
    }
    
    public usuario readLogin(usuario login){
        PreparedStatement pstms = null;

        usuario dadosLogin = new usuario();
        Connection conn = conexao.getConnection();
        boolean retorno = true;
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE login = '" + login.getLogin()+ "'");
            if (rs.next()) {
                dadosLogin = loadDadosLogin(login, rs);
            } else {
                login = null;
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return login;
    }
    
    private usuario loadDadosLogin(usuario dadosLogin, ResultSet rs) throws SQLException {
        dadosLogin.setId_user(rs.getInt("id_user"));
        dadosLogin.setCpf(rs.getString("cpf"));
        dadosLogin.setNome(rs.getString("nome"));
        dadosLogin.setLogin(rs.getString("login"));
        dadosLogin.setSenha(rs.getString("senha"));
        return dadosLogin;
    }
    
    public boolean deleteLogin(usuario login){
        boolean retorno = true;
        Connection conn = conexao.getConnection();
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM usuarios WHERE cpf = '" + login.getCpf() + "'");
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public boolean createLogin(usuario login){
        Connection conn = conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement("INSERT INTO usuarios (id_user, nome, cpf, login, senha) VALUES (? ,?, ?, ?, ?)");
            pstmt.setInt(1, login.getId_user());
            pstmt.setString(2, login.getNome());
            pstmt.setString(3, login.getCpf());
            pstmt.setString(4, login.getLogin());
            pstmt.setString(5, login.getSenha());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public boolean updateLogin(usuario login) {
        boolean retorno = true;
        Connection conn = conexao.getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement("UPDATE usuarios SET id_user = ?, nome = ?, cpf = ?, login = ?, senha = ? WHERE login = '" + login.getLogin()+ "'");
            pstmt.setInt(1, login.getId_user());
            pstmt.setString(2, login.getNome());
            pstmt.setString(3, login.getCpf());
            pstmt.setString(4, login.getLogin());
            pstmt.setString(5, login.getSenha());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public funcionario searchFuncionarioByID(funcionario func) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Connection conn = conexao.getConnection();
        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionarios WHERE id_func = "  + func.getId_func());
            rs = stmt.executeQuery();
            if (rs.next()){
                func = loadFuncionario(func, rs);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ID não encontrado: " + ex);
        } finally {
            conn.close();
        }
        return func;
    }
    
    public List<funcionario> readFuncionarios() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<funcionario> funcionarios = new ArrayList<>();
        Connection conn = conexao.getConnection();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionarios");
            rs = stmt.executeQuery();
            while (rs.next()){
                funcionario dadosFunc = new funcionario();
                loadFuncionario(dadosFunc, rs);
                funcionarios.add(dadosFunc);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            conn.close();
        }
        return funcionarios;
    }

    private funcionario loadFuncionario(funcionario dadosFunc, ResultSet rs) throws SQLException {
        dadosFunc.setId_func(rs.getInt("id_func"));
        dadosFunc.setNome(rs.getString("nome"));
        dadosFunc.setCpf(rs.getString("cpf"));
        dadosFunc.setDt_nascimento(rs.getString("dt_nasc"));
        dadosFunc.setSexo(rs.getString("sexo"));
        dadosFunc.setTelfix(rs.getString("telfix"));
        dadosFunc.setRamal(rs.getString("ramal"));
        dadosFunc.setTelcel(rs.getString("telcel"));
        dadosFunc.setCep(rs.getString("cep"));
        dadosFunc.setCargo(rs.getString("cargo"));
        dadosFunc.setTurno(rs.getString("turno"));
        dadosFunc.setSalario(rs.getString("salario"));
        return dadosFunc;
    }
    
    public boolean deleteFunc(funcionario func){ // TO CHECK
        boolean retorno = true;
        Connection conn = conexao.getConnection();
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM pacientes WHERE cpf = '" + func.getCpf() + "'");
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public boolean createFuncionario(funcionario func) throws SQLException{
        Connection conn = conexao.getConnection();
        PreparedStatement pstmt = null;
        boolean sucesso = false;
        try {
            pstmt = conn.prepareStatement("INSERT INTO funcionarios (nome, cpf, dt_nasc, sexo, telfix, ramal, telcel, cep, cargo, turno, salario) VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?)");
            pstmt.setString(1, func.getNome());
            pstmt.setString(2, func.getCpf());
            pstmt.setString(3, func.getDt_nascimento());
            pstmt.setString(4, func.getSexo());
            pstmt.setString(5, func.getTelfix());
            pstmt.setString(6, func.getRamal());
            pstmt.setString(7, func.getTelcel());
            pstmt.setString(8, func.getCep());
            pstmt.setString(9, func.getCargo());
            pstmt.setString(10, func.getTurno());
            pstmt.setString(11, func.getSalario());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
            sucesso = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+ex);
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return sucesso;
    }
    
    public boolean updateDadoFuncionario (String dadoNovo, int nomeCol, int id) throws SQLException {
        boolean sucesso = false;
        boolean dadoSelecionado = true;
        Connection conn = conexao.getConnection();
        PreparedStatement stmt = null;
        String dadoParaAtt = null;
        
        System.out.println(nomeCol);
        switch (nomeCol) {
            case 1:
                dadoParaAtt = "nome";
                break;
            case 2:
                dadoParaAtt = "cpf";
                break;
            case 3:
                dadoParaAtt = "dt_nasc";
                break;
            case 4:
                dadoParaAtt = "sexo";
                break;
            case 5:
                dadoParaAtt = "telfix";
                break;
            case 6:
                dadoParaAtt = "ramal";
                break;
            case 7:
                dadoParaAtt = "telcel";
                break;
            case 8:
                dadoParaAtt = "cep";
                break;
            case 9:
                dadoParaAtt = "cargo";
                break;
            case 10:
                dadoParaAtt = "turno";
                break;
            case 11:
                dadoParaAtt = "salario";
                break;
            default:
                dadoSelecionado = false;
                break;
        }
        if (dadoSelecionado){
            try {
                stmt = conn.prepareStatement("UPDATE funcionarios SET " + dadoParaAtt + " = ? WHERE id_func = " + String.valueOf(id));
                stmt.setString(1, dadoNovo);
                stmt.executeUpdate();
                sucesso = true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar o dado: " + ex, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } finally {
                conn.close();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Dado para atualizar não selecionado.\nPor favor, selecione um dado para ser atualizado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        return sucesso;
    }
    
    public paciente searchPacienteByID(paciente pac) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Connection conn = conexao.getConnection();
        try {
            stmt = conn.prepareStatement("SELECT * FROM pacientes WHERE id_pac = " + pac.getId_pac());
            rs = stmt.executeQuery();
            if (rs.next()){
                pac = loadPaciente(pac, rs);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ID não encontrado: " + ex);
        } finally {
            conn.close();
        }
        return pac;
    }
    
    public List<paciente> readPacientes() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
                
        List<paciente> pacientes = new ArrayList<>();
        Connection conn = conexao.getConnection();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM pacientes");
            rs = stmt.executeQuery();
            while (rs.next()){
                paciente dadosPac = new paciente();
                loadPaciente(dadosPac, rs);
                pacientes.add(dadosPac);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            conn.close();
        }
        return pacientes;
    }

    private paciente loadPaciente(paciente dadosPac, ResultSet rs) throws SQLException {
        dadosPac.setId_pac(rs.getInt("id_pac"));
        dadosPac.setNome(rs.getString("nome"));
        dadosPac.setCpf(rs.getString("cpf"));
        dadosPac.setDt_nascimento(rs.getString("dt_nasc"));
        dadosPac.setSexo(rs.getString("sexo"));
        dadosPac.setTelfix(rs.getString("telfix"));
        dadosPac.setTelcel(rs.getString("telcel"));
        dadosPac.setCep(rs.getString("cep"));
        dadosPac.setPlano_saude(rs.getString("plano_saude"));
        return dadosPac;
    }
    
    public boolean deletePac(paciente pac){ // TO CHECK
        boolean retorno = true;
        Connection conn = conexao.getConnection();
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM pacientes WHERE cpf = '" + pac.getCpf() + "'");
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public boolean createPaciente(paciente pac){
        Connection conn = conexao.getConnection();
        PreparedStatement pstmt;
        boolean retorno = true;
        try {
            pstmt = conn.prepareStatement("INSERT INTO pacientes (id_pac, nome, cpf, dt_nasc, sexo, telfix, telcel, cep, plano_saude) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)");
            pstmt.setInt(1, pac.getId_pac());
            pstmt.setString(2, pac.getNome());
            pstmt.setString(3, pac.getCpf());
            pstmt.setString(4, pac.getDt_nascimento());
            pstmt.setString(5, pac.getSexo());
            pstmt.setString(6, pac.getTelfix());
            pstmt.setString(7, pac.getTelcel());
            pstmt.setString(8, pac.getCep());
            pstmt.setString(9, pac.getPlano_saude());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            retorno = false;
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public boolean updateDadoPaciente (String dadoNovo, int nomeCol, int id) throws SQLException {
        boolean sucesso = false;
        boolean dadoSelecionado = true;
        Connection conn = conexao.getConnection();
        PreparedStatement stmt = null;
        String dadoParaAtt = null;
        switch (nomeCol) {
            case 1:
                dadoParaAtt = "nome";
                break;
            case 2:
                dadoParaAtt = "cpf";
                break;
            case 3:
                dadoParaAtt = "dt_nasc";
                break;
            case 4:
                dadoParaAtt = "sexo";
                break;
            case 5:
                dadoParaAtt = "telfix";
                break;
            case 6:
                dadoParaAtt = "telcel";
                break;
            case 7:
                dadoParaAtt = "cep";
                break;
            case 8:
                dadoParaAtt = "plano_saude";
                break;
            default:
                dadoSelecionado = false;
                break;
        }
        if (dadoSelecionado){
            try {
                stmt = conn.prepareStatement("UPDATE pacientes SET " + dadoParaAtt + " = ? WHERE id_pac = " + String.valueOf(id));
                stmt.setString(1, dadoNovo);
                stmt.executeUpdate();
                sucesso = true;
                JOptionPane.showMessageDialog(null, "Dado atualizado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar o dado: " + ex, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } finally {
                conn.close();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Dado para atualizar não selecionado.\nPor favor, selecione um dado para ser atualizado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        return sucesso;
    }
}
