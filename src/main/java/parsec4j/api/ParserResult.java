package parsec4j.api;

import java.util.Optional;

public class ParserResult<T> {
	public ParserResult(Optional<String> error, Optional<T> result, String nextInput) {
		super();
		this.error = error;
		this.result = result;
		this.nextInput = nextInput;
	}

	Optional<String> error;
	Optional<T> result;
	String nextInput;
	
//	public static <S> ParserResult<S> error(String error, String nextInput) {
//		return new ParserResult<>(Optional.of(error), Optional.empty(), nextInput);
//	}

	public static <S> ParserResult<S> error(String error) {
		return new ParserResult<>(Optional.of(error), Optional.empty(), "");
	}

	public static <S> ParserResult<S> success(String nextInput, S result) {
		return new ParserResult<>(Optional.empty(), Optional.of(result), nextInput);
	}

	public boolean isOk() {
		return !isError();
	}

	public boolean isError() {
		return error.isPresent();
	}

	public String getNextInput() {
		return nextInput;
	}
	
	public void setResult(T result) {
		this.result = Optional.of(result);
	}

	public T getResult() {
		return result.get();
	}

}
