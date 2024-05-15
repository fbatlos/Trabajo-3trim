package output

import entity.CTFS
import entity.Grupos
import service.CTFSServiceImpl
import service.GruposServiceImpl

//import logicaES.IEntradaSalida

/**
 * Clase de utilidades para proporcionar funciones comunes utilizadas en el juego de Bingo.
 * Incluye métodos para comprobar argumentos de entrada, generar nombres de ficheros de log, y obtener configuraciones de juego.
 */
class Utilidades {

    companion object {

        /**
         * Comprueba y extrae los argumentos necesarios para configurar el juego.
         *
         * @param args Array de strings conteniendo los argumentos de entrada.
         * @param consola Interfaz de entrada/salida para comunicarse con el usuario.
         *
         */
        fun comprobarArgumentos( args: Array<String>, consola: IOutputiinfo,gruposServiceImpl: GruposServiceImpl,ctfsServiceImpl: CTFSServiceImpl){
            try {
                val formato: String? = args[0]

                val newargs:Array<String>

                when (formato) {

                    "-g" -> {

                        newargs = arrayOf(args[1])
                        newargs.forEach { println(it) }
                        crearGrupo(newargs, gruposServiceImpl)
                    }

                    "-p" -> {

                        newargs = arrayOf(args[1],args[2],args[3])
                        newargs.forEach { println(it) }
                        crearCTFS(newargs,ctfsServiceImpl)

                    }

                    "-t" -> {
                        "T"
                    }

                    "-e" -> {
                        "E"
                    }

                    "-l" -> {
                        "L"
                    }

                    "-c" -> {
                        "C"
                    }

                    "-f" -> {
                        "F"
                    }

                    "-i" -> {
                        "I"
                    }

                    else -> {
                        error("")
                    }
                }
            }catch (e:ArrayIndexOutOfBoundsException){
                consola.showMenssage("ERROR: El número de parámetros no es adecuado.")
            }catch (e:IllegalStateException){
                consola.showMenssage("ERROR: El número de parámetros no es adecuado.")
            }catch (e:StringIndexOutOfBoundsException) {
                consola.showMenssage("ERROR: No hay suficientes datos.")
            }catch (e:NumberFormatException){
                consola.showMenssage("ERROR: No ha introducido el parámetro adecuado.")
            }catch (e:Exception){
                consola.showMenssage("ERROR: Algo a salido muy mal.")
            }
        }
        fun crearGrupo(args: Array<String>, gruposServiceImpl: GruposServiceImpl) {

            if (args[0][0].isDigit() || args.isEmpty()) {
                error("")
            } else {
                gruposServiceImpl.crearGrupo(Grupos(grupodesc = args[0]))
            }

        }

        fun crearCTFS(args: Array<String>, ctfsServiceImpl: CTFSServiceImpl) {

            if (args[0][0].isDigit() || args[1][0].isDigit() || args[2][0].isDigit()) {
                ctfsServiceImpl.crearCTFS(CTFS(CTDid = args[0].toInt(), grupoid = args[1].toInt() , puntuacion = args[2].toInt() ))
            } else {
                error("")
            }

        }
    }


}

