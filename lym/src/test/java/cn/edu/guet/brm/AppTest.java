package cn.edu.guet.brm;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
    @org.junit.Test
	public void testName() throws Exception {
		String str="{'department': [1, 2]}";
		Object object = JSONObject.parseObject(str).get("department");
		System.out.println(object.getClass());
		if (object instanceof JSONArray) {
			JSONArray department = (JSONArray) object;
			int intValue = department.getIntValue(0);
			List<Object> subList = department.subList(0, department.size());
			for (int i = 0; i < subList.size(); i++) {
				System.out.println(subList.get(i));
			}
			System.out.println(intValue);
		}
	}
    @org.junit.Test
	public void test1() throws Exception {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.println(j);
				if(j==2)
					break;
			}
		}
	}
    @org.junit.Test
	public void test2() throws Exception {
		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		JSONArray A=new JSONArray();
		A.add(list);
		JSONObject n=new JSONObject();
		n.put("department", list);
		System.out.println(n.toString());
		System.out.println(A.toString());
	} 
}
