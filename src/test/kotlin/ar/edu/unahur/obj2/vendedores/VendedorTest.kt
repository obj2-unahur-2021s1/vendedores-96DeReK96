package ar.edu.unahur.obj2.vendedores


import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  val certificacionProd = Certificacion(true,8)
  val certificacionNoProducto = Certificacion(false,7)
  val certificacionProd2 = Certificacion(true,15)

  describe("Vendedor fijo") {
    //asignamos una ciudad nueva y su misma provincia (misiones).
    val obera = Ciudad(misiones)
    // creamos un vendedor Fijo que vive en Obera en provincia Misiones
    val vendedorFijo = VendedorFijo(obera)


    vendedorFijo.agregarCertificacion(certificacionProd)
    vendedorFijo.agregarCertificacion(certificacionNoProducto)
    vendedorFijo.agregarCertificacion(certificacionProd2)


    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad que no es su origen") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
      it("es versatil") {
        vendedorFijo.esVersatil().shouldBeTrue()
      }
      it("no versatil") {
        vendedorFijo.quitar(certificacionProd2)
        vendedorFijo.esVersatil().shouldBeFalse()
      }
    }

    describe("es firme") {
      it("el vendedor fijo, es firme") {
        vendedorFijo.esFirme().shouldBeTrue()
      }
      it("el vendedor fijo no es firme") {
        vendedorFijo.quitar(certificacionProd2)
        vendedorFijo.esFirme().shouldBeFalse()
      }
    }
    describe("es influyente") {
      // Siempre queda No Influyente
      it("no es influyente") {
        vendedorFijo.esInfluyente().shouldBeFalse()

      }
    }
  }

  describe("Viajante") {
    val chubut = Provincia(220000)
    val rawson = Ciudad(chubut)
    val cordoba = Provincia(20000000)
    val viajante = Viajante(listOf(misiones, cordoba))


    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(rawson).shouldBeFalse()
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
