package ba.unsa.etf.bp.udat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ba.unsa.etf.bp.udat.ba.unsa.etf.bp.udat.models.LoginResponseData;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db = null;
    private String dbNameDefault = "udatDb";
    private String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS user(role VARCHAR, jwt VARCHAR);";

    public DatabaseHelper(Context context) {
        super(context, "udatDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table if exists if database is upgraded
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void storeUser(LoginResponseData loginResponseData) {
        db = this.getWritableDatabase();
        removeUser();
        db.execSQL("INSERT INTO user VALUES('" + loginResponseData.getRole() +
                "' , '" + loginResponseData.getJwt() +"');");
    }

    public void removeUser() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM user;");
    }

    public int usersCount() {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user;", null);
        return cursor.getCount();
    }

    public String getJwt() {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT jwt FROM user;", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String tmp = cursor.getString(0);
            return tmp;
        }
        return null;
    }
}
