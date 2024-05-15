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
                        if (crearGrupo(newargs, gruposServiceImpl) is Grupos){
                            consola.showMenssage("Se añadió con éxito el grupo.")
                        }else{
                            consola.showMenssage("No se pudo añadir.")
                        }
                    }

                    "-p" -> {

                        newargs = arrayOf(args[1],args[2],args[3])
                        newargs.forEach { println(it) }
                        if (crearCTFS(newargs,ctfsServiceImpl) is CTFS){
                            consola.showMenssage("Se añadió con éxito el ctf's.")
                        }else{
                            consola.showMenssage("No se pudo añadir.")
                        }
                    }

                    "-t" -> {

                        newargs = arrayOf(args[1])
                        if (eliminarGrupo(newargs,gruposServiceImpl,ctfsServiceImpl)==true){
                            consola.showMenssage("Se eliminó al grupo.")
                        }else{
                            consola.showMenssage("No se pudo eliminar al grupo.")
                        }
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
        fun crearGrupo(args: Array<String>, gruposServiceImpl: GruposServiceImpl):Grupos? {

            if (args[0][0].isDigit() || args.isEmpty()) {
                error("")
            } else {
                return gruposServiceImpl.crearGrupo(Grupos(grupodesc = args[0]))
            }

        }

        fun crearCTFS(args: Array<String>, ctfsServiceImpl: CTFSServiceImpl):CTFS? {

            if (args[0][0].isDigit() && args[1][0].isDigit() && args[2][0].isDigit()) {
                return ctfsServiceImpl.crearCTFS(CTFS(CTDid = args[0].toInt(), grupoid = args[1].toInt() , puntuacion = args[2].toInt() ))
            } else {
                error("")
            }

        }

        fun eliminarGrupo(args: Array<String>, gruposServiceImpl: GruposServiceImpl,ctfsServiceImpl: CTFSServiceImpl):Boolean?{
            if (args[0][0].isDigit()) {
                if (ctfsServiceImpl.eliminarCTFS(args[0].toInt()) != null){
                    return gruposServiceImpl.eliminarGrupo(args[0].toInt())
                }else{
                    return null
                }
            } else {
                error("")
            }
        }
    }


}

