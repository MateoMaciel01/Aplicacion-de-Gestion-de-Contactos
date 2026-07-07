package com.example.tp_android_grupo_15;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import Entidades.Contacto;
import OpenHelper.OpenHelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FormularioListarContactos extends AppCompatActivity {

    private ListView lvContactos;
    private ArrayList<Contacto> listaContactosOriginal;
    private ArrayList<String> listaParaMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);androidx.activity.EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_listar_contactos);

        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            androidx.core.graphics.Insets systemBars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Listado de Contactos");
        }
        lvContactos = findViewById(R.id.lvContactos);

        OpenHelper dbHelper = new OpenHelper(this, "ContactosDB", null, 1);
        listaContactosOriginal = dbHelper.getListadoContactos();
        listaParaMostrar = new ArrayList<>();

        for (Contacto c : listaContactosOriginal) {
           String formato = c.getNombre().toUpperCase() + " " + c.getApellido().toUpperCase() + " - " + c.getEmail().toUpperCase();
            listaParaMostrar.add(formato);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaParaMostrar
        );
        lvContactos.setAdapter(adapter);


        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contacto contactoSeleccionado = listaContactosOriginal.get(position);

                String detalleCompleto = "Nombre: " + contactoSeleccionado.getNombre() + "\n" +
                        "Apellido: " + contactoSeleccionado.getApellido() + "\n" +
                        "Telefono: " + contactoSeleccionado.getTelefono() + "-" + contactoSeleccionado.getTipoTelefono() + "\n" +
                        "Email: " + contactoSeleccionado.getEmail() + "-" +contactoSeleccionado.getTipoEmail() + "\n" +
                        "Direccion: "  + contactoSeleccionado.getDireccion() + "\n" +
                        "Fecha de Nacimiento: " + contactoSeleccionado.getFechaNacimiento() + "\n" +
                        "Nivel de estudios: " + contactoSeleccionado.getNivelEstudios() + "\n\n" +
                        "INTERESES:\n" +
                        "Ámbito de Deportes: " + (contactoSeleccionado.getInteresDeporte() == 1 ? "Sí" : "No") + "\n" +
                        "Música: " + (contactoSeleccionado.getInteresMusica() == 1 ? "Sí" : "No") + "\n" +
                        "Arte: " + (contactoSeleccionado.getInteresArte() == 1 ? "Sí" : "No") + "\n" +
                        "Ámbito de la Tecnología: " + (contactoSeleccionado.getInteresTecnologia() == 1 ? "Sí" : "No") + "\n\n" +
                        "Quiere recibir Información: " + (contactoSeleccionado.getRecibirInformacion() == 1 ? "Sí" : "No");




                AlertDialog.Builder builder = new AlertDialog.Builder(FormularioListarContactos.this,R.style.DialogoEstilo);
                builder.setTitle("Información del Contacto");
                builder.setMessage(detalleCompleto);

                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FormularioListarContactos.this, FormularioEditarContacto.class);
                        intent.putExtra("ID", contactoSeleccionado.getId());
                        intent.putExtra("NOMBRE", contactoSeleccionado.getNombre());
                        intent.putExtra("APELLIDO", contactoSeleccionado.getApellido());
                        intent.putExtra("TELEFONO", contactoSeleccionado.getTelefono());
                        intent.putExtra("TIPOTELEFONO", contactoSeleccionado.getTipoTelefono());
                        intent.putExtra("EMAIL", contactoSeleccionado.getEmail());
                        intent.putExtra("TIPOEMAIL", contactoSeleccionado.getTipoEmail());
                        intent.putExtra("DIRECCION", contactoSeleccionado.getDireccion());
                        intent.putExtra("FECHANACIMIENTO", contactoSeleccionado.getFechaNacimiento());
                        intent.putExtra("NIVELESTUDIOS", contactoSeleccionado.getNivelEstudios());
                        intent.putExtra("DEPORTE", contactoSeleccionado.getInteresDeporte());
                        intent.putExtra("MUSICA", contactoSeleccionado.getInteresMusica());
                        intent.putExtra("ARTE", contactoSeleccionado.getInteresArte());
                        intent.putExtra("TECNOLOGIA", contactoSeleccionado.getInteresTecnologia());
                        intent.putExtra("RECIBIRINFORMACION", contactoSeleccionado.getRecibirInformacion());
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder confirmacion= new AlertDialog.Builder(FormularioListarContactos.this,
                                R.style.DialogoEstilo);
                        confirmacion.setTitle("Confirmar eliminación");
                        confirmacion.setMessage("Está seguro que desea eliminar este contacto?");

                        confirmacion.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog2, int which){
                                boolean borradoEjecutado = dbHelper.EliminarContacto(contactoSeleccionado);

                                if(borradoEjecutado) {

                                    listaContactosOriginal.remove(position);
                                    listaParaMostrar.remove(position);

                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(FormularioListarContactos.this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(FormularioListarContactos.this, "Ha ocurrido un problema al eliminar", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        confirmacion.setNegativeButton("No", null);
                        confirmacion.show();

                    }
                });

                builder.setNeutralButton("Cerrar", null);

                builder.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();

        if (id == R.id.AgregarContactos) {
            Intent intent = new Intent(this, FormularioAgregarContactos.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.ListadoContactos) {
            Intent intent = new Intent(this, FormularioListarContactos.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

