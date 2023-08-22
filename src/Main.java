import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String path = "C:\\Windows\\Temp\\produtos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Product> list = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String fields[] = line.split(",");
                list.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = br.readLine();
            }

            double media = list.stream()
                    .map(p -> p.getPreco())
                    .reduce(0.0, (x, y) -> x + y) / list.size();

            System.out.println("Preço média: " + String.format("%.2f", media));

            Comparator<String> comparator = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> nomes = list.stream()
                    .filter(p -> p.getPreco() < media)
                    .map(p -> p.getNome())
                    .sorted(comparator.reversed())
                    .collect(Collectors.toList());

            nomes.forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}