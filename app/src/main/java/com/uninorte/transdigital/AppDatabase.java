package com.uninorte.transdigital;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Antonio on 14/04/17.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 1;
}
