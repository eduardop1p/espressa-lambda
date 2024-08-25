// A11-Exercicio-fixacao

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: "); // C:\Windows\Temp\in.txt
        String path = sc.nextLine();
        System.out.print("Enter salary: ");
        Double salary = sc.nextDouble();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            List<Employee> employees = new ArrayList<>();

            while (line != null) {
                String[] fields = line.split(",");
                employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));

                line = br.readLine();
            }
            System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary));

            Comparator<String> comparator = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
            List<String> emails = employees.stream().filter(item -> item.getSalary() > salary).map(Employee::getEmail).sorted(comparator).toList();
            emails.forEach(System.out::println);

            Double sumSalary = employees.stream().filter(item -> item.getName().startsWith("M")).map(Employee::getSalary).reduce(0.0, Double::sum);
            System.out.println("Sum of salary of people whose name starts with 'M': " + sumSalary);
        }catch (IOException err) {
            System.out.println(err.getMessage());
        }

        sc.close();
    }
}