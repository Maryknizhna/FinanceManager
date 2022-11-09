public class Report {
    private Category maxCategory;
    private Category maxYearCategory;
    private Category maxMonthCategory;
    private Category maxDayCategory;

    public void setMaxCategory(Category maxCategory) {
        this.maxCategory = maxCategory;
    }

    public void setMaxYearCategory(Category maxYearCategory) {
        this.maxYearCategory = maxYearCategory;
    }

    public void setMaxMonthCategory(Category maxMonthCategory) {
        this.maxMonthCategory = maxMonthCategory;
    }

    public void setMaxDayCategory(Category maxDayCategory) {
        this.maxDayCategory = maxDayCategory;
    }

    public Category getMaxCategory() {
        return maxCategory;
    }

    public Category getMaxYearCategory() {
        return maxYearCategory;
    }

    public Category getMaxMonthCategory() {
        return maxMonthCategory;
    }

    public Category getMaxDayCategory() {
        return maxDayCategory;
    }

    public Report(Category maxCategory, Category maxYearCategory, Category maxMonthCategory, Category maxDayCategory) {
        this.maxCategory = maxCategory;
        this.maxYearCategory = maxYearCategory;
        this.maxMonthCategory = maxMonthCategory;
        this.maxDayCategory = maxDayCategory;
    }

    @Override
    public String toString() {
        return "maxCategory\n" + maxCategory.toString() +
                "maxYearCategory\n" + maxYearCategory.toString() +
                "maxMonthCategory\n" + maxMonthCategory.toString() +
                "maxDayCategory\n" + maxDayCategory;
    }
}