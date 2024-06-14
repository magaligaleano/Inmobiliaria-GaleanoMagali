package ar.edu.unlam.pb1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Inmobiliaria {

	private String nombre;
	private String direccion;
	private String eMail;
	private Integer telefono;
	private HashSet <Cliente> clientes;
	private ArrayList <Propiedad> propiedades;
	private ArrayList <Venta>ventas;
	private ArrayList <Alquiler> alquileres;
	
	
	public Inmobiliaria(String nombre, String direccion, String eMail, Integer telefono) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.eMail = eMail;
		this.telefono = telefono;
		this.clientes = new HashSet<>();
		this.propiedades = new ArrayList<>();
		this.ventas = new ArrayList<>();
		this.alquileres = new ArrayList<>();
	}
	public Boolean agregarPropiedad(Propiedad nueva) {
		return propiedades.add(nueva);
	}	
		
	public Boolean agregarCliente(Cliente nuevo) {
		for(Cliente cliente: clientes) {
			if(cliente.compareTo(nuevo)==0) {
				return false;
			}
		}
		return clientes.add(nuevo);
		
			
	}
	
	public Boolean agregarVenta(Venta nueva) {
		return ventas.add(nueva);
	}
	public Boolean agregarAlquiler(Alquiler nuevo) {
		return alquileres.add(nuevo);
	}
	public Boolean isVenta(Propiedad propiedad, Cliente cliente) {
		if(propiedad.estaDisponibleParaVenta()) {
			propiedad.setEstaDisponible(false);
			propiedad.setNombrePropietario(cliente.getNombre());
			return true;
		}
		
		 return false;
	}
	public Boolean isAlquiler(Propiedad propiedad, Cliente cliente) {
		if(propiedad.estaDisponibleParaAlquiler()) {
			propiedad.setEstaDisponible(false);
			propiedad.setNombreInquilino(cliente.getNombre());
			return true;
		}
		return false;
	}
			
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	//mostrar propiedades
	
	public ArrayList<Propiedad> mostrarPropiedades(){
		if(propiedades.size()>0) {
			return propiedades;
		}
		return null;
	}
	
	public ArrayList<Propiedad> mostrarPropiedadesCasas() {
		ArrayList<Propiedad> propiedadesCasas = new ArrayList<>();
		for(Propiedad actual : propiedades) {
			if(actual instanceof Casa ) {
				propiedadesCasas.add(actual);
			}
		}
		return propiedadesCasas;
	}
	
	public ArrayList<Propiedad> mostrarPropiedadesDeptos() {
		ArrayList<Propiedad> propiedadesDeptos = new ArrayList<>();
		for(Propiedad actual : propiedades) {
			if(actual instanceof Departamento ) {
				propiedadesDeptos.add(actual);
			}
		}
		return propiedadesDeptos;
	}
	
	public ArrayList<Propiedad> mostrarPropiedadesPhs() {
		ArrayList<Propiedad> propiedadesPhs = new ArrayList<>();
		for(Propiedad actual : propiedades) {
			if(actual instanceof PH ) {
				propiedadesPhs.add(actual);
			}
		}
		return propiedadesPhs;
	}
	
	public ArrayList<Propiedad> mostrarPropiedadesTerrenos() {
		ArrayList<Propiedad> propiedadesTerrenos = new ArrayList<>();
		for(Propiedad actual : propiedades) {
			if(actual instanceof Terreno ) {
				propiedadesTerrenos.add(actual);
			}
		}
		return propiedadesTerrenos;
	}
	
	public ArrayList<Propiedad> mostrarPropiedadesCampos() {
		ArrayList<Propiedad> propiedadesCampos = new ArrayList<>();
		for(Propiedad actual : propiedades) {
			if(actual instanceof Campo ) {
				propiedadesCampos.add(actual);
			}
		}
		return propiedadesCampos;
	}

	public HashSet<Cliente> mostrarClientes(){
		return clientes;
	}
	//Buscar y modificar propiedades
	
	public Propiedad buscarCodigoPropiedad(String codigo) {
		Propiedad propiedadABuscar = null;
		for(Propiedad actual : propiedades) {
			if(actual.getCodigo().equals(codigo)) {
				return propiedadABuscar = actual;
			}
		}
		return propiedadABuscar;
	}
	
	public Boolean modificarPropiedad(String codigo, Propiedad actualizada) {
		for(Propiedad actual : propiedades) {
			if(actual.getCodigo().equals(codigo)) {
				actual = actualizada;
				return true;
			}
		}
		return false;
	}

	//ordenar propiedades por precio
	
	public ArrayList<Propiedad> ordenarPropiedadesPorPrecio(ArrayList<Propiedad> propiedades) {
		Collections.sort(propiedades, new OrdenadosPorPrecio());
		return propiedades;
		
	}

