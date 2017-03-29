package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class IPokemonMetadataProviderTest {
	@Mock private IPokemonMetadataProvider pokemonMetadataProviderMock;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before
	public void setUp() throws PokedexException {
		Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(-1)).thenThrow(new PokedexException(""));
	}
	
	@Test(expected=PokedexException.class)
	public void testPokemonNotFoundException() throws PokedexException {
		pokemonMetadataProviderMock.getPokemonMetadata(-1);
	}
	
}
