package service


import dao.IGruposDAO
import entity.Grupos

import output.IOutputiinfo

class GruposServiceImpl(private val gruposDAO: IGruposDAO ,private val consola: IOutputiinfo):IGruposServices {
    override fun crearGrupo(grupos: Grupos) {
        gruposDAO.crearGrupo(grupos,consola)
    }
}