package com.example.second_app;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.second_app.database.UserBaseHelper;
import com.example.second_app.database.UserDbSchema;

public class UserList {

    private static UserList userList;
    private Context context;
    private SQLiteDatabase database;
    private List users;// = new ArrayList(); ----ОДИН ИЗ ВИНОВНИКОВ ДУБЛИРОВАНИЯ

    public static UserList get(Context context){
        if(userList == null){
            userList = new UserList(context);
        }
        return userList;
    }
    private UserList(Context context){
        this.context = context.getApplicationContext();
        database = new UserBaseHelper(context).getWritableDatabase();

    }
    public List getUsers(){
        users = new ArrayList(); // Вследствии видимости переменной, перенёс её сюда.
        UserCursorWrapper cursor = queryUsers(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                users.add(cursor.getUser()); // Добавил Юзера в список для вывода
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return users;
    }

    public void addUser(User user){
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME, null, values);
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserDbSchema.UserTable.Cols.USERLASTNAME, user.getUserLastName());
        return values;
    }

    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs){
        Cursor cursor = database.query(
                UserDbSchema.UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new UserCursorWrapper(cursor);
    }

    public void updateUser(User user){
        String uuidString = user.getUuid().toString();
        ContentValues values = getContentValues(user);

        database.update(UserDbSchema.UserTable.NAME,values,
                UserDbSchema.UserTable.Cols.UUID+"=?",
                new String[]{uuidString});
    }

    public void deleteUser(User user){
        // Удаляем пользователя
        String uuidString = user.getUuid().toString();
       // ContentValues values = getContentValues(user);
        database.delete(UserDbSchema.UserTable.NAME, UserDbSchema.UserTable.Cols.UUID+"=?", new String[]{uuidString});
    }

}
