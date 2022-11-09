import java.io.Serializable;

public class Inf implements Serializable {
    private String categ;
    private String date;
    private int sum;

    public void total(int sum) {
        this.sum += sum;
    }

    public Inf(String categ, String date, int sum) {
        this.categ = categ;
        this.date = date;
        this.sum = sum;
    }

    public String getCategory() {
        return categ;
    }

    public String getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }
}