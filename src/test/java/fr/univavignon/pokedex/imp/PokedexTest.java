package fr.univavignon.pokedex.imp;

import org.junit.Before;

import fr.univavignon.pokedex.api.IPokedexTest;

public class PokedexTest extends IPokedexTest {

	@Before
	public void setUp() {
		pokedexMock = new Pokedex(new PokemonMetadataProvider(), new PokemonFactory());
	}

}
