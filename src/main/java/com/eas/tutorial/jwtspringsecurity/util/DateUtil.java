package com.eas.tutorial.jwtspringsecurity.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component // para autoinyectarla en cualquier parte de la app
public class DateUtil {

    // para inyectarle a TIMEZONE el valor de la propiedad en el yml
    @Value("${eas.jwt.timezone}")
    private String TIMEZONE;

    private SimpleDateFormat simpleDateFormat() {

        // creo un objeto que representa fecha y hora en formato ISO 8601
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE)); // establezco zona horaria
        return sdf;
    }

    public String getDateString() {
        // retorna la fecha actual del sistema en string

        Date now = new Date(); // creo objeto Date
        return simpleDateFormat().format(now); // lo formateo
    }

    public Long getDateMillis() {
        // retorna la fecha actual en milisegundos

        // obtengo la fecha actual y la formateo
        Date now = new Date();
        String strDate = simpleDateFormat().format(now);

        Date strNow = new Date();

        // parseo a objeto Date
        try {
            strNow = simpleDateFormat().parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return strNow.getTime();
    }
}

