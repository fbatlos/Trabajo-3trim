
import db_connection.DataSourceFactory

import output.Consola

fun main() {
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val consola =Consola()


}