package oscillogscope;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestLogParser.class,
	TestRESTApi.class})
public class OscillogscopeTests {
}
