// Add a Pet class here
package petProj;

public class Pet {

	private String name;
	private int mood;
	private int hungry;
	private int skillPoints;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public int getHungry() {
		return hungry;
	}

	public void setHungry(int hungry) {
		this.hungry = hungry;
	}

	public int getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	// Add a constructor
	public Pet(String initName) {
		name = initName;
		mood = 0;
		hungry = 100;
		skillPoints = 0;
		age = 0;
	}

	// Add 3 simple methods for the pet with no arguments
	public void jump() {
		if (skillPoints <= 100) {
			skillPoints++;
		}
	}

	public void feed() {
		if (hungry <= 100) {
			hungry++;
		}
	}

	public void play() {
		if (mood <= 100) {
			mood++;
		}
	}

	public void print() {
		System.out.println("name: " + name);
		System.out.println("mood: " + mood);
		System.out.println("is hungry?: " + hungry);
		System.out.println("Skill points: " + skillPoints);
	}

}
