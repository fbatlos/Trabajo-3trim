package output

class Consola :IOutputiinfo{
    override fun showMenssage(mensaje: String, lineBreak: Boolean) {
        if (lineBreak){
            println(mensaje)
        }else{
            print(mensaje)
        }
    }


}