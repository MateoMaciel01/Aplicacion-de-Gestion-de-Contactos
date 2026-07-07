package com.example.tp_android_grupo_15;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormularioAgregarContactos extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellido;
    private EditText etTelefono;
    private EditText etEmail;
    private EditText etDireccion;
    private EditText etFechaNacimiento;
    private Spinner spinnerTelefono, spinnerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_agregar_contactos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);

        spinnerTelefono = findViewById(R.id.spinnerTelefono);
        spinnerEmail = findViewById(R.id.spinnerEmail);


        String[] opcionesSpinner = {"Casa", "Trabajo", "Móvil"};

        android.widget.ArrayAdapter<String> adapter = new android.widget.ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTelefono.setAdapter(adapter);
        spinnerEmail.setAdapter(adapter);


        etFechaNacimiento.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                java.util.Calendar calendario = java.util.Calendar.getInstance();
                int anio = calendario.get(java.util.Calendar.YEAR);
                int mes = calendario.get(java.util.Calendar.MONTH);
                int dia = calendario.get(java.util.Calendar.DAY_OF_MONTH);


                android.app.DatePickerDialog datePicker = new android.app.DatePickerDialog(
                        FormularioAgregarContactos.this,
                        R.style.DialogoEstilo,
                        new android.app.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                int mesReal = monthOfYear + 1;

                                String diaFormateado = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                                String mesFormateado = (mesReal < 10) ? "0" + mesReal : String.valueOf(mesReal);

                                etFechaNacimiento.setText(diaFormateado + "/" + mesFormateado + "/" + year);
                            }
                        }, anio, mes, dia);

                datePicker.show();
            }
        });

        android.widget.Button btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if (!Validaciones()) {
                    android.widget.Toast.makeText(FormularioAgregarContactos.this,
                            "Hay errores en el formulario",
                            android.widget.Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(FormularioAgregarContactos.this, FormularioMasDatosContacto.class);
                intent.putExtra("NOMBRE", etNombre.getText().toString());
                intent.putExtra("APELLIDO", etApellido.getText().toString());
                intent.putExtra("TELEFONO", etTelefono.getText().toString());
                intent.putExtra("TIPOTELEFONO", spinnerTelefono.getSelectedItem().toString());
                intent.putExtra("EMAIL", etEmail.getText().toString());
                intent.putExtra("TIPOEMAIL", spinnerEmail.getSelectedItem().toString());
                intent.putExtra("DIRECCION", etDireccion.getText().toString());
                intent.putExtra("FECHANACIMIENTO", etFechaNacimiento.getText().toString());
                startActivity(intent);
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Formulario de Contacto 1/2");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.AgregarContactos) {
            Intent intent = new Intent(this, FormularioAgregarContactos.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.ListadoContactos) {
            Intent intent = new Intent(this, FormularioListarContactos.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean Validaciones() {

        boolean esNombreValido = ValidarCampo(etNombre, "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+", "No se permiten numeros");
        boolean esApellidoValido = ValidarCampo(etApellido, "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+", "No se permiten numeros");
        boolean esTelefonoValido = ValidarCampo(etTelefono, "[0-9-]+", "No se permiten letras");
        boolean esEmailValido = ValidarCampo(etEmail, null, null);
        boolean esDireccionValido = ValidarCampo(etDireccion, null, null);
        boolean esFechaValida = ValidarFecha(etFechaNacimiento);

        if (esEmailValido) {
            String email = etEmail.getText().toString().trim();
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Ingrese un email válido");
                esEmailValido = false;
            }
        }

        return esNombreValido && esApellidoValido && esTelefonoValido && esEmailValido && esDireccionValido && esFechaValida;

    }

    private boolean ValidarCampo(EditText campo, String regex, String errorFormato) {
        String valor = campo.getText().toString().trim();
        campo.setError(null);

        if (valor.isEmpty()) {
            campo.setError("Campo requerido");
            return false;
        }
        if (regex != null && !valor.matches(regex)) {
            campo.setError(errorFormato);
            return false;
        }
        return true;
    }

    private boolean ValidarFecha(EditText campo) {
        String fecha = campo.getText().toString().trim();
        campo.setError(null);

        if (fecha.isEmpty()) {
            campo.setError("Campo requerido");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setLenient(false);

        try {
            sdf.parse(fecha);
            return true;
        } catch (ParseException e) {
            campo.setError("Ingrese una fecha valida (DD/MM/YYYY)");
            return false;
        }
    }
}
