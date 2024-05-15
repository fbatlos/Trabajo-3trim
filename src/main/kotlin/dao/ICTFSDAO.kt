package dao



import entity.CTFS
import output.IOutputiinfo

interface ICTFSDAO{

    fun crearCTFS(ctfs: CTFS , consola: IOutputiinfo):Boolean?

    fun eliminarCTFSPorIdgrupo(idgrupo:Int, consola: IOutputiinfo):Boolean?

    fun eliminarCTFS(ctfsid:Int,idgroup:Int ,consola:IOutputiinfo):Boolean?
}