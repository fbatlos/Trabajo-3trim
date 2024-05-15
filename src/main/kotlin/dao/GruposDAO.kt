package dao

import entity.Grupos

import output.IOutputiinfo
import javax.sql.DataSource


class GruposDAO(private val dataSource: DataSource):IGruposDAO {
    override fun crearGrupo(grupos: Grupos, consola: IOutputiinfo):Grupos? {
        val sql = "INSERT INTO GRUPOS (grupodesc) VALUES (?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, grupos.grupodesc)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        grupos
                    } else {
                        consola.showMenssage("Algo no fue bien!! (${rs})", true)
                        null
                    }
                }
            }
        } catch (e: Exception) {
            consola.showMenssage("Algo no fue bien!! (${e.message})", true)
            null
        }
    }

    override fun eliminarGrupo(idgrupo: Int, consola: IOutputiinfo): Boolean? {
        val sql = "DELETE FROM GRUPOS where grupoid = (?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, idgrupo)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        true
                    } else {
                        consola.showMenssage("Algo no fue bien!! (${rs})", true)
                        null
                    }
                }
            }
        } catch (e: Exception) {
            consola.showMenssage("Algo no fue bien!! (${e.message})", true)
            null
        }
    }
}