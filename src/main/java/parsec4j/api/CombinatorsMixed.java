package parsec4j.api;

import java.util.ArrayList;
import java.util.List;

public interface CombinatorsMixed {
	public static <T>  Parser<List<T>> seperatedList1(Parser<T> element, ParserToken seperator) {
		return input -> {
			List<T> result = new ArrayList<>();
			ParserResult<T> parseResult = element.parse(input);
			if (parseResult.isError()) {
				return ParserResult.error(input);
			} else {
				result.add(parseResult.getResult());
			}
			while(true) {
				ParserResult<String> seperatorResult = seperator.parse(parseResult.getNextInput());
				if (seperatorResult.isError()) {
					return ParserResult.success(seperatorResult.getNextInput(), result);
				} else {
					parseResult = element.parse(seperatorResult.getNextInput());
					if (parseResult.isError()) {
						return ParserResult.error(seperatorResult.getNextInput());
					} else {
						result.add(parseResult.getResult());
					}
				}
			}
			
		};
	}

}
