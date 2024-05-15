package dao



import entity.CTFS
import output.IOutputiinfo

interface ICTFSDAO{

    fun crearCTFS(ctfs: CTFS , consola: IOutputiinfo):CTFS?

}