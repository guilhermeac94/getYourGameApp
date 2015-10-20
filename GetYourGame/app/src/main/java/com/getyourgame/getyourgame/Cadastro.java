package com.getyourgame.getyourgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.getyourgame.getyourgame.model.Usuario;
import com.getyourgame.getyourgame.util.Http;
import com.getyourgame.getyourgame.util.Util;
import com.getyourgame.getyourgame.util.Webservice;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class Cadastro extends AppCompatActivity {

    Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etNome = (EditText) findViewById(R.id.etNome);
        final EditText etSenha = (EditText) findViewById(R.id.etSenha);
        etNome.requestFocus();

        Button btCadastrar = (Button) findViewById(R.id.btCadastro);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
                map.add("nome",String.valueOf(etNome.getText().toString()));
                map.add("email",String.valueOf(etEmail.getText().toString()));
                map.add("senha", String.valueOf(etSenha.getText().toString()));

                Webservice ws = new Webservice();

                new HttpCadastro(ws.cadastro(),map,Usuario.class,"").execute();
            }
        });
    }

    private class HttpCadastro extends Http {
        public HttpCadastro(Webservice ws, MultiValueMap<String, String> map, Class classe, String apikey) {
            super(ws, map, classe, apikey);
        }

        @Override
        protected void onPostExecute(Object retorno) {
            Usuario usuario = (Usuario) retorno;
            if(!usuario.getError()) {
                Intent intentPrincipal = new Intent(Cadastro.this, CarregaCadastros.class);
                Bundle param = new Bundle();
                param.putString("nome", usuario.getNome());
                intentPrincipal.putExtras(param);
                startActivity(intentPrincipal);
                util.toast(getApplicationContext(), "Usuário cadastrado com sucesso!");
            }else{
                util.msgDialog(Cadastro.this, "Alerta", usuario.getMessage());
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
