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
import modelo.Jogada;

/**
 *
 * @author Aluno
 */
public class JogadaDAO {

    public JogadaDAO(){}
    public boolean inserir(Jogada jogada)
    {
        String sql = "INSERT INTO jogada(entrada,posicao,jogador,id_jogo) VALUES(?,?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = ConexaoJogo.getPreparedStatement(sql);
        try {
            pst.setString(1, jogada.getEntrada());
            pst.setInt(2, jogada.getPosicao());
            pst.setBoolean(3, jogada.isVez());
            pst.setInt(4, jogada.getId_jogo());
         
            
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
