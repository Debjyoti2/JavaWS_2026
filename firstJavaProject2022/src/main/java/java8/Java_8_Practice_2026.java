package java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java_8_Practice_2026 {

    static void main() {

        String input = "Java articles are Awesome";

        Stream.of(input.split("")).filter(e->!" ".equals(e))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream()
                .filter(e->e.getValue()>1)
                .findFirst()
                .map(e->e.getKey())
                .get();

        Integer[]nums = {1,2,3,4,5};
        boolean flag = Stream.of(nums).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream()
                .anyMatch(e->e.getValue()>1);
        System.out.println(flag);

        List<Integer> listOfIntegers1 = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        listOfIntegers1.stream().sorted(Collections.reverseOrder()).limit(3).collect(Collectors.toList());
        listOfIntegers1.stream().sorted().limit(3).collect(Collectors.toList());


        String s1 = "RaceCar";
        String s2 = "CarRace";
        Map<Long,String> s1Map = java.util.stream.IntStream.range(0, s1.length())
                .boxed()
                .collect(Collectors.toMap(
                        i -> (long) i,
                        i -> String.valueOf(s1.charAt(i)).toLowerCase(),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        System.out.println(s1Map);


        String str3 = "Java Concept Of The Day";
        String reverseStr =  Stream.of(str3.split(" ")).map(e->new StringBuilder(e).reverse().toString()).collect(Collectors.joining(""));
        System.out.println("reverseStr :: " + reverseStr);

        List<String> listOfStrings = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");
        String most = listOfStrings.stream().collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e->e.getValue()))
                .findFirst()
                .map(e->e.getKey())
                .get();
        System.out.println("most :: " + most);

        List<Employee2> employees = new ArrayList<>();
        employees.add(new Employee2("John", "IT", 75000, 28, "Male"));
        employees.add(new Employee2("Sarah", "HR", 80000, 32, "Female"));
        employees.add(new Employee2("Mike", "Finance", 80000, 35, "Male"));
        employees.add(new Employee2("Emily", "Marketing", 60000, 26, "Female"));
        employees.add(new Employee2("David", "IT", 85000, 40, "Male"));
        employees.add(new Employee2("Lisa", "HR", 70000, 30, "Female"));
        employees.add(new Employee2("James", "Finance", 90000, 45, "Male"));
        employees.add(new Employee2("Anna", "Marketing", 55000, 24, "Female"));
        employees.add(new Employee2("Robert", "IT", 78000, 33, "Male"));
        employees.add(new Employee2("Maria", "HR", 68000, 29, "Female"));

        //1.Max Salary Emp by Department
        Map<String,Optional<Employee2>> maxSalarybyDept = employees.stream()
                .collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.maxBy(Comparator.comparing(Employee2::getSalary))));
        System.out.println("\nMax Salary by Department:");
        maxSalarybyDept.entrySet().stream().forEach(e->System.out.println(e.getKey() + "   " + e.getValue().get()));


       //2.Average Salary by Department, where avg sal >80000
        Map<String,Double> avgSalbyDept = employees.stream().collect(Collectors.groupingBy(e->e.getDepartment(),Collectors.averagingDouble(Employee2::getSalary)))
                        .entrySet().stream().filter(e->e.getValue()>80000)
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
        System.out.println("\nAverage Salary by Department:");
        avgSalbyDept.entrySet().stream().forEach(e->System.out.println(e.getKey() + "   " + e.getValue()));

        //3.Partition by Salary (Above/Below Average)
       double avgSal = employees.stream().collect(Collectors.averagingDouble(e->e.getSalary()));
       Map<Boolean,List<Employee2>> partitionedBySalary =employees.stream().collect(Collectors.partitioningBy(e->e.getSalary()>=avgSal));
        System.out.println("\nPartition by Salary (Above/Below Average):");
        System.out.println("Above Average Salary: " + partitionedBySalary.get(true));
        System.out.println("Below Average Salary: " + partitionedBySalary.get(false));

        //4.Second Highest Salary
        double secondhighestSal = employees.stream().sorted(Comparator.comparingDouble(Employee2::getSalary).reversed())
                .skip(1).findFirst().get().getSalary();
        System.out.println("Second Highest Salary: " + secondhighestSal);

        //Employees by Department (Sorted Names)
        Map<String, String> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getDepartment(),
                        Collectors.mapping(
                                e -> e.getName(),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        names -> names.stream().sorted().collect(Collectors.joining(", "))
                                )
                        )
                ));
        System.out.println("\nEmployees by Department (Sorted Names):");
        employeesByDept.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));


        //5.Department with highest salary
        Long deptCount = employees.stream().map(Employee2::getDepartment).distinct().count();
        String departmentWithHighestSalary = employees.stream().collect(Collectors.groupingBy(e->e.getDepartment(),Collectors.summingDouble(Employee2::getSalary)))
                .entrySet().stream()
                .sorted(Comparator.comparingDouble(e->e.getValue()))
                .skip(deptCount-1)
                .findFirst().get().getKey();
        System.out.println("Department with highest salary: " + departmentWithHighestSalary);

       //6. Rank employees by salary within each dept
        Map<String,Map<String,Double>> abc = employees.stream().collect(Collectors.groupingBy(e->e.getDepartment(),
                Collectors.toMap(e->e.getName(),e->e.getSalary(),(v1,v2)->v1)));
        System.out.println("\nRank employees by salary within each dept:" +  abc);


        //7. Find Departments where all employees earn >70000
        List<String> deptsWithAllHighSalaries = employees.stream().collect(Collectors.groupingBy(e->e.getDepartment(),Collectors.toList()))
                        .entrySet().stream().filter(e->e.getValue().stream().allMatch(e1->e1.getSalary()>70000))
                        .map(e->e.getKey())
                                .collect(Collectors.toList());
        System.out.println("\nDepartments where all employees earn >70000: " + deptsWithAllHighSalaries);

       //8. Find duplicate salary
        Set<Double> duplicates = new HashSet<>();
        employees.stream().filter(e->!duplicates.add(e.getSalary())).collect(Collectors.toList())
                .stream().map(e->e.getSalary())
                .forEach(System.out::println);

        //9.First non-repeating character
        String str = "swiss";
        String nonrep = Stream.of(str.split("")).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e->e.getValue()<2)
                .findFirst()
                .map(e->e.getKey())
                .get();
        System.out.println("\nFirst non-repeating character: " + nonrep);


        //10. count the occurance of each char
        String str2 = "banana";
        Stream.of(str2.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream().forEach(e->System.out.println(e.getKey() + " - " + e.getValue()));

        //11. reverse each word (keep the order of words)
        String str4 = "Java is Fun";
        String reversestr= Stream.of(str4.split(" ")).map(e->new StringBuilder(e).reverse().toString()).collect(Collectors.joining(" "));
        System.out.println("\nReversed string: " + reversestr);

        //12. check if both are anagrams
        String Str6 = "listen";
        String Str7 = "silent";
        boolean areAnagrams = Stream.of(Str6.split(""))
                .collect(Collectors.toSet())
                .equals(Stream.of(Str7.split(""))
                        .collect(Collectors.toSet()));
        System.out.println("\nAre anagrams: " + areAnagrams);

        //13. Find pairs of numbers that add up to a target
        int[] intarr = {1,5,7,3,4,6};
        int target = 10;
        for(int i=0;i<intarr.length;i++){
            for(int j=i+1;j<intarr.length;j++){
                if(intarr[i]+intarr[j]==target){
                    System.out.println("Pair: " + intarr[i] + ", " + intarr[j]);
                }
            }
        }

        //14. Partitioning by even/odd
        int[] intarr2 = {1,2,3,4,5,6,7,8};
        Map<Boolean, List<Integer>> partitions = Arrays.stream(intarr2).boxed().collect(Collectors.partitioningBy(e->e%2==0));
        System.out.println("\n14. Partitioning by even/odd: " + partitions);

        //15. Longest word
        List<String> words = Arrays.asList("java", "spring", "microservices", "boot");
        String longestWord= words.stream().collect(Collectors.toMap(e->e,e->e.length()))
                .entrySet()
                .stream().sorted(Comparator.comparing(e->e.getValue()))
                .skip(words.size()-1)
                .map(e->e.getKey())
                .findFirst().get();
        System.out.println("\n15. Longest word: " + longestWord);

        //16. Distinct elements
        int[] intarr3 = {5,3,5,1,3,2,1};
        List<Integer> distincts = Arrays.stream(intarr3).boxed().distinct().collect(Collectors.toList());
        System.out.println("\n16. Distinct elements: " + distincts);

        //17. Sort map by value
        Map<String,Long> newMap = Map.of("apple", 2L, "banana", 1L,"cherry",5L,"date",2L);
        newMap.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .forEach(System.out::println);

        //18. merge two lists into a map
        List<String> keyList = List.of("a","b","c");
        List<Long> valueList = List.of(1L,2L,3L);
        Map<String,Long> mmp = IntStream.range(0,keyList.size()).boxed()
                .collect(Collectors.toMap(e->keyList.get(e),e->valueList.get(e)));
        System.out.println("\n18. merge two lists into a map: " + mmp);

        //19. reverse map
        Map<String,Long> newMap2 = Map.of("a", 1L, "b", 2L,"c",3L,"d",4L);
        Map<Long,String> newMap3 =newMap2.entrySet().stream().collect(Collectors.toMap(e->e.getValue(),e->e.getKey()));
        System.out.println("\n19. newMap3: " + newMap3);

























    }

}

