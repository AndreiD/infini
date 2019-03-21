import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

public class MyClassBTest {

    @org.junit.Test
    public void findSequencesOne() {

        List<Integer> inputList = Arrays.asList(2, 10, 4, 3, 8, 6, 9, 9, 18, 27);
        List<List<Integer>> sequence = MyClassB.findSequences(inputList);
        List<Integer> expected = Arrays.asList(2, 4, 8);
        List<Integer> actual = sequence.get(0);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());

        expected = Arrays.asList(3, 9, 27);
        actual = sequence.get(1);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @org.junit.Test
    public void impossibleSequence() {
        List<Integer> inputList = Arrays.asList(9,8,7,6,5,4,3,2,1);
        List<List<Integer>> sequence = MyClassB.findSequences(inputList);
        Assert.assertEquals(sequence.size(), 0);
    }

    @org.junit.Test
    public void anotherSequence() {
        List<Integer> inputList = Arrays.asList(5,0,0,0,0,25,0,0,0,0,0,125);
        List<List<Integer>> sequence = MyClassB.findSequences(inputList);
        List<Integer> expected = Arrays.asList(5, 25);
        List<Integer> actual = sequence.get(0);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}


