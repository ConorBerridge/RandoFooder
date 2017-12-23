package co.conor.randofooder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    static String selectedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_editor);


        this.popList();



        secondList = (ListView)findViewById(R.id.editFoodList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1, allFood);

        secondList.setAdapter(adapter);

        // ListView on item selected listener.
        secondList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedValue = allFood.get(position);
                EditText text = (EditText) findViewById(R.id.SelText);
                text.setText(selectedValue);

            }
        });
    }

    public void popList(){
        allFood = MainActivity.getAllFoods();
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
        MainActivity.getAllFoods().remove(selectedValue);
        this.popList();

        Toast.makeText(FoodListEditor.this, selectedValue + " deleted", Toast.LENGTH_SHORT).show();
    }

    public void saveOnClick(View a){
        Button button = (Button) a;
        TextView textView = (TextView)findViewById(R.id.SelText);
        String temp = textView.getText().toString();
        MainActivity.addFood(temp);
        this.popList();
    }




}
