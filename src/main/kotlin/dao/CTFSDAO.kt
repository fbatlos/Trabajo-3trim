package dao


import entity.CTFS
import output.IOutputiinfo
import javax.sql.DataSource


class CTFSDAO(private val dataSource: DataSource) : ICTFSDAO {
    override fun crearCTFS(ctfs: CTFS, consola: IOutputiinfo):CTFS? {
        val sql = "INSERT INTO CTFS  (CTFid , grupoid , puntuacion) VALUES (?,?,?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, ctfs.CTDid)
                    stmt.setInt(2,ctfs.grupoid)
                    stmt.setInt(3,ctfs.puntuacion)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        ctfs
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

