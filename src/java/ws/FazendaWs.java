/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import dao.EventoDAO;
import dao.JogadaDAO;
import dao.JogoDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Usuario;
import modelo.Eventos;
import modelo.Jogada;
import modelo.Jogo;

/**
 * REST Web Service
 *
 * @author desenvolverdor
 */
@Path("Fazenda")
public class FazendaWs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FazendaWs
     */
    public FazendaWs() {
    }

    /**
     * Retrieves representation of an instance of ws.FazendaWs
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        //TODO return proper representation object
       // throw new UnsupportedOperationException();
       return "Meu primeiro Hello World na Web Service";
       
    }
    
    
    //Comando do Banco de Dados Jogo da Velha
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/Banco/CriandoJogo/{nome}")
    public String criarJogo(@PathParam("nome") String nome){
        Jogo j = new Jogo();
        j.setNomecriador(nome);
        JogoDAO jd = new JogoDAO();
        jd.inserir(j);
        return null;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/Banco/EntrandoEmJogo/{nome}/{id}")
    public String entrarJogo(@PathParam("nome") String nome,
                             @PathParam("id") int id_jogo){
        
        
     
        JogoDAO jd = new JogoDAO();
        Boolean update = jd.update(nome,id_jogo);
        return ""+update;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/Banco/InserindoJogada/{entrada}/{posicao}/{jogador}/{idjogo}")
    public String inserindoJogada(@PathParam("entrada") String entrada,
                                    @PathParam("posicao") int position,
                                    @PathParam("jogador")boolean jogador,
                                    @PathParam("idjogo")int id_jogo){
        JogadaDAO jd = new JogadaDAO();
        Jogada j = new Jogada();
        j.setEntrada(entrada);
        j.setId_jogo(id_jogo);
        j.setPosicao(position);
        j.setVez(jogador);
        Boolean b = jd.inserir(j);
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/test/apagar/{telefone}")
    public String getApagar(@PathParam("telefone") int telefone) {
       Usuario u = new Usuario();

       u.setFone(telefone);
       
       UsuarioDAO dao= new UsuarioDAO();
       dao.excluir(u);
       
        //Converter para Json
       
       Gson g = new Gson();
       return g.toJson(u);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/inserir/{nome}/{email}/{telefone}/{apelido}")
    public String getInserir(@PathParam("nome") String nome,
                            @PathParam("email") String email,
                            @PathParam("telefone") int telefone,
                            @PathParam("apelido") String apelido){
        //Gson g=new Gson()
        UsuarioDAO dao =new UsuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNome(nome);
        usuario.setFone(telefone);
        usuario.setApelido(apelido);
        boolean a = dao.inserir(usuario);
        String resultado;
        if(a==false)
            resultado = "Ta bem não Viado";
        else
            resultado = "Ai dentu";
        
        return resultado;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Evento/inserir/{nome}/{idusuario}/{data}/{descricao}/{tipo}/{destaque}")
    public String getInserirEvento(@PathParam("nome") String nome,
                            @PathParam("idusuario") int usuario,
                            @PathParam("data") int data,
                            @PathParam("descricao") String descricao,
                            @PathParam("tipo")String tipo,
                            @PathParam("desqtaque")boolean destaque){
        //Gson g=new Gson()
        EventoDAO dao =new EventoDAO();
        Eventos evento =new Eventos();
        evento.setUsuario(usuario);
        evento.setNome(nome);
        evento.setData(data);
        evento.setTipo(tipo);
        evento.setDestaque(destaque);
        boolean a = dao.inserir(evento);
        String resultado;
        if(a==false)
            resultado = "Ta bem não Viado";
        else
            resultado = "Ai dentu";
        
        return resultado;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/test2/{numero}")
    public String getCalculo(@PathParam("numero") String a) {
       System.out.println("Chegou aqui: "+a);
       int[] numero=new int[a.length()];
       String[] pala =a.split("");
       for(int i=0;i<a.length();i++){
           numero[i]= Integer.parseInt(pala[i]);
       }
       int aux=0;
       for(int i=0;i<numero.length;i++){
           for(int j=0;j<numero.length;j++){
               if(numero[i]<numero[j]){
                   aux=numero[i];   
                   numero[i]= numero[j];
                   numero[j]=aux;
               }
           }
       }
       Gson g = new Gson();
       return g.toJson(numero);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/list")
    public String listUsuario() {
        
       List<Usuario> usu = null;       
       UsuarioDAO dao = new UsuarioDAO();
       
       usu = dao.listar();
   
       //Converter para Json
       Gson g = new Gson();
       return g.toJson(usu);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Evento/list")
     public String listEvento() {
        
       List<Eventos> usu = null;       
       EventoDAO dao = new EventoDAO();
       
       usu = dao.listar();
   
       //Converter para Json
       Gson g = new Gson();
       return g.toJson(usu);
    }
     
       @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/JogoVelha/CriarJogo/{nome}")
    public String setCriarJogo(@PathParam("nome") String nome ){
        ComunicationDatabase cd = new ComunicationDatabase();
        return cd.chamadaGet("http://10.180.23.201:8080/JogodaVelhaWebServiceBanco/webresources/Fazenda/Usario/Banco/Criando/"+nome);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/JogoVelha/CriarJogo/{nome}")
    public boolean setEntrandoJogo(@PathParam("nome") String nome,
                                    @PathParam("id")String id){
        ComunicationDatabase cd = new ComunicationDatabase();
        String x = cd.chamadaGet("http://10.180.23.201:8080/JogodaVelhaWebServiceBanco/webresources/Fazenda/Usario/Banco/EntrandoEmJogo/"+nome+"/"+id);
        if(x.equals("true"))
            return true;
        else
            return false;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/JogoVelha/")
    public String setCriar() throws IOException{
        ComunicationDatabase cd = new ComunicationDatabase();
        return cd.chamadaGet("http://10.180.23.201:8080/JogodaVelhaWebServiceBanco/webresources/Fazenda/Usario/Banco/Lista/2");
    }
    /**
     * PUT method for updating or creating an instance of FazendaWs
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
