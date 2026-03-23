import java.util.Scanner;

public class SmartCalculator {

    public static void main(String[] args) {
         /* Kısaca notum
        == → referans karşılaştırır, String'lerde kullanma
        .equals() → içerik karşılaştırır, her zaman bunu kullan
        Koleksiyona koyacaksan Wrapper, hesaplamada primitive daha verimli
        String'i döngüde birleştiriyorsan StringBuilder kullan
        var tip güvenliğini bozmaz, sadece yazım kolaylığı
        */

        // ✅ 1. var keyword — Java 10+, tip çıkarımı yapar
        // Derleyici sağ taraftaki değere bakarak tipi anlar
        var scanner = new Scanner(System.in);

        System.out.println("=== Smart Calculator ===");

        // ✅ 2. Primitive vs Wrapper farkı
        // primitive: stack'te tutulur, null olamaz, daha hızlı
        double primitiveResult = 0.0;

        // Wrapper: heap'te tutulur, null olabilir, koleksiyonlarda kullanılır
        Double wrapperResult = null; // primitive double bunu yapamaz!

        System.out.print("Enter first number: ");
        // ✅ 3. Autoboxing — double (primitive) otomatik Double (wrapper) olur
        Double num1 = scanner.nextDouble(); // autoboxing burada gerçekleşiyor

        System.out.print("Enter second number: ");
        Double num2 = scanner.nextDouble();

        System.out.print("Choose operation (+, -, *, /): ");
        var operation = scanner.next(); // var burada String tipini çıkarır

        // ✅ 4. String immutability — her + işlemi yeni bir String nesnesi yaratır
        // Çok sayıda birleştirme için StringBuilder kullanmak daha verimlidir
        String resultMessage = "Result: ";

        switch (operation) {
            case "+" -> primitiveResult = num1 + num2;  // unboxing: Double → double
            case "-" -> primitiveResult = num1 - num2;
            case "*" -> primitiveResult = num1 * num2;
            case "/" -> {
                if (num2 == 0) {
                    // ✅ 5. Wrapper'ın gücü: null ile "henüz hesaplanmadı" ifade edilebilir
                    wrapperResult = null;
                    System.out.println("Error: Division by zero!");
                    scanner.close();
                    return;
                }
                primitiveResult = num1 / num2;
            }
            default -> {
                System.out.println("Invalid operation.");
                scanner.close();
                return;
            }
        }

        // ✅ 6. Autoboxing: primitive double → Double wrapper (atama sırasında)
        wrapperResult = primitiveResult;

        // ✅ 7. String Pool: aynı literal iki String birini paylaşır
        String label1 = "result"; // pool'dan alınır
        String label2 = "result"; // aynı nesneyi gösterir!
        String label3 = new String("result"); // yeni nesne, farklı referans

        System.out.println("--- String comparison demo ---");
        System.out.println("label1 == label2      : " + (label1 == label2));         // true  (aynı referans)
        System.out.println("label1 == label3      : " + (label1 == label3));         // false (farklı nesne)
        System.out.println("label1.equals(label3) : " + label1.equals(label3));      // true  (içerik aynı)
        System.out.println("------------------------------");

        // ✅ String birleştirme: + operatörü arka planda StringBuilder kullanır
        resultMessage = resultMessage + wrapperResult;
        System.out.println(resultMessage);

        // ✅ 8. Wrapper metodları — primitive'lerde bu metodlar olmaz
        System.out.println("Max double value : " + Double.MAX_VALUE);
        System.out.println("Is NaN?          : " + Double.isNaN(wrapperResult));

        scanner.close();

    }
}