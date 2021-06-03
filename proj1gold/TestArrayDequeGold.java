import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudent() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        Integer sadres;
        Integer adsres;
        while(true) {
            double numberBetweenZeroToOne = StdRandom.uniform();
            Integer random = StdRandom.uniform(10);
            if (sad.isEmpty() && ads.isEmpty()) {
                if (numberBetweenZeroToOne < 0.5) {
                    sad.addFirst(random);
                    ads.addFirst(random);
                } else {
                    sad.addLast(random);
                    ads.addLast(random);
                }
            } else {
                if (numberBetweenZeroToOne < 0.25) {
                    sad.addFirst(random);
                    ads.addFirst(random);
                } else if (numberBetweenZeroToOne < 0.5) {
                    sad.addLast(random);
                    ads.addLast(random);
                } else if (numberBetweenZeroToOne < 0.75) {
                    sadres = sad.removeFirst();
                    adsres = ads.removeFirst();
                    assertEquals("StudentArrayDeque is" + " " + sadres + " " +
                            "but ArrayDequeSolution is" + " " + adsres, adsres, sadres);
                } else {
                    sadres = sad.removeLast();
                    adsres = ads.removeLast();
                    assertEquals("StudentArrayDeque is" + " " + sadres + " " +
                            "but ArrayDequeSolution is" + " " + adsres, adsres, sadres);
                }
            }
        }
    }
}

