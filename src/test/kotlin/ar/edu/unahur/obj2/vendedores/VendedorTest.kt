package ar.edu.unahur.obj2.vendedores


import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)
    vendedorFijo.agregarCertificacion(Certificacion(esDeProducto = true, puntaje = 30))
    vendedorFijo.agregarCertificacion(Certificacion(esDeProducto = false, puntaje = 50))
    vendedorFijo.agregarCertificacion(Certificacion(esDeProducto = true, puntaje = 15))


    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
      it("es versatil") {
        vendedorFijo.esVersatil().shouldBeTrue()
      }
    }

    describe("es firme") {
      it("el vendedor fijo, es firme") {
        vendedorFijo.esFirme().shouldBeTrue()
      }
    }
    describe("es influyente") {
      it("no es influyente") {
        vendedorFijo.esInfluyente().shouldBeFalse()

      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(20000000)
    val viajante = Viajante(listOf(misiones, cordoba))


    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("es influyente") {
        viajante.esInfluyente().shouldBeTrue()
      }
    }
  }
  describe("ComercioCorresponal") {
    val santaFe = Provincia(3000000)
    val cordoba = Provincia(2000000)
    val entreRios = Provincia(1000000)

    val rosario = Ciudad(santaFe)
    val rafaela = Ciudad(santaFe)
    val sanFrancisco = Ciudad(cordoba)
    val diamante = Ciudad(entreRios)

    val vendedorComerciante = ComercioCorresponsal(listOf(rosario, rafaela, sanFrancisco, diamante))
    describe("puedeTrabajarEn") {
      it("una ciuad que tenga sucursal") {
        vendedorComerciante.puedeTrabajarEn(rosario).shouldBeTrue()
      }
      it("una ciudad que no tiene sucursal") {
        vendedorComerciante.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
      // cumple la condicion de 3 provincias
      it("es influyente") {
        vendedorComerciante.esInfluyente().shouldBeTrue()
      }
    }
  }
  describe("centroDeDistribucion") {

  }

})

// continuar con etapa 2

//class CentroTest : DescribeSpec({
//  val santaFe = Provincia(3000000)
//  val rafaela = Ciudad(santaFe)
//  val vendedorFijo = VendedorFijo(rafaela)
//})
