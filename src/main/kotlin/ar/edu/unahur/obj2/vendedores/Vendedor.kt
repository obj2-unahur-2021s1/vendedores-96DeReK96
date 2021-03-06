package ar.edu.unahur.obj2.vendedores
//al crear esta clase, si queremos agragar val, tenemos que definir si son booleano o un numero entero
class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

abstract class Vendedor {
  // Acá es obligatorio poner el tipo de la lista, porque como está vacía no lo puede inferir.
  // Además, a una MutableList se le pueden agregar elementos
  val certificaciones = mutableListOf<Certificacion>()

  // Definimos el método abstracto.
  // Como no vamos a implementarlo acá, es necesario explicitar qué devuelve.
  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean
  abstract fun esInfluyente(): Boolean



  // En las funciones declaradas con = no es necesario explicitar el tipo
  fun esVersatil() =
    certificaciones.size >= 3
            && this.certificacionesDeProducto() >= 1
            && this.otrasCertificaciones() >= 1

  // Si el tipo no está declarado y la función no devuelve nada, se asume Unit (es decir, vacío)
// posible error en !c.esDeProducto()
  fun esGenerico(): Boolean  =
    certificaciones.any { c -> !c.esDeProducto }

// realiza una accion de agregar/quitar un objeto a la lista
  fun agregarCertificacion(certificacion: Certificacion) {
    certificaciones.add(certificacion)
  }
  fun quitar(certificacion: Certificacion) {
    certificaciones.remove(certificacion)
  }
// devuelve un booleano
  fun esFirme() = this.puntajeCertificaciones() >= 30

  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }
  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }

  fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje }
}

// En los parámetros, es obligatorio poner el tipo
class VendedorFijo(val ciudadOrigen: Ciudad) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudad == ciudadOrigen
  }

  override fun esInfluyente(): Boolean = false
}



// A este tipo de List no se le pueden agregar elementos una vez definida
class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {

  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return provinciasHabilitadas.contains(ciudad.provincia)
  }

  override fun esInfluyente(): Boolean = provinciasHabilitadas.sumBy {p -> p.poblacion} >= 10000000
}

class ComercioCorresponsal(val ciudades: List<Ciudad>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudades.contains(ciudad)
  }
  fun provincias() = ciudades.map { c -> c.provincia }.toSet()

  override fun esInfluyente(): Boolean = (ciudades.size > 4) or (this.provincias().size >2)

}

class CentroDeDistrib(val ciudadActual: Ciudad) {
  val vendedoresQueTrabajan = mutableListOf<Vendedor>()

  var vendedoresFirmes = vendedoresQueTrabajan.filter { v -> v.esFirme() }

  fun agregarVendedor(vendedor: Vendedor) {
    if (vendedoresQueTrabajan.contains(vendedor)) {
      throw Exception ("ya esta agregado")
    }
    vendedoresQueTrabajan.add(vendedor)
  }

  fun vendedorEstrella() = vendedoresQueTrabajan.maxBy {v -> v.puntajeCertificaciones() }

  fun puedeCubrir(city: Ciudad): Boolean =
    vendedoresQueTrabajan.any {v -> v.puedeTrabajarEn(city)}
  fun vendedoresGenericos() =
    vendedoresQueTrabajan.filter { v -> v.esGenerico() }
  fun esRobusto(): Boolean {
    val vendedoresFirmes = vendedoresQueTrabajan.count() { v -> v.esFirme() }
    return vendedoresFirmes > 2
  }
}
