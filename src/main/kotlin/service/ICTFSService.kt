package service

import entity.CTFS


interface ICTFSService {
    fun crearCTFS(ctfs: CTFS):Boolean?

    fun eliminarCTFSPorIdgrupo(idgroup:Int):Boolean?

    fun eliminarCTFS(idgroup:Int , ctfsid:Int):Boolean?
}