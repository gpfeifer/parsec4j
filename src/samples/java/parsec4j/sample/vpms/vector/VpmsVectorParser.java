package parsec4j.sample.vpms.vector;

import static parsec4j.api.CombinatorsGeneral.alt;
import static parsec4j.api.CombinatorsToken.tag;

import java.util.ArrayList;
import java.util.List;

import parsec4j.api.Parser;
import parsec4j.api.ParserResult;
public class VpmsVectorParser {
	
	
	public static Parser<List<String>> emptyVector() {
		return input -> {
			ParserResult<String> result = tag("(#)").parse(input);
			return result.isOk()
				? ParserResult.success(result.getNextInput(), new ArrayList<>())
				: ParserResult.error(result.getNextInput());
		};
	}

	public static Parser<List<String>> string() {
		return input -> {
			if (input.length() == 0) {
				return ParserResult.success(input, new ArrayList<>());
			}
			if (input.charAt(0) != '(') {
				List<String> result = new ArrayList<>();
				result.add(input.substring(1));
				return ParserResult.success("", result);
			} else {
				
				return ParserResult.error(input);
			}
		};
	}
	
	public List<String> parseValidationVector(String input) {
		ParserResult<List<String>> parseResult = alt(
			emptyVector()
		).parse(input);
		return parseResult.isOk()
			? parseResult.getResult()
			: new ArrayList<>();
	}

}
