package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.Comparendo;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Sort;
import model.data_structures.Stack;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10
	private Queue<Comparendo> cola1;
	private Queue<Comparendo> cola2;
	private Queue<Comparendo> cola3;
	private Queue<Comparendo> cola4;
	private Queue<Comparendo> cola5;
	private Queue<Comparendo> cola6;
	private Queue<Comparendo> cola7;

	private Comparendo mayorObjectId;
	private double mayLatitud;
	private double mayLongitud;
	private double minLatitud;
	private double minLongitud;


	public static String PATH = "./data/comparendos_dei_2018_small.geojson";

	public Modelo()
	{
		cola1 = new Queue<Comparendo>();
		cola2 = new Queue<Comparendo>();
		cola3 = new Queue<Comparendo>();
		cola4 = new Queue<Comparendo>();
		cola5 = new Queue<Comparendo>();
		cola6 = new Queue<Comparendo>();
		cola7 = new Queue<Comparendo>();

		mayorObjectId = null;
		mayLatitud = -90;
		mayLongitud = -90;
		minLatitud = 90;
		minLongitud = 90;
	}


	// Retorno de estructuras -----------------------
	public Queue<Comparendo> darCola1()
	{
		return cola1;
	}


	//---------------------------------------------


	
	public Comparendo darMayorObjectId()
	{
		return mayorObjectId;
	}
	
	public double darMayLatitud()
	{
		return mayLatitud;
	}
	
	public double darMayLongitud()
	{
		return mayLongitud;
	}
	
	public double darMinLatitud()
	{
		return minLatitud;
	}
	
	public double darMinLongitud()
	{
		return minLongitud;
	}


	//Tamaño ----------------------------------------------
	public int darTamCola()
	{
		return cola1.getSize();
	}

	//---------------------------------------------------

	public Comparendo darComparendoQueue()
	{
		return cola1.getElement();
	}

	public void cargarDatos() 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();


				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);

				if(cola1.isEmpty() == true)
				{
					mayorObjectId = c;
				}
				else 
				{
					if(mayorObjectId.darObjectId() < c.darObjectId() )
					{
						mayorObjectId = c;
					}
					if(mayLatitud < c.darLatitud())
					{
						mayLatitud = c.darLatitud();
					}
					if(mayLongitud < c.darLongitud())
					{
						mayLongitud = c.darLongitud();
					}
					if(minLatitud > c.darLatitud())
					{
						minLatitud = c.darLatitud();
					}
					if(minLongitud > c.darLongitud())
					{
						minLongitud = c.darLongitud();
					}
				}


				cola1.enqueue(c);
				cola2.enqueue(c);
				cola3.enqueue(c);
				cola4.enqueue(c);
				cola5.enqueue(c);
				cola6.enqueue(c);
				cola7.enqueue(c);
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	//Taller 
	public Comparendo unoParteA(String pLocalidad)
	{
		Queue<Comparendo> copia = cola1;
		copia.dequeue();
		int tam = cola1.getSize();
		boolean termine = false;
		Comparendo mio = null;
		Comparendo a = null;
		
		for(int i = 0; i <tam && termine == false; i++)
		{
			a = copia.dequeue();
			if(a.darLocalidad().equalsIgnoreCase(pLocalidad))
			{
				mio = a;
				termine = true;
			}
		}
		return mio;
	}
	
	public Comparendo unoParteB(String pInfraccion)
	{
		int tam = cola2.getSize();
		boolean termine = false;
		Comparendo mio = null;
		Comparendo a = null;
		
		for(int i = 0; i <tam && termine == false; i++)
		{
			a = cola2.dequeue();
			if(a.darCodInfeaccion().equalsIgnoreCase(pInfraccion))
			{
				mio = a;
				termine = true;
			}
		}
		return mio;
	}
	
	
	public Queue<Comparendo> dosParteA(Date pFecha)
	{
		int m = cola3.getSize();
		Comparendo[] elementos = new Comparendo[m];
		Comparendo a = null;
		for(int i=0;i<m &&cola3.isEmpty() ==false ;i++)
		{
			a=cola3.dequeue();
			if(a.darFecha().equals(pFecha))
			{
				elementos[i] = a;
			}
		}
		Sort.sort(elementos, 0);
		return cambiarArregloCola(elementos);
	}
	
	public Queue<Comparendo> tresParteA1(Date pFecha)
	{
		int m = cola4.getSize();
		Comparendo[] elementos = new Comparendo[m];
		Comparendo a = null;
		for(int i=0;i<m &&cola4.isEmpty() ==false ;i++)
		{
			a=cola4.dequeue();
			if(a.darFecha().equals(pFecha))
			{
				elementos[i] = a;
			}
		}
		Sort.sort(elementos, 0);
		return cambiarArregloCola(elementos);
	}
	
	
	public Queue<Comparendo> tresParteA2(Date pFecha)
	{
		int m = cola5.getSize();
		Comparendo[] elementos = new Comparendo[m];
		Comparendo a = null;
		for(int i=0;i<m &&cola5.isEmpty() ==false ;i++)
		{
			a=cola5.dequeue();
			if(a.darFecha().equals(pFecha))
			{
				elementos[i] = a;
			}
		}
		Sort.sort(elementos, 0);
		return cambiarArregloCola(elementos);
	}

	
	public Queue<Comparendo> dosParteB(String pInfraccion)
	{
		int m = cola4.getSize();
		Comparendo[] elementos = new Comparendo[m];
		Comparendo a = null;
		for(int i=0;i<m && cola4.isEmpty() ==false ;i++)
		{
			a=cola4.dequeue();
			if(a.darCodInfeaccion().equalsIgnoreCase(pInfraccion))
			{
				elementos[i] = a;
			}
		}
		Sort.sort(elementos, 1);
		return cambiarArregloCola(elementos);
	}
	
	public Stack<Comparendo> tresParteBParticular()
	{
		return null;
	}
	public Stack<Comparendo> tresParteBPublico()
	{
		return null;
	}
	
