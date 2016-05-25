package oscillogscope;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import model.Sql2oModel;

/**
 * @author fbielejec
 */
public class TestSql20Model {

	@Test
	public void testInsertRow() {
		
		String tableName = "test";
		final String expected = "INSERT INTO " + tableName +" (name3,name2,name1) VALUES (2.0,1.0,0.0);";
		
		Sql2oModel model = new Sql2oModel(null);
		
		Map<String, Double> testRow = new HashMap<String, Double>();
		
		testRow.put("name1", 0.0);
		testRow.put("name2", 1.0);
		testRow.put("name3", 2.0);
		
		String result = model.prepareStatement(testRow, tableName);

		Assert.assertEquals(expected, result);
		
		
	}
	
}
