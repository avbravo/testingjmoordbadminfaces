/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.testing.controller;

import com.avbravo.jmoordb.configuration.JmoordbConfiguration;
import com.avbravo.jmoordb.configuration.JmoordbConnection;
import com.avbravo.jmoordb.configuration.JmoordbContext;
import com.avbravo.jmoordb.mongodb.history.repository.ConfiguracionRepository;
import com.avbravo.jmoordb.mongodb.history.repository.RevisionHistoryRepository;
import com.avbravo.jmoordb.mongodb.history.services.AutoincrementableServices;
import com.avbravo.jmoordb.services.RevisionHistoryServices;
import com.avbravo.jmoordbutils.JsfUtil;
import com.avbravo.transporteejb.entity.Rol;
import com.avbravo.transporteejb.entity.Usuario;
import com.avbravo.transporteejb.repository.RolRepository;
import com.avbravo.transporteejb.repository.UsuarioRepository;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author avbravo
 */
@SessionScoped
@Named
public class LoginController implements Serializable{
        private static final long serialVersionUID = 1L;
     //Atributos para la interface IController
    @Inject
    RevisionHistoryRepository revisionHistoryRepository;
    @Inject
    RevisionHistoryServices revisionHistoryServices;
    @Inject
    AutoincrementableServices autoincrementableServices;
    @Inject
    ConfiguracionRepository configuracionRepository;
    

  
    private HashMap<String, String> parameters = new HashMap<>();

    private String passwordold;
    private String passwordnew;
    private String passwordnewrepeat;
    Boolean loggedIn = false;
    private String username;
    private String password;
    private String foto;
    private String id;
    private String key;
    String usernameSelected;
    Boolean recoverSession = false;
    Boolean userwasLoged = false;
    Boolean tokenwassend = false;
    String usernameRecover = "";
    String myemail = "@gmail.com";
    String mytoken = "";
    
      @Inject
    UsuarioRepository usuarioRepository;
    Usuario usuario = new Usuario();
    @Inject
    RolRepository rolRepository;
    Rol rol = new Rol();

    public String doLogin(){
        try {
             JmoordbConnection jmoordbConnection = new JmoordbConnection.Builder()
               .withSecurity(false)
               .withDatabase("transporte")
                .withHost("")
               .withPort(0)
               .withUsername("")
               .withPassword("")
                .build();
            //Agregar al context
            JmoordbConfiguration jmc = new JmoordbConfiguration.Builder()
                    .withSpanish(true)
                    .withRepositoryRevisionHistory(revisionHistoryRepository)
                    .withRevisionHistoryServices(revisionHistoryServices)
                    .withRevisionSave(true)
                    .withUsername(username)
                    .build();

            JmoordbContext.put("jmoordb_user", usuario);
            JmoordbContext.put("jmoordb_rol", rol);
          
        Faces.getFlash().setKeepMessages(true);
        Messages.addGlobalInfo("Logged in successfully!");
        return "/index.xhtml?faces-redirect=true";
 
        } catch (Exception e) {
            JsfUtil.errorDialog("doLogin()", e.getLocalizedMessage());
        }
        return "";
    }
}
