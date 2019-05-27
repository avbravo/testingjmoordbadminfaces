package com.avbravo.analytics.start;



import com.avbravo.jmoordb.configuration.JmoordbConnection;
import com.avbravo.jmoordbutils.JsfUtil;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;

@Singleton
@Startup
public class Application implements Serializable {



    @PostConstruct
    public void init() {
        try {

            System.out.println("---->Arranco la aplicacion....");
        //Configuracion de la base de datos
//        JmoordbConnection jmc = new JmoordbConnection.Builder()
//                .withSecurity(false)
//                .withDatabase("transporte")
//                .withHost("")
//                .withPort(0)
//                .withUsername("")
//                .withPassword("")
//                .build();
        } catch (Exception e) {
            JsfUtil.errorDialog("Application", e.getLocalizedMessage());
        }
      
    }


   

}
