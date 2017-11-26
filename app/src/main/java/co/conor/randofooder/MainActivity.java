package co.conor.randofooder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    static ArrayList<String> foodList;
    static ArrayList<String> weekdays;
    static ArrayList<String> pickedFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.createLists();
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

        Collections.shuffle(foodList);

        int i = 0;
        while(i !=7){
            pickedFoods.add(foodList.get(i));
            i ++;
        }
        System.out.println(pickedFoods);

    }

}
