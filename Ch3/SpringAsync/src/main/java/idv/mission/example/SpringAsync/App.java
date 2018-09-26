package idv.mission.example.SpringAsync;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		TaskService service = context.getBean(TaskService.class);
		for (int x = 0; x < 10; x++) {
			service.funcA(x);
			service.funcB(x);
		}
		context.close();
	}
}
