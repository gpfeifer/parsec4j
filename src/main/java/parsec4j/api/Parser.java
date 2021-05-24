package parsec4j.api;

@FunctionalInterface
public interface Parser<T> {
	ParserResult<T> parse(String input);
	
	
}
