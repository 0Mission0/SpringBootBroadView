package idv.mission.example.SpringAsync;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Async
	public void funcA(int i) {
		System.out.println("A = " + i);
		try {
			Thread.currentThread().sleep(100);
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	@Async
	public void funcB(int i) {
		System.out.println("B = " + i);
	}

}
