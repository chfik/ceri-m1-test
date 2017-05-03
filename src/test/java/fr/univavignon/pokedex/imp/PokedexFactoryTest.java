package fr.univavignon.pokedex.imp;

import org.junit.Before;

import fr.univavignon.pokedex.api.IPokedexFactoryTest;

public class PokedexFactoryTest extends IPokedexFactoryTest {

	@Before
	public void setUp() {
		
		pokedexFactoryMock = new PokedexFactory();
		
	}

}
