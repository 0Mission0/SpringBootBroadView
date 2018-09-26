package idv.mission.example.SpringCustomAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		DemoService service = context.getBean(DemoService.class);
		service.print();
		context.close();
	}
}
