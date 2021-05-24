package parsec4j.api;

public interface CombinatorsToken {


	public static ParserToken oneChar(char c) {
		return input -> {
			if (input.length() == 0) {
				return ParserResult.error(input);
			}
			return input.charAt(0) == c 
					? ParserResult.success(input.substring(1), String.valueOf(c))
					: ParserResult.error(input);
		};
	}

	public static ParserToken noneOf(String match) {
		return input -> {
			StringBuilder result = new StringBuilder();
			int index = 0;
			for(;index < input.length(); index++) {
				char c = input.charAt(index);
				if (match.indexOf(c) < 0) {
					result.append(c);
				} else {
					return ParserResult.success(input.substring(index), result.toString());
				}
			}
			String nextInput = input.substring(index);
			return ParserResult.success(nextInput, result.toString());
		};
	}

	public static ParserToken tag(String match) {
		return input -> 
			(input.startsWith(match)) 
				? ParserResult.success(input.substring(match.length()), match)
				: ParserResult.error(input);
	}

	public static ParserToken escapedTransform(ParserToken normal, Character escape, ParserToken transform) {
		return input -> {
			ParserResult<String> parseResult = normal.parse(input);
			while (parseResult.isOk()) {
				String resultString = parseResult.result.get();
				ParserResult<String> parseEsacape = oneChar(escape).parse(parseResult.getNextInput());
				if (parseEsacape.isError()) {
					return parseResult;
				}
				ParserResult<String> transformResult = transform.parse(parseEsacape.nextInput);
				if (transformResult.isError()) {
					return transformResult;
				}
				resultString = resultString + transformResult.result.get();
				parseResult.setResult(resultString);
				ParserResult<String> normalResult = normal.parse(transformResult.getNextInput());
				if (normalResult.isOk()) {
					resultString = resultString + normalResult.result.get();
					parseResult = normalResult;
					parseResult.setResult(resultString);
				} else {
					return parseResult;
				}
				
			}
			return parseResult;
		};
	}

}
