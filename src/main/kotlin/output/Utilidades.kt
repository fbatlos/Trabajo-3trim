package output

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
        fun comprobarArgumentos( args: ArrayList<String>, consola: IOutputiinfo,gruposServiceImpl: GruposServiceImpl,ctfsServiceImpl: CTFSServiceImpl){
            try {
                val formato: String? = args[0]

                when (formato) {

                    "-g" -> {
                        args.drop(0)
                        println(args)
                        crearGrupo(args,gruposServiceImpl,consola)
                    }

                    "-p" -> {
                        "P"
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
            }catch (e:IllegalStateException){
                consola.showMenssage("Datos no validos.")
            }
        }
        fun crearGrupo(args: ArrayList<String>, gruposServiceImpl: GruposServiceImpl,consola: IOutputiinfo){
            try {
                if (args[1][1].isDigit() || args.isEmpty()){
                    error("")
                }else{
                    gruposServiceImpl.crearGrupo(Grupos(grupodesc = args[1]))
                }
            }catch (e:IllegalStateException){
                consola.showMenssage("ERROR: El número de parámetros no es adecuado.")
            }catch (e:Exception){
                consola.showMenssage("ERROR: Algo a salido mal.")
            }
        }
    }


}

