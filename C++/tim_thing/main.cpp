// Tim Palmer
// 11/7/2024
// This is a simple text-based game where the player chooses a class and fights an enemy.
// Each class has their own abilities and stats.

/*
Warrior have lots of health & armour, but their heavy armour slowly degrades over the course of the battle
	Attack - Swing your sword for 10-25 damage
	Defend - Gain 10 defense, reducing the damage taken from the next attack
	Charge - Deal damage equal to the distance between you and your opponent

Sorcerers have low health & no armour, but have a variety of spells to aid them in battle. Overspending mana
will damage the sorcerer
	Fireball - Deal 30 damage to your opponent, but also deals damage to you if they are too close
	Bone Curse - Curse your opponent, reducing their damage
	Decomposition - Apply 3 decay to your opponent, applying waste each turn (waste ignores armour)
	Holy Shield - Choose an amount of mana to spend to gain that much defense
	Ritual - Sacrifice 20 health to gain 10 mana
	Magic Missile - Spend 15 mana to send forth three bolts of energy dealing 12 damage each

Rogues' main strategy is to hide. Already sporting a 20% dodge chance, they have a 100% chance to evade attacks
while hidden, as well as deal double damage.
	Slice - Deal 20 damage to your opponent
	Darts - Throw 5 darts dealing 5 damage each
	Hide - Attempt to hide, with better chances the furthur away you are
	Antidote - Remove decay and waste


Each class also has the option to enclose (move closer 5 feet) or retreat (move back 5 feet)
*/

#include <iostream>
#include <string>
#include <random>
#include <ctime>
#include <thread>
#include <chrono>

using namespace std;

void SetColor(int textColor) { cout << "\033[" << textColor << "m"; }
void ResetColor() { cout << "\033[0m"; }

// Check if a string is numeric
static bool isNumeric(string str) {
	for (int i = 0; i < str.length(); i++) {
		if (!isdigit(str[i])) {
			return false;
		}
	}
	return true;
}

// If less than 0, return 0
static void nonNeg(double& num) {
	num = (num < 0) ? 0 : num;
}

static void nonNeg(int& num) {
	num = (num < 0) ? 0 : num;
}

static int chooseInt(int num) {
	string str_choice = "";
	while (true) {
		getline(cin, str_choice);
		if (isNumeric(str_choice)) {
			if (stoi(str_choice) >= 1 && stoi(str_choice) <= num) {
				break;
			}
			else {
				cout << "Invalid choice. Please enter a number between 1 and " << num << endl;
			}
		}
		else {
			cout << "Invalid choice. Please enter a number between 1 and " << num << endl;
		}
	}
	return stoi(str_choice);
}


// Base class for all game characters
// armour is a percentage reduction in damage taken. The warrior's heavy armouru degrades over the battle
// defense is a temporary damage reduction
class Game {
	protected:
		string name;
		int moveCount, waste, decay;
		double dodgeChance;
		
	public:
		double hp, armour;
		double defense;
		int curse;

		Game() {
			name = "Defualt";
			hp = 0;
			armour = 0;
			moveCount = 0;
			defense = 0;
			dodgeChance = 0;
			curse = 0;
			decay = 0;
			waste = 0;
		}

		virtual void perish() {
			cout << name << " has perished." << endl;
		}

		virtual void win() {
			cout << name << " has won." << endl;
		}

		virtual void startTurn() {
			if (defense > 0) {
				defense = 0;
				SetColor(36);
				cout << name << "'s defense falls." << endl;
				ResetColor();
			}

			if (curse > 0) {
				curse--;
				if (curse == 0) {
					SetColor(35);
					cout << name << "'s curse fades." << endl;
					ResetColor();
				}
			}

			resolveWaste();

		}

		// Each character can move a certain distance each turn
		void retreat(int& distance) {
			distance += 5;
			cout << name << " retreats." << endl;
		}

		void enclose(int& distance) {
			distance -= 5;
			cout << name << " closes in." << endl;

			if (distance < 0) {
				distance = 0;
				cout << name << " is already close enough." << endl;
			}
		}

		string getName() {
			return name;
		}

		int getMoveCount() {
			return moveCount;
		}

		void setDecay(int decay) {
			this->decay = decay;
		}

