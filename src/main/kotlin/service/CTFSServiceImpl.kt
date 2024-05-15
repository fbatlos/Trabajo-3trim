package service


import dao.ICTFSDAO
import entity.CTFS
import output.IOutputiinfo


//private val userDao: IUserDAO, private val consola: Consola
class CTFSServiceImpl(private val ctfsdao:ICTFSDAO,private val consola:IOutputiinfo) : ICTFSService {
    override fun crearCTFS(ctfs: CTFS): Boolean? {
        return ctfsdao.crearCTFS(ctfs,consola)
    }

    override fun eliminarCTFSPorIdgrupo(idgroup: Int): Boolean? {
        return ctfsdao.eliminarCTFSPorIdgrupo(idgroup,consola)
    }

    override fun eliminarCTFS(ctfsid: Int,idgroup: Int): Boolean? {
        return ctfsdao.eliminarCTFS(ctfsid,idgroup, consola)
    }

}