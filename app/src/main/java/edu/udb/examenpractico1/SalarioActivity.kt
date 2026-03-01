package edu.udb.examenpractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class SalarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        val etNombreEmpleado = findViewById<EditText>(R.id.etNombreEmpleado)
        val etSalario = findViewById<EditText>(R.id.etSalario)
        val btnCalcularSalario = findViewById<Button>(R.id.btnCalcularSalario)
        val tvResultadoSalario = findViewById<TextView>(R.id.tvResultadoSalario)
        val btnRegresarSalario = findViewById<Button>(R.id.btnRegresarSalario)

        btnCalcularSalario.setOnClickListener {

            val nombre = etNombreEmpleado.text.toString()
            val salarioTexto = etSalario.text.toString()

            // VALIDACIÓN
            if (nombre.isEmpty() || salarioTexto.isEmpty()) {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val salarioBase = salarioTexto.toDouble()

            // VALIDACIÓN
            if (salarioBase <= 0) {
                Toast.makeText(this, "El salario debe ser mayor a 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cálculos de AFP e ISSS
            val descuentoAFP = salarioBase * 0.0725
            val descuentoISSS = salarioBase * 0.03

            // Renta
            val salarioSujetoRenta = salarioBase - descuentoAFP - descuentoISSS

            val descuentoRenta = calcularRenta(salarioSujetoRenta)

            val salarioNeto = salarioBase - descuentoAFP - descuentoISSS - descuentoRenta

            val formato = DecimalFormat("#.##")

            tvResultadoSalario.text = """
                Empleado: $nombre
                Salario Base: $${formato.format(salarioBase)}
                
                Descuentos:
                - AFP (7.25%): $${formato.format(descuentoAFP)}
                - ISSS (3%): $${formato.format(descuentoISSS)}
                - Renta: $${formato.format(descuentoRenta)}
                
                SALARIO NETO: $${formato.format(salarioNeto)}
            """.trimIndent()
        }

        btnRegresarSalario.setOnClickListener {
            finish()
        }
    }

    private fun calcularRenta(monto: Double): Double {
        var retencion = 0.0

        // Tramo I
        if (monto >= 0.01 && monto <= 472.00) {
            retencion = 0.0
        }
        // Tramo II
        else if (monto >= 472.01 && monto <= 895.24) {
            val exceso = monto - 472.00
            retencion = (exceso * 0.10) + 17.67
        }
        // Tramo III
        else if (monto >= 895.25 && monto <= 2038.10) {
            val exceso = monto - 895.24
            retencion = (exceso * 0.20) + 60.00
        }
        // Tramo IV
        else if (monto >= 2038.11) {
            val exceso = monto - 2038.10
            retencion = (exceso * 0.30) + 288.57
        }

        return retencion
    }
}