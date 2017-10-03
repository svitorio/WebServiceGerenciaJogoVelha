/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Jogo;

/**
 *
 * @author Aluno
 */
public class JogoDAO {
     
     public JogoDAO()
    {
    }
    
    public boolean inserir(Jogo jogo)
    {
        String sql = "INSERT INTO jogo(nome_criador) VALUES(?)";
        Boolean retorno = false;
        PreparedStatement pst = ConexaoJogo.getPreparedStatement(sql);
        try {
            pst.setString(1, jogo.getNomecriador());
         
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    }

    public Boolean update(String nome, int id_jogo) {
        String sql = "UPDATE jogo set nome_participante=? where id_jogo=?";
        Boolean retorno = false;
        PreparedStatement pst = ConexaoJogo.getPreparedStatement(sql);
        try {
          
            pst.setString(1, nome);
            pst.setInt(2, id_jogo);
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    }
}