		virtual void dealDamage(double damage) {
			mt19937 rng(time(0));
			uniform_real_distribution<double> rand(0, 1);

			if (rand(rng) < dodgeChance) {
				cout << name << " evades the attack." << endl;
				return;
			}

			double actualDmg = damage * (1 - armour / 100) - defense;
			nonNeg(actualDmg);
			hp -= actualDmg;
			nonNeg(hp);
			if (hp < 0) { hp = 0; }

			SetColor(31);
			cout << name << " takes " << actualDmg << " damage." << endl;
			cout << "They have " << hp << " health remaining." << endl;
			ResetColor();
		}

		virtual void playerAction(Game* opp, int& dist) {
			cout << "Nothing happens to them" << endl;
		}

		virtual void oppAction(Game* player, int action, int& dist) {
			cout << "Nothing happens to me" << endl;
		}

		void resolveWaste() {
			if (decay > 0) {
				waste += decay;
				decay--;
			}

			if (waste > 0) {
				hp -= waste;
				nonNeg(hp);
				SetColor(31);
				cout << name << " takes " << waste << " waste damage." << endl;
				ResetColor();
				waste--;
			}
		}

		virtual void display() {
			SetColor(33);
			cout << "Hp: " << hp << endl;
			ResetColor();

			if (armour > 0) {
				SetColor(94);
				cout << "Armour: " << armour << endl;
				ResetColor();
			}

			if (defense > 0) {
				SetColor(36);
				cout << "Defense: " << defense << endl;
				ResetColor();
			}
			
			if (curse > 0) {
				SetColor(35);
				cout << "Curse: " << curse << endl;
				ResetColor();
			}

			if (decay > 0) {
				SetColor(90);
				cout << "Decay: " << decay << endl;
				ResetColor();
			}

			if (waste > 0) {
				SetColor(90);
				cout << "Waste: " << waste << endl;
				ResetColor();
			}

		}

};

class Warrior : public Game {
	public:

		Warrior(string name) : Game() {
			this->name = name;
			this->hp = 125;
			this->armour = 100;
			this->dodgeChance = 0.05;
					
			this->moveCount = 5;
		}

		void perish() {
			cout << "The brave warrior " << name << " has fallen in battle." << endl;
		}

		void win() {
			cout << "The mighty warrior " << name << " has emerged victorious." << endl;
		}

		// Warrior's heavy armour takes the first attack, but also deteriorates over time
		void dealDamage(double damage) {
			mt19937 rng(time(0));
			uniform_real_distribution<double> rand(0, 1);

			if (rand(rng) < dodgeChance) {
				cout << name << " evades the attack." << endl;
				return;
			}

			double actualDmg = damage * (1 - armour / 100) - defense;
			nonNeg(actualDmg);
			hp -= actualDmg;
			nonNeg(hp);
			double armourDmg = (damage - defense) / 5; // higher defense than damage will not reduce armour
			nonNeg(armourDmg);
			armour -= armourDmg;
			nonNeg(armour);

			SetColor(31);
			cout << name << " takes " << actualDmg << " damage." << endl;
			cout << "They have " << hp << " health remaining." << endl;
			SetColor(34);
			cout << "They have " << armour << " armour" << endl;
			ResetColor();
		}

		// Warrior's basic attack - 15 dmg
		void attack(Game* target, int& distance) {
			// If the enemy is too far away, move closer instead
			if (distance > 10) {
				cout << "The enemy is too far away." << endl;
				cout << "You move closer." << endl;
				distance -= 5;
				return;
			}

			mt19937 rng(time(0));
			uniform_int_distribution<int> rand(10, 25);

			int dmg = rand(rng) - curse;
			nonNeg(dmg);

			cout << name << " swings their sword." << endl;
			target->dealDamage(dmg );
		}

		void defend() {
			cout << name << " raises their shield." << endl;
			SetColor(32);
			cout << name << " gains 10 defense." << endl;
			ResetColor();
			defense = 30;
		}

		void charge(Game* target, int& distance) {
			cout << name << " raises their shield and charges at their foe." << endl;
			int damage = distance - curse;
			nonNeg(damage);
			target->dealDamage(damage);
			distance = 5;
		}

