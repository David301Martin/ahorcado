package ahorcado;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Ahorcado")
public class Ahorcado extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] Palabras=new String[] {"tenedor","mesa","pared","hoja","balon","ordenador"};
		
		String palabraAleatoria;
		int vidasRestantes;
		String letrasUsadas;
		String[] celdas;
		String ultimaLetra;
		int intentosUsados;
		String FinDelJuego=null;
		
		if(request.getParameter("empezar")!=null) {
			HttpSession fin=request.getSession();
			fin.invalidate();
		}
	
			if(request.getSession(false)==null) {
				HttpSession miSesion=request.getSession(true);
				palabraAleatoria=Palabras[(int)(Math.random()*(Palabras.length-1))];
				vidasRestantes=6;
				letrasUsadas="";
				celdas=new String[palabraAleatoria.length()];
				
				for(int i=0;i<palabraAleatoria.length();i++) {
					celdas[i]="_";
				}
				
				ultimaLetra="";
				intentosUsados=0;
				FinDelJuego=null;
				
				miSesion.setAttribute("palabraAleatoria", palabraAleatoria);
				miSesion.setAttribute("vidasRestantes", vidasRestantes);
				miSesion.setAttribute("letrasUsadas", letrasUsadas);
				miSesion.setAttribute("celdas", celdas);
				miSesion.setAttribute("ultimaLetra", ultimaLetra);
				miSesion.setAttribute("intentosUsados", intentosUsados);
				

			}
			else {
			HttpSession juegoNuevo=request.getSession();
			palabraAleatoria=(String)juegoNuevo.getAttribute("palabraAleatoria");
			vidasRestantes=(int)juegoNuevo.getAttribute("vidasRestantes");
			letrasUsadas=(String)juegoNuevo.getAttribute("letrasUsadas");
			celdas=(String[])juegoNuevo.getAttribute("celdas");
			ultimaLetra=(String)juegoNuevo.getAttribute("ultimaLetra");
			intentosUsados=(int)juegoNuevo.getAttribute("intentosUsados");
			try {
			FinDelJuego=(String)juegoNuevo.getAttribute("FinDelJuego");
			}
			catch(Exception e){
				FinDelJuego=null;
				}
			}
		
			request.setAttribute("palabraAleatoria", palabraAleatoria);
			request.setAttribute("vidasRestantes", vidasRestantes);
			request.setAttribute("letrasUsadas", letrasUsadas);
			request.setAttribute("celdas", celdas);
			request.setAttribute("ultimaLetra", ultimaLetra);
			request.setAttribute("intentosUsados", intentosUsados);
			request.setAttribute("FinDelJuego", FinDelJuego);
		
		
		String vista ="/Ahorcado.jsp";
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession juegoActual=request.getSession();
		
		String palabraAleatoria;
		int vidasRestantes;
		String letrasUsadas;
		String[] celdas;
		String ultimaLetra;
		int intentosUsados;
		String FinDelJuego=null;
		
		palabraAleatoria=(String)juegoActual.getAttribute("palabraAleatoria");
		vidasRestantes=(int)juegoActual.getAttribute("vidasRestantes");
		letrasUsadas=(String)juegoActual.getAttribute("letrasUsadas");
		celdas=(String[])juegoActual.getAttribute("celdas");
		ultimaLetra=request.getParameter("letra");
		intentosUsados=(int)juegoActual.getAttribute("intentosUsados");
		
		if(validaciones.validar.validarLetra(letrasUsadas, ultimaLetra)) {
			letrasUsadas=letrasUsadas+ultimaLetra;
			if(palabraAleatoria.contains(ultimaLetra)) {
			validaciones.validar.pintarLetras(palabraAleatoria, ultimaLetra, celdas);
			intentosUsados++;
			}
			else {
				intentosUsados++;
				vidasRestantes--;
			}
		}else {
			request.setAttribute("error", "Error!, escribe una letra válida");
		}
		
		if(vidasRestantes==0) {
			FinDelJuego="Lo siento, has perdido";
		}
		else if(validaciones.validar.comprobarFinal(celdas,palabraAleatoria)==true) {
			FinDelJuego="Enhorabuena! Has ganado!";
		}
		
		juegoActual.setAttribute("palabraAleatoria", palabraAleatoria);
		juegoActual.setAttribute("vidasRestantes", vidasRestantes);
		juegoActual.setAttribute("letrasUsadas", letrasUsadas);
		juegoActual.setAttribute("celdas", celdas);
		juegoActual.setAttribute("ultimaLetra", ultimaLetra);
		juegoActual.setAttribute("intentosUsados", intentosUsados);
		juegoActual.setAttribute("FinDelJuego", FinDelJuego);
		
		request.setAttribute("palabraAleatoria", palabraAleatoria);
		request.setAttribute("vidasRestantes", vidasRestantes);
		request.setAttribute("letrasUsadas", letrasUsadas);
		request.setAttribute("celdas", celdas);
		request.setAttribute("ultimaLetra", ultimaLetra);
		request.setAttribute("intentosUsados", intentosUsados);
		request.setAttribute("FinDelJuego", FinDelJuego);
		
		
		String vista ="/Ahorcado.jsp";
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request, response);
	}
}

 