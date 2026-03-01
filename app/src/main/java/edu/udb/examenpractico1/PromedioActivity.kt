package edu.udb.examenpractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class PromedioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etNota1 = findViewById<EditText>(R.id.etNota1)
        val etNota2 = findViewById<EditText>(R.id.etNota2)
        val etNota3 = findViewById<EditText>(R.id.etNota3)
        val etNota4 = findViewById<EditText>(R.id.etNota4)
        val etNota5 = findViewById<EditText>(R.id.etNota5)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        btnCalcular.setOnClickListener {

            val nombre = etNombre.text.toString()
            val n1Str = etNota1.text.toString()
            val n2Str = etNota2.text.toString()
            val n3Str = etNota3.text.toString()
            val n4Str = etNota4.text.toString()
            val n5Str = etNota5.text.toString()

            // VALIDACIÓN
            if (nombre.isEmpty() || n1Str.isEmpty() || n2Str.isEmpty() || n3Str.isEmpty() || n4Str.isEmpty() || n5Str.isEmpty()) {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val n1 = n1Str.toDouble()
            val n2 = n2Str.toDouble()
            val n3 = n3Str.toDouble()
            val n4 = n4Str.toDouble()
            val n5 = n5Str.toDouble()

            // VALIDACIÓN
            if (n1 < 0 || n1 > 10 || n2 < 0 || n2 > 10 || n3 < 0 || n3 > 10 || n4 < 0 || n4 > 10 || n5 < 0 || n5 > 10) {
                Toast.makeText(this, "Las notas deben estar entre 0 y 10", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val promedioFinal = calcularPromedio(n1, n2, n3, n4, n5)

            val formato = DecimalFormat("#.##")
            val promedioTexto = formato.format(promedioFinal)

            var estado = "REPROBADO"
            if (promedioFinal >= 6.0) {
                estado = "APROBADO"
            }

            tvResultado.text = "Estudiante: $nombre\nPromedio: $promedioTexto\nEstado: $estado"
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun calcularPromedio(n1: Double, n2: Double, n3: Double, n4: Double, n5: Double): Double {
        val calc1 = n1 * 0.15
        val calc2 = n2 * 0.15
        val calc3 = n3 * 0.20
        val calc4 = n4 * 0.20
        val calc5 = n5 * 0.30

        return calc1 + calc2 + calc3 + calc4 + calc5
    }
}