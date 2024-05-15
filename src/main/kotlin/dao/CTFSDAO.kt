package dao


import entity.CTFS
import output.IOutputiinfo
import javax.sql.DataSource


class CTFSDAO(private val dataSource: DataSource) : ICTFSDAO {
    override fun crearCTFS(ctfs: CTFS, consola: IOutputiinfo):Boolean? {
        val sql = "INSERT INTO CTFS  (CTFid , grupoid , puntuacion) VALUES (?,?,?)"
        var comprobacion:Boolean
        comprobacion = comprobarCtfsId(ctfs.CTDid,consola)
        if (comprobacion==false){
            return null
        }
        comprobacion = comprobarIdGrupo(ctfs.grupoid,consola)
        if (comprobacion == false){
            return null
        }
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, ctfs.CTDid)
                    stmt.setInt(2,ctfs.grupoid)
                    stmt.setInt(3,ctfs.puntuacion)
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
            val sql = "UPDATE CTFS set puntuacion = (?) where  CTFid = (?) and grupoid = (?)"
            return try {
                dataSource.connection.use { conn ->
                    conn.prepareStatement(sql).use { stmt ->
                        stmt.setInt(2, ctfs.CTDid)
                        stmt.setInt(3,ctfs.grupoid)
                        stmt.setInt(1,ctfs.puntuacion)
                        val rs = stmt.executeUpdate()
                        if (rs == 1) {
                            false
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

    fun comprobarCtfsId(ctfsID: Int, consola: IOutputiinfo):Boolean{
        val sql = "SELECT COUNT(DISTINCT GRUPOID) as participantes from GRUPOS;"
        return try {
            var numParticipantes=0
            dataSource.connection.use { conn ->
                conn.createStatement().use { stmt ->
                    stmt.executeQuery(sql).use { rs ->
                        while (rs.next()) {
                            numParticipantes=rs.getInt("participantes")
                        }
                    }
                }
            }
            if (ctfsID<= numParticipantes){
                true
            }else{
                consola.showMenssage("Error: CTF'S ID no valido.")
                false
            }
        } catch (e: Exception) {
            consola.showMenssage("Error: Al intentar comprobar los CTF'S.")
            false
        }
    }

    fun comprobarIdGrupo(idgrupo: Int,consola: IOutputiinfo):Boolean{
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement("SELECT GRUPOID from GRUPOS where GRUPOID = (?)").use { stmt ->
                    stmt.setInt(1,idgrupo)
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        true
                    } else {
                        consola.showMenssage("Error: ID del grupo no se ha encontrado.")
                        false
                    }
                }
            }
        } catch (e: Exception) {
            consola.showMenssage("Error: ID del grupo no se ha encontrado.")
            false
        }
    }

    override fun eliminarCTFSPorIdgrupo(idgrupo: Int,consola:IOutputiinfo): Boolean? {
        val sql = "DELETE FROM CTFS  where grupoid = (?)"
        comprobarIdGrupo(idgrupo,consola)
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, idgrupo)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        true
                    } else {
                        null
                    }
                }
            }
        } catch (e: Exception) {
            consola.showMenssage("Algo no fue bien!! (${e.message})", true)
            null
        }
    }

    override fun eliminarCTFS(ctfsid: Int,idgroup: Int , consola:IOutputiinfo): Boolean? {
        val sql = "DELETE FROM CTFS  where grupoid = (?) and CTFID = (?)"
        comprobarIdGrupo(idgroup,consola)
        comprobarCtfsId(ctfsid,consola)
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, idgroup)
                    stmt.setInt(2,ctfsid)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        true
                    } else {
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

