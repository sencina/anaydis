package anaydis.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TSTTester {

    private final TSTMap<Integer> map = new TSTMap<>();

    @Test
    public void putTest(){
        map.clear();
        Assert.assertNull(map.put("carlos", 1));
        assertEquals((Integer) 1,map.put("carlos",2));
        Assert.assertNull(map.put("carla", 1));
        assertEquals((Integer) 1,map.get("carla"));
    }

    @Test
    public void sizeTest(){

        map.clear();
        map.put("carlos",1);
        assertEquals(1,map.size());
        map.put("carlos",2);
        assertEquals(1,map.size());
        map.put("carla",1);
        assertEquals(2,map.size());
    }

    @Test
    public void getTest(){

        map.clear();
        map.put("carlos",2);
        map.put("carla",1);

        assertEquals((Integer) 2,map.get("carlos"));
        assertEquals((Integer) 1,map.get("carla"));
        Assert.assertNull( map.get("noman"));

    }

    @Test
    public void containsTest(){

        map.clear();
        map.put("carlos",2);
        map.put("carla",1);

        assertTrue(map.containsKey("carla"));
        assertFalse(map.containsKey("carlas"));
    }

    @Test
    public void clearTest(){

        map.clear();
        map.put("carlos",2);
        map.put("carla",1);

        assertTrue(map.size() > 0);

        map.clear();
        assertEquals(0,map.size());
        Assert.assertNull(map.get("carlos"));
        Assert.assertNull(map.get("carla"));
    }

    @Test
    public void autoCompleteTest(){

        map.clear();
        map.put("carlos",2);
        map.put("carla",1);

        List<String> completedList = map.autoComplete("ca");

        assertEquals(2,completedList.size());
        assertTrue(completedList.contains("carlos"));
        assertTrue(completedList.contains("carla"));
    }

    @Test
    public void wildCard(){

        map.clear();
        map.put("pepe",1);
        map.put("pone",1);
        map.put("pose",1);
        map.put("paco",1);
        map.put("pare",1);

        List<String> wildList = map.wildCard("p**e");
        Assert.assertEquals(4, wildList.size());
        Assert.assertFalse(wildList.contains("paco"));
        Assert.assertTrue(wildList.contains("pepe"));

    }


}
