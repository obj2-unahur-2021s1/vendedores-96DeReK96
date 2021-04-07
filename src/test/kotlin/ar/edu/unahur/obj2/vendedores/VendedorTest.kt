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
      it("es firme") {
        vendedorFijo.esFirme().shouldBeTrue()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(20000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
  }
  describe("ComercioCorresponal") {
    val santaFe = Provincia(3000000)
    val rafaela = Ciudad(santaFe)
    val viajante = Viajante(listOf(santaFe))
    describe("puedeTrabajarEn") {
      it("una ciuad que tenga sucursal") {
        viajante.puedeTrabajarEn(rafaela).shouldBeTrue()
      }
      it("una ciudad que no tiene sucursal") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
  }

})

// continuar con etapa 2

//class CentroTest : DescribeSpec({
//  val santaFe = Provincia(3000000)
//  val rafaela = Ciudad(santaFe)
//  val vendedorFijo = VendedorFijo(rafaela)
//})
