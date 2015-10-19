package com.getyourgame.getyourgame.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 14/09/2015.
 */
public class Util extends Activity{

    public void msgDialog(Activity act, String titulo, String msg){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(act);
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle(titulo);
        dlgAlert.setNeutralButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public void toast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void retornaMap(final Object objeto){
        ReflectionUtils.doWithFields(objeto.getClass(), new ReflectionUtils.FieldCallback() {

            @Override
            public void doWith(final Field field)
                    throws IllegalArgumentException, IllegalAccessException {

                System.out.println("Field name: " + field.getName());
                field.setAccessible(true);
                System.out.println("Field value: " + field.get(objeto));

            }
        });
    }

    public void redirecionar(Activity atual, Class destino, Bundle param){
        Intent intentPrincipal = new Intent(atual, destino);
        intentPrincipal.putExtras(param);
        startActivity(intentPrincipal);
    }


    public static Object[] convertToObjectArray(Object array) {
        Class ofArray = array.getClass().getComponentType();
        if (ofArray.isPrimitive()) {
            List ar = new ArrayList();
            int length = Array.getLength(array);
            for (int i = 0; i < length; i++) {
                ar.add(Array.get(array, i));
            }
            return ar.toArray();
        }
        else {
            return (Object[]) array;
        }
    }

    public void carregaSpinner(Spinner spinner, Activity act, List objetos ){
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(act, android.R.layout.simple_spinner_item, objetos);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    public Integer recebeIdUsuario(Intent intent){
        try{
            Intent dadosRecebidos = intent;

            if(dadosRecebidos != null){
                Bundle recebe = dadosRecebidos.getExtras();
                return recebe.getInt("id_usuario");
            }
        }catch(Exception e){}
        return null;
    }
}
