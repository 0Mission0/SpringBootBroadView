package idv.mission.example.SpringAware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		AwareService service = context.getBean(AwareService.class);
		service.print();
		context.close();
	}
}
