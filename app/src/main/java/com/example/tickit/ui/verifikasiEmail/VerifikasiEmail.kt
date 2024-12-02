package com.example.tickit.ui.verifikasiEmail

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickit.R

class VerifikasiEmail : Fragment() {

    private lateinit var backButton: ImageButton
    private lateinit var editTexts: List<EditText>
    private lateinit var keypadButtons: List<Button>
    private lateinit var deleteButton: Button
    private lateinit var nextButton: Button
    private lateinit var timerTextView: TextView
    private var currentIndex = 0

    private lateinit var countdownTimer: CountDownTimer
    private var isTimerRunning = false
    private val timerDuration = 300000L // 5 menit dalam milidetik

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_verifikasi_email, container, false)

        // Inisialisasi view
        backButton = view.findViewById(R.id.backButton)
        editTexts = listOf(
            view.findViewById(R.id.editText1),
            view.findViewById(R.id.editText2),
            view.findViewById(R.id.editText3),
            view.findViewById(R.id.editText4),
            view.findViewById(R.id.editText5),
            view.findViewById(R.id.editText6)
        )
        keypadButtons = listOf(
            view.findViewById(R.id.button0),
            view.findViewById(R.id.button1),
            view.findViewById(R.id.button2),
            view.findViewById(R.id.button3),
            view.findViewById(R.id.button4),
            view.findViewById(R.id.button5),
            view.findViewById(R.id.button6),
            view.findViewById(R.id.button7),
            view.findViewById(R.id.button8),
            view.findViewById(R.id.button9)
        )
        deleteButton = view.findViewById(R.id.buttonD)
        nextButton = view.findViewById(R.id.nextButton)
        timerTextView = view.findViewById(R.id.resendCodeText)

        setupListeners()
        startCountdownTimer()
        return view
    }

    private fun setupListeners() {
        // Tombol kembali
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_verifikasiEmailFragment_to_registerFragment)
        }

        // Input keypad
        keypadButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (currentIndex < editTexts.size) {
                    editTexts[currentIndex].setText(index.toString())
                    currentIndex++
                }
            }
        }

        // Tombol hapus
        deleteButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                editTexts[currentIndex].setText("")
            }
        }

        // Tombol selanjutnya
        nextButton.setOnClickListener {
            val code = editTexts.joinToString("") { it.text.toString() }
            if (code.length == 6) {
                verifyCode(code)
            } else {
                Toast.makeText(requireContext(), "Kode belum lengkap", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCountdownTimer() {
        countdownTimer = object : CountDownTimer(timerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerTextView.text = String.format("Kirim ulang dalam %02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                isTimerRunning = false
                sendCodeAgain()
            }
        }.start()
        isTimerRunning = true
    }

    private fun sendCodeAgain() {
        Toast.makeText(requireContext(), "Kode baru telah dikirim ke email Anda", Toast.LENGTH_SHORT).show()
        // Panggil API atau logika backend untuk mengirim ulang kode verifikasi
        startCountdownTimer() // Restart timer setelah mengirim ulang kode
    }

    private fun verifyCode(code: String) {
        // Simulasi verifikasi kode
        if (code == "123456") { // Ganti dengan logika backend yang sesuai
            countdownTimer.cancel() // Hentikan timer jika verifikasi berhasil
            findNavController().navigate(R.id.action_verifikasiEmailFragment_to_konfirmasiPasswordFragment)
        } else {
            Toast.makeText(requireContext(), "Kode salah", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (isTimerRunning) countdownTimer.cancel() // Pastikan timer dihentikan saat fragment dihancurkan
    }
}
