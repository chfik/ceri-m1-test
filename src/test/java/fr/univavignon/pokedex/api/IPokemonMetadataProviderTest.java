package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;


public class IPokemonMetadataProviderTest {
	@Mock private IPokemonMetadataProvider pokemonMetadataProviderMock;
	
	@Before
	public void setUp() throws PokedexException {
			Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(-1)).thenThrow(new PokedexException(""));
	}
	
	@Test(expected=PokedexException.class)
	public void testPokemonNotFoundException() throws PokedexException {
		pokemonMetadataProviderMock.getPokemonMetadata(-1);
	}
	
}
