package service

import entity.CTFS


interface ICTFSService {
    fun crearCTFS(ctfs: CTFS):CTFS?

    fun eliminarCTFS(idgroup:Int):Boolean?
}