package idv.mission.example.SpringCustomAnnotation;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
	public void print() {
		System.out.println("Found Service!");
	}
}
