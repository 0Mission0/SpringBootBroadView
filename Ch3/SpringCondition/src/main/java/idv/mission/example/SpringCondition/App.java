package idv.mission.example.SpringCondition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ListService listService = context.getBean(ListService.class);
		System.out.println("cmd: " + listService.showListCmd());
		context.close();
	}
}
