package com.getyourgame.getyourgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getyourgame.getyourgame.model.Usuario;
import com.getyourgame.getyourgame.util.Http;
import com.getyourgame.getyourgame.util.SwipeDetector;
import com.getyourgame.getyourgame.util.Util;
import com.getyourgame.getyourgame.util.Webservice;

import org.springframework.util.MultiValueMap;


public class Principal extends AppCompatActivity{

    Util util = new Util();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        final TextView tvBemBindo = (TextView) findViewById(R.id.tvBemVindo);

        View v = (RelativeLayout)this.findViewById(R.id.principal);
        new SwipeDetector(v).setOnSwipeListener(new SwipeDetector.onSwipeEvent() {
            @Override
            public void SwipeEventDetected(View v, SwipeDetector.SwipeTypeEnum swipeType) {
                if(swipeType==SwipeDetector.SwipeTypeEnum.LEFT_TO_RIGHT){

                    Intent mainIntent = new Intent(Principal.this,
                            ListaUsuarioJogo.class);
                    startActivity(mainIntent);
                    Principal.this.finish();
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                }
            }
        });

        /*try{
            Intent dadosRecebidos = getIntent();

            if(dadosRecebidos != null){
                Bundle recebe = dadosRecebidos.getExtras();
                int id_usuario = recebe.getInt("id_usuario");
                String apikey = recebe.getString("chave_api");

                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
                map.add("id_usuario",String.valueOf(id_usuario));
                Usuario usuario = new Usuario();
                Webservice ws = new Webservice();
                new HttpCadastro(ws.buscarUsuario(id_usuario),null,Usuario.class,apikey).execute();


                //tvBemBindo.setText("Bem vindo, "+nome+"!");
            }
        }catch(Exception e){}*/
    }

    private class HttpCadastro extends Http {
        public HttpCadastro(Webservice ws, MultiValueMap<String, String> map, Class classe, String apikey) {
            super(ws, map, classe, apikey);
        }

        @Override
        protected void onPostExecute(Object retorno) {
            Usuario usuario = (Usuario) retorno;
            if(!usuario.getError()) {
                final TextView tvBemBindo = (TextView) findViewById(R.id.tvBemVindo);
                tvBemBindo.setText("Bem vindo, "+usuario.getNome()+"!");
            }else{
                util.msgDialog(Principal.this, "Alerta", usuario.getMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
