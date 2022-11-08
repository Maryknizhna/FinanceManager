public class Product {
    private String title;
    private String date;
    private Integer sum;

    public Product(String title, String date, Integer sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public Integer getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Bought{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }
}
