package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btJogar;
    private Button btPlay;
    private EditText etLetra;
    private ForcaView forcaView;
    private ForcaController forcaController;

    private String[] palavra = new String[]{"onix"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btJogar = (Button)findViewById( R.id.btJogar );
        btPlay = (Button)findViewById( R.id.btPlay );
        etLetra = (EditText)findViewById( R.id.etLetra );

        forcaView = (ForcaView)findViewById( R.id.fvJogo);

        init();
    }

    private void init(){

        btJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etLetra.getText().toString().trim().length()==0)
                    return;
                getForcaController().joga( etLetra.getText().toString().trim().charAt(0) );
                forcaView.invalidate();
                etLetra.getText().clear();

                if( getForcaController().isTerminou() ){
                    btJogar.setEnabled( false );
                    btPlay.setEnabled( true );
                    if( getForcaController().isMorreu())
                        Toast.makeText(getApplicationContext(), "Ops! Você perdeu",Toast.LENGTH_LONG).show();
                    else
                        if( getForcaController().isGanhou()) {
                            Toast.makeText(getApplicationContext(), "Parabéns, Você ganhou!", Toast.LENGTH_LONG).show();
                        }
                }
            }
        });

        btPlay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    setForcaController( new ForcaController(palavra[ new Random().nextInt(palavra.length)]) );
                forcaView.setForcaController( getForcaController() );

                etLetra.getText().clear();
                etLetra.setEnabled( true );
                btJogar.setEnabled( true );
                btPlay.setEnabled( false );
            }
        });
    }

    public ForcaController getForcaController() {
        return forcaController;
    }
    public void setForcaController(ForcaController forcaController) {
        this.forcaController = forcaController;
    }
}