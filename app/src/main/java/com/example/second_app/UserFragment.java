package com.example.second_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserFragment extends Fragment{

    private User user;
    private TextView userName_userLastname_View;
    private EditText editName;
    private EditText editLastname;
    private Button updateBtn;
    private UserList userList;
    private Button deleteBtn;

    @Override
    public void onCreate(Bundle savedInstanceState){ //вызывается при создании или перезапуска активности
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user"); // Принимаем объект user
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){ // метод(onCreateView вызывается один раз, когда фрагмент должен загрузить на экран свой интерфейс) принимает аргументы из метода для взятия данных из XML-файла, контейнера содержащего виды и макеты, метод для сохранения состояния.
        View view = inflater.inflate(R.layout.fragment_user,container,false); // беру данные из fragment_user для контейнера
        userList = UserList.get(getActivity());
        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);
        editName = view.findViewById(R.id.editName);
        editLastname = view.findViewById(R.id.editLastname);
        updateBtn = view.findViewById(R.id.updateBtn);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        final String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();
        userName_userLastname_View.setText(userName); //

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUserName = editName.getText().toString(); //
                String newUserLastname = editLastname.getText().toString();
                user.setUserName(newUserName);
                user.setUserLastName(newUserLastname);
                userList.updateUser(user);
                Toast.makeText(getActivity(),"Данные изменены",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList.deleteUser(user);
                Toast.makeText(getActivity(), "Пользователь удалён", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });


        return view;
    }
}
