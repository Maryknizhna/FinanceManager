import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.fail;

public class InformationTest {
    private static void clearInf(){
        File file = new File("data.bin");
        if (file.exists() && !file.isDirectory()){
            file.delete();
        }
    }

    @BeforeAll
    static void Stat(){
        clearInf();
    }

    @Test
    void Tests(){
        Information information;
        Product product;
        Report report;
        try {
            information = new Information();
        } catch (IOException e) {
            fail("I/O error");
            return;
        }

        product = new Product("булка", "2000.01.01", 1000);
        report = information.buyProduct(product);
        Assertions.assertEquals(report.getMaxCategory().getCategory(), "еда");
        Assertions.assertEquals(report.getMaxDayCategory().getCategory(), "еда");
        Assertions.assertEquals(report.getMaxMonthCategory().getCategory(), "еда");
        Assertions.assertEquals(report.getMaxYearCategory().getCategory(), "еда");

        product = new Product("тапки", "2000.02.01", 100);
        report = information.buyProduct(product);
        Assertions.assertEquals(report.getMaxCategory().getCategory(), "еда");
        Assertions.assertEquals(report.getMaxDayCategory().getCategory(), "одежда");
        Assertions.assertEquals(report.getMaxMonthCategory().getCategory(), "одежда");
        Assertions.assertEquals(report.getMaxYearCategory().getCategory(), "еда");

        product = new Product("мыло", "2000.02.02", 10);
        report = information.buyProduct(product);
        Assertions.assertEquals(report.getMaxCategory().getCategory(), "еда");
        Assertions.assertEquals(report.getMaxDayCategory().getCategory(), "быт");
        Assertions.assertEquals(report.getMaxMonthCategory().getCategory(), "одежда");
        Assertions.assertEquals(report.getMaxYearCategory().getCategory(), "еда");

        product = new Product("скрепка", "2001.01.01", 1);
        report = information.buyProduct(product);
        Assertions.assertEquals(report.getMaxCategory().getCategory(), "еда");
        Assertions.assertEquals(report.getMaxDayCategory().getCategory(), "другое");
        Assertions.assertEquals(report.getMaxMonthCategory().getCategory(), "другое");
        Assertions.assertEquals(report.getMaxYearCategory().getCategory(), "другое");
    }

    @AfterAll
    static void Fin(){
        clearInf();
    }
}
