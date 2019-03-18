package com.andela.javadevsnairobi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.andela.javadevsnairobi.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView devsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devsRecyclerView = findViewById(R.id.devs_recycler_view);
        devsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
