package dao



import output.Consola


interface ICTFSDAO {

    fun getAllLibros(consola: Consola):MutableList<String>?

}