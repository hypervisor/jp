public class O1A {
	public static void skrivUt (int[] tabell) {
		for (int i = 0; i < tabell.length; ++i) {
			System.out.println(i + ": " + tabell[i]);
		}
	}
	
	public static String tilStreng(int[] tabell) {
		String ret = "[";
		for (int i = 0; i < tabell.length; ++i) {
			ret += tabell[i];
			if (i != tabell.length - 1) {
				ret += ",";
			}
		}
		return ret + "]";
	}
	
	// Metode 1
	public static int summer1(int[] tabell) {
		int sum = 0;
		for (int x : tabell)
			sum += x;
		
		return sum;
	}
	
	// Metode 2
	public static int summer2(int[] tabell) {
		int sum = 0;
		for (int i = 0; i < tabell.length; ++i)
			sum += tabell[i];
		
		return sum;
	}
	
	// Metode 3
	public static int summer3(int[] tabell) {
		int sum = 0;
		int i = 0;
		while (i < tabell.length) {
			sum += tabell[i];
			i++;
		}
		return sum;
	}
	
	public static boolean finnesTall(int[] tabell, int tall) {
		for (int x : tabell)
			if (x == tall)
				return true;
		return false;
	}
	
	public static int posisjonTall(int[] tabell, int tall) {
		for (int i = 0; i < tabell.length; ++i) {
			if (tabell[i] == tall)
				return i;
		}
		return -1;
	}
	
	public static int[] reverser(int[] tabell) {
		int[] ny = new int[tabell.length];
		for (int i = 0; i < tabell.length; ++i) {
			ny[tabell.length - 1 - i] = tabell[i];
		}
		return ny;
	}
	
	public static boolean erSortert(int[] tabell) {
		int last = tabell[0];
		for (int i = 1; i < tabell.length; ++i) {
			if (tabell[i] < last) {
				return false;
			}
		}
		return true;
	}
	
	public static int[] settSammen(int[] tabell1,int[] tabell2) {
		int[] ny = new int[tabell1.length + tabell2.length];
		for (int i = 0; i < tabell1.length; ++i)
			ny[i] = tabell1[i];
		
		for (int i = 0; i < tabell2.length; ++i)
			ny[tabell1.length + i] = tabell2[i];
		
		return ny;
	}
	
	public static void main(String[] args) {
		int[] tabell = { 42,67,89 };
		int[] tab2 = { 3, 4, 32 };
		//System.out.println(summer1(tabell));
		//System.out.println(summer2(tabell));
		//System.out.println(summer3(tabell));
		//skrivUt(reverser(tabell));
		//System.out.println(erSortert(tabell));
		skrivUt(settSammen(tabell, tab2));
	}
}
