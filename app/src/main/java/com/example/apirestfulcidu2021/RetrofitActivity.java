package com.example.apirestfulcidu2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.apirestfulcidu2021.modelos.Usuarios;
import com.example.apirestfulcidu2021.interfaces.UsuariosAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

        private TextView txtLista;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_retrofit);
            txtLista = (TextView) findViewById(R.id.txtLista);
            GETRetrofit();
        }

        private  void GETRetrofit(){
            final ProgressDialog loading = ProgressDialog.show(this, "Por favor espere...", "Actualizando datos",false,false);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://gorest.co.in/public/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UsuariosAPI usuariosAPI = retrofit.create(UsuariosAPI.class);
            Call<List<Usuarios>> call = usuariosAPI.getData();
            call.enqueue(new Callback<List<Usuarios>>() {
                @Override
                public void onResponse(Call<List<Usuarios>> call, retrofit2.Response<List<Usuarios>> response) {
                    loading.dismiss();
                    if(!response.isSuccessful()){
                        txtLista.setText(" codigo:  "+response.code());
                        return;
                    }
                    List<Usuarios> usuariosList = response.body();
                    for (Usuarios usuarios : usuariosList){
                        String content = "";
                        content+= "id:      "+usuarios.getId()    + "\n";
                        content+= "Nombre:  "+usuarios.getName()  + "\n";
                        content+= "Email:   "+usuarios.getEmail() + "\n";
                        content+= "Genero:  "+usuarios.getGender()+ "\n";
                        content+= "Estado:  "+usuarios.getStatus()+ "\n\n";
                        txtLista.append(content);
                    }
                }

                @Override
                public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                    txtLista.setText(t.getMessage());
                }
            });
        }
}
