package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class IPokemonMetadataProviderTest {
	@Mock
	protected IPokemonMetadataProvider pokemonMetadataProviderMock;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before
	public void setUp() throws PokedexException {
		Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(-1)).thenThrow(new PokedexException(""));
		Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0,"Bulbasaur",126,126,90));
		Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(151)).thenThrow(new PokedexException(""));
	}
	
	@Test(expected=PokedexException.class)
	public void testPokemonMetadata0NotFoundException() throws PokedexException {
		pokemonMetadataProviderMock.getPokemonMetadata(-1);
	}

	@Test(expected=PokedexException.class)
	public void testPokemonMetadata151NotFoundException() throws PokedexException {
		pokemonMetadataProviderMock.getPokemonMetadata(151);
	}
	
	@Test 
	public void testPokemonMetadata() throws PokedexException{
		PokemonMetadata pokemonMetadata = pokemonMetadataProviderMock.getPokemonMetadata(0); 
		assertEquals(126,pokemonMetadata.getAttack());
		assertEquals(126,pokemonMetadata.getDefense());
		assertEquals(0,pokemonMetadata.getIndex());
		assertEquals("Bulbasaur",pokemonMetadata.getName());
		assertEquals(90,pokemonMetadata.getStamina());
	}
}
 