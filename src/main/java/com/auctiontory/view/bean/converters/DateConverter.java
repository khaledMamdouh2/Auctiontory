package com.auctiontory.view.bean.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@FacesConverter("dateConvert")
public class DateConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println(s);
        Date date = null;
        if(s.trim().matches("^([1-9]|([012][0-9])|(3[01]))/([0]{0,1}[1-9]|1[012])/\\d\\d\\d\\d [012]{0,1}[0-9]:[0-6][0-9]$")){
            System.out.println("mazboot");
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd/MM/yyyy H:m");
            try {
                date = formatter.parse(s);
                return date;
            } catch (ParseException ex) {
                Logger.getLogger(DateConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("la2 m4 mzboot");
            FacesMessage msg = new FacesMessage("invalid date format");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }
        return date;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Date date = (Date) o;
        String year = Integer.toString(date.getYear()-100+2000);
        String month = Integer.toString(date.getMonth());
        String day = Integer.toString(date.getDate());
        String hour = Integer.toString(date.getHours());
        String minutes = Integer.toString(date.getMinutes());
        if(minutes.length()==1){
            minutes = "0"+minutes;
        }
        String result = day+"/"+month+"/"+year+" "+hour+":"+minutes;
        return result;
    }
}
