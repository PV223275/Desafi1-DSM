package edu.udb.examenpractico1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnEjercicio1).setOnClickListener {
            startActivity(Intent(this, PromedioActivity::class.java))
        }
        findViewById<Button>(R.id.btnEjercicio2).setOnClickListener {
            startActivity(Intent(this, SalarioActivity::class.java))
        }
        findViewById<Button>(R.id.btnEjercicio3).setOnClickListener {
            startActivity(Intent(this, CalculadoraActivity::class.java))
        }
    }
}