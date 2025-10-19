package br.ulbra.calculadoradesalario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calculadoraSalario";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_USUARIOS = "USUARIOS";
    private static final String COL_ID = "ID";
    private static final String COL_NOME = "NOME";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_SENHA = "SENHA";
    private static final String COL_SALARIO = "SALARIO";
    private static final String COL_FILHO = "FILHOS";
    private static final String COL_SEXO = "SEXO";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_USUARIOS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NOME + " VARCHAR(80) NOT NULL, "
                + COL_EMAIL + " VARCHAR(122) NOT NULL UNIQUE, "
                + COL_SENHA + " VARCHAR(122) NOT NULL, "
                + COL_SALARIO + " DECIMAL(10, 2) NOT NULL, CONSTRAINT CHK_SALARIO CHECK(" + COL_SALARIO + " > 0), "
                + COL_FILHO + "INTEGER, CONSTRAINT CHK_FILHO CHECK(" + COL_FILHO + " >= 0), "
                + COL_SEXO + " BOOLEAN NOT NULL"
                + " )";

        db.execSQL(SQL_CREATE_TABLE);
        Log.d("DB_LOG", "Table " + TABLE_USUARIOS + " created successfully. Prepare for data.");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }


    public boolean addUser(String nome, String email, String senha, double salario, int filhos, boolean sexo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NOME, nome);
        values.put(COL_EMAIL, email);
        values.put(COL_SENHA, senha);
        values.put(COL_SALARIO, salario);
        values.put(COL_FILHO, filhos);
        values.put(COL_SEXO, sexo);


        long result = db.insert(TABLE_USUARIOS, null, values);



        return result != -1;
    }


    public boolean checkUser(String email, String senha) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COL_ID};
        String selection = COL_EMAIL + " = ? AND " + COL_SENHA + " = ?";
        String[] selectionArgs = {email, senha};


        Cursor cursor = db.query(
                TABLE_USUARIOS,
                columns,
                selection,
                selectionArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        int cursorCount = cursor.getCount(); // How many Waldos did you find?

        cursor.close();
        db.close();

        return cursorCount > 0; // If you found at least one, the user exists!
    }
}

