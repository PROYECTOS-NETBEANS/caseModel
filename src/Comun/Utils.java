/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comun;

import Negocio.clsTabla;
import com.google.gson.Gson;

/**
 *
 * @author usuario
 */
public class Utils {
    public static void verTabla(clsTabla t){
        Gson g = new Gson();
        String s = g.toJson(t, clsTabla.class);
        System.out.println("tabla : " + s);
    }
}
