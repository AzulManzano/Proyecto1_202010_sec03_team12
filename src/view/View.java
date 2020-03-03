package view;

import java.sql.Date;

import model.data_structures.Comparendo;
import model.data_structures.Queue;
import model.logic.Modelo;

public class View 
{
	/**
	 * Metodo constructor
	 */
	private Modelo modelo;
	public View()
	{
		modelo = new Modelo();
	}

	public void bienvenida()
	{
		System.out.println(".---.  _                                _    .-. \n"     
				+": .; ::_;                              :_;   : :      \n"
				+":   .'.-. .--. ,-.,-..-..-. .--. ,-.,-..-. .-' : .--. \n"
				+": .; :: :' '_.': ,. :: `; :' '_.': ,. :: :' .; :' .; :\n"
				+":___.':_;`.__.':_;:_;`.__.'`.__.':_;:_;:_;`.__.'`.__.'\n");
		System.out.println("");
	}

	public void printMenu()
	{
		System.out.println("MENU DEL USUARIO");
		System.out.println("1. Leer los datos");
		System.out.println("2. Primer comparendo por Localidad(1A)");
		System.out.println("3. Comparendos por fecha(2A)");
		System.out.println("4. Total de comparendos de cada código de INFRACCION para cada FECHA_HORA. (3A)");
		System.out.println("5. Primer comparendo por Infraccion(1B)");
		System.out.println("6. Comparendos por Infraccion(2B)");
		System.out.println("7. ");
		System.out.println("8. (1C))");
		System.out.println("9. (2C)");
		System.out.println("0. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) 
	{
		System.out.println(mensaje);
	}		

	public void printModelo(Modelo modelo)
	{
		// TODO implementar
	}
	
	public void parteA3(java.util.Date date11, java.util.Date date21)
	{
		Queue<Comparendo> el1 = modelo.tresParteA1(date11);
		Queue<Comparendo> el2 = modelo.tresParteA2(date21);
		Comparendo a = null;
		Comparendo b = null;
		
		int fecha1 = 0;
		int fecha2 = 0;
		String cod1 ="";
		String cod2 ="";
		boolean hola = false;
		System.out.println("Comparación de comparendos por Infracción en dos fechas");
		System.out.println("Infracción    | "+date11+"    | "+date21);
		while(hola == false)
		{
			if(el1.isEmpty()== false && el2.isEmpty()== false)
			{
				
			}
			else if(el1.isEmpty()== true)
			{
				cod1 = el2.dequeue().darCodInfeaccion();
				String un = cod1;
				while(un.equals(cod1))
				{
					fecha2++;
					un = el2.dequeue().darCodInfeaccion();
				}
				System.out.println(cod1 +"    | 0"+"    | "+fecha2);
				cod1="";
				fecha2 =0;
			}
			else if(el2.isEmpty()== true)
			{
				cod2 = el1.dequeue().darCodInfeaccion();
				String un = cod2;
				while(un.equals(cod2))
				{
					fecha1++;
					un = el2.dequeue().darCodInfeaccion();
				}
				System.out.println(cod2 +"    | "+fecha1+"    | 0");
				cod2="";
				fecha1 =0;
			}
			else
			{
				hola = true;
			}
		}
	}
}
