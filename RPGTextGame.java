package report;

import java.util.Scanner;

public class RPGTextGame {
	static int hero_level, hero_power, hero_hp, hero_defense, hero_mp, hero_experience, hero_money;
	static int monster_hp, monster_defense, monster_power, monster_mp, monster_level, monster_experience, monster_money;
	static String hero_name, monster_name;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number;
		int num;
		Scanner in = new Scanner(System.in);
		System.out.print("영웅의 이름을 입력하세요: ");
		hero_name = in.next();
		System.out.println("이름이 입력되었습니다.");
		System.out.println("게임에 입장하였습니다.");
		System.out.println("*************************");
		hero_level = 1;
		hero_power = 15;
		hero_defense = 25;
		hero_hp = 80;
		hero_mp = 0;
		hero_experience = 0;
		hero_money = 0;
		while (true) {
			System.out.println("*************************");
			System.out.println("현재 " + hero_name + "의 아름: " + hero_name);
			System.out.println("현재 " + hero_name + "의 레벨: " + hero_level);
			System.out.println("현재 " + hero_name + "의 힘: " + hero_power);
			System.out.println("현재 " + hero_name + "의 방어력: " + hero_defense);
			System.out.println("현재 " + hero_name + "의 HP: " + hero_hp);
			System.out.println("현재 " + hero_name + "의 MP: " + hero_mp);
			System.out.println("현재 " + hero_name + "의 경험치: " + hero_experience);
			System.out.println("현재 " + hero_name + "의 돈: " + hero_money + "원");
			System.out.println("*************************");
			System.out.println("1. 사냥터");
			System.out.println("2. 포션 상점");
			System.out.println("3. 무기 상점");
			System.out.println("4. 게임 종료");
			System.out.print("입장할 장소를 선택해주세요");
			num = in.nextInt();
			if (num == 1) {
				System.out.println("사냥터에 입장했습니다.");
				System.out.println("1. 너구리");
				System.out.println("2. 살쾡이");
				System.out.print("전투할 상대를 입력히세요: ");
				number = in.nextInt();
				if (number == 1) {
					monster_name = "너구리";
					monster_hp = 100;
					monster_mp = 0;
					monster_level = 1;
					monster_power = 20;
					monster_defense = 5;
					monster_money = 10;
					monster_experience = 10;
				}
				if (number == 2) {
					monster_name = "살쾡이";
					monster_hp = 2000;
					monster_mp = 0;
					monster_level = 5;
					monster_power = 100;
					monster_defense = 20;
					monster_money = 30;
					monster_experience = 50;
				}
				System.out.println(monster_name + "와 전투를 시작합니다.");
				while (true) {
					System.out.println(hero_name + "의 공격입니다.");
					monster_attacked(hero_attack());
					if (monster_hp <= 0) {
						System.out.println(monster_name + "가 죽었습니다.");
						hero_experience += monster_experience;
						hero_money += monster_money;
						break;
					}

					System.out.println(monster_name + "의 공격입니다.");
					hero_attacked(monster_attack());
					if (hero_hp <= 0) {
						System.out.println(hero_name + "가 죽었습니다.");
						hero_hp = 1;
						System.out.println(hero_name + "이 체력 " + hero_hp + "으로 부활했습니다.");
						break;
					}

				}
			}
			if (num == 2) {
				System.out.println("포션 상점에 입장했습니다.");
				System.out.println("1. 힘 증강 포션 (30원)");
				System.out.println("2. 방어 증강 포션 (30원)");
				System.out.println("3. 경험치 증강 포션 (100원)");
				System.out.println("4. 체력 증강 포션 (10원)");
				System.out.println("5. 마력 증강 포션 (10원)");
				System.out.print("원하는 물건을 입력하세요: ");
				number = in.nextInt();
				int fail_money = hero_money;
				System.out.println("포션 상점에서 물건을 구매 시도중입니다.");
				if ((hero_money = potionStore_show(hero_money, number)) != -1) {
					System.out.println("구입이 완료되었습니다");
					if (number == 1)
						hero_power += 3;
					if (number == 2)
						hero_defense += 3;
					if (number == 3)
						hero_experience += 50;
					if (number == 4)
						hero_hp += 50;
					if (number == 5)
						hero_mp += 50;
				} else {
					System.out.println("돈이 부족합니다.");
					hero_money = fail_money;
				}
			}
			if (num == 3) {
				System.out.println("무기 상점에 입장했습니다.");
				System.out.println("1. 검 (150원)");
				System.out.println("2. 방패 (150원)");
				System.out.print("원하는 물건을 입력하세요: ");
				number = in.nextInt();
				int fail_money = hero_money;
				System.out.println("무기 상점에서 물건을 구매 시도중입니다.");
				if ((hero_money = WeaponStore(hero_money, number)) != -1) {
					System.out.println("구입이 완료되었습니다");
					if (number == 1)
						hero_power += 15;
					if (number == 2)
						hero_defense += 15;
				} else {
					System.out.println("돈이 부족합니다.");
					hero_money = fail_money;
				}
			}
			if (num == 4) {
				System.out.println("게임을 종료합니다.");
				break;
			}
			if (hero_experience >= hero_level * 80) {
				hero_level += 1;
				System.out.println(hero_name + "의 레벨이 " + hero_level + "이 되었습니다.");
				hero_money += hero_level * 50;
				System.out.println("레벨업 가념으로 " + hero_name + "의 돈이 " + hero_level * 50 + "원 증가 하었습니다.");
				hero_experience = 0;
			}
		}
	}

	static int hero_attack() {
		int sum = 0;
		sum += hero_level * 10 + hero_power * 30;
		System.out.println(monster_name + "가 받는 데미지는 " + sum + " 입니다.");
		return sum;
	}

	static int monster_attack() {
		System.out.println(hero_name + "가 받는 데미지는 " + monster_power + " 입니다.");
		return monster_power;
	}

	static void hero_attacked(int sum) {
		if (hero_defense >= monster_power)
			hero_hp = hero_hp;
		else
			hero_hp = hero_hp + hero_defense - monster_power;
	}

	static void monster_attacked(int sum) {
		if (monster_defense >= sum)
			monster_hp = monster_hp;
		else
			monster_hp = monster_hp + monster_defense - sum;
	}

	static int potionStore_show(int hero_money, int number) {
		int pow_up = 30;
		int def_up = 30;
		int exp_up = 100;
		int hp_up = 10;
		int mp_up = 10;
		if (number == 1) {
			if (hero_money >= pow_up)
				return hero_money - pow_up;
		}
		if (number == 2) {
			if (hero_money >= def_up)
				return hero_money - def_up;
		}
		if (number == 3) {
			if (hero_money >= exp_up)
				return hero_money - exp_up;
		}
		if (number == 4) {
			if (hero_money >= hp_up)
				return hero_money - hp_up;
		}
		if (number == 5) {
			if (hero_money >= mp_up)
				return hero_money - mp_up;
		}
		return -1;
	}

	static int WeaponStore(int hero_money, int number) {
		int sword = 150;
		int shiled = 150;
		if (number == 1) {
			if (hero_money >= sword)
				return hero_money - sword;
		}
		if (number == 2) {
			if (hero_money >= shiled)
				return hero_money - shiled;
		}
		return -1;
	}

}
