package edu.udb.examenpractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        val etNum1 = findViewById<EditText>(R.id.etNumero1)
        val etNum2 = findViewById<EditText>(R.id.etNumero2)
        val tvResultado = findViewById<TextView>(R.id.tvResultadoCalc)

        // Botones de operaciones
        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnMultiplicar = findViewById<Button>(R.id.btnMultiplicar)
        val btnDividir = findViewById<Button>(R.id.btnDividir)
        val btnExponente = findViewById<Button>(R.id.btnExponente)
        val btnRaiz = findViewById<Button>(R.id.btnRaiz)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarCalc)

        // SUMA
        btnSuma.setOnClickListener {
            if (validarCampos(etNum1, etNum2)) {
                val n1 = etNum1.text.toString().toDouble()
                val n2 = etNum2.text.toString().toDouble()
                tvResultado.text = "Resultado: ${sumar(n1, n2)}"
            }
        }

        // RESTA
        btnResta.setOnClickListener {
            if (validarCampos(etNum1, etNum2)) {
                val n1 = etNum1.text.toString().toDouble()
                val n2 = etNum2.text.toString().toDouble()
                tvResultado.text = "Resultado: ${restar(n1, n2)}"
            }
        }

        // MULTIPLICACIÓN
        btnMultiplicar.setOnClickListener {
            if (validarCampos(etNum1, etNum2)) {
                val n1 = etNum1.text.toString().toDouble()
                val n2 = etNum2.text.toString().toDouble()
                tvResultado.text = "Resultado: ${multiplicar(n1, n2)}"
            }
        }

        // DIVISIÓN
        btnDividir.setOnClickListener {
            if (validarCampos(etNum1, etNum2)) {
                val n1 = etNum1.text.toString().toDouble()
                val n2 = etNum2.text.toString().toDouble()

                if (n2 == 0.0) {
                    Toast.makeText(this, "Error: No se puede dividir entre cero", Toast.LENGTH_LONG).show()
                } else {
                    tvResultado.text = "Resultado: ${dividir(n1, n2)}"
                }
            }
        }

        // EXPONENTE
        btnExponente.setOnClickListener {
            if (validarCampos(etNum1, etNum2)) {
                val n1 = etNum1.text.toString().toDouble()
                val n2 = etNum2.text.toString().toDouble()
                tvResultado.text = "Resultado: ${exponente(n1, n2)}"
            }
        }

        // RAÍZ CUADRADA
        btnRaiz.setOnClickListener {
            val n1Str = etNum1.text.toString()
            if (n1Str.isEmpty()) {
                etNum1.error = "Ingrese un número aquí para la raíz"
            } else {
                val n1 = n1Str.toDouble()
                if (n1 < 0) {
                    Toast.makeText(this, "No se puede calcular raíz de negativos", Toast.LENGTH_SHORT).show()
                } else {
                    tvResultado.text = "Resultado: ${raizCuadrada(n1)}"
                }
            }
        }

        // REGRESAR AL MENÚ
        btnRegresar.setOnClickListener { finish() }
    }

    private fun validarCampos(c1: EditText, c2: EditText): Boolean {
        if (c1.text.toString().isEmpty() || c2.text.toString().isEmpty()) {
            Toast.makeText(this, "Por favor llene ambos números", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun sumar(n1: Double, n2: Double): Double = n1 + n2
    private fun restar(n1: Double, n2: Double): Double = n1 - n2
    private fun multiplicar(n1: Double, n2: Double): Double = n1 * n2
    private fun dividir(n1: Double, n2: Double): Double = n1 / n2
    private fun exponente(base: Double, exponente: Double): Double = base.pow(exponente)
    private fun raizCuadrada(n: Double): Double = sqrt(n)
}