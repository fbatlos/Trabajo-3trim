package service

import entity.Grupos

interface IGruposServices {
    fun crearGrupo(grupos: Grupos):Grupos?

    fun eliminarGrupo(idgrupo: Int):Boolean?

    fun buscarGrupo(idgrupo: Int):String?
}