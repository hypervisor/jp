public class O1B {
	public static void skrivUtv1(int[][] matrise) {
		for (int[] tab : matrise) {
			for (int x : tab) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
	
	public static String tilStreng(int[][] matrise) {
		String ret = "";
		for (int[] tab : matrise) {
			for (int x : tab) {
				ret += x + " ";
			}
			ret += "\n";
		}
		return ret;
	}
	
	public static int[][] skaler(int tall, int[][] matrise) {
		int[][] mat = new int[matrise.length][];
		for (int i = 0; i < matrise.length; ++i) {
			mat[i] = new int[matrise[i].length];
			for (int j = 0; j < matrise[i].length; ++j) {
				mat[i][j] = matrise[i][j] * tall;
			}
		}
		return mat;
	}
	
	public static boolean erLik(int[][] mat1, int[][] mat2) {
		if (mat1.length != mat2.length)
			return false;
		
		for (int i = 0; i < mat1.length; ++i) {
			if (mat1[i].length != mat2[i].length)
				return false;
			
			for (int j = 0; j < mat1[i].length; ++j) {
				if (mat1[i][j] != mat2[i][j])
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[][] mat = { {1,2,3}, {4,5,6}, {7,8,9} };
		//skrivUtv1(mat);
		//System.out.println(tilStreng(mat));
		skrivUtv1(skaler(5, mat));
	}
}
