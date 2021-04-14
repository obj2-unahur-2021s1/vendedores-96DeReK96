package ar.edu.unahur.obj2.vendedores


import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  val certificacionProd = Certificacion(true, 11)
  val certificacionProd2 = Certificacion(true, 9)
  val certificacionProd3 = Certificacion(true, 2)

  val certificacionNoProducto = Certificacion(false, 12)
  val certificacionNoProducto2 = Certificacion(false, 1)
  val certificacionNoProducto3 = Certificacion(false, 3)

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
    }

    describe("Versatil") {
//    ya tiene 3 certificaciones, 2 de productos y 1 de noProductos

//    val certificacionProd = Certificacion(true,15)
//    val certificacionProd2 = Certificacion(true,2)
//    val certificacionNoProducto = Certificacion(false,15)


//    Este devuelve true, ya que cumple las 3 condiciones por que..

//    1]- Tiene 3 certificaciones y la condicion es tener 3 o mayor que eso.
//    2]- De los 3 certificaciones, 2 son de tipo producto y la 2da condicion es tener al menos 1 de tipo producto
//    3]- De los 3 certificaciones, 1 es de tipo NoProducto y la 3ra condicion es tener al menos 1 de tipo NoProducto

      it("Es Versatil") {
        vendedorFijo.esVersatil().shouldBeTrue()
      }
//    Se Agrega otro certificado para cumplir la condicion de ser mayor a 3 certificados
      it("Es Versatil otra alternativa") {
        vendedorFijo.agregarCertificacion(certificacionNoProducto2)
        vendedorFijo.esVersatil().shouldBeTrue()
      }
//    Tiene 3 certificaciones 2 de tipo Producto y 1 de noProducto. le quitamos 1 de Produto y quedaran 2 certificados.
//    eso hace que no cumpla la condicion de tener 3 o mayor de certificados. pero los demas condiciones cumple.
//    devuelve False.
      it("No versatil") {
        vendedorFijo.quitar(certificacionProd2)
        vendedorFijo.esVersatil().shouldBeFalse()
      }
//    Le agregamos un certificadoProducto y quitamos el noProducto y
//    En este ya no cumple la condicion de tener al menos 1 certificado noProducto, solo quedan 3 certificados con productos en total.
//    devuelve False
      it("No versatil alternativa 2") {
        vendedorFijo.agregarCertificacion(certificacionProd3)
        vendedorFijo.quitar(certificacionNoProducto)
        vendedorFijo.esVersatil().shouldBeFalse()
      }
//    Le agregamos 2 certificados tipo noProducto y quitamos 2 de tipo Producto, quedan 3 certificados no producto pero.
//    pero no cumple la condicion de tener al menos uno de tipo Producto.
//    devuelve False
      it("No versatil alternativa 3") {
        vendedorFijo.agregarCertificacion(certificacionNoProducto2)
        vendedorFijo.agregarCertificacion((certificacionNoProducto3))
        vendedorFijo.quitar(certificacionProd)
        vendedorFijo.quitar(certificacionProd2)
        vendedorFijo.esVersatil().shouldBeFalse()
      }
//    Le quitamos todos los certificados que tiene y ya no cumple ninguna condicion.
//    devuelve False
      it("No versatil alternativa 4") {
        vendedorFijo.quitar(certificacionProd)
        vendedorFijo.quitar(certificacionProd2)
        vendedorFijo.quitar(certificacionNoProducto)
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
    val neuquen = Provincia(4000000)
    val barrancas = Ciudad(neuquen)

    val buenosAires = Provincia(8000000)
    val liniers = Ciudad(buenosAires)

    val chubut = Provincia(3000000)
    val rawson = Ciudad(chubut)

    val rioNegro = Provincia(5000000)
    val catriel = Ciudad(rioNegro)


    val viajanteInfluyente = Viajante(listOf(neuquen, buenosAires))
    val viajanteNoInfluyente = Viajante(listOf(chubut, rioNegro))



    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajanteInfluyente.puedeTrabajarEn(liniers).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajanteInfluyente.puedeTrabajarEn(rawson).shouldBeFalse()
      }
    describe("Influyente") {
      it("es influyente") {
        viajanteInfluyente.esInfluyente().shouldBeTrue()
      }
      it("no Influyente") {
        viajanteNoInfluyente.esInfluyente().shouldBeFalse()
      }
    }
    }
    describe("ComercioCorresponal") {
      val santaFe = Provincia(3000000)
      val cordoba = Provincia(2000000)
      val entreRios = Provincia(1000000)

      val rosario = Ciudad(santaFe)
      val rafaela = Ciudad(santaFe)
      val amstrong = Ciudad(santaFe)
      val sanFrancisco = Ciudad(cordoba)
      val diamante = Ciudad(entreRios)
      val rioCuarto = Ciudad(cordoba)
      val cosquin = Ciudad(cordoba)



      val vendedorComercianteInfluyente = ComercioCorresponsal(listOf(rosario, rafaela, sanFrancisco, diamante))

      val vendedorComercianteInfluyente2 = ComercioCorresponsal(listOf(rosario, rafaela, rioCuarto, cosquin, sanFrancisco))

      val vendedorComercianteNoInfluyente = ComercioCorresponsal(listOf(rosario, rafaela, amstrong, diamante))

      describe("puedeTrabajarEn") {
        it("una ciudad que tenga sucursal") {
          vendedorComercianteInfluyente.puedeTrabajarEn(rosario).shouldBeTrue()
        }
        it("una ciudad que no tiene sucursal") {
          vendedorComercianteInfluyente.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
        }
      describe("Influyente") {
//      Cumple solo la condicion de 3 provincias
        it("es Influyente") {
          vendedorComercianteInfluyente.esInfluyente().shouldBeTrue()
        }
//      Cumple solo la condicion de 5 ciudades
        it("esInfluyente") {
          vendedorComercianteInfluyente2.esInfluyente().shouldBeTrue()
        }
//      No cumple ninguno de las 2 condiciones de los ultimos test
        it("NoEsInfluyente") {
          vendedorComercianteNoInfluyente.esInfluyente().shouldBeFalse()
        }
      }
      }
    }
  }
})