//	public String CompararPorServiciosCod()
//	{
//		String retorno="Infraccion | Particular |Publico "+"\n";
//
//		 Stack<Comparendo> pilaPart= new Stack<Comparendo>();
//		 Stack<Comparendo> pilaPub= new Stack<Comparendo>(); 
//		 Stack<Comparendo> pila1 = pila;
//		 while (pila != null) {
//			 Comparendo actual=pila.pop();
//			 if(actual.darClase_Vehi().equalsIgnoreCase("Particular"));{
//				 pilaPart.push(actual);
//			 }
//			 if(actual.darClase_Vehi().equalsIgnoreCase("Publico"));{
//				 pilaPub.push(actual);
//			 }
//			 
//			 
//		 }
//		ArrayList<Comparendo> all= new ArrayList<Comparendo>();
//		ArrayList<Comparendo> listpart= new ArrayList<Comparendo>();
//		ArrayList<Comparendo> listpub= new ArrayList<Comparendo>();
//		ArrayList<String> usados= new ArrayList<String>();
//		
//		for (int i = 0; i < pilaPart.getSize(); i++) {
//			Comparendo index = pilaPart.pop();
//			if(index.darClase_Vehi().equalsIgnoreCase("Particular")) {
//				listpart.add(index);
//			}
//			if(index.darClase_Vehi().equalsIgnoreCase("Publico")) {
//				listpart.add(index);
//			}
//			all.add(index);
//		}
//		
//		for (int i = 0; i < all.size(); i++)
//		{
//			String codAct=all.get(i).darCodInfraccion();
//			if(!usados.contains(codAct)) {
//				int contpart=0;
//				int contpub=0;
//				for (int j = 0; j < listpart.size(); j++) {
//					String element=listpart.get(j).darCodInfraccion();
//					if(element.equalsIgnoreCase(codAct)) {
//						contpart++;
//					}
//				}
//				
//				for (int j = 0; j < listpub.size(); j++) {
//					String element2=listpart.get(j).darCodInfraccion();
//					if(element2.equalsIgnoreCase(codAct)) {
//						contpub++;
//					}
//				
//				}
//				String adding= codAct + " | "+ contpart +" | "+ contpub +" \n";
//				retorno=retorno + adding;
//			}
//			
//			usados.add(codAct);
//			
//		}
//		
//		return retorno;
//	}
//		 
//	
//	public String alinear(String pNombre) {
//		ArrayList<Comparendo> all= new ArrayList<Comparendo>();
//		ArrayList<String> usados= new ArrayList<String>();
//		for (int i = 0; i < all.size(); i++)
//		{
//			String locAct=all.get(i).darLocalidad();
//			if(!usados.contains(locAct)) 
//			{
//				usados.add(locAct);
//			}
//		}
//		
//		int largestString = usados.get(0).length();
//        int index = 0;
//
//        for(int i = 0; i < usados.size(); i++)
//        {
//            if(usados.get(i).length() > largestString)
//            {
//                largestString = usados.get(i).length();
//                                index = i;
//            }
//        }
//        String retorno="";
//        if (pNombre.length()< usados.get(index).length())
//        {
//        	for(int i = 0; i < usados.get(index).length()-pNombre.length(); i++)
//        	{
//        		retorno=retorno + "-";
//        	}
//        }
//        return retorno;
//		
//	}
	public Queue<Comparendo> unoParteC(String pLocalidad, Date pFecha1, Date pFecha2)
	{
		int m = cola6.getSize();
		Comparendo[] elementos = new Comparendo[m];
		Comparendo a = null;
		for(int i=0;i<m &&cola6.isEmpty() ==false ;i++)
		{
			a=cola6.dequeue();
			if(a.darLocalidad().equalsIgnoreCase(pLocalidad) && a.darFecha().after(pFecha1) && a.darFecha().before(pFecha2))
			{
				elementos[i] = a;
			}
		}
		Sort.sort(elementos, 0);
		return cambiarArregloCola(elementos);
	}
	
	
	
	public Queue<Comparendo> dosParteC( Date pFecha1, Date pFecha2)
	{
		int m = cola7.getSize();
		Comparendo[] elementos = new Comparendo[m];
		Comparendo a = null;
		for(int i=0;i<m &&cola7.isEmpty() ==false ;i++)
		{
			a=cola7.dequeue();
			if(a.darFecha().after(pFecha1) && a.darFecha().before(pFecha2))
			{
				elementos[i] = a;
			}
		}
		Sort.sort(elementos, 0);
		return cambiarArregloCola(elementos);
	
	}
	
	

