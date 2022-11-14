import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Information {
    final private String categoriesFile = "categories.tsv";
    final private String saveFile = "data.bin";
    private Map<String, String> itemsForBuy;
    private Collection<String> categories;
    private ArrayList<Inf> products;
    protected void parseCategories() throws IOException {
        itemsForBuy = new HashMap<>();
        try(BufferedReader bufReader = new BufferedReader(new FileReader(categoriesFile))){
            String line;
            while ((line = bufReader.readLine()) != null) {
                String[] words = line.split("\t");
                itemsForBuy.put(words[0], words[1]);
            }
        }
        itemsForBuy.put("другое", "другое");
        categories = itemsForBuy.values().stream().distinct().collect(Collectors.toList());
    }

    private void saveInformation(){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveFile));){
            if (products != null) {
                objectOutputStream.writeObject(products);
            }
        } catch (IOException e) {
            System.out.println("Не могу сохранить информацию");
        }
    }

    private void loadInformation(){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(saveFile));){
            products = (ArrayList<Inf>)objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ранее сохранённая информация не найдена");
        } catch (IOException e) {
            System.out.println("Не могу загрузить информацию");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (products == null){
            products = new ArrayList<>();
        }
    }

    public Information() throws IOException {
        parseCategories();
        loadInformation();
    }

    Category getMaxCategoryReportForPeriod(String date) {
        Map<String, Integer> spendByCategory = products.stream()
                .filter(p -> date == null || p.getDate().startsWith(date))
                .collect(Collectors.groupingBy(Inf::getCategory, Collectors.summingInt(Inf::getSum)));
        Map.Entry<String, Integer> spendMax = null;
        for (Map.Entry<String, Integer> spend : spendByCategory.entrySet()) {
            if (spendMax == null) {
                spendMax = spend;
            } else {
                if (spendMax.getValue() < spend.getValue()) {
                    spendMax = spend;
                }
            }
        }

        return spendMax == null ? null : new Category(spendMax.getKey(), spendMax.getValue());
    }

    private Inf infProduct(Product p){
        String categ = itemsForBuy.get(p.getTitle());
        if (categ == null) {
            categ = "другое";
        }

        return new Inf(categ, p.getDate(), p.getSum());
    }

    public Report buyProduct(Product product){
        if (product == null){
            return null;
        }

        if (!Pattern.matches("^\\d{4}[.]\\d{2}[.]\\d{2}$", product.getDate())){
            return null;
        }

        Inf info = infProduct(product);
        boolean foundMatchedProduct = false;
        for (Inf p : products) {
            if (p.getCategory().equals(info.getCategory()) && p.getDate().equals(info.getDate())){
                p.total(info.getSum());
                foundMatchedProduct = true;
                break;
            }
        }
        if (!foundMatchedProduct) {
            products.add(info);
        }


    Category maxCategory = getMaxCategoryReportForPeriod(null);
    Category maxYearCategory = getMaxCategoryReportForPeriod(product.getDate().substring(0, 4));
    Category maxMonthCategory = getMaxCategoryReportForPeriod(product.getDate().substring(0, 7));
    Category maxDayCategory = getMaxCategoryReportForPeriod(product.getDate());

    Report report = new Report(maxCategory, maxYearCategory, maxMonthCategory, maxDayCategory);

    saveInformation();

        return report;
    }
}
