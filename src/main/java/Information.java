import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Information {
    final private String categoriesFile = "categories.tsv";
    final private String saveFile = "data.bin";
    private Map<String, String> itemsForBuy;
    private ArrayList<Product> products;
    protected void ParseCategories() throws IOException {
        try(BufferedReader bufReader = new BufferedReader(new FileReader(categoriesFile))){
            String line;
            while ((line = bufReader.readLine()) != null)
            {
                String[] words = line.split("\t");
                itemsForBuy.put(words[0], words[1]);
            }
        }
    }

    private void SaveInformation(){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveFile));){
            objectOutputStream.writeObject(products);
        } catch (IOException e) {
            System.out.println("Не могу сохранить информацию");
        }
    }

    private void LoadInformation(){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(saveFile));){
            products = (ArrayList<Product>)objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ранее сохранённая информация не найдена");
        } catch (IOException e) {
            System.out.println("Не могу загрузить информацию");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Information() throws IOException {
        itemsForBuy = new HashMap<>();
        ParseCategories();
        LoadInformation();
    }

    public boolean buyProduct(Product product){
        if (product == null){
            return false;
        }

        if (!Pattern.matches("^\\d{4}[.]\\d{2}[.]\\d{2}$", product.getDate())){
            return false;
        }

        products.add(product);
        SaveInformation();
        return true;
    }

    public Report GetReport() {
        Report report = new Report();

        return report;
    }
}
