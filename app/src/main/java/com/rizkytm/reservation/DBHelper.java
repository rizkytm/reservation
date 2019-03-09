package com.rizkytm.reservation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Reservasi.db";
    private static final int DATABASE_VERSION = 1;

    private static DBHelper instance;

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_ANTREAN_TABLE = "CREATE TABLE " +
                Contract.AntreanTable.TABLE_NAME + "( " +
                Contract.AntreanTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.AntreanTable.COLUMN_NAME + " TEXT," +
                Contract.AntreanTable.COLUMN_DATE + " TEXT," +
                Contract.AntreanTable.COLUMN_NO + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_ANTREAN_TABLE);
        fillAntreanTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.AntreanTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillAntreanTable() {

        Reservation r1 = new Reservation("Rizky", "08/03/2019", 1);
        addReservation(r1);
    }

    private void addReservation(Reservation reservation) {
        ContentValues cv = new ContentValues();
        cv.put(Contract.AntreanTable.COLUMN_NAME, reservation.getName());
        cv.put(Contract.AntreanTable.COLUMN_DATE, reservation.getDate());
        cv.put(Contract.AntreanTable.COLUMN_NO, reservation.getNo());
        db.insert(Contract.AntreanTable.TABLE_NAME, null, cv);
    }

    public List<Reservation> getReservation() {
        List<Reservation> reservationList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Contract.AntreanTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Reservation reservation = new Reservation();
                reservation.setId(c.getInt(c.getColumnIndex(Contract.AntreanTable._ID)));
                reservation.setName(c.getString(c.getColumnIndex(Contract.AntreanTable.COLUMN_NAME)));
                reservation.setDate(c.getString(c.getColumnIndex(Contract.AntreanTable.COLUMN_DATE)));
                reservation.setNo(c.getInt(c.getColumnIndex(Contract.AntreanTable.COLUMN_NO)));
                reservationList.add(reservation);
            } while (c.moveToNext());
        }

        c.close();
        return reservationList;
    }

    public String noAntrean() {
        Integer noAntrean;
        String antreanSaya;
        db = getReadableDatabase();
        String todayDate = "08/03/2019";
        Cursor c = db.rawQuery("SELECT * FROM " + Contract.AntreanTable.TABLE_NAME + " WHERE DATE = " + "'08/03/2019'", null);
        noAntrean = c.getCount();
        System.out.println(noAntrean);
        noAntrean = noAntrean + 1;
        antreanSaya = Integer.toString(noAntrean);

        return antreanSaya;
    }
}
