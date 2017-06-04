package direccion;

import excepciones.direccion.NoHayDireccionPosibleException;
import org.junit.Assert;
import org.junit.Test;

import tablero.Posicion;

public class DireccionTest {

	@Test
	public void testCreoDireccionNorteDevuelveEnX0EnY1(){
		Norte norte = new Norte();
		Assert.assertEquals(norte.getDireccionX(), 0);
		Assert.assertEquals(norte.getDireccionY(), 1);
	}
	
	@Test
	public void testCreoDireccionSurDevuelveEnX0EnYmenos1(){
		Sur sur = new Sur();
		Assert.assertEquals(sur.getDireccionX(), 0);
		Assert.assertEquals(sur.getDireccionY(), -1);
	}
	
	@Test
	public void testCreoDireccionEsteDevuelveEnX1EnY0(){
		Este este = new Este();
		Assert.assertEquals(este.getDireccionX(), 1);
		Assert.assertEquals(este.getDireccionY(), 0);
	}
	
	@Test
	public void testCreoDireccionOesteDevuelveEnXmenos1EnY0(){
		Oeste oeste = new Oeste();
		Assert.assertEquals(oeste.getDireccionX(),-1);
		Assert.assertEquals(oeste.getDireccionY(), 0);
	}

	@Test
	public void testCreoDireccionSuroesteDevuelveEnXmenos1EnYmenos1(){
		Suroeste suroeste = new Suroeste();
		Assert.assertEquals(suroeste.getDireccionX(), -1);
		Assert.assertEquals(suroeste.getDireccionY(), -1);
	}
	
	@Test
	public void testCreoDireccionSuresteDevuelveEnX1EnYmenos1(){
		Sureste sureste = new Sureste();
		Assert.assertEquals(sureste.getDireccionX(), 1);
		Assert.assertEquals(sureste.getDireccionY(), -1);
	}
	
	@Test
	public void testCreoDireccionNoroesteDevuelveEnXmenos1EnY1(){
		Noroeste noroeste = new Noroeste();
		Assert.assertEquals(noroeste.getDireccionX(), -1);
		Assert.assertEquals(noroeste.getDireccionY(), 1);
	}
	
	@Test
	public void testCreoDireccionNoresteDevuelveEnX1EnY1(){
		Noreste noreste = new Noreste();
		Assert.assertEquals(noreste.getDireccionX(), 1);
		Assert.assertEquals(noreste.getDireccionY(), 1);
	}

	@Test
	public void testPidoDireccionConPosicionesEnDireccionNorteDevuelveInstanciaDeClaseNorte(){
		Posicion posicionInicial = new Posicion (3,1);
		Posicion posicionFinal = new Posicion (3,5);
		Direccion direccionResultado;
		
		try {
			direccionResultado = Direccion.getDireccion(posicionInicial, posicionFinal);
			Assert.assertEquals(direccionResultado.getClass(), Norte.class);	
		}catch (NoHayDireccionPosibleException e) {}
		
	}

	@Test
	public void testPidoDireccionConPosicionesEnDireccionSurDevuelveInstanciaDeClaseSur(){
		Posicion posicionInicial = new Posicion (3,5);
		Posicion posicionFinal = new Posicion (3,1);
		Direccion direccionResultado;
		
		try{
			direccionResultado = Direccion.getDireccion(posicionInicial, posicionFinal);
			Assert.assertEquals(direccionResultado.getClass(), Sur.class);	
		}catch (NoHayDireccionPosibleException e) {}
	}

	@Test
	public void testPidoDireccionConPosicionesEnDireccionEsteDevuelveInstanciaDeClaseEste(){
		Posicion posicionInicial = new Posicion (1,5);
		Posicion posicionFinal = new Posicion (3,5);
		Direccion direccionResultado;
		
		try{
			direccionResultado = Direccion.getDireccion(posicionInicial, posicionFinal);
			Assert.assertEquals(direccionResultado.getClass(), Este.class);	
		}catch (NoHayDireccionPosibleException e) {}
	}
	
	@Test
	public void testPidoDireccionConPosicionesEnDireccionOesteDevuelveInstanciaDeClaseOeste(){
		Posicion posicionInicial = new Posicion (3,5);
		Posicion posicionFinal = new Posicion (1,5);
		Direccion direccionResultado;
		try{
			direccionResultado = Direccion.getDireccion(posicionInicial, posicionFinal);
			Assert.assertEquals(direccionResultado.getClass(), Oeste.class);	
		}catch (NoHayDireccionPosibleException e) {}
	}
		
	@Test
	public void testPidoDireccionConPosicionesEnDireccionNoresteDevuelveInstanciaDeClaseNoreste(){
		Posicion posicionInicial = new Posicion (1,2);
		Posicion posicionFinal = new Posicion (3,4);
		Direccion direccionResultado;
		try{
			direccionResultado = Direccion.getDireccion(posicionInicial, posicionFinal);
			Assert.assertEquals(direccionResultado.getClass(), Noreste.class);	
		}catch (NoHayDireccionPosibleException e) {}
	}

	@Test
	public void testPidoDireccionConPosicionesEnDireccionSuroesteDevuelveInstanciaDeClaseSuroeste(){
		Posicion posicionInicial = new Posicion (3,4);
		Posicion posicionFinal = new Posicion (1,2);
		Direccion direccionResultado;
		try{
			direccionResultado = Direccion.getDireccion(posicionInicial, posicionFinal);
			Assert.assertEquals(direccionResultado.getClass(), Suroeste.class);	
		}catch (NoHayDireccionPosibleException e) {}
	}
	
	@Test
	public void testPidoDireccionConPosicionesEnDireccionNoresteDevuelveInstanciaDeClaseNoroeste(){
		Posicion posicionInicial = new Posicion (4,2);
		Posicion posicionFinal = new Posicion (2,4);
		Direccion direccionResultado;

		try{
			direccionResultado = Direccion.getDireccion(posicionInicial, posicionFinal);
			Assert.assertEquals(direccionResultado.getClass(), Noroeste.class);	
		}catch (NoHayDireccionPosibleException e) {}
	}
	
	@Test
	public void testPidoDireccionConPosicionesEnDireccionSuresteDevuelveInstanciaDeClaseSureste(){
		Posicion posicionInicial = new Posicion (2,4);
		Posicion posicionFinal = new Posicion (4,2);
		Direccion direccionResultado;
		
		try {
			direccionResultado = Direccion.getDireccion(posicionInicial, posicionFinal);
			Assert.assertEquals(direccionResultado.getClass(), Sureste.class);	
		} catch (NoHayDireccionPosibleException e) {}		
	}
	
	@Test (expected = NoHayDireccionPosibleException.class)
	public void testPidoDireccionConDosPosicionesIgualesDevuelveNoHayDireccionPosibleException() throws NoHayDireccionPosibleException{
		Posicion posicionInicial = new Posicion (2,4);
		Posicion posicionFinal = new Posicion (2,4);
		
		Direccion.getDireccion(posicionInicial, posicionFinal);
	}
	
	@Test (expected = NoHayDireccionPosibleException.class)
	public void testPidoDireccionConDosPosicionesNoAlineadasDevuelveNoHayDireccionPosibleException() throws NoHayDireccionPosibleException{
		Posicion posicionInicial = new Posicion (1,1);
		Posicion posicionFinal = new Posicion (3,7);
		
		Direccion.getDireccion(posicionInicial, posicionFinal);
	}

}
