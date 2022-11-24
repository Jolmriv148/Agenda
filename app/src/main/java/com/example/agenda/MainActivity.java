package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);

        SharedPreferences sp = getSharedPreferences("contador", Context.MODE_PRIVATE);
        //Accedemos al valor de la cuenta. Si no existe ponemos el valor 0 a cuenta
        int cuenta=sp.getInt("cuenta",0);

        //Incrementamos contador
        cuenta++;

        //Guardamos los cambios en el archivo de prefs
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("cuenta",cuenta);
        editor.commit();

        Toast.makeText(getApplicationContext(),"Has entrado en la aplicación "+cuenta+" veces.",Toast.LENGTH_LONG).show();


    }

    public void guardar(View view) {

        SharedPreferences sp = getSharedPreferences("agenda", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        //Extremos fecha y contenido
        String fecha=et1.getText().toString();
        String contenido=et2.getText().toString();

        //Guardamos en el archivo de prefs
        editor.putString(fecha,contenido);
        editor.commit();

        Toast.makeText(getApplicationContext(),"Se ha guardado el registro",Toast.LENGTH_LONG).show();
        et1.setText("");
        et2.setText("");




    }

    public void recuperar(View view) {

        SharedPreferences sp = getSharedPreferences("agenda", Context.MODE_PRIVATE);

        //Cogemos la fecha
        String fecha=et1.getText().toString();

        //La usamos para consultar si hay algún registro asociado
        String contenido=sp.getString(fecha,"");

        //Mostrar el contenido en el et2
        et2.setText(contenido);



    }
}