package dao


import output.Consola
import javax.sql.DataSource


class CTFSDAO(private val dataSource: DataSource) : ICTFSDAO {



    override fun getAllLibros(consola: Consola): MutableList<String>? {
        return try {
            val libros = mutableListOf<String>()
            dataSource.connection.use { conn ->
                conn.createStatement().use { stmt ->
                    stmt.executeQuery("SELECT TITULO FROM biblioteca").use { rs ->
                        while (rs.next()) {
                            libros.add(rs.getString("TITULO"))
                        }
                    }
                }
            }
            libros
        } catch (e: Exception) {
            consola.showMenssage("Algo no fue bien!! (${e.message})", true)
            null
        }
    }

}

