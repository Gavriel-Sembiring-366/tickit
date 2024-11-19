package com.example.tickit.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.tickit.R
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

        db.execSQL("INSERT INTO $TABLE_FILM (judul, genre, durasi, sinopsis, sutradara, tahun_rilis, umur_rating) VALUES ('Smile 2', 'Horror', 110, 'Saat akan memulai tur dunia baru, sensasi pop global Skye Riley mulai mengalami kejadian yang semakin mengerikan dan tidak dapat dijelaskan. Terbebani oleh kengerian yang meningkat dan tekanan ketenaran, Skye terpaksa menghadapi masa lalunya yang kelam untuk mendapatkan kembali kendali atas hidupnya sebelum menjadi tidak terkendali.', 'Parker Finn', 2024, 'R')")

        db.execSQL("INSERT INTO $TABLE_FILM (judul, genre, durasi, sinopsis, sutradara, tahun_rilis, umur_rating) VALUES ('The Substance', 'Sci-Fi', 100, 'Seorang wanita pesohor yang tengah meredup neat mencoba sebuah obat dari pasar gelap, suatu zat pereplikasi sel tubuh yang dapat mewujudkan versi lebih muda dan lebih baik dirinya untuk sementara.', 'Coralie Fargeat', 2024, 'PG-13')")

        db.execSQL("INSERT INTO $TABLE_FILM (judul, genre, durasi, sinopsis, sutradara, tahun_rilis, umur_rating) VALUES ('Tebusan Dosa', 'Drama', 125, 'Wening, seorang ibu mengalami kejadian tragis ketika Nirmala, ananya yang berusia 11 tahun, hilang dalam kecelakaan motor di sebuah jembatan.\n" +
                "Kecelakaan itu juga merenggut nyawa Uti Yah, ibunda Wening. Wening merasa sangat berdosa karena membuat ibunya meninggal dan anakya hanyut di sungai, tapi dia percaya Nirmala masi hidup. Tirta, seorang perempuan kreator podcast misteri, berminat memviralkan tragisnya kehidupan Wening, namun bantuannya malah membuat Tirta menyibak rahasia gelap masa lalu Wening yang mengakibatkan hilangnya Nirmala.\n" +
                "Dengan segala upaya dan penuh harapan, Wening mencari Nirmala, termasuk meminta bantuan Tetsuya, peneliti dari Jepang. Wening juga meminta bantuan Mbah Gowa, seorang dukun misterius. Namun, di tengah pencarian, Wening selalu didatangi oleh hantu Uti Yah. Akankah Wening bersatu lagi dengan Nirmala?', 'Yosep Anggi Noen', 2024, 'PG-13')")

        db.execSQL("INSERT INTO $TABLE_FILM (judul, genre, durasi, sinopsis, sutradara, tahun_rilis, umur_rating) VALUES ('Taklee Genesis', 'Sci-Fi', 140, 'Stella, seorang ibu tunggal, kembali ke kampung halamannya bersama putrinya untuk merawat ibunya yang sakit. Terhantui ole hilangnya ayahnya yang misterius 30 tahun yang lalu, dia menemukan bahwa pemimpin komunitas dan putranya tidak tampak menua sedikit pun. Suatu malam, ayahnya menghubunginya melalui radio tua, mengungkapkan bahwa hanya 30 menit yang telah berlalu baginya. Untuk membawanya kembali, Stella harus melakukan perjalanan waktu dan mengaktifkan Taklee Genesis, memulai petualangan yang berlangsung selama seribu tahun.', 'Chukiat Sakweerakul', 2024, 'PG-13')")

        db.execSQL("INSERT INTO $TABLE_FILM (judul, genre, durasi, sinopsis, sutradara, tahun_rilis, umur_rating) VALUES ('Petak Umpet', 'Horror', 95, 'Rahman (Randy Martin) diminta oleh sang Ibu untuk menemani Sari (Alesha Fadhillah Kurniawan) adikva bermain bersama teman sebayanya.\n" +
                "Rahman yang merasa keberatan menyuruh Sari bermain petak umpet, sementara Rahman menunggui sambil bermain game online dari ponser. Masalah dimulai ketika Sari tak kunjung ditemukan. Teman-temannya bilang Sari bersembunyi di dalam rumah tua yang sudah lama tak dihuni. Konon katanya, Sari diculik oleh urban legend yang biasa menculik anak-anak, Wewe Gombel. Rahman yang merasa bersalah kemudian berniat memasuki rumah tua tempat Sari terakhir terlihat. la ditemani oleh dua sahabatnya, Rinto (Adam Farrel) dan Shila (Saskia Chadwick). Rumah tua tersebut rupanya memang sudah lama dikenal angker. Rahman dan kawan-kawan yang datang untuk menyelamatkan Sari sekarang malah menjadi orang yang perlu diselamatkan.\n', 'Rizal Mantovani', 2024, 'PG')")

        db.execSQL("INSERT INTO $TABLE_FILM (judul, genre, durasi, sinopsis, sutradara, tahun_rilis, umur_rating) VALUES ('Heretic', 'Thriller', 105, 'Dua misionaris muda dipaksa untuk membuktikan iman mereka ketika mereka mengetuk pintu yang salah dan disambut oleh Tuan\n" +
                "Reed yang jahat (Hugh Grant), terjebak dalam permainan kucing-dan-tikus yang mematikan.\n', 'Scott Beck, Bryan Woods', 2024, 'R')")
        db.execSQL("INSERT INTO $TABLE_BIOSKOP (nama_bioskop, alamat, kapasitas) VALUES ('Bioskop XXI', 'Jl. Sudirman No.45', 150)")
        db.execSQL("INSERT INTO $TABLE_BIOSKOP (nama_bioskop, alamat, kapasitas) VALUES ('Cinema 21', 'Jl. Thamrin No.21', 200)")
        db.execSQL("INSERT INTO $TABLE_BIOSKOP (nama_bioskop, alamat, kapasitas) VALUES ('CGV Blitz', 'Jl. Gatot Subroto No.7', 180)")
        db.execSQL("INSERT INTO $TABLE_BIOSKOP (nama_bioskop, alamat, kapasitas) VALUES ('IMAX Theatre', 'Jl. Merdeka No.10', 250)")

        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 01:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 02:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 03:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 04:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 05:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 06:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 07:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 08:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 09:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 10:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 11:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 12:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 13:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 14:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 15:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 16:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 17:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 18:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 19:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 20:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 21:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 22:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 23:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 24:00:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 01:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 02:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 03:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 04:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 05:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 06:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 07:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 08:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 09:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 10:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 11:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 12:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 13:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 14:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 15:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 16:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 17:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 18:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 19:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 20:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 21:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 22:30:00')")
        db.execSQL("INSERT INTO $TABLE_JADWAL (id_film, id_bioskop, waktu_tayang) VALUES (1, 1, '2024-11-21 23:30:00')")


    }

    fun populateImage(context: Context){
        MediaStoreHelper(context).addImageToMediaStore(R.drawable.smile_2_card, "smile_2_card.jpeg")
        MediaStoreHelper(context).addImageToMediaStore(R.drawable.smile_2_carousel, "smile_2_carousel.jpeg")

        MediaStoreHelper(context).addImageToMediaStore(R.drawable.tebusan_dosa_card, "tebusan_dosa_card.jpeg")
        MediaStoreHelper(context).addImageToMediaStore(R.drawable.tebusan_dosa_carousel, "tebusan_dosa_carousel.jpeg")
    }

}