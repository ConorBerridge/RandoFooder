package co.conor.randofooder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    static ArrayList<String> foodList;
    static ArrayList<String> weekdays;
    static ArrayList<String> pickedFoods;
    static ArrayList<String> weekFood = new ArrayList<String>();
    ListView myList;
    ArrayAdapter myAdapter;
    static Boolean swtichedAlready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainActivity.createLists();


        this.pickFoodsNow();

        this.popList();

    }


    public static void createLists(){
        foodList = new ArrayList<String>();
        weekdays = new ArrayList<String>();
        pickedFoods = new ArrayList<String>();

        weekdays.add("Monday");
        weekdays.add("Tuesday");
        weekdays.add("Wednesday");
        weekdays.add("Thursday");
        weekdays.add("Friday");
        weekdays.add("Saturday");
        weekdays.add("Sunday");

        foodList.add("Roast");
        foodList.add("Curry");
        foodList.add("Bolognese");
        foodList.add("Fajitas");
        foodList.add("Homemade pizza");
        foodList.add("One pot");
        foodList.add("Pesto pasta");
        foodList.add("Thai curry");
        foodList.add("Stirfry");
        foodList.add("Falafel");
        foodList.add("Meat and greens");
        foodList.add("Sausage and potato");
        foodList.add("Omelette");
        foodList.add("Stew");
        foodList.add("Chilli");
        foodList.add("Tacos");
        foodList.add("Pasta bake");
        foodList.add("Fish and veg");


    }

    public void popList(){
        pickedFoods.clear();
        weekFood.clear();

        //FoodListEditor fe = new FoodListEditor();
        //fe.readFromFile();

        this.pickFoodsNow();

        myList = (ListView) findViewById(R.id.foodList);

        myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, weekFood);

        myList.setAdapter(myAdapter);

    }

    public ArrayList<String> pickFoodsNow(){
        Collections.shuffle(foodList);

        int i = 0;
        while(i !=7){
            pickedFoods.add(foodList.get(i));
            StringBuilder sb = new StringBuilder();
            sb.append(weekdays.get(i)).append(" : ").append(pickedFoods.get(i));
            weekFood.add(sb.toString());
            i ++;
        }


        System.out.println(pickedFoods);
        return null;
    }

    public void pickOnClick(View a){
        Button button = (Button) a;

        this.popList();
    }

    public void editListOnClick(View a){
        Button button = (Button) a;
        startActivity(new Intent(MainActivity.this , FoodListEditor.class));

    }

    public static ArrayList<String> getAllFoods(){
        return foodList;
    }

    public static void addFood(String food){
        foodList.add(food);
    }


}
