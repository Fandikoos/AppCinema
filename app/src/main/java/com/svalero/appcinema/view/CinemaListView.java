package com.svalero.appcinema.view;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.svalero.appcinema.R;
import com.svalero.appcinema.adapter.CinemaAdapter;
import com.svalero.appcinema.contract.CinemaListContract;
import com.svalero.appcinema.domain.Cinema;
import com.svalero.appcinema.presenter.CinemaListPresenter;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class CinemaListView extends AppCompatActivity implements CinemaListContract.View {

    private List<Cinema> cinemas;

    private CinemaAdapter adapter;

    private CinemaListContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_list);

        presenter = new CinemaListPresenter(this);

        cinemas = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.cinema_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CinemaAdapter(cinemas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();

        presenter.loadAllCinemas();
    }

    public void addCinema(View view){
        goToAddCinema();
    }

    //TODO HACER LA ACTION BAR E IMPLEMENTAR LOS METODOS (id=action_bar)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_cinema) {
            goToAddCinema();
            return true;
        }

        //TODO hacer lo de las preferencias maybe
        return super.onOptionsItemSelected(item);
    }

    public void goToAddCinema(){
        Intent intent = new Intent(this, RegisterCinemaView.class);
        startActivity(intent);
    }

    @Override
    public void listCinemas(List<Cinema> cinemas) {
        this.cinemas.clear();
        this.cinemas.addAll(cinemas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}