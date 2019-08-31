package yoon.namecompatibility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Name {

	static Name name = new Name();

	// 초성, 중성, 종성 각 획수에 해당하는 모든 자모의 인덱스
	Map<Integer, int[]> choseongList = new HashMap<Integer, int[]>();
	Map<Integer, int[]> jungseongList = new HashMap<Integer, int[]>();
	Map<Integer, int[]> jongseongList = new HashMap<Integer, int[]>();

	private void setProperty() {
		choseongList.put(1, new int[] { 11 });
		choseongList.put(2, new int[] { 0, 2, 9 });
		choseongList.put(3, new int[] { 3, 12, 15, 18 });
		choseongList.put(4, new int[] { 1, 6, 7, 10, 14, 16, 17 });
		choseongList.put(5, new int[] { 5 });
		choseongList.put(6, new int[] { 4, 13 });
		choseongList.put(8, new int[] { 8 });

		jungseongList.put(1, new int[] { 18, 20 });
		jungseongList.put(2, new int[] { 0, 4, 8, 13, 19 });
		jungseongList.put(3, new int[] { 1, 2, 5, 6, 11, 12, 16, 17 });
		jungseongList.put(4, new int[] { 3, 7, 9, 14 });
		jungseongList.put(5, new int[] { 10, 15 });

		jongseongList.put(0, new int[] { 0 });
		jongseongList.put(1, new int[] { 21 });
		jongseongList.put(2, new int[] { 1, 4, 19 });
		jongseongList.put(3, new int[] { 7, 22, 24, 27 });
		jongseongList.put(4, new int[] { 2, 3, 16, 17, 20, 23, 25, 26 });
		jongseongList.put(5, new int[] { 5, 6, 8 });
		jongseongList.put(6, new int[] { 18 });
		jongseongList.put(7, new int[] { 9, 12 });
		jongseongList.put(8, new int[] { 15 });
		jongseongList.put(9, new int[] { 10, 11, 13, 14 });
	}

	private void convertToStroke() {
		// 초성, 중성, 종성에 해당하는 자모를 획수별로 분류
		/*
		 * for (int i = 0; i <= 10; i++) {
		 * System.out.print("jongseongList.put("+i+", new int[] {"); for (int j = 0; j <
		 * name.jongseongStroke.length; j++) { if (name.jongseongStroke[j] == i) {
		 * System.out.print(j); System.out.print(", "); } } System.out.print("});\n"); }
		 */
	}

	private void convertStrokeToChar() {
		// 입력된 획수에 해당하는 모든 음절(초,중,종성으로 구성됨)을 출력

		name.setProperty();
		int test = 5;
		int tmp;
		int count = 0;
		while (test <= 22) {
			for (int i = 1; i <= 8; i++) {
				if (i == 7)
					continue;
				tmp = test - i;
				if (tmp >= 1 && tmp <= 14) {
					for (int j = 1; j <= 5; j++) {
						tmp = test - i - j;
						if (tmp >= 0 && tmp <= 9) {
							for (int k = 0; k < name.choseongList.get(i).length; k++) {
								for (int m = 0; m < name.jungseongList.get(j).length; m++) {
									for (int n = 0; n < name.jongseongList.get(tmp).length; n++) {
										int a = name.jongseongList.get(tmp)[n];
										if (a == 2 || a == 3 || a == 5 || a == 6 || (a >= 9 && a <= 15) || a == 18)
											continue;
										System.out.print((char) ((name.choseongList.get(i)[k] * 21
												+ name.jungseongList.get(j)[m]) * 28 + name.jongseongList.get(tmp)[n]
												+ 0xAC00) + " ");
										count++;
									}
								}
							}
						}
					}
				}
			}
			test += 10;
		}
		System.out.println("\n" + count);

	}

	private void formatLastName()
	{
		// 해당 사이트의 성 포맷을 배열로 입력할 수 있게 변환 (1 ~ 100)
		// https://ko.wikipedia.org/wiki/%ED%95%9C%EA%B5%AD%EC%9D%98_%EC%84%B1%EC%94%A8
		Scanner scan = new Scanner(System.in);
		String input;
		char tmp;
		int i = 0;
		int j;
		char[] lastName = new char[100];
		while ((input = scan.nextLine()) != null) {
			if (input.equals("stop"))
				break;
			tmp = input.split("\t")[1].charAt(0);
			for (j = 0; j < i; j++) {
				if (lastName[j] == tmp)
					break;
			}
			if (j == i) {
				lastName[i++] = tmp;
			}
		}
		for (char each : lastName) {
			System.out.print("'"+each + "',");
		}
		
	}
	
	private String[] formatFirstName()
	{
		// 해당 사이트의 이름 포맷을 배열로 입력할 수 있게 변환 (성별 별 1000개씩, 2글자 이름만)
		// https://koreanname.me/
		Scanner scan = new Scanner(System.in);
		String input;
		String tmp;
		int i = 0;
		String[] firstName = new String[2000];
		while ((input = scan.nextLine()) != null) {
			if (input.equals("stop"))
				break;
			tmp = input.split("\t")[0];
			if (tmp.length() == 2) {
				firstName[i++] = tmp;
			}
		}
	/*	for (String each : firstName) {
			System.out.print("'"+each + "',");
		}*/
		return firstName;
	}
	
	private void sortLastNameByStroke()
	{
		// 입력된 값을 획수별로 분류
		char[] lastName = {'김','이','박','최','정','강','조','윤','장','임','한','오','서','신','권','황','안','송','전','홍','유','고','문','양','손','배','백','허','남','심','노','하','곽','성','차','주','우','구','민','류','진','나','지','엄','채','원','천','방','공','현','함','변','염','여','추','도','소','석','선','설','마','길','연','위','표','명','기','반','라','왕','금','옥','육','인','맹','제','모','탁','국','어','은','편'};
		NameCompat nc = new NameCompat();
		int stroke;
		Map<Integer, Set<Character>> map = new HashMap<>();
		for(char c : lastName)
		{
			stroke = nc.convertCharToStoke(c);
			if(map.get(stroke) == null)
				map.put(stroke, new HashSet<>());
			map.get(stroke).add(c);
		}
		Set<Character> set;
		for(int i = 2; i <= 22; i++)
		{
			set = map.get(i);
			if(set != null)
			{
				System.out.print("lastNameList.put("+i+", new char[] {");
				Object[] arr = set.toArray();
				for(int j = 0; j < arr.length; j++)
				{
					if(j != 0)
						System.out.print(',');
					System.out.print("\'" + arr[j] + "\'");
				}
				System.out.println("});");
			}
		}
	}
	
	private void sortFirstNameByStroke(String[] firstName)
	{
		// 입력된 값을 획수별로 분류
		NameCompat nc = new NameCompat();
		int stroke;
		Map<Integer, Set<String>> map = new HashMap<>();
		for(String name : firstName)
		{
			if (name == null)
				break;
			stroke = nc.convertCharToStoke(name.charAt(0)) * 100 + nc.convertCharToStoke(name.charAt(1));
			if(map.get(stroke) == null)
				map.put(stroke, new HashSet<>());
			map.get(stroke).add(name);
		}
		Set<String> set;
		for(int i = 0; i <= 2222; i++)
		{
			set = map.get(i);
			if(set != null)
			{
				System.out.print("firstNameList.put("+i+", new String[] {");
				Object[] arr = set.toArray();
				for(int j = 0; j < arr.length; j++)
				{
					if(j != 0)
						System.out.print(',');
					System.out.print("\"" + arr[j] + "\"");
				}
				System.out.println("});");
			}
		}
	}
	
	public static void main(String[] args) {
		name.sortFirstNameByStroke(name.formatFirstName());
	}

}
