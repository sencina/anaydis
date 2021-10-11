package anaydis.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RWayTester {

    private final RWayTrieMap<Integer> map = new RWayTrieMap<>();

    @Test
    public void autoCompleteTest(){
        map.clear();
        map.put("carlos",2);
        map.put("carla",1);
        map.put("coco",7);
        map.put("coca",7);
        map.put("caco",7);
        map.put("cuco",7);
        map.put("cico",7);

        List<String> completedList = map.autoComplete("ca");

        Assert.assertEquals(3,completedList.size());
        Assert.assertTrue(completedList.contains("carlos"));
        Assert.assertTrue(completedList.contains("carla"));
    }

}
