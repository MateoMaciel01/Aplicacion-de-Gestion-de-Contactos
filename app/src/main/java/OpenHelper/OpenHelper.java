package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import java.util.ArrayList;

import Entidades.Contacto;

public class OpenHelper extends SQLiteOpenHelper {

    public static String ContactosCreationTable =
            "CREATE TABLE IF NOT EXISTS Contactos" +
                    "(_ID integer primary key autoincrement," +
                    "Nombre text NOT NULL, " +
                    "Apellido text NOT NULL, " +
                    "Telefono text NOT NULL, " +
                    "TipoTelefono text NOT NULL, " +
                    "Email text NOT NULL, " +
                    "TipoEmail text NOT NULL, " +
                    "Direccion text NOT NULL, " +
                    "FechaNacimiento text NOT NULL, " +
                    "NivelEstudios text NOT NULL, " +
                    "InteresDeporte integer NOT NULL DEFAULT 0, " +
                    "InteresMusica integer NOT NULL DEFAULT 0, " +
                    "InteresArte integer NOT NULL DEFAULT 0, " +
                    "InteresTecnologia integer NOT NULL DEFAULT 0, " +
                    "RecibirInformacion integer NOT NULL DEFAULT 0)";

    public static String ContactosTable = "Contactos";
    public static String ContactosColumnaID = "_ID";
    public static String ContactosNombre = "Nombre";
    public static String ContactosApellido = "Apellido";
    public static String ContactosTelefono = "Telefono";
    public static String ContactosTipoTelefono = "TipoTelefono";
    public static String ContactosEmail = "Email";
    public static String ContactosTipoEmail = "TipoEmail";
    public static String ContactosDireccion = "Direccion";
    public static String ContactosFechaNacimiento = "FechaNacimiento";
    public static String ContactosNivelEstudios = "NivelEstudios";
    public static String ContactosInteresDeporte = "InteresDeporte";
    public static String ContactosInteresMusica  = "InteresMusica";
    public static String ContactosInteresArte = "InteresArte";
    public static String ContactosInteresTecnologia = "InteresTecnologia";
    public static String ContactosRecibirInformacion = "RecibirInformacion";

    public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /// Query de creacion de tabla
        db.execSQL(ContactosCreationTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long InsertarContacto(Contacto contacto) {

        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues values = new android.content.ContentValues();

        values.put(ContactosNombre, contacto.getNombre());
        values.put(ContactosApellido, contacto.getApellido());
        values.put(ContactosTelefono, contacto.getTelefono());
        values.put(ContactosTipoTelefono, contacto.getTipoTelefono());
        values.put(ContactosEmail, contacto.getEmail());
        values.put(ContactosTipoEmail, contacto.getTipoEmail());
        values.put(ContactosDireccion, contacto.getDireccion());
        values.put(ContactosFechaNacimiento, contacto.getFechaNacimiento());
        values.put(ContactosNivelEstudios, contacto.getNivelEstudios());
        values.put(ContactosInteresDeporte, contacto.getInteresDeporte());
        values.put(ContactosInteresMusica, contacto.getInteresMusica());
        values.put(ContactosInteresArte, contacto.getInteresArte());
        values.put(ContactosInteresTecnologia, contacto.getInteresTecnologia());
        values.put(ContactosRecibirInformacion, contacto.getRecibirInformacion());

        long resultado = db.insert(ContactosTable, null, values);
        db.close();

        return resultado;
    }

    public boolean EliminarContacto(Contacto contacto)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        int cantidad = db.delete(ContactosTable, ContactosColumnaID + " = ?", new String[]{String.valueOf(contacto.getId())});

        db.close();

        return cantidad > 0;

    }

    public ArrayList<Contacto> getListadoContactos() {

        ArrayList<Contacto> listaContactos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        android.database.Cursor cursor = db.rawQuery("SELECT * FROM " + ContactosTable, null);

        if (cursor.moveToFirst()) {
            do {
                Contacto c = new Contacto();
                c.setId(cursor.getInt(0));
                c.setNombre(cursor.getString(1));
                c.setApellido(cursor.getString(2));
                c.setTelefono(cursor.getString(3));
                c.setTipoTelefono(cursor.getString(4));
                c.setEmail(cursor.getString(5));
                c.setTipoEmail(cursor.getString(6));
                c.setDireccion(cursor.getString(7));
                c.setFechaNacimiento(cursor.getString(8));
                c.setNivelEstudios(cursor.getString(9));
                c.setInteresDeporte(cursor.getInt(10));
                c.setInteresMusica(cursor.getInt(11));
                c.setInteresArte(cursor.getInt(12));
                c.setInteresTecnologia(cursor.getInt(13));
                c.setRecibirInformacion(cursor.getInt(14));

                listaContactos.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaContactos;
    }
    public boolean ActualizarContacto(Contacto contacto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(ContactosNombre, contacto.getNombre());
        v.put(ContactosApellido, contacto.getApellido());
        v.put(ContactosTelefono, contacto.getTelefono());
        v.put(ContactosTipoTelefono, contacto.getTipoTelefono());
        v.put(ContactosEmail, contacto.getEmail());
        v.put(ContactosTipoEmail, contacto.getTipoEmail());
        v.put(ContactosDireccion, contacto.getDireccion());
        v.put(ContactosFechaNacimiento, contacto.getFechaNacimiento());
        v.put(ContactosNivelEstudios, contacto.getNivelEstudios());
        v.put(ContactosInteresDeporte, contacto.getInteresDeporte());
        v.put(ContactosInteresMusica, contacto.getInteresMusica());
        v.put(ContactosInteresArte, contacto.getInteresArte());
        v.put(ContactosInteresTecnologia, contacto.getInteresTecnologia());
        v.put(ContactosRecibirInformacion, contacto.getRecibirInformacion());

        int filas = db.update(ContactosTable, v, ContactosColumnaID + " = ?",
                new String[]{String.valueOf(contacto.getId())});
        db.close();
        return filas > 0;
    }
}