class centroTest : DescribeSpec ({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  val certificacionProd = Certificacion(true, 11)
  val certificacionProd2 = Certificacion(true, 9)
  val certificacionProd3 = Certificacion(true, 2)

  val certificacionNoProducto = Certificacion(false, 12)
  val certificacionNoProducto2 = Certificacion(false, 1)
  val certificacionNoProducto3 = Certificacion(false, 3)

  describe("centro test") {
    val obera = Ciudad(misiones)
    val tierraDelFuego = Provincia(200000)
    val ushuaia = Ciudad(tierraDelFuego)
    // creamos un vendedor Fijo que vive en Obera en provincia Misiones
    val vendedorFijo = VendedorFijo(obera)
    val vendedorFijo2 = VendedorFijo(ushuaia)
    vendedorFijo.agregarCertificacion(certificacionProd)
    vendedorFijo.agregarCertificacion(certificacionNoProducto)
    vendedorFijo.agregarCertificacion(certificacionProd2)

    vendedorFijo2.agregarCertificacion(certificacionProd3)
    vendedorFijo2.agregarCertificacion(certificacionNoProducto2)
    vendedorFijo2.agregarCertificacion(certificacionNoProducto3)

//////////////////////////////////////////////////////////////////////

    val neuquen = Provincia(4000000)
    val barrancas = Ciudad(neuquen)

    val buenosAires = Provincia(8000000)
    val liniers = Ciudad(buenosAires)

    val chubut = Provincia(3000000)
    val rawson = Ciudad(chubut)

    val rioNegro = Provincia(5000000)
    val catriel = Ciudad(rioNegro)


    val viajanteInfluyente = Viajante(listOf(neuquen, buenosAires))
    val viajanteNoInfluyente = Viajante(listOf(chubut, rioNegro))

//////////////////////////////////////////////////////////////////////

    val santaFe = Provincia(3000000)
    val cordoba = Provincia(2000000)
    val entreRios = Provincia(1000000)

    val rosario = Ciudad(santaFe)
    val rafaela = Ciudad(santaFe)
    val amstrong = Ciudad(santaFe)
    val sanFrancisco = Ciudad(cordoba)
    val diamante = Ciudad(entreRios)
    val rioCuarto = Ciudad(cordoba)
    val cosquin = Ciudad(cordoba)



    val vendedorComercianteInfluyente = ComercioCorresponsal(listOf(rosario, rafaela, sanFrancisco, diamante))

    val vendedorComercianteInfluyente2 = ComercioCorresponsal(listOf(rosario, rafaela, rioCuarto, cosquin, sanFrancisco))

    val vendedorComercianteNoInfluyente = ComercioCorresponsal(listOf(rosario, rafaela, amstrong, diamante))

    val central1 = CentroDeDistrib(ushuaia)

    describe("vendedor estrella") {
//    se agregan 2 tipos de vendedores al central1, el vendedor estrella es vendedor fijo.
      it("es estrella") {
        central1.agregarVendedor(vendedorFijo)
        central1.agregarVendedor(vendedorFijo2)
        central1.vendedorEstrella().shouldBe(vendedorFijo)
      }
    }
  }
})