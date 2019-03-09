package com.rizkytm.reservation;

import android.provider.BaseColumns;
import android.support.v4.app.FragmentManager;

public final class Contract {

    private Contract(){}

    public static class AntreanTable implements BaseColumns {
        public static final String TABLE_NAME = "antrean";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_NO = "nomor";
    }
}
