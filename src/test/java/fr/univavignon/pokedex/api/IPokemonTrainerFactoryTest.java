package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokemonTrainerFactoryTest {
	@Mock protected IPokemonTrainerFactory pokemonTrainerFactoryMock;
	@Mock protected IPokedexFactory pokedexFactoryMock;
	@Mock private IPokedex pokedexMock;
	private Pokemon pokemon = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before
	public void setUp() throws PokedexException {
		PokemonTrainer pokemonTrainer = new PokemonTrainer("trainer2", Team.INSTINCT, pokedexMock);
		Mockito.when(pokemonTrainerFactoryMock.createTrainer("trainer2", Team.INSTINCT, pokedexFactoryMock)).thenReturn(pokemonTrainer);
		Mockito.when(pokedexMock.size()).thenReturn(1);
		Mockito.when(pokedexMock.addPokemon(pokemon)).thenReturn(0);
		Mockito.when(pokedexMock.getPokemon(0)).thenReturn(pokemon);
		
		
	}

	@Test 
	public void testPokemonTrainer() throws PokedexException {
		
		PokemonTrainer pokemonTrainer = pokemonTrainerFactoryMock.createTrainer("trainer2", Team.INSTINCT, pokedexFactoryMock);
		assertEquals("trainer2", pokemonTrainer.getName());
		assertEquals(Team.INSTINCT, pokemonTrainer.getTeam());
		int index = pokemonTrainer.getPokedex().addPokemon(pokemon);
		assertEquals(1, pokemonTrainer.getPokedex().size());
		
		PokemonTrainer pokemonTrainer2 = pokemonTrainerFactoryMock.createTrainer("trainer2", Team.INSTINCT, pokedexFactoryMock);
		assertEquals("trainer2", pokemonTrainer2.getName());
		assertEquals(Team.INSTINCT, pokemonTrainer2.getTeam());
		assertEquals(1, pokemonTrainer2.getPokedex().size());
		assertEquals(pokemon.getName(), pokemonTrainer2.getPokedex().getPokemon(index).getName());
		assertEquals(pokemon.getIndex(), pokemonTrainer2.getPokedex().getPokemon(index).getIndex());
		
	}
	
	@After
	public void eraseFiles(){
		try{
			File trainer2 = new File("trainers/trainer2.ser");
			trainer2.delete();
		}catch(Exception e){
			//Le fichier n'existe pas on fait rien
		}
		
	}
	
}
