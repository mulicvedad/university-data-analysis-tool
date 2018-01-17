package ba.unsa.etf.bp.udat;

import android.content.Context;

import ba.unsa.etf.bp.udat.ba.unsa.etf.bp.udat.models.LoginResponseData;

public class SessionService {
    public static boolean isUserLoggedIn(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        if (databaseHelper.usersCount() > 0)
            return true;
        else return false;
    }

    public static String getJwt(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getJwt();
    }

    public static void destroySession(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        databaseHelper.removeUser();
    }

    public static void startSession(LoginResponseData data, Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        databaseHelper.storeUser(data);
    }

}