//	//ordenar propiedades por ubicacion
	public void ordenarPropiedadesPorUbicacion() {
		for(int i= 0; i<propiedades.size();i++) {
			for(int j= 0; j<propiedades.size()-1;j++) {
				if(propiedades.get(j).getCiudad().compareTo(propiedades.get(j+1).getCiudad())> 0) {
					Propiedad temp = propiedades.get(j);
					propiedades.set(j, propiedades.get(j+1));
					propiedades.set(j+1, temp);
				}
			}
		}
	}
	
	//buscar por rango de precios
	
	public ArrayList<Propiedad> buscarPorRangoDePrecio(Double precioMinimo, Double precioMaximo) {
		ArrayList <Propiedad> propiedadesEncontradas = null;
		Integer contador = 0;
		for(Propiedad actual : propiedades) {
			if(actual.getPrecio()>=precioMinimo && actual.getPrecio()<=precioMaximo) { 
				contador++;
			}
		}
		if(contador>0) {
			propiedadesEncontradas = new ArrayList<>();
			for(Propiedad actual : propiedades) {
				if(actual.getPrecio()>=precioMinimo && actual.getPrecio()<=precioMaximo) { 
					propiedadesEncontradas.add(actual);
				}
			}
		}
	   return propiedadesEncontradas;
		
	}

	//buscar por ubicacion
	
	public ArrayList<Propiedad> buscarPorUbicacion(String ubicacion){
		ArrayList<Propiedad> propiedadesEncontradas = null;
		Integer contador = 0;
		for(Propiedad actual : propiedades) {
			if(actual.getCiudad().equals(ubicacion)) {
				contador++;
			}
		}
		if(contador>0) {
			propiedadesEncontradas = new ArrayList<>();
			for(Propiedad actual : propiedades) {
				if(actual.getCiudad().equals(ubicacion)) {
					propiedadesEncontradas.add(actual);
				}
		}
	}
		return propiedadesEncontradas;
	}
	

	//promedio de precio de propiedades
	
	public Double calcularPromedioDePropiedades() {
		Double sumatoria = 0.0;
		Double promedio = 0.0;
		Integer contadorPropiedades = 0;
		
		for(Propiedad actual : propiedades) {
			if(actual!=null) {
				sumatoria+=actual.getPrecio();
				contadorPropiedades++;
			}
			
		}
		if(contadorPropiedades==0) {
			return 0.0;
		}
		promedio = sumatoria/ contadorPropiedades;
		return promedio;
	}
	
	public Double calcularPromedioPrecioDeLasCasas() {
		Double sumatoria = 0.0;
		Double promedio = 0.0;
		Integer cantidadDeCasas = 0;
		
		for(Propiedad actual : propiedades) {
			if(actual instanceof Casa) {
				sumatoria+=actual.getPrecio();
				cantidadDeCasas++;
			}
			
		}
		if(cantidadDeCasas==0) {
			return 0.0;
		}
		promedio = sumatoria/ cantidadDeCasas;
		return promedio;
		
	}
	public Double calcularPromedioPrecioDeLosDeptos() {
		Double sumatoria = 0.0;
		Double promedio = 0.0;
		Integer cantidadDeDeptos = 0;
		
		for(Propiedad actual : propiedades) {
				sumatoria+=actual.getPrecio();
				cantidadDeDeptos++;
			}
			
		if(cantidadDeDeptos==0) {
			return 0.0;
		}
		promedio = sumatoria/ cantidadDeDeptos;
		return promedio;
		
	}

//	public Boolean verificacionDeDireccionCasa(String calle, Integer numero, String ciudad) {
//		for(Casa actual : casas) {
//			if(actual.getCalle().equals(calle)&& actual.getNumero().equals(numero) && actual.getCiudad().equals(ciudad)) {
//				return false;
//			}
//			
//		}
//		return true;
//	}
//	public Boolean verificacionDeDireccionDepto(String calle, Integer nroDepto, Integer piso, String ciudad) {
//		for(Departamento actual : departamentos) {
//			if(actual.getCalle().equals(calle)&& actual.getNumero().equals(nroDepto) 
//					&& actual.getPiso().equals(piso) && actual.getCiudad().equals(ciudad)) {
//				return false;
//			}
//			
//		}
//		return true;
//	}
	public Cliente buscarCliente(Integer dni) {
		Cliente clienteABuscar = null;
		for(Cliente cliente : clientes) {
			if(cliente.getDni().equals(dni)){
				clienteABuscar = cliente;
				return clienteABuscar;
			}
		}
		return null;
	}
	
	public ArrayList<Venta> mostrarVentas(){
		ArrayList<Venta> propiedadesVendidas = null;
		
		if(ventas.size()>0) {
			propiedadesVendidas = new ArrayList<>();
			for(Venta venta : ventas) {
				propiedadesVendidas.add(venta);
			}
		}
		return propiedadesVendidas;
	}
	
	public ArrayList<Alquiler> mostrarAlquileres(){
		ArrayList<Alquiler> propiedadesAlquiladas = null;
	
		if(alquileres.size()>0) {
			propiedadesAlquiladas = new ArrayList<>();
			for(Alquiler alquiler : alquileres) {
				propiedadesAlquiladas.add(alquiler);
			}
		}
		return propiedadesAlquiladas;
	}
}


