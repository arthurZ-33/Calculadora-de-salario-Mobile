package br.ulbra.calculadoradesalario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calculadoraSalario";
    private  static  final int DATABASE_VERSION = 1;

    private static final String TABLE_USUARIOS = "USUARIOS";
    private static final String COL_ID = "ID";
    private static final String COL_NOME = "NOME";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_SENHA = "SENHA";

    public DbHelper(Context context){
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
