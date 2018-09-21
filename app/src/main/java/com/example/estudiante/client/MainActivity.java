package com.example.estudiante.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements Receptor.onMessage{

    Button btn_ingresar;
    EditText edt_usuario,edt_contrasena;
    TextView tv_valida;
    Cliente c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c=new Cliente (this,null);
        c.start();


        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasena = findViewById(R.id.edt_contrasena);
        btn_ingresar = findViewById(R.id.btn_ingresar);
        tv_valida= findViewById(R.id.tv_valida);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(edt_usuario.getText().length() != 0 && edt_contrasena.getText().length() !=0 ){
                    String user = edt_usuario.getText().toString();
                    String pass= edt_contrasena.getText().toString();


                    c.mensaje = user+pass;

                    c.enviarDatos();
                    edt_usuario.setText("");
                    edt_contrasena.setText("");
                    tv_valida.setText("");

                }else{
                    tv_valida.setText("INCORRECTO");
                }





            }
        });





    }

    @Override
    public void onReceived(final String mensaje) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,mensaje,Toast.LENGTH_SHORT).show();
                tv_valida.setText(mensaje);
            }
        });
    }
}
