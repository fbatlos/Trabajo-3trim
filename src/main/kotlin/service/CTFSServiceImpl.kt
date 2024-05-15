package service


import dao.ICTFSDAO
import entity.CTFS
import output.IOutputiinfo


//private val userDao: IUserDAO, private val consola: Consola
class CTFSServiceImpl(private val ctfsdao:ICTFSDAO,private val consola:IOutputiinfo) : ICTFSService {
    override fun crearCTFS(ctfs: CTFS) {
        ctfsdao.crearCTFS(ctfs,consola)
    }

}