package anaydis.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BinaryTrieTest {

    private final BinaryTrieMap<Integer> map = new BinaryTrieMap<>();

    @Test
    public void putTest(){
        map.clear();
        Assert.assertNull(map.put("carlos", 1));
        Assert.assertEquals((Integer) 1,map.put("carlos",2));
        Assert.assertNull(map.put("carla", 1));
        Assert.assertEquals((Integer) 1,map.get("carla"));
    }

    @Test
    public void sizeTest(){

        map.clear();
        map.put("carlos",1);
        Assert.assertEquals(1,map.size());
        map.put("carlos",2);
        Assert.assertEquals(1,map.size());
        map.put("carla",1);
        Assert.assertEquals(2,map.size());
    }

    @Test
    public void getTest(){

        map.clear();
        map.put("carlos",2);
        map.put("carla",1);

        Assert.assertEquals((Integer) 2,map.get("carlos"));
        Assert.assertEquals((Integer) 1,map.get("carla"));
        Assert.assertNull( map.get("noman"));

    }

    @Test
    public void containsTest(){

        map.clear();
        map.put("carlos",2);
        map.put("carla",1);

        Assert.assertTrue(map.containsKey("carla"));
        Assert.assertFalse(map.containsKey("carlas"));
    }

    @Test
    public void clearTest(){

        map.clear();
        map.put("carlos",2);
        map.put("carla",1);

        Assert.assertTrue(map.size() > 0);

        map.clear();
        Assert.assertEquals(0,map.size());
        Assert.assertNull(map.get("carlos"));
        Assert.assertNull(map.get("carla"));
    }


}
