package dao

import entity.Grupos

import output.IOutputiinfo

interface IGruposDAO {
    fun crearGrupo(grupos: Grupos,consola: IOutputiinfo):Grupos?
}