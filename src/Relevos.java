import java.util.concurrent.Semaphore;

public class Relevos {
	private static Semaphore testigo=new Semaphore(1);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<4;i++){
			Corredor corredor=new Corredor("corredor"+i,testigo);			
			corredor.start();
			try {
				corredor.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("Todos los corredores terminaron");

	}

}

class Corredor extends Thread{
	Semaphore testigo;
	Corredor(String nombre,Semaphore testigo){
		super(nombre);
		this.testigo=testigo;
	}
	public void run(){	
		try {
			testigo.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("El corredor "+this.getName()+" "+" corriendo...");
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Terminé. Paso el testigo");
		testigo.release();
	}
}
