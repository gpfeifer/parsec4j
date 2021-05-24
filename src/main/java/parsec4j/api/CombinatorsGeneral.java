package parsec4j.api;

public interface CombinatorsGeneral {
	
	@SafeVarargs
	public static <T>  Parser<T> alt(final Parser<T>... parsers) {
		return input -> {
			for (Parser<T> parser : parsers) {
				ParserResult<T> result = parser.parse(input);
				if (result.isOk()) {
					return result;
				}
			}
			return ParserResult.error(input);
		};
	}


}
