package com.eas.tutorial.jwtspringsecurity.util;

import com.google.gson.Gson;

public class GsonUtil {

    public static String serialize(Object src) {
        // recibe un objeto y lo serializa en formato JSON

        Gson gson = new Gson();
        return gson.toJson(src);
    }

    public static <D> D toObject(String json, Class<D> dClass) {
        // recibe una cadena JSON y la convierte en un objeto de la clase recibida

        Gson gson = new Gson();
        return gson.fromJson(json, dClass);
    }

    public static <D> D toObject(Object src, Class<D> dClass) {
        // convierte un objeto a una cadena JSON, luego la deserializa en un objeto de la clase recibida

        Gson gson = new Gson();
        String srcJson = gson.toJson(src);
        return gson.fromJson(srcJson, dClass);
    }
}
