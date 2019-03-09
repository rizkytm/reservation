package com.rizkytm.reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ReservationActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView textViewNoAntreanSaatIni;
    TextView textViewNoAntreanSaya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        textViewNoAntreanSaatIni = (TextView) findViewById(R.id.antreanSaatIni);
        textViewNoAntreanSaya = (TextView) findViewById(R.id.antreanSaya);

        loadAntrean();
    }

    private void loadAntrean() {
        dbHelper = DBHelper.getInstance(this);
        List<Reservation> reservations = dbHelper.getReservation();
        String noAntri = dbHelper.noAntrean();
        textViewNoAntreanSaya.setText("Nomor Antrian Anda: " + noAntri);
        textViewNoAntreanSaatIni.setText("Nomor Antrian saat ini: " + reservations.get(0).getNo());
    }
}
