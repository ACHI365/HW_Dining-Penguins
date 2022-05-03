package fop.w11dining;

public class Penguin implements Runnable {
	
	private String name;
	private Table table;
	private int index;

	public Penguin(String name, Table table, int index) {
		this.name =  name;
		this.table = table;
		this.index = index;
	}

	public void eat() {
		int firstFork = index;
		int secondFork = (index + 1) % 4;

		int minFork = Math.min(firstFork, secondFork);
		int maxFork = Math.max(firstFork, secondFork);

		// Take fork # 1
		while(!table.forkAvailable(minFork)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Something went wrong. Interrupted!");
				return;
			}
		}

		table.takeFork(minFork);
		System.out.println(name + " takes fork #" + minFork);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Something went wrong. Interrupted!");
			return;
		}

		// Take fork # 2
		while(!table.forkAvailable(maxFork)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Something went wrong. Interrupted!");
				return;
			}
		}

		table.takeFork(maxFork);
		System.out.println(name + " takes fork #" + maxFork);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Something went wrong. Interrupted!");
			return;
		}

    	System.out.println(name + " ate.");

		table.returnFork(minFork);
		table.returnFork(maxFork);
	}

	@Override
	public void run() {
		eat();
	}

}