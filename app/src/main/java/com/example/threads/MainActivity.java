package com.example.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvNumero, tvResultado;
    private int numero, numeroTexto;
    private boolean flag;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumero = (TextView) findViewById(R.id.tvNumero);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        handler = new Handler();
    }

    public void iniciarTarefa(View v) {
        flag = true;

        new Thread() {
            @Override
            public void run() {
                while (flag) {
                    Log.i("TT", "Tarefa Executando");
                    System.out.println("Cheguei aqui");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String numeroText = tvNumero.getText().toString();
                            int numero = Integer.parseInt(numeroText);

                            int resultado = numero * 2;

                            tvResultado.setText(tvNumero.getText() + "valor: " + resultado);
                        }

                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (flag) {
                    Log.i("TT", "Tarefa Executando");
                    System.out.println("Cheguei aqui");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String numeroText = tvNumero.getText().toString();
                            int numero = Integer.parseInt(numeroText);

                            int resultado = numero * 3;

                            tvResultado.setText(tvNumero.getText() + "valor: " + resultado);
                        }

                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
    }


    public void pararTarefa(View v) {
        flag = false;
    }
}