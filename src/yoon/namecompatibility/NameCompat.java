package yoon.namecompatibility;

import java.util.Scanner;

public class NameCompat {

	static NameCompat nc = new NameCompat();

	// 유니코드 상 인덱스에 해당하는 획순

	// ㄱ,ㄲ,ㄴ,ㄷ,ㄸ,ㄹ,ㅁ,ㅂ,ㅃ,ㅅ,ㅆ,ㅇ,ㅈ,ㅉ,ㅊ,ㅋ,ㅌ,ㅍ,ㅎ
	int choseongStroke[] = { 2, 4, 2, 3, 6, 5, 4, 4, 8, 2, 4, 1, 3, 6, 4, 3, 4, 4, 3 };
	// ㅏ,ㅐ,ㅑ,ㅒ,ㅓ,ㅔ,ㅕ,ㅖ,ㅗ,ㅘ,ㅙ,ㅚ,ㅛ,ㅜ,ㅝ,ㅞ,ㅟ,ㅠ,ㅡ,ㅢ,ㅣ
	int jungseongStroke[] = { 2, 3, 3, 4, 2, 3, 3, 4, 2, 4, 5, 3, 3, 2, 4, 5, 3, 3, 1, 2, 1 };
	// 없음,ㄱ,ㄲ,ㄳ,ㄴ,ㄵ,ㄶ,ㄷ,ㄹ,ㄺ,ㄻ,ㄼ,ㄽ,ㄾ,ㄿ,ㅀ,ㅁ,ㅂ,ㅄ,ㅅ,ㅆ,ㅇ,ㅈ,ㅊ,ㅋ,ㅌ,ㅍ,ㅎ
	int jongseongStroke[] = { 0, 2, 4, 4, 2, 5, 5, 3, 5, 7, 9, 9, 7, 9, 9, 8, 4, 4, 6, 2, 4, 1, 3, 4, 3, 4, 4, 3 };

	private int[] convertNameToStroke(String name) {
		int arr[] = new int[3];
		int uniVal;
		for (int i = 0; i < name.length(); i++) {
			uniVal = name.charAt(i) - 0xAC00;
			// 순서대로 초성, 중성, 종성
			arr[i] = choseongStroke[uniVal / 588];
			arr[i] += jungseongStroke[uniVal / 28 % 21];
			arr[i] += jongseongStroke[uniVal % 28];
		}
		return arr;
	}

	// 이름점 순서를 A X B Y C Z 라고 할 때 A + 6B + C 값으로 X + Y 를 도출
	int xpyMap[][] = { { 0, 5, 10, 15 }, { 3, 8, 13, 18 }, { 1, 6, 11, 16 }, { 4, 9, 14 }, { 2, 7, 12, 17 } };

	private int[][] getBestStroke(int[] stroke) {
		int xpyList[] = xpyMap[(9 - (stroke[0] + 6 * stroke[1] + stroke[2]) % 10) / 2];
		int count = 0;
		for (int xpy : xpyList) {
			if (xpy > 9)
				count += 19 - xpy;
			else
				count += xpy + 1;
		}
		int strokeList[][] = new int[count][3];
		int index = 0;
		int tmp;
		for (int xpy : xpyList) {
			for (int i = 0; i <= 9; i++) {
				tmp = xpy - i;
				if (tmp <= 9 && tmp >= 0) {
					strokeList[index][0] = i;
					strokeList[index][1] = tmp;
					strokeList[index][2] = 9 - (4 * (stroke[1] + stroke[2]) + i + 6 * tmp) % 10;
					index++;
				}
			}
		}
		return strokeList;
	}

	public String[] getPartners(String name) {
		int stroke[] = convertNameToStroke(name);
		int partnerStroke[][] = getBestStroke(stroke);
		for (int i = 0; i < partnerStroke.length; i++) {
			System.out.printf("%d %d %d\n", partnerStroke[i][0], partnerStroke[i][1], partnerStroke[i][2]);
		}
		String partners[] = new String[0];
		return partners;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		String partners[] = nc.getPartners(name);
	}

}