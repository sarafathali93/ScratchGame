package game.scratchgame;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameConfigParser {

	 public static GameConfig parse(String configFileName) throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        System.out.println("Config File Path : "+configFileName);
//	        JsonNode rootNode = mapper.readTree(new File(configFilePath));
	        InputStream inputStream = GameConfigParser.class.getResourceAsStream("/" + configFileName);
	        if (inputStream == null) {
	            throw new IOException("Config file not found: " + configFileName);
	        }
	        JsonNode rootNode = mapper.readTree(inputStream);

	        int columns = rootNode.path("columns").asInt();
	        int rows = rootNode.path("rows").asInt();
	        JsonNode symbolsNode = rootNode.path("symbols");

	        Map<String, Symbol> symbols = new HashMap<>();
	        Map<String, Symbol> bonusSymbols = new HashMap<>();
	        Map<String, Symbol> standardSymbols = new HashMap<>();
	        symbolsNode.fields().forEachRemaining(entry -> {
	            String symbolName = entry.getKey();
	            JsonNode symbolNode = entry.getValue();
	            Symbol symbol = new Symbol(symbolName, symbolNode.path("reward_multiplier").asDouble(), SymbolType.valueOf(symbolNode.path("type").asText().toUpperCase()));
	            if (symbolNode.has("extra")) {
	                symbol.setExtra(symbolNode.path("extra").asInt());
	            }
	            if (symbolNode.has("impact")) {
	                symbol.setImpact(symbolNode.path("impact").asText());
	            }
	            if (symbolNode.path("type").equals("bonus")) {
	            	bonusSymbols.put(symbolName, symbol);
	            } else if (symbolNode.path("type").equals("bonus")) {
	            	standardSymbols.put(symbolName, symbol);
	            }
	            symbols.put(symbolName, symbol);
	        });

	        List<StandardSymbolProbability> standardSymbolsProbabilities = new ArrayList<>();
	        JsonNode standardSymbolsNode = rootNode.path("probabilities").path("standard_symbols");
	        standardSymbolsNode.forEach(node -> {
	            int column = node.path("column").asInt();
	            int row = node.path("row").asInt();
	            Map<String, Integer> symbolProbabilities = new HashMap<>();
	            node.path("symbols").fields().forEachRemaining(entry -> {
	                symbolProbabilities.put(entry.getKey(), entry.getValue().asInt());
	            });
	            standardSymbolsProbabilities.add(new StandardSymbolProbability(column, row, symbolProbabilities));
	        });

	        Map<String, Integer> bonusSymbolProbabilities = new HashMap<>();
	        JsonNode bonusSymbolsNode = rootNode.path("probabilities").path("bonus_symbols").path("symbols");
	        bonusSymbolsNode.fields().forEachRemaining(entry -> {
	            bonusSymbolProbabilities.put(entry.getKey(), entry.getValue().asInt());
	        });

	        Map<String, WinCombination> winCombinations = new HashMap<>();
	        JsonNode winCombinationsNode = rootNode.path("win_combinations");
	        winCombinationsNode.fields().forEachRemaining(entry -> {
	            String winCombinationName = entry.getKey();
	            JsonNode winCombinationNode = entry.getValue();
	            double rewardMultiplier = winCombinationNode.path("reward_multiplier").asDouble();
	            WinConditionType winConditionType = WinConditionType.valueOf(winCombinationNode.path("when").asText().toUpperCase());
	            int count = winCombinationNode.path("count").asInt();
	            String group = winCombinationNode.path("group").asText();
	            List<List<String>> coveredAreas = new ArrayList<>();
	            if (winCombinationNode.has("covered_areas")) {
	                JsonNode coveredAreasNode = winCombinationNode.path("covered_areas");
	                coveredAreasNode.forEach(areaNode -> {
	                    List<String> area = new ArrayList<>();
	                    areaNode.forEach(cell -> {
	                        area.add(cell.asText());
	                    });
	                    coveredAreas.add(area);
	                });
	            }
	            WinCombination winCombination = new WinCombination(winCombinationName, rewardMultiplier, winConditionType, count, group, coveredAreas);
	            winCombinations.put(winCombinationName, winCombination);
	        });

	        return new GameConfig(columns, rows, symbols, bonusSymbols, standardSymbols, standardSymbolsProbabilities, bonusSymbolProbabilities, winCombinations);
	    }

	 
}
