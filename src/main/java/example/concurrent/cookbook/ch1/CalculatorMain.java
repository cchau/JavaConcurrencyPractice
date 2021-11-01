package example.concurrent.cookbook.ch1;


class Calculator implements Runnable{
	private int number;
	
	public Calculator(int number) {
		this.number = number;
	}
	
	@Override
	public void run() {
		
		for (int i=1; i<11; i++){
			System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i*number);
		}
		
	}
	
}

public class CalculatorMain {

	public static void main(String[] args) {
		
		for (int i=1; i<11; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}

	}

}
