package demo.web;


import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/test")
public class Test {
	
	@GET
	@Path("/sincrona")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONdatos sincrona(@QueryParam("id") String id){
		JSONdatos jd = new JSONdatos();
		jd.codigo=0;
		jd.identificador = id;
		jd.mensaje="Llamada sincrona";
		return jd;
	}
	
	@GET
	@Path("/asincrona")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONdatos asincrona(@QueryParam("id") String id){
		JSONdatos jd = new JSONdatos();
		jd.codigo=0;
		jd.identificador = id;
		jd.mensaje="Llamada asincrona";
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jd;
	}
	
	@GET
	@Path("/eventos")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONdatos eventos(){
		JSONdatos jd= new JSONdatos();
		int maxloop = 20;
		int curloop = 0;
		do {
			if ( Eventos.sk.size() > 0 ){
				System.out.println("leyendo evento");
				jd.identificador = Eventos.sk.pop();
				jd.codigo=0;
				jd.mensaje="Llamada eventos";
				break;
			}
			try {
				curloop++;
				System.out.println("espero 3 segundos para ver si tengo eventos nuevos");
				Thread.sleep(3000);
				if ( curloop>=maxloop) break;
			} catch (InterruptedException e) {e.printStackTrace();}
		}while(true);
		return jd;
	}
	
	@GET
	@Path("/apilar")
	public void apilar(){
		String[] str = {"patata","chorizo","morcilla","raton","rana","culebra","nesquik","cola-cao","vaca"};
		int val = new Random().nextInt(10 - 1);
		System.out.println("meto en la pila "+str[val]);
		Eventos.sk.push(str[val]);
	}
	
}
