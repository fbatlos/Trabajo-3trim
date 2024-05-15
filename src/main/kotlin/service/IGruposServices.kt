package service

import entity.Grupos

interface IGruposServices {
    fun crearGrupo(grupos: Grupos):Grupos?

    fun eliminarGrupo(idgrupo: Int):Boolean?
}