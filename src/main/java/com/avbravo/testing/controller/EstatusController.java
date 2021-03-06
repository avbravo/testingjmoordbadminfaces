/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.testing.controller;

// <editor-fold defaultstate="collapsed" desc="imports">
import com.avbravo.jmoordb.configuration.JmoordbContext;
import com.avbravo.jmoordb.configuration.JmoordbControllerEnvironment;
import com.avbravo.jmoordb.interfaces.IController;
import com.avbravo.jmoordbutils.printer.Printer;
import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.mongodb.history.services.AutoincrementableServices;

 
import com.avbravo.jmoordbutils.JmoordbResourcesFiles;
import com.avbravo.transporteejb.datamodel.EstatusDataModel;
import com.avbravo.transporteejb.entity.Estatus;
import com.avbravo.transporteejb.entity.Usuario;
import com.avbravo.transporteejb.repository.EstatusRepository;
import com.avbravo.transporteejb.services.EstatusServices;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.primefaces.event.SelectEvent;
// </editor-fold>

/**
 *
 * @authoravbravo
 */
@Named
@ViewScoped
@Getter
@Setter
public class EstatusController implements Serializable, IController {

// <editor-fold defaultstate="collapsed" desc="fields">  
    private static final long serialVersionUID = 1L;

    private Boolean writable = false;
    //DataModel
    private EstatusDataModel estatusDataModel;

    Integer page = 1;
    Integer rowPage = 25;
    List<Integer> pages = new ArrayList<>();

    //Entity
    Estatus estatus = new Estatus();
    Estatus estatusSelected;
    Estatus estatusSearch = new Estatus();

    //List
    List<Estatus> estatusList = new ArrayList<>();
    List<Estatus> estatusListFiltered = new ArrayList<>();
    List<Estatus> estatusListSelection = new ArrayList<>();

    //Repository
    @Inject
    EstatusRepository estatusRepository;
    //Services
    @Inject
    AutoincrementableServices autoincrementableServices;
    @Inject
    ErrorInfoServices errorServices;
    @Inject
    EstatusServices estatusServices;
    @Inject
    JmoordbResourcesFiles rf;
    @Inject
    Printer printer;

    //List of Relations
    //Repository of Relations
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="getter/setter">
    public List<Integer> getPages() {

        return estatusRepository.listOfPage(rowPage);
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="constructor">
    public EstatusController() {
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        try {

            /*
            configurar el ambiente del controller
             */
            HashMap parameters = new HashMap();
            Usuario jmoordb_user = (Usuario) JmoordbContext.get("jmoordb_user");
            //    parameters.put("P_EMPRESA", jmoordb_user.getEmpresa().getDescripcion());

            JmoordbControllerEnvironment jmc = new JmoordbControllerEnvironment.Builder()
                    .withController(this)
                    .withRepository(estatusRepository)
                    .withEntity(estatus)
                    .withService(estatusServices)
                    .withNameFieldOfPage("page")
                    .withNameFieldOfRowPage("rowPage")
                    .withTypeKey("primary")
                    .withSearchLowerCase(false)
                    .withPathReportDetail("/resources/reportes/estatus/details.jasper")
                    .withPathReportAll("/resources/reportes/estatus/all.jasper")
                    .withparameters(parameters)
                     .withResetInSave(true)
                    .build();

            start();
            List<Estatus> list = estatusRepository.findAll();
            for(Estatus e:list){
                System.out.println("....> "+e.getIdestatus());
            }

        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage());
        }
    }// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="handleSelect">
    public void handleSelect(SelectEvent event) {
        try {
        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage());
        }
    }// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="move(Integer page)">
    @Override
    public void move(Integer page) {
        try {
            this.page = page;
            estatusDataModel = new EstatusDataModel(estatusList);
            Document doc;

            switch ((String) JmoordbContext.get("searchestatus")) {
                case "_init":
                case "_autocomplete":
                    estatusList = estatusRepository.findPagination(page, rowPage);
                    break;

                case "idestatus":
                    if (JmoordbContext.get("_fieldsearchestatus") != null) {
                        estatusSearch.setIdestatus(JmoordbContext.get("_fieldsearchestatus").toString());
                        doc = new Document("idestatus", estatusSearch.getIdestatus());
                        estatusList = estatusRepository.findPagination(doc, page, rowPage, new Document("idestatus", -1));
                    } else {
                        estatusList = estatusRepository.findPagination(page, rowPage);
                    }

                    break;
                case "activo":
                    if (JmoordbContext.get("_fieldsearchestatus") != null) {
                        estatusSearch.setActivo(JmoordbContext.get("_fieldsearchestatus").toString());
                        doc = new Document("activo", estatusSearch.getActivo());
                        estatusList = estatusRepository.findPagination(doc, page, rowPage, new Document("idestatus", -1));
                    } else {
                        estatusList = estatusRepository.findPagination(page, rowPage);
                    }
                    break;

                default:
                    estatusList = estatusRepository.findPagination(page, rowPage);
                    break;
            }

            estatusDataModel = new EstatusDataModel(estatusList);

        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage());

        }

    }// </editor-fold>
}