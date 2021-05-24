package parsec4j.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static parsec4j.api.CombinatorsMixed.seperatedList1;
import static parsec4j.api.CombinatorsToken.oneChar;
import static parsec4j.api.CombinatorsToken.tag;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import parsec4j.api.Parser;

public class CombinatorMixedTest {

	@Test
	public void testSeperatedList1() {
		Parser<List<String>> parser = seperatedList1(tag("a"), oneChar(','));
//		assertEquals(Arrays.asList("a"), parser.parse("a").getResult());
		assertEquals(Arrays.asList("a", "a"), parser.parse("a,a").getResult());
		
	//	assertEquals(Arrays.asList("aa"), parser.parse("ab,").getResult());

	}

}
