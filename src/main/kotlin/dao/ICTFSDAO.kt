package dao



import output.IOutputiinfo


interface ICTFSDAO {

    fun getAllLibros(consola: IOutputiinfo):MutableList<String>?

}