		// Warrior actions
		void playerAction(Game* opp, int& distance) {
			cout << "1. Attack" << endl;
			cout << "2. Defend" << endl;
			cout << "3. Charge" << endl;
			cout << "4. Enclose" << endl;
			cout << "5. Retreat" << endl;

			int choice = chooseInt(moveCount);

			switch (choice) {
			case 1:
				attack(opp, distance);
				break;

			case 2:
				defend();
				break;

			case 3:
				charge(opp, distance);
				break;

			case 4:
				enclose(distance);
				break;

			case 5:
				retreat(distance);
				break;
			}

		}

		void oppAction(Game* player, int action, int& distance) {
			// The action parameter selects which action is taken
			// The action is determined in main
			switch (action) {
				case 1:
					attack(player, distance);
					break;

				case 2:
					defend();
					break;

				case 3:
					charge(player, distance);
					break;

				case 4:
					enclose(distance);
					break;

				case 5:
					retreat(distance);
					break;

				default:
					cout << "The enemy does nothing." << endl;
			}
		}
};

class Sorcerer : public Game {
	private:
		int mp;


	public:
		Sorcerer(string name) : Game() {
			this->name = name;
			this->hp = 75;
			this->mp = 29;
			this->moveCount = 7;
		}

		void perish() {
			cout << "The powerful sorcerer " << name << " has been vanquished." << endl;
		}

		void win() {
			cout << "The wise sorcerer " << name << " has triumphed." << endl;
		}

		// Powerful spell with collateral damage
		void fireball(Game* target, int& distance) {
			cout << name << " casts fireball." << endl;

			int dmg = 30 - curse;
			nonNeg(dmg);

			if (distance == 5) {
				dealDamage(dmg/5);
				cout << "He is unfortunately caught in the blast." << endl;
			}
			else if (distance == 0) {
				dealDamage(dmg);
				cout << "In an impressive lack of forsight, his target happens to also be where he is standing." << endl;
			}

			target->dealDamage(dmg);
		}

		// Curses the enemy, reducing their damage
		void boneCurse(Game* target) {
			int cost = 4;
			mp -= cost;
			if (mp < 0) {
				hp += mp;
				nonNeg(hp);
				SetColor(31);
				cout << "The spell takes a toll" << endl;
				cout << name << " takes " << -mp << " damage." << endl;
				ResetColor();
				mp = 0;
			}

			SetColor(35);
			cout << name << " curses their foe." << endl;
			ResetColor();
			target->curse += 4;
		}

		// Decomposition - applies 2 decay to the enemy, inflicting waste damage (waste damage bypasses armour)
		void decomposition(Game* target) {
			int cost = 6;
			mp -= cost;
			if (mp < 0) {
				hp += mp;
				nonNeg(hp);
				SetColor(31);
				cout << "The spell takes a toll" << endl;
				cout << name << " takes " << -mp << " damage." << endl;
				ResetColor();
				mp = 0;
			}

			SetColor(35);
			cout << name << " casts decomposition." << endl;
			ResetColor();

			target->setDecay(3);			
		}

		// Spend an amount of mp to gain that much defense
		void holyShield() {
			// If the player is ai, choose a random amount of mp to spend
			if (name == "Vaeline") {
				mt19937 rng(time(0));
				uniform_int_distribution<int> rand(1, mp);
				int cost = rand(rng);
				mp -= cost;

				SetColor(32);
				cout << name << " raises a holy shield." << endl;
				cout << "They gain " << cost << " defense." << endl;
				ResetColor();
				defense += cost;
				return;
			}

			cout << "How much mana would you like to spend?" << endl;
			int cost = chooseInt(mp);

			SetColor(32);
			cout << name << " raises a holy shield." << endl;
			cout << "They gain " << cost << " defense." << endl;
			ResetColor();

			mp -= cost;
			defense += cost;
		}

		// Ritual - spend 20 health to gain 10 mp
		void ritual() {
			cout << name << " performs a ritual." << endl;
			hp -= 20;
			SetColor(31);
			cout << "They sacrifices 20 health to increase their mana." << endl;
			ResetColor();
			nonNeg(hp);

			if (hp == 0) {
				SetColor(31);
				cout << "Unfortunately, " << name << " could not pay the price." << endl;
				ResetColor();
				return;
			}

			mp += 10;
		}

