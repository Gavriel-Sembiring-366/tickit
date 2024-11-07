package com.example.tickit.database

import android.database.sqlite.SQLiteDatabase
import com.example.tickit.database.DBHelper.Companion.TABLE_BANGKU
import com.example.tickit.database.DBHelper.Companion.TABLE_BIOSKOP
import com.example.tickit.database.DBHelper.Companion.TABLE_FILM
import com.example.tickit.database.DBHelper.Companion.TABLE_JADWAL
import com.example.tickit.database.DBHelper.Companion.TABLE_PEMBAYARAN
import com.example.tickit.database.DBHelper.Companion.TABLE_PENGGUNA
import com.example.tickit.database.DBHelper.Companion.TABLE_RIWAYAT_PEMESANAN
import com.example.tickit.database.DBHelper.Companion.TABLE_TIKET

class populatedata {
    fun populateMockData(db: SQLiteDatabase) {
//        db.execSQL("INSERT INTO $TABLE_BIOSKOP (id_bioskop, nama_bioskop, alamat, kapasitas) VALUES (1, 'Cinema XXI', 'Jl. ABC No. 123', 150)")
//        db.execSQL("INSERT INTO $TABLE_BIOSKOP (id_bioskop, nama_bioskop, alamat, kapasitas) VALUES (2, 'CGV Blitz', 'Jl. XYZ No. 456', 200)")
//
//        db.execSQL("INSERT INTO $TABLE_FILM (id_film, judul, genre, durasi, sinopsis, tahun_rilis, umur_rating) VALUES (1, 'The Great Adventure', 'Action', 120, 'An epic adventure film.', 2022, 'PG-13')")
//        db.execSQL("INSERT INTO $TABLE_FILM (id_film, judul, genre, durasi, sinopsis, tahun_rilis, umur_rating) VALUES (2, 'Comedy Night', 'Comedy', 90, 'A hilarious comedy movie.', 2021, 'PG')")
//
//        db.execSQL("INSERT INTO $TABLE_JADWAL (id_jadwal, id_film, id_bioskop, waktu_tayang) VALUES (1, 1, 1, '2024-01-01 10:00:00')")
//        db.execSQL("INSERT INTO $TABLE_JADWAL (id_jadwal, id_film, id_bioskop, waktu_tayang) VALUES (2, 2, 2, '2024-01-01 14:00:00')")
//
//        db.execSQL("INSERT INTO $TABLE_BANGKU (id_bangku, id_bioskop, nomor_bangku) VALUES (1, 1, 'A1')")
//        db.execSQL("INSERT INTO $TABLE_BANGKU (id_bangku, id_bioskop, nomor_bangku) VALUES (2, 1, 'A2')")
//        db.execSQL("INSERT INTO $TABLE_BANGKU (id_bangku, id_bioskop, nomor_bangku) VALUES (3, 2, 'B1')")
//        db.execSQL("INSERT INTO $TABLE_BANGKU (id_bangku, id_bioskop, nomor_bangku) VALUES (4, 2, 'B2')")
//
//        db.execSQL("INSERT INTO $TABLE_PENGGUNA (id_pengguna, nama, email, password) VALUES (1, 'Alice', 'alice@example.com', 'password123')")
//        db.execSQL("INSERT INTO $TABLE_PENGGUNA (id_pengguna, nama, email, password) VALUES (2, 'Bob', 'bob@example.com', 'password456')")
//
//        db.execSQL("INSERT INTO $TABLE_TIKET (id_tiket, id_pengguna, id_jadwal, id_bangku, status_pembayaran) VALUES (1, 1, 1, 1, 'Paid')")
//        db.execSQL("INSERT INTO $TABLE_TIKET (id_tiket, id_pengguna, id_jadwal, id_bangku, status_pembayaran) VALUES (2, 2, 2, 3, 'Unpaid')")
//
//        db.execSQL("INSERT INTO $TABLE_PEMBAYARAN (id_pembayaran, id_tiket, metode_pembayaran, status_pembayaran) VALUES (1, 1, 'Credit Card', 'Paid')")
//        db.execSQL("INSERT INTO $TABLE_PEMBAYARAN (id_pembayaran, id_tiket, metode_pembayaran, status_pembayaran) VALUES (2, 2, 'Cash', 'Pending')")
//
//        db.execSQL("INSERT INTO $TABLE_RIWAYAT_PEMESANAN (id_riwayat, id_pengguna, id_tiket, waktu_pemesanan) VALUES (1, 1, 1, '2023-12-25 09:00:00')")
//        db.execSQL("INSERT INTO $TABLE_RIWAYAT_PEMESANAN (id_riwayat, id_pengguna, id_tiket, waktu_pemesanan) VALUES (2, 2, 2, '2023-12-25 10:00:00')")
    }

}