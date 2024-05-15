
import dao.CTFSDAO
import dao.GruposDAO
import db_connection.DataSourceFactory

import output.Consola
import output.Utilidades
import service.CTFSServiceImpl
import service.GruposServiceImpl
//args: Array<String>
fun main() {

    val args = arrayOf("-e","1","5")

    val consola =Consola()

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val gruposDAO  =GruposDAO(dataSource)

    val ctfsdao = CTFSDAO(dataSource)

    val gruposServiceImpl = GruposServiceImpl(gruposDAO,consola)

    val ctfsServiceImpl =CTFSServiceImpl(ctfsdao,consola)

    Utilidades.comprobarArgumentos(args,consola,gruposServiceImpl,ctfsServiceImpl)

}