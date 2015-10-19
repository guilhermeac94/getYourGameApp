package com.getyourgame.getyourgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.getyourgame.getyourgame.model.Usuario;
import com.getyourgame.getyourgame.util.Http;
import com.getyourgame.getyourgame.util.Util;
import com.getyourgame.getyourgame.util.Webservice;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


public class Login extends AppCompatActivity {

    Util util = new Util();

    private CallbackManager mCallbackManager;

    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            /*
            Profile profile = Profile.getCurrentProfile();
            if(profile!=null){
                redirecionar(profile.getName());
            }
            */
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        mCallbackManager = CallbackManager.Factory.create();

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etSenha = (EditText) findViewById(R.id.etSenha);
        etEmail.requestFocus();

        Button btEntrar = (Button) findViewById(R.id.btEntrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
                map.add("email",String.valueOf(etEmail.getText().toString()));
                map.add("senha",String.valueOf(etSenha.getText().toString()));

                Usuario usuario = new Usuario();
                Webservice ws = new Webservice();
                new HttpCadastro(ws.login(),map,Usuario.class,"").execute();


                //new HttpPostTask().execute(etEmail.getText().toString(), etSenha.getText().toString());
            }
        });


        TextView tvCadastrar = (TextView) findViewById(R.id.tvCadastrar);

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent tela1 = new Intent(Login.this, Cadastro.class);
                startActivity(tela1);
            }
        });

        /*
        Button btCadastrar = (Button) findViewById(R.id.btCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent tela1 = new Intent(Login.this, Cadastro.class);
                startActivity(tela1);
            }
        });
        */

        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile profile, Profile newProfile) {
                if(newProfile!=null) {
                    //redirecionar(newProfile.getName());
                }
            }
        };

        LoginButton FBLoginButton = (LoginButton) findViewById(R.id.fb_login_button);
        FBLoginButton.registerCallback(mCallbackManager, mCallBack);
    }


    private class HttpCadastro extends Http {
        public HttpCadastro(Webservice ws, MultiValueMap<String, String> map, Class classe, String apiKey) {
            super(ws, map, classe, apiKey);
        }

        @Override
        protected void onPostExecute(Object retorno) {
            Usuario usuario = (Usuario) retorno;
            if(!usuario.getError()) {
                redirecionar(usuario.getId_usuario(), usuario.getChave_api());
            }else{
                util.msgDialog(Login.this, "Alerta", usuario.getMessage());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    private void redirecionar(int id_usuario, String apikey){
        Intent intentPrincipal = new Intent(Login.this, CarregaCadastros.class);
        Bundle param = new Bundle();
        param.putInt("id_usuario", id_usuario);
        param.putString("chave_api", apikey);
        intentPrincipal.putExtras(param);
        startActivity(intentPrincipal);
        util.toast(getApplicationContext(), "Login efetuado com sucesso!");
    }
}
