package infinigold;

import java.util.Arrays
import org.junit.Assert

class MyClassTest {

  @org.junit.Test
  fun findSequencesOne() {

    val inputList = Arrays.asList(2, 10, 4, 3, 8, 6, 9, 9, 18, 27)
    val sequence = MyClass.findSequences(inputList)
    var expected = Arrays.asList(2, 4, 8)
    var actual = sequence[0]
    Assert.assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())

    expected = Arrays.asList(3, 9, 27)
    actual = sequence[1]
    Assert.assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
  }

  @org.junit.Test
  fun impossibleSequence() {
    val inputList = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1)
    val sequence = MyClass.findSequences(inputList)
    Assert.assertEquals(sequence.size.toLong(), 0)
  }

  @org.junit.Test
  fun anotherSequence() {
    val inputList = Arrays.asList(5, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 125)
    val sequence = MyClass.findSequences(inputList)
    val expected = Arrays.asList(5, 25)
    val actual = sequence[0]
    Assert.assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
  }

}