//	public Queue<Comparendo> tresParteC()
//	{
//		String retorno="Aproximación del número de comparendos por localidad. " + " \n";
//		Queue<Comparendo> all= new Queue<Comparendo>();
//		Queue<String> usados= new Queue<String>();
//		
//		for (int i = 0; i < all.getSize(); i++)
//		{
//			String locAct=all.getElement().darLocalidad();
//			if(!usados.contains(locAct)) {
//				int contador=0;
//				String asterisks="";
//				for (int j = 0; j < all.getSize(); j++) {
//					String search=all.getElement().darLocalidad();
//					if (locAct.equalsIgnoreCase(search))
//					{
//						contador++;
//						if (contador%50==0) {
//							asterisks=asterisks + "*";
//						}
//					}
//				}
//				if (contador%50!=0) {
//					asterisks=asterisks + "*";
//				}
//				
//				
//				
//				String adding= locAct + alinear(locAct) + " | "+ asterisks + " \n";
//				retorno=retorno + adding;
//			}
//			
//			usados.add(locAct);
//			
//		}
//		
//		return retorno;
//	}
	
	
	//Cambiar de cola a arreglo
	public Comparendo[] cambiarColaArreglo(Queue<Comparendo> dato)
	{ 
		int m = dato.getSize();
		Comparendo[] elementos = new Comparendo[m];
		
		for(int i=0;i<m;i++)
		{
			elementos[i]= dato.dequeue();
		}
		return elementos;
	}
	//Cambiar de arreglo a cola
	public Queue<Comparendo> cambiarArregloCola( Comparendo[] dato)
	{ 
		int m = dato.length;
		Queue<Comparendo> el = new Queue<Comparendo>();
		
		for(int i=0;i<m;i++)
		{
			el.enqueue(dato[i]);
		}
		return el;
	}
}
