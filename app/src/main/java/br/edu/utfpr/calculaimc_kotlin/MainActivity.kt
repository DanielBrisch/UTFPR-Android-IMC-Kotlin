package br.edu.utfpr.calculaimc_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var valorPeso : EditText
    private lateinit var valorAltura : EditText
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpar : Button
    private lateinit var valorImc : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciaComponentes()

        btnCalcular.setOnClickListener {
            calcular()
        }

        btnLimpar.setOnClickListener {
            limpar()
        }
    }

    private fun calcular() {
        if (!verificaCamposVazios()) {
            return;
        }

        val imc = Calculo()
        val result = imc.calculaIMC(valorPeso.text.toString().toDouble(), valorAltura.text.toString().toDouble(), "en")

        valorImc.text = result.toString()
    }

    private fun verificaCamposVazios() : Boolean {
        if (valorPeso.text.toString().isEmpty()) {
            valorPeso.error = getString(R.string.campo_vazio)
            valorPeso.requestFocus()
            return false
        }

        if (valorAltura.text.toString().isEmpty()) {
            valorAltura.error = getString(R.string.campo_vazio)
            valorAltura.requestFocus()
            return false
        }
        return true
    }

    private fun limpar() {
        valorPeso.setText("")
        valorAltura.setText("")
        valorImc.text = getString(R.string.hint_imc)
        valorPeso.requestFocus()
        Toast.makeText(this, getString(R.string.toas_limpar), Toast.LENGTH_LONG).show()
    }

    private fun iniciaComponentes() {
        valorPeso = findViewById(R.id.valorPeso)
        valorAltura = findViewById(R.id.valorAltura)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpar = findViewById(R.id.btnLimpar)
        valorImc = findViewById(R.id.valorImc)
    }
}

class Calculo {

    fun calculaIMC(peso: Double, altura: Double, locale: String): Double {
        var result = 0.0

        if (locale == "en")  {
            result = peso / altura.pow(2)
        } else if (locale == "pt") {
            result = 703 * (peso / altura.pow(2))
        }

        val numberFormat: NumberFormat = when (locale) {
            "en" -> NumberFormat.getNumberInstance(Locale.US)
            "pt" -> NumberFormat.getNumberInstance(Locale("pt", "BR"))
            else -> NumberFormat.getNumberInstance(Locale.getDefault())
        }

        return numberFormat.format(result).toDouble()
    }
}