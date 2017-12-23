package co.conor.randofooder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FoodListEditor extends AppCompatActivity {


    static String selectedValue;
    ListView secondList;
    ArrayAdapter secondAdapter;
    ArrayList<String> allFood = new ArrayList<String>(MainActivity.getAllFoods());
    Context context = FoodListEditor.this;
    File path = context.getExternalFilesDir(null);
    File file = new File(path, "RandoFooderFoodList.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_editor);


        this.popList();


        secondList = (ListView) findViewById(R.id.editFoodList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, allFood);

        secondList.setAdapter(adapter);

        // ListView on item selected listener.
        secondList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedValue = allFood.get(position);
                EditText text = (EditText) findViewById(R.id.SelText);
                text.setText(selectedValue);

            }
        });

        this.writeToFile();
        this.readFile();
    }

    public void popList() {
        allFood = MainActivity.getAllFoods();
        Collections.sort(allFood, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        secondList = (ListView) findViewById(R.id.editFoodList);

        secondAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, allFood);

        secondList.setAdapter(secondAdapter);
    }


    public void backOnClick(View a) {
        Button button = (Button) a;

        startActivity(new Intent(context, MainActivity.class));

    }

    public void deleteOnClick(View a) {
        Button button = (Button) a;
        MainActivity.getAllFoods().remove(selectedValue);
        this.popList();

        Toast.makeText(context, selectedValue + " deleted", Toast.LENGTH_SHORT).show();
    }

    public void saveOnClick(View a) {
        Button button = (Button) a;
        TextView textView = (TextView) findViewById(R.id.SelText);
        String temp = textView.getText().toString();
        MainActivity.addFood(temp);
        this.popList();
        this.writeToFile();
    }


    public void writeToFile() {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            stream.write("TESTESTETSTETSTETSTESTSETETSETESTESTSET".getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            in.read(bytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String contents = new String(bytes);

        Toast.makeText(context, contents, Toast.LENGTH_SHORT).show();

    }


}
