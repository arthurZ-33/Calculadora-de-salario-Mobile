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

    private static final String COL_SALARIO = "SALARIO";
    private static final String COL_FILHO = "FILHOS";
    private static final String COL_SEXO = "SEXO";

    public DbHelper(Context context){
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_USUARIOS + " ("

                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + COL_NOME + " VARCHAR(80) NOT NULL, "

                + COL_SENHA + " VARCHAR(122) NOT NULL , CONSTRAINT CHK_SENHA CHECK (LENGTH(" + COL_SENHA + ") >= 8), "

                + COL_EMAIL + " VARCHAR(122) NOT NULL UNIQUE, "

                + COL_SALARIO + " DECIMAL(10, 2) NOT NULL, CONSTRAINT CHK_SALARIO CHECK(" + COL_SALARIO + " > 0) "

                + COL_FILHO + " INT, CONSTRAINT CHK_FILHO CHECK(" + COL_FILHO + " >= 0) "

                + COL_SEXO + " BOOLEAN NOT NULL"

                + " )";

         db.execSQL(SQL_CREATE_TABLE);
         android.util.Log.d("DB_LOG", "Table USUARIOS created successfuly.");
         
    }
}
