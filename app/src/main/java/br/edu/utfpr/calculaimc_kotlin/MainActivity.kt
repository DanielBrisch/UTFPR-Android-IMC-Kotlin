package br.edu.utfpr.calculaimc_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        if (valorPeso.text.toString().isEmpty()) {
            valorPeso.error = "Campo deve ser preenchido"
            valorPeso.requestFocus()
            return
        }

        if (valorAltura.text.toString().isEmpty()) {
            valorAltura.error = "Campo deve ser preenchido"
            valorAltura.requestFocus()
            return
        }

        val peso = valorPeso.text.toString().toDouble()
        val altura = valorAltura.text.toString().toDouble()
        val imc = peso / altura.pow(2)
        valorImc.text = "%.2f".format(imc)
    }

    private fun limpar() {
        valorPeso.setText("")
        valorAltura.setText("")
        valorImc.setText("0.0")
        valorPeso.requestFocus()
        Toast.makeText(this, "Tela reiniciada", Toast.LENGTH_LONG).show()
    }

    private fun iniciaComponentes() {
        valorPeso = findViewById(R.id.valorPeso)
        valorAltura = findViewById(R.id.valorAltura)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpar = findViewById(R.id.btnLimpar)
        valorImc = findViewById(R.id.valorImc)
    }
}