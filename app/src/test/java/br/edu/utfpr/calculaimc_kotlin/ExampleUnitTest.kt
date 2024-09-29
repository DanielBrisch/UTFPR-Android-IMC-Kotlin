package br.edu.utfpr.calculaimc_kotlin

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun calculaImcBrazil() {
        val imc = Calculo()
        assertTrue("IMC calculado: ", imc.calculaIMC(80.0, 1.80, "pt") in 24.6 .. 24.7)
    }
}