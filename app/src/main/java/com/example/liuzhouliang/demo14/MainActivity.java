package com.example.liuzhouliang.demo14;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    //对控件对象进行声明
    CheckBox beijing=null;
    CheckBox shanghai=null;
    CheckBox shenzhen=null;
    EditText editText1=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过控件的ID来得到代表控件的对象
        beijing=(CheckBox)findViewById(R.id.beijing);
        shanghai=(CheckBox)findViewById(R.id.shanghai);
        shenzhen=(CheckBox)findViewById(R.id.shenzhen);
        editText1=(EditText)findViewById(R.id.editText1);
//        beijing.setClickable(false);
        beijing.setChecked(true);
        beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"no",Toast.LENGTH_LONG).show();
//                if(((CheckBox)v).isChecked()){
//                    ((CheckBox)v).setChecked(false);
//                }
            }
        });
//        beijing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                beijing.setOnCheckedChangeListener(null);
//               if( ((CheckBox)v).isChecked()){
//                   Toast.makeText(MainActivity.this,"yes",Toast.LENGTH_LONG).show();
//                   //给CheckBox设置事件监听
//
////                   beijing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
////                       @Override
////                       public void onCheckedChanged(CompoundButton buttonView,
////                                                    boolean isChecked) {
////                           // TODO Auto-generated method stub
////                           if(isChecked){
////                               editText1.setText(buttonView.getText()+"选中");
////                           }else{
////                               editText1.setText(buttonView.getText()+"取消选中");
////                           }
////                       }
////                   });
//               }else{
//                   Toast.makeText(MainActivity.this,"no",Toast.LENGTH_LONG).show();
//               }
//            }
//        });

        shanghai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    editText1.setText(buttonView.getText()+"选中");
                }else{
                    editText1.setText(buttonView.getText()+"取消选中");
                }
            }
        });
        shenzhen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    editText1.setText(buttonView.getText()+"选中");
                }else{
                    editText1.setText(buttonView.getText()+"取消选中");
                }
            }
        });
    }
}

