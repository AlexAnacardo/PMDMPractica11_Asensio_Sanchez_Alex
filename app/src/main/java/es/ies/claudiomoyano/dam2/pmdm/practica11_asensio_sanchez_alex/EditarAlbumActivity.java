package es.ies.claudiomoyano.dam2.pmdm.practica11_asensio_sanchez_alex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class EditarAlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editar);

        Intent intentPadre = getIntent();

        int posicion = intentPadre.getIntExtra("posicion", 0);
        int idFoto = intentPadre.getIntExtra("foto", 0);
        String nombreAlbum = intentPadre.getStringExtra("nombre");
        String nombreBanda = intentPadre.getStringExtra("banda");
        String discografica = intentPadre.getStringExtra("discografica");
        int copias = intentPadre.getIntExtra("copias", 0);
        String fechaLanzamiento = intentPadre.getStringExtra("fechaLanzamiento");

        ImageView imagen = findViewById(R.id.fotoAlbumEditar);
        EditText tiAlbum = findViewById(R.id.tiNombre);
        EditText tiBanda = findViewById(R.id.tiBanda);
        EditText tiDiscografica = findViewById(R.id.tiDiscografica);
        EditText tiCopias = findViewById(R.id.tiCopias);
        EditText tiLanzamiento = findViewById(R.id.editTextDate);


        imagen.setImageResource(idFoto);
        tiAlbum.setText(nombreAlbum);
        tiBanda.setText(nombreBanda);
        tiDiscografica.setText(discografica);
        tiCopias.setText(String.valueOf(copias));
        tiLanzamiento.setText(fechaLanzamiento);

        Button botonAceptar = findViewById(R.id.buttonAceptarEditar);
        Button botonCancelar = findViewById(R.id.buttonCancelarEditar);

        botonAceptar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent();

                    intent.putExtra("posicion", posicion);
                    intent.putExtra("nuevoAlbum", tiAlbum.getText().toString());
                    intent.putExtra("nuevoBanda", tiBanda.getText().toString());
                    intent.putExtra("nuevoDiscografica", tiDiscografica.getText().toString());
                    intent.putExtra("nuevoCopias", Integer.parseInt(tiCopias.getText().toString()));

                    intent.putExtra("nuevoFecha", LocalDate.parse(tiLanzamiento.getText().toString()));

                    setResult(RESULT_OK, intent);

                    finish();
                }catch(DateTimeParseException ex){
                    Toast.makeText(EditarAlbumActivity.this, "Formato de fecha incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                setResult(RESULT_CANCELED);

                finish();
            }
        });

    }


}
