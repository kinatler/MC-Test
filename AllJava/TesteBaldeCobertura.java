import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TesteBaldeCobertura {

	private Balde b = null;
	
	@Before
	public void setUp() {
		b = new Balde(3, 5, 8, 4, 4);
	}
	
	@Test
	public void testBalde_getVolume() {
		int[] vol = b.getVolume();
		assertEquals("Balde1 inicializado incorretamente!", 0, vol[0]);
		assertEquals("Balde2 inicializado incorretamente!", 0, vol[1]);
		assertEquals("Balde3 inicializado incorretamente!", 8, vol[2]);		
	}
	
	@Test
	public void testBalde_E1() {
		try {
			b.mover(0,0);
		} catch (Exception e) {
			assertEquals("Mensagem de erro diferente!",
					"Origem precisa ser diferente de destino!",
					e.getMessage());
			return;
		}
	}
	
	@Test
	public void testBalde_E2_1caso_true() {
		try {
			b.mover(-1,0);
		} catch (Exception e) {
			assertEquals("Mensagem de erro diferente!",
					"Origem e destino precisam estar entre 0..2!",
					e.getMessage());
			return;
		}
	}
	
	@Test	
	public void testBalde_E2_2caso_true() {
		try {
			b.mover(3,0);
		} catch (Exception e) {
			assertEquals("Mensagem de erro diferente!",
					"Origem e destino precisam estar entre 0..2!",
					e.getMessage());
			return;
		}
	}	
	
	@Test
	public void testBalde_E2_3caso_true() {
		try {
			b.mover(0,-1);
		} catch (Exception e) {
			assertEquals("Mensagem de erro diferente!",
					"Origem e destino precisam estar entre 0..2!",
					e.getMessage());
			return;
		}
	}	

	@Test
	public void testBalde_E2_4caso_true() {
		try {
			b.mover(0,3);
		} catch (Exception e) {
			assertEquals("Mensagem de erro diferente!",
					"Origem e destino precisam estar entre 0..2!",
					e.getMessage());
			return;
		}
	}
	
	
	
	@Test
	public void testBalde_E3() {
		try {
			b.mover(0, 1);
		}  catch (Exception e) {
			assertEquals("Mensagem de erro diferente!",
					"O balde origem está vazio!",
					e.getMessage());
			return;
		}		
	}
	
	@Test
	public void testBalde_E4() {
		try {
			b.mover(2, 1);
			b.mover(2, 1);
		}  catch (Exception e) {
			assertEquals("Mensagem de erro diferente!",
					"O destino já está cheio!",
					e.getMessage());
			return;
		}			
	}
	
	@Test
	public void testBalde_Solucao() {
		try {
			b.mover(2,1);
			b.mover(1,0);
			b.mover(0,2);
			b.mover(1,0);
			b.mover(2,1);
			b.mover(1,0);
			b.mover(0,2);
		} catch (Exception e) {
			fail("Não deveria gerar uma exceção neste teste!");
		}
	}
	
//	@Test
//	public void testBaldeConstrutor_valoresInvalidos() {
//		try {
//			b = new Balde(-3, 5, 8, 4, 4);
//			fail("Este cenário de inicialização não é válido!");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}	
//	}

}
