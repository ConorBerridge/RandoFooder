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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FoodListEditor extends AppCompatActivity {


    static String selectedValue;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RandoFooder";
    ListView secondList;
    ArrayAdapter secondAdapter;
    ArrayList<String> allFood = new ArrayList<String>(MainActivity.getAllFoods());
    Context context = FoodListEditor.this;
    File dir;


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


        dir = new File(path);
        dir.mkdirs();


    }

    public String arrayToString() {
        StringBuilder sb = new StringBuilder();
        for (Object s : allFood) {
            sb.append(s);
            sb.append("\t");
        }
        return sb.toString();
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

        writeToSDcardFile();
    }

    public void saveOnClick(View a) {
        Button button = (Button) a;
        TextView textView = (TextView) findViewById(R.id.SelText);
        String temp = textView.getText().toString();
        MainActivity.addFood(temp);
        this.popList();

        writeToSDcardFile();


    }

    private void writeToSDcardFile() {
        String fileName = "RandoFooderList";
        StringBuilder sb = new StringBuilder();
        for (String s : allFood){
            sb.append(s);
            sb.append(",");
        }
        String sSomeText = new String(sb.toString());



        File sdcard = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(sdcard.getAbsolutePath() + "/Rando");
        dir.mkdirs();
        File file = new File(dir, fileName);// for example "myData.txt"
        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(sSomeText +"Hello world!");
            pw.println("Other text");
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String readFileOnExternalSD(){
        String fileName = "RandoFooderList";


        File sdcard = Environment.getExternalStorageDirectory();

        //Get the text file
        File file = new File(sdcard.getAbsolutePath() + "/Rando",fileName);

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            // error handling here
        }
        return text.toString();
    }
}
