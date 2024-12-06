package com.example.tickit.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Create tables
        db.execSQL("PRAGMA foreign_keys = ON")
        db.execSQL(CREATE_TABLE_BIOSKOP)
        db.execSQL(CREATE_TABLE_FILM)
        db.execSQL(CREATE_TABLE_JADWAL)
        db.execSQL(CREATE_TABLE_BANGKU)
        db.execSQL(CREATE_TABLE_PENGGUNA)
        db.execSQL(CREATE_TABLE_TIKET)
        db.execSQL(CREATE_TABLE_PEMBAYARAN)
        db.execSQL(CREATE_TABLE_RIWAYAT_PEMESANAN)
        db.execSQL(CREATE_TABLE_BANGKU_DIPESAN)
        db.execSQL(CREATE_TABLE_BANGKU_TERPAKAI)
    }
    fun populateData(){
        val db = this.writableDatabase
        populatedata().populateMockData(db)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older tables if existed
        db.execSQL("PRAGMA foreign_keys = ON")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BIOSKOP")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FILM")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_JADWAL")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BANGKU")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PENGGUNA")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TIKET")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PEMBAYARAN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RIWAYAT_PEMESANAN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BANGKU_DIPESAN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BANGKU_TERPAKAI")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "slebew.db"

        // Table names
        const val TABLE_BIOSKOP = "bioskop"
        const val TABLE_FILM = "film"
        const val TABLE_JADWAL = "jadwal"
        const val TABLE_BANGKU = "bangku"
        const val TABLE_PENGGUNA = "pengguna"
        const val TABLE_TIKET = "tiket"
        const val TABLE_PEMBAYARAN = "pembayaran"
        const val TABLE_RIWAYAT_PEMESANAN = "riwayat_pemesanan"
        const val TABLE_BANGKU_DIPESAN = "bangku_dipesan"
        const val TABLE_BANGKU_TERPAKAI = "bangku_terpakai"

        // Create table statements

        private const val CREATE_TABLE_BANGKU_DIPESAN = ("CREATE TABLE $TABLE_BANGKU_DIPESAN (" +
                "id_bangku_dipesan INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_jadwal INTEGER," +
                "id_bangku INTEGER, " +
                "FOREIGN KEY(id_jadwal) REFERENCES $TABLE_JADWAL(id_jadwal)," +
                "FOREIGN KEY(id_bangku) REFERENCES $TABLE_JADWAL(id_bangku))")


        private const val CREATE_TABLE_BANGKU_TERPAKAI = ("CREATE TABLE $TABLE_BANGKU_TERPAKAI (" +
                "id_bangku_terpakai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_jadwal INTEGER," +
                "id_bangku INTEGER, " +
                "FOREIGN KEY(id_jadwal) REFERENCES $TABLE_JADWAL(id_jadwal)," +
                "FOREIGN KEY(id_bangku) REFERENCES $TABLE_BANGKU(id_bangku))")

        private const val CREATE_TABLE_BIOSKOP = ("CREATE TABLE $TABLE_BIOSKOP (" +
                "id_bioskop INTEGER PRIMARY KEY, " +
                "nama_bioskop TEXT, " +
                "alamat TEXT, " +
                "kapasitas INTEGER)")

        private const val CREATE_TABLE_FILM = ("CREATE TABLE $TABLE_FILM (" +
                "id_film INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "judul TEXT, " +
                "genre TEXT, " +
                "durasi INTEGER, " +
                "sinopsis TEXT, " +
                "sutradara TEXT, " +
                "tahun_rilis INTEGER, "+
                "umur_rating TEXT)")

        private const val CREATE_TABLE_JADWAL = ("CREATE TABLE $TABLE_JADWAL (" +
                "id_jadwal INTEGER PRIMARY KEY, " +
                "id_film INTEGER, " +
                "id_bioskop INTEGER, " +
                "waktu_tayang TEXT, " +
                "FOREIGN KEY(id_film) REFERENCES $TABLE_FILM(id_film), " +
                "FOREIGN KEY(id_bioskop) REFERENCES $TABLE_BIOSKOP(id_bioskop))")

        private const val CREATE_TABLE_BANGKU = ("CREATE TABLE $TABLE_BANGKU (" +
                "id_bangku INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomor_bangku TEXT)")

        private const val CREATE_TABLE_PENGGUNA = ("CREATE TABLE $TABLE_PENGGUNA (" +
                "id_pengguna INTEGER PRIMARY KEY, " +
                "nama TEXT, " +
                "email TEXT, " +
                "password TEXT)")

        private const val CREATE_TABLE_TIKET = ("CREATE TABLE $TABLE_TIKET (" +
                "id_tiket INTEGER PRIMARY KEY, " +
                "id_pengguna INTEGER, " +
                "id_jadwal INTEGER, " +
                "id_bangku INTEGER, " +
                "status_pembayaran TEXT, " +
                "FOREIGN KEY(id_pengguna) REFERENCES $TABLE_PENGGUNA(id_pengguna), " +
                "FOREIGN KEY(id_jadwal) REFERENCES $TABLE_JADWAL(id_jadwal), " +
                "FOREIGN KEY(id_bangku) REFERENCES $TABLE_BANGKU(id_bangku))")

        private const val CREATE_TABLE_PEMBAYARAN = ("CREATE TABLE $TABLE_PEMBAYARAN (" +
                "id_pembayaran INTEGER PRIMARY KEY, " +
                "id_tiket INTEGER, " +
                "metode_pembayaran TEXT, " +
                "status_pembayaran TEXT, " +
                "FOREIGN KEY(id_tiket) REFERENCES $TABLE_TIKET(id_tiket))")

        private const val CREATE_TABLE_RIWAYAT_PEMESANAN =
            ("CREATE TABLE $TABLE_RIWAYAT_PEMESANAN (" +
                    "id_riwayat INTEGER PRIMARY KEY, " +
                    "id_pengguna INTEGER, " +
                    "id_tiket INTEGER, " +
                    "waktu_pemesanan DATETIME, " +
                    "FOREIGN KEY(id_pengguna) REFERENCES $TABLE_PENGGUNA(id_pengguna), " +
                    "FOREIGN KEY(id_tiket) REFERENCES $TABLE_TIKET(id_tiket))")
    }
}