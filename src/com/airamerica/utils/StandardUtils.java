package src.com.airamerica.utils;

public class StandardUtils {

	/* Returns a random string of six characters 
	 * using [A-Z] and [1-9]
	 */
	public static String generatePNR() {
			char pnr[] = new char[6];
			for(int i=0;i<6;i++) {
				int num = (int)(Math.random() * 35) + 56;
				if(num<65) num=num-7;
				pnr[i] = (char)num;
			}
			return String.valueOf(pnr);
			}
}
