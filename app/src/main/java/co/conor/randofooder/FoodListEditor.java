package co.conor.randofooder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FoodListEditor extends AppCompatActivity {


    ListView secondList;
    ArrayAdapter secondAdapter;
    ArrayList<String> allFood = new ArrayList<String>(MainActivity.getAllFoods());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_editor);


        this.popList();
    }

    public void popList(){
        Collections.sort(allFood, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        secondList = (ListView) findViewById(R.id.editFoodList);

        secondAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allFood);

        secondList.setAdapter(secondAdapter);
    }


   public void backOnClick(View a){
        Button button = (Button) a;

       startActivity(new Intent(FoodListEditor.this , MainActivity.class));

    }

    public void deleteOnClick(View a){
        Button button = (Button) a;


    }

    public void saveOnClick(View a){
        Button button = (Button) a;
        TextView textView = (TextView)findViewById(R.id.editText2);
        String temp = textView.getText().toString();
        MainActivity.addFood(temp);
        this.popList();
    }


}
