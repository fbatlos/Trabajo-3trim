
import dao.CTFSDAO
import dao.GruposDAO
import db_connection.DataSourceFactory

import output.Consola
import output.Utilidades
import service.CTFSServiceImpl
import service.GruposServiceImpl

fun main() {
    val arg = arrayListOf("-g")

    val consola =Consola()

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val gruposDAO  =GruposDAO(dataSource)

    val ctfsdao = CTFSDAO(dataSource)

    val gruposServiceImpl = GruposServiceImpl(gruposDAO,consola)

    val ctfsServiceImpl =CTFSServiceImpl(ctfsdao,consola)







    Utilidades.comprobarArgumentos(arg,consola,gruposServiceImpl,ctfsServiceImpl)

}