package output

import entity.CTFS
import entity.Grupos
import service.CTFSServiceImpl
import service.GruposServiceImpl
import service.ICTFSService
import service.IGruposServices

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
                        val p = crearCTFS(newargs,ctfsServiceImpl)
                        when{
                            p == true -> {
                                consola.showMenssage("Se añadió con éxito el ctf's.")
                            }
                            p == false ->{
                                consola.showMenssage("Se actualizó con éxito el ctf's.")
                            }
                            else -> {
                                consola.showMenssage("No se pudo añadir.")
                            }
                        }
                    }


                    "-t" -> {
                        if(args.size > 2 ){
                            error("")
                        }
                        newargs = arrayOf(args[1])
                        if (eliminarGrupo(newargs,gruposServiceImpl,ctfsServiceImpl)==true){
                            consola.showMenssage("Se eliminó al grupo.")
                        }
                    }

                    "-e" -> {
                        if(args.size > 3 ){
                            error("")
                        }
                        newargs = arrayOf(args[1],args[2])
                        if (eliminarCTFS(newargs,ctfsServiceImpl) ==true){
                            consola.showMenssage("Se eliminó el CTFS de id = ${newargs[0]} y idgrupo = ${newargs[1]}.")
                        }
                    }

                    "-l" -> {
                        if (args.size > 2){
                            error("")
                        }
                        buscarGrupo(args,gruposServiceImpl,ctfsServiceImpl)

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

        fun crearCTFS(args: Array<String>, ctfsServiceImpl: CTFSServiceImpl):Boolean? {

            if (args[0][0].isDigit() && args[1][0].isDigit() && args[2][0].isDigit()) {
                return ctfsServiceImpl.crearCTFS(CTFS(CTDid = args[0].toInt(), grupoid = args[1].toInt() , puntuacion = args[2].toInt() ))
            } else {
                error("")
            }

        }

        fun eliminarGrupo(args: Array<String>, gruposServiceImpl: GruposServiceImpl,ctfsServiceImpl: CTFSServiceImpl):Boolean?{
            if (args[0][0].isDigit()) {
                if (ctfsServiceImpl.eliminarCTFSPorIdgrupo(args[0].toInt()) != null){
                    return gruposServiceImpl.eliminarGrupo(args[0].toInt())
                }else{
                    return null
                }
            } else {
                error("")
            }
        }

        fun eliminarCTFS(args: Array<String>,ctfsServiceImpl: CTFSServiceImpl):Boolean?{
            if (args[0][0].isDigit() && args[1][0].isDigit()) {
                if (ctfsServiceImpl.eliminarCTFS(args[0].toInt(),args[1].toInt()) != null){
                    return true
                }else{
                    return null
                }
            } else {
                error("")
            }
        }

        fun buscarGrupo(args:Array<String>,gruposServiceImpl:IGruposServices,ctfsServiceImpl:ICTFSService){
            if (args[1][0].isDigit()){
                gruposServiceImpl.buscarGrupo(args[1].toInt())
            } else {

            }
        }
    }


}

