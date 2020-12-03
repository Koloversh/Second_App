package com.example.second_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserList userList = UserList.get(); //
        FragmentManager fragmentManager = getSupportFragmentManager(); //Управление отображениями фрагментов на экране методом, возвращающим элемент FragmentManager
        Fragment fragment = new UserListFragment(); // создан объект Fragment с именем fragment
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit(); // у fragmentManager вызываю метод помещающий элементы на экран и передаю аргументы "куда положить" и сам фрагмент и вызываю фиксацию элемента на экран
    }
}