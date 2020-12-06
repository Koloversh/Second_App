package com.example.second_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserFragment extends Fragment{

    private User user;
    private TextView userName_userLastname_View;
    @Override
    public void onCreate(Bundle savedInstanceState){ //вызывается при создании или перезапуска активности
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        //user.setUserName("Ivan");
        user = (User) bundle.getSerializable("user"); // Принимаем объект user
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){ // метод(onCreateView вызывается один раз, когда фрагмент должен загрузить на экран свой интерфейс) принимает аргументы из метода для взятия данных из XML-файла, контейнера содержащего виды и макеты, метод для сохранения состояния.
        View view = inflater.inflate(R.layout.fragment_user,container,false); // беру данные из fragment_user для контейнера
        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);
        String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();  // вывод имени и фамилии
        userName_userLastname_View.setText(userName); //

        return view;
    }
}
