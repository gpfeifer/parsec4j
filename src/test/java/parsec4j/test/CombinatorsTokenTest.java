package parsec4j.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static parsec4j.api.CombinatorsToken.*;

import org.junit.Test;

import parsec4j.api.Parser;
import parsec4j.api.ParserToken;

public class CombinatorsTokenTest {

	@Test
	public void testNoneOf() {
		Parser<String> parser = noneOf(",;");
		assertTrue(parser.parse(("(#)")).isOk());
		assertEquals("aa", parser.parse("aa,").getResult());
		assertEquals("aa", parser.parse(("aa;")).getResult());
		assertEquals("aa", parser.parse(("aa")).getResult());

	}

	@Test
	public void testTag() {
		assertTrue(tag("(#)").parse(("(#)")).isOk());
		assertTrue(tag("(#)").parse(("(")).isError());
	}

	@Test
	public void testEscapedTransform() {
		ParserToken parser = escapedTransform(noneOf("/()!"), '/', oneChar('('));
		assertEquals("abc", parser.parse(("abc")).getResult());
		assertEquals("abc", parser.parse(("abc(")).getResult());
		assertEquals("abc(", parser.parse(("abc/(")).getResult());
		assertTrue("abc(", parser.parse(("abc/x")).isError());
	}


}
