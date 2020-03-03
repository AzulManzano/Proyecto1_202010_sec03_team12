package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.data_structures.Comparendo;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.logic.Modelo;
import view.View;

public class Controller 
{
	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() throws ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;


		view.bienvenida();

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			if(option == 1)
			{
				modelo.cargarDatos();
			}
			switch(option)
			{
			case 1:
				view.printMessage("Los datos fueron leidos satisfactoriamente");
				view.printMessage("  La cantidad de comparendos que fueron leidos es: " + modelo.darTamCola());
				view.printMessage("");
				view.printMessage("El comparendo con mayor OBJECTID encontrado es:");
				view.printMessage(modelo.darMayorObjectId().darInformacion());
				view.printMessage("");
				view.printMessage("La zona Minimax es:");
				view.printMessage("La mayor longitud: "+ modelo.darMayLongitud() + " La mayor latitud: "+ modelo.darMayLatitud()+"\nLa menor longitud: "+ modelo.darMinLongitud() + "  La menor latitud: " +modelo.darMinLatitud());
				view.printMessage("");
				view.printMessage("");
				view.printMessage(modelo.unoParteA("BOSA").darInformacion());
				break;

			case 2:
				view.printMessage("");
				view.printMessage("Ingrese la localidad");
				String a = lector.next();
				if(modelo.unoParteA(a) != null)
				{
					view.printMessage("El primer comparendo por Localidad es:");
					view.printMessage(modelo.unoParteA(a).darInformacion());
				}
				else
				{
					view.printMessage("No se encontro ningun comparendo con esa localidad.");
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 3:			
				view.printMessage("");
				view.printMessage("Ingrese la fecha en el formato Año/Mes/Día");
				String da = lector.next();
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				Date date = format.parse(da);
				view.printMessage("");
				int numero1 = modelo.dosParteA(date).getSize();
				Queue<Comparendo> el = modelo.dosParteA(date);
				view.printMessage("El numero de comparendos de la consulta es: "+ numero1+" y la informacion de cada uno es la siguiente:");
				view.printMessage("");
				for(int i = 0; i<numero1; i++)
				{
					view.printMessage(el.dequeue().darInformacion());
				}
				break;

			case 4:			
				view.printMessage("");
				view.printMessage("Ingrese la fecha numero 1 en el formato Año/Mes/Día");
				String un1 = lector.next();
				DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
				Date date11 = format1.parse(un1);
				view.printMessage("");
				view.printMessage("Ingrese la fecha numero 2 en el formato Año/Mes/Día");
				String un2 = lector.next();
				DateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
				Date date21 = format2.parse(un2);

				view.parteA3(date11, date21);
				break;
			case 5:			
				view.printMessage("");
				view.printMessage("Ingrese la infraccion");
				String b = lector.next();
				if(modelo.unoParteB(b) != null)
				{
					view.printMessage("El primer comparendo por infraccion es:");
					view.printMessage(modelo.unoParteB(b).darInformacion());
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 6:			
				view.printMessage("");
				view.printMessage("Ingrese la infraccion");
				String per = lector.next();
				view.printMessage("");
				int numero2 = modelo.dosParteB(per).getSize();
				Queue<Comparendo> al = modelo.dosParteB(per);
				view.printMessage("El numero de comparendos de la consulta es: "+ numero2+" y la informacion de cada uno es la siguiente:");
				view.printMessage("");
				for(int i = 0; i<numero2; i++)
				{
					view.printMessage(al.dequeue().darInformacion());
				}
				break;

			case 7:			

				break;

			case 8:			
				view.printMessage("");
				view.printMessage("Ingrese la localidad");
				String ind = lector.next();
				view.printMessage("Ingrese la fecha inicial en frmato Año/Mes/Día");
				String ul = lector.next();
				DateFormat formatal = new SimpleDateFormat("yyyy/MM/dd");
				Date dateal = formatal.parse(ul);
				view.printMessage("Ingrese la fecha final en frmato Año/Mes/Día");
				String ol = lector.next();
				DateFormat formatol = new SimpleDateFormat("yyyy/MM/dd");
				Date dateol = formatol.parse(ol);
				view.printMessage("");
				view.printMessage("Comparación de comparendos en "+ind+" del " +ul+" al "+ol);
				view.printMessage("Infracción    | # Comparendos ");
				Queue<Comparendo> jaj = modelo.unoParteC(ind, dateal, dateol);
				String codigo = "";
				int c = 0;
				while(jaj.isEmpty()==false)
				{
					codigo = jaj.dequeue().darCodInfeaccion();
					String as = codigo;
					while(as.equalsIgnoreCase(codigo))
					{
						c++;
						as = jaj.dequeue().darCodInfeaccion();
					}
					view.printMessage(codigo+"   | "+c);
					c=0;
				}
				break;

			case 9:			
				view.printMessage("");
				view.printMessage("Ingrese la cantidad de frecuencias que quiere ver");
				int fre = lector.nextInt();
				view.printMessage("Ingrese la fecha inicial en frmato Año/Mes/Día");
				String loci = lector.next();
				DateFormat formatloci = new SimpleDateFormat("yyyy/MM/dd");
				Date dateloci = formatloci.parse(loci);
				view.printMessage("Ingrese la fecha final en frmato Año/Mes/Día");
				String pit = lector.next();
				DateFormat formatpit = new SimpleDateFormat("yyyy/MM/dd");
				Date datepit = formatpit.parse(pit);
				view.printMessage("");
				view.printMessage("");
				view.printMessage("Ranking de las "+ fre+ " mayores infracciones del "+ loci +" al " +pit);
				view.printMessage("Infracción    | # Comparendos "); 
				Queue<Comparendo> ji = modelo.dosParteC(dateloci, datepit);
				Queue<String> codigos = new Queue<String>();
				Queue<Integer> numeros = new Queue<Integer>();
				String codigo1 = "";
				int ca = 0;
				while(ji.isEmpty()==false)
				{
					codigo1 = ji.dequeue().darCodInfeaccion();
					String as = codigo1;
					while(as.equalsIgnoreCase(codigo1))
					{
						ca++;
						as = ji.dequeue().darCodInfeaccion();
					}
					codigos.enqueue(codigo1);
					numeros.enqueue(ca);
					ca=0;
				}
				
				

				break;

			case 0:			
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
