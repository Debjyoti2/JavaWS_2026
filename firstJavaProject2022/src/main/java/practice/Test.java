package practice;

public class Test {


	private static Object lock = new Object();
	private static boolean isChar =true;


	public static void main(String[] args) {
		String a = "A";

		String b = "B";

		String c = "A";

		String d = new String("A");

		String e = new String("A");

		//sysout(a==b): false --ok
		System.out.println("sysout(a==b): false " + (a==b));
		
		
		//sysout (a==c) : true --ok
		System.out.println("sysout (a==c) : true " + (a==c));
		
		
		//sysout(a==d) : false
		System.out.println("sysout (a==d) : false " + (a==d));
		
		
		//d==e : false
		System.out.println("sysout (d==e) : false " + (d==e));
		
		
		 //give me some more scenarios like this.. i want to solve..

		// More scenarios to practice:

		// Scenario 1: intern() method
		String f = new String("A").intern();
		System.out.println("a==f (after intern): " + (a==f)); // true

		// Scenario 2: String concatenation with literals
		String g = "A" + "B";
		String h = "AB";
		System.out.println("g==h (literal concat): " + (g==h)); // true

		// Scenario 3: String concatenation with variables
		String i = "A";
		String j = i + "B";
		System.out.println("h==j (variable concat): " + (h==j)); // false

		// Scenario 4: equals() vs ==
		System.out.println("d.equals(e): " + d.equals(e)); // true
		System.out.println("d.equals(a): " + d.equals(a)); // true

		// Scenario 5: null comparison
		String k = null;
		System.out.println("k==null: " + (k==null)); // true
		// System.out.println(k.equals("A")); // NullPointerException

		// Scenario 6: empty strings
		String l = "";
		String m = new String("");
		System.out.println("l==m (empty strings): " + (l==m)); // false
		System.out.println("l.equals(m): " + l.equals(m)); // true

		// Scenario 7: String pool with same value
		String n = "Java";
		String o = "Java";
		String p = new String("Java").intern();
		System.out.println("n==o: " + (n==o)); // true
		System.out.println("n==p: " + (n==p)); // true

		// Scenario 8: Case sensitivity
		String q = "java";
		System.out.println("n==q (case sensitive): " + (n==q)); // false
		System.out.println("n.equalsIgnoreCase(q): " + n.equalsIgnoreCase(q)); // true

		// Scenario 9: substring
		String r = "JavaProgramming";
		String s = r.substring(0, 4);
		String t = "Java";
		System.out.println("s==t (substring): " + (s==t)); // false (substring creates new object)
		System.out.println("s.equals(t): " + s.equals(t)); // true

		// Scenario 10: valueOf vs new
		String u = String.valueOf(10);
		String v = new String("10");
		System.out.println("u==v: " + (u==v)); // false

		// Multithreading A1B2C3
		System.out.print("\nA1B2C3 pattern: ");
		//printA1B2C3();



		//Solution 2
		Thread charThread = new Thread(()->{
			char charstr = 'A';
			for(int i1=0;i1<3;i1++){
					synchronized (lock){
					if(!isChar){
                        try {
                            lock.wait();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
					System.out.print(charstr++);
					isChar=false;
					lock.notify();
				}
			}
		});

		Thread numThread = new Thread(()->{
			for(int j1=0;j1<3;j1++){
				synchronized (lock){
					if(isChar){
                        try {
                            lock.wait();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
					System.out.print(j1+1);
					isChar=true;
					lock.notify();
				}
			}

		});

		charThread.start();
		numThread.start();









	}

	//with multithreading how we can print A1B2C3
	public static void printA1B2C3() {
		Object lock = new Object();
		char[] chars = {'A', 'B', 'C'};
		int[] numbers = {1, 2, 3};
		boolean[] printChar = {true};

		// Thread to print characters
		Thread charThread = new Thread(() -> {
			synchronized (lock) {
				for (char c : chars) {
					while (!printChar[0]) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(c);
					printChar[0] = false;
					lock.notify();
				}
			}
		});

		// Thread to print numbers
		Thread numberThread = new Thread(() -> {
			synchronized (lock) {
				for (int num : numbers) {
					while (printChar[0]) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(num);
					printChar[0] = true;
					lock.notify();
				}
			}
		});

		charThread.start();
		numberThread.start();

		try {
			charThread.join();
			numberThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