		// Magic missile - spend 15 mp to send forth three bolts of energy dealing 12 damage each
		void magicMissile(Game* target) {
			cout << name << " sends forth a barrage of magical energy." << endl;
			int cost = 15;
			mp -= cost;
			if (mp < 0) {
				hp += mp;
				nonNeg(hp);
				SetColor(31);
				cout << "The spell takes a toll" << endl;
				cout << name << " takes " << -mp << " damage." << endl;
				ResetColor();
				mp = 0;
			}

			target->dealDamage(12);
			target->dealDamage(12);
			target->dealDamage(12);
		}


		void playerAction(Game* opp, int& distance) {
			cout << "1. Fireball" << endl;
			cout << "2. Bone Curse" << endl;
			cout << "3. Decomposition" << endl;
			cout << "4. Holy Shield" << endl;
			cout << "5. Ritual" << endl;
			cout << "6. Magic Missile" << endl;
			cout << "7. Enclose" << endl;
			cout << "8. Retreat" << endl;

			int choice = chooseInt(moveCount);

			switch (choice) {
				case 1:
					fireball(opp, distance);
					break;

				case 2:
					boneCurse(opp);
					break;

				case 3:
					decomposition(opp);
					break;

				case 4:
					holyShield();
					break;

				case 5:
					ritual();
					break;

				case 6:
					magicMissile(opp);
					break;

				case 7:
					enclose(distance);
					break;

				case 8:
					retreat(distance);
					break;
			}
		}

		void oppAction(Game* player, int action, int& distance) {
			switch (action) {
				case 1:
					fireball(player, distance);
					break;
					
				case 2:
					boneCurse(player);
					break;

				case 3:
					decomposition(player);
					break;

				case 4:
					holyShield();
					break;

				case 5:
					ritual();
					break;

				case 6:
					magicMissile(player);
					break;

				case 7:
					enclose(distance);
					break;

				case 8:
					retreat(distance);
					break;
			}
		}

		void startTurn() {
			if (defense > 0) {
				defense = 0;
				SetColor(36);
				cout << name << "'s defense falls." << endl;
				ResetColor();
			}

			if (curse > 0) {
				curse--;
				if (curse == 0) {
					SetColor(35);
					cout << name << "'s curse fades." << endl;
					ResetColor();
				}
			}

			resolveWaste();
			mp++;
		}

		void display() {
			SetColor(32);
			cout << "Hp: " << hp << endl;
			SetColor(34);
			cout << "Mp: " << mp << endl;
			if (armour > 0) {
				SetColor(33);
				cout << "Armour: " << armour << endl;
			}

			if (defense > 0) {
				SetColor(36);
				cout << "Defense: " << defense << endl;
			}

			if (curse > 0) {
				SetColor(35);
				cout << "Curse: " << curse << endl;
			}

			ResetColor();
		}
};

class Rogue : public Game {
	bool hidden;

	public:
		Rogue(string name) {
			this->name = name;
			this->hp = 100;
			this->armour = 25;
			this->dodgeChance = 0.2;

			this->moveCount = 6;
			this->hidden = false;
		}

		// Rogue's melee - 20 dmg
		void slice(Game* target) {
			int dmg = 20 - curse;
			if (hidden) { dmg *= 2; }
			nonNeg(dmg);

			if (hidden) {
				hidden = false;
				SetColor(96);
				cout << name << " springs from the shadows for a surprise attack." << endl;
			}
			else {
				cout << name << " strikes with their dagger." << endl;
			}

			target->dealDamage(dmg);
		}

		// Rogue's ranged attack - 5 dmg, 5 darts
		void darts(Game* target) {
			cout << name << " throws a handful of darts." << endl;
			int dmg = 5 - curse;
			if (hidden) { dmg *= 2; }
			nonNeg(dmg);

			for (int i = 0; i < 5; i++) {
				target->dealDamage(dmg);
			}

			// 30% chance cover is blown
			mt19937 rng(time(0));
			uniform_real_distribution<double> rand(0, 1);
			if (rand(rng) < 0.3) {
				hidden = false;
				SetColor(96);
				cout << name << "'s position is revealed" << endl;
				ResetColor();
			}
		}

		// Hide - chance to apply the "hidden" status, based on distance
		void hide(int distance) {
			// Random number generator
			mt19937 rng(time(0));
			uniform_real_distribution<double> rand(0, 1);

			double random = rand(rng);
			double chance = distance / 60.0;
			chance = 1; // temp

			if (random < chance) {
				SetColor(96);
				cout << name << " receeds into the shadows." << endl;
				ResetColor();
				hidden = true;
				dodgeChance = 1.0;
			}
			else {
				cout << name << " attempts to hide, but is unsuccessful." << endl;
			}
		}

