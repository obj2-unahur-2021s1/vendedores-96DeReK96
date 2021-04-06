package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion {
    val ciudad = mutableListOf<Ciudad>()
    val vendedores = mutableListOf<Vendedor>()
    var vendedoresFirmes = vendedores.filter { v -> v.esFirme() }
    fun agregarVendedor(vendedor: Vendedor) {
        if (vendedores.contains(vendedor)) {
            throw Exception ("ya esta agregado")
        } else {
            vendedores.add(vendedor)
        }
    }
    fun vendedorEstrella() = vendedores.maxBy {v -> v.puntajeCertificaciones() }

    fun puedeCubrir(city: Ciudad): Boolean =
        vendedores.any {v -> v.puedeTrabajarEn(city)}
    fun vendedoresGenericos() =
        vendedores.filter { v -> v.esGenerico() }
    fun esRobusto(): Boolean {
        val vendedoresFirmes = vendedores.count() { v -> v.esFirme() }
        return vendedoresFirmes > 2
    }
}