package fr.univavignon.pokedex.imp;

import java.io.Serializable;

import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonMetadataProvider implements IPokemonMetadataProvider, Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -4406239882126981838L;

	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		
		if(index < 0 || index > 150)
			throw new PokedexException("Invalid index !");
		
		try {
			String pokemonsDatas = Request.Get("https://raw.githubusercontent.com/PokemonGo-Enhanced/node-pokemongo-data/master/data.json").execute().returnContent().asString();
			JSONArray pokemons = new JSONArray(pokemonsDatas);	
			JSONObject our_pok = pokemons.getJSONObject(index);
			PokemonMetadata pokemonMetadata = getPokemonMetadataFromJSONObject(our_pok);
			return pokemonMetadata;
		} catch (Exception e) {
			throw new PokedexException("error");
		}
		
	}
	
	public PokemonMetadata getPokemonMetadataFromJSONObject(JSONObject data) throws JSONException{
		PokemonMetadata pokemonMetadata;
		pokemonMetadata = new PokemonMetadata(	data.getInt("PkMn")-1,
												data.getString("Identifier"), 
												data.getInt("BaseAttack"), 
												data.getInt("BaseDefense"), 
												data.getInt("BaseStamina"));
		return pokemonMetadata;
		
	}

}