		// Antidote - removes decay and waste
		void antidote() {
			cout << name << " drinks an antidote." << endl;
			decay = 0;
			waste = 0;
		}

		
		void playerAction(Game* opp, int& distance) {
			cout << "1. Slice" << endl;
			cout << "2. Darts" << endl;
			cout << "3. Hide" << endl;
			cout << "4. Antidote" << endl;
			cout << "5. Enclose" << endl;
			cout << "6. Retreat" << endl;

			int choice = chooseInt(moveCount);

			switch (choice) {
				case 1:
					slice(opp);
					break;

				case 2:
					darts(opp);
					break;

				case 3:
					hide(distance);
					break;

				case 4:
					antidote();
					break;

				case 5:
					enclose(distance);
					break;

				case 6:
					retreat(distance);
					break;
			}
		}

		void oppAction(Game* player, int action, int& distance) {
			switch (action) {
				case 1:
					slice(player);
					break;

				case 2:
					darts(player);
					break;

				case 3:
					hide(distance);
					break;

				case 4:
					antidote();
					break;

				case 5:
					enclose(distance);
					break;

				case 6:
					retreat(distance);
					break;
			}
		}


		void startTurn() {
			if (defense > 0) {
				defense = 0;
				SetColor(36);
				cout << name << "'s defense falls." << endl;
				ResetColor();
			}

			if (curse > 0) {
				curse--;
				if (curse == 0) {
					SetColor(35);
					cout << name << "'s curse fades." << endl;
					ResetColor();
				}
			}

			if (hidden) {
				dodgeChance = 1.0;
			}
			else {
				dodgeChance = 0.2;
			}

			resolveWaste();

		}

		void perish() {
			cout << "The cunning rogue " << name << " has performed their last heist." << endl;
		}

		void win() {
			cout << "The elusive rogue " << name << " has outwitted their foes." << endl;
		}
};


void playerTurn(Game* player, Game* opp, int& dist) {
	player->startTurn();
	cout << "It is " << player->getName() << "'s turn." << endl;
	player->display();
	cout << "What will you do?" << endl;
	player->playerAction(opp, dist);	
}

void oppTurn(Game* player, Game* opp, int action, int& dist) {
	opp->startTurn();
	cout << "It is " << opp->getName() << "'s turn." << endl;
	opp->display();
	opp->oppAction(player, action, dist);
}


int main() {
	mt19937 rng(time(0));
	uniform_real_distribution<double> rand(0, 1);

	string names[3] = { "Brynjar", "Vaeline", "Lirae" };
	int oppInt = (int)floor(3 * rand(rng));
	Game* opp = nullptr;

	switch (oppInt) {
		case 0:
			opp = new Warrior(names[oppInt]);
			break;

		case 1:
			opp = new Sorcerer(names[oppInt]);
			break;

		case 2:
			opp = new Rogue(names[oppInt]);
			break;

		default:
			cout << "Wait you weren't supposed to pick " << oppInt << endl;
			exit(1);
	}

	Game* player = nullptr;

	cout << "Name your character: " << endl;
	string name;
	getline(cin, name);
	cout << endl;

	cout << "Which class would you like to play?" << endl;
	cout << "1. Warrior" << endl;
	cout << "2. Sorcerer" << endl;
	cout << "3. Rogue" << endl;

	int choice = chooseInt(3);

	switch (choice) {
		case 1:
			player = new Warrior(name);
			break;

		case 2:
			player = new Sorcerer(name);
			break;

		case 3:
			player = new Rogue(name);
			break;
	}
	

	int distance = 10;
	// Game loop
	while (player->hp > 0 && opp->hp > 0) {
		cout << "The two adversaries face off " << distance << " feet apart." << endl;

		playerTurn(player, opp, distance);
		cout << endl;
		if (opp->hp <= 0) { break; }

		this_thread::sleep_for(chrono::seconds(1));
		int action = (int)floor(opp->getMoveCount() * rand(rng))+1;
		oppTurn(player, opp, action, distance);
		cout << endl;
		
	}

	if (player->hp <= 0) {
		player->perish();
	}
	else {
		player->win();
	}
}