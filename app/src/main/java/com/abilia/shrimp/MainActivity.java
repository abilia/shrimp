package com.abilia.shrimp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.abilia.shrimp.models.Person;
import com.abilia.shrimp.service.GreetingService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GreetingService greetingService = new GreetingService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderGreeting();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_greeting:
                    renderGreeting();
                    return true;
                case R.id.navigation_businesscard:
                    // TODO
                    return true;
            }
            return false;
        }
    };

    private void renderGreeting() {
        TextView greeting = findViewById(R.id.greeting);

        Person person = greetingService.getGreetingPerson();

        greeting.setText(person.getFullName());
    }
}
