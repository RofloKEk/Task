
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class RTest {

    @Test
    public void testRtriangleIsRight() {
        Rtriangle triangle = RtriangleProvider.getRtriangle();

        int x1 = triangle.getApexX1();
        int y1 = triangle.getApexY1();
        int x2 = triangle.getApexX2();
        int y2 = triangle.getApexY2();
        int x3 = triangle.getApexX3();
        int y3 = triangle.getApexY3();

        System.out.printf("Coordinates: A(%d, %d), B(%d, %d), C(%d, %d)%n", x1, y1, x2, y2, x3, y3);


        int dotProduct1 = (x2 - x1) * (x3 - x1) + (y2 - y1) * (y3 - y1);
        int dotProduct2 = (x1 - x2) * (x3 - x2) + (y1 - y2) * (y3 - y2);
        int dotProduct3 = (x1 - x3) * (x2 - x3) + (y1 - y3) * (y2 - y3);

        System.out.printf("Dot products: %d, %d, %d%n", dotProduct1, dotProduct2, dotProduct3);

        assertTrue(dotProduct1 == 0 || dotProduct2 == 0 || dotProduct3 == 0, "Triangle is not a right triangle");
    }
}
