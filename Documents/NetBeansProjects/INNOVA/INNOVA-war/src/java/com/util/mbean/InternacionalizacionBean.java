/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util.mbean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;



@ManagedBean(name = "internacionalizacion")
@SessionScoped
public class InternacionalizacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigoIdioma;
    private static Map<String, Object> idiomas;

    private Locale locale = new Locale("es");

    static {
        idiomas = new LinkedHashMap<String, Object>();
        idiomas.put("Español", new Locale("es"));
        // label,value
        idiomas.put("Portugues", new Locale("pt"));
        idiomas.put("Inglés", new Locale("en"));
    }

    public void cambiarIdioma(ValueChangeEvent e) {
        codigoIdioma = e.getNewValue().toString();
        for (Map.Entry<String, Object> entry : idiomas.entrySet()) {
            if (entry.getValue().toString().equals(codigoIdioma)) {
                locale = (Locale) entry.getValue();
                FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
            }
        }
    }

    public Map<String, Object> getIdiomas() {
        return idiomas;
    }

    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    public void setCodigoIdioma(String localeCode) {
        this.codigoIdioma = localeCode;
    }

    public Locale getLocale() {
        System.out.println(locale);
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
