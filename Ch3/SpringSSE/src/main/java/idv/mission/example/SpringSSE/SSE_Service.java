package idv.mission.example.SpringSSE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

// 參考：https://www.jeejava.com/server-sent-events-spring-push-notifications/
@Service
@EnableScheduling
public class SSE_Service extends SpringBootServletInitializer {

	final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

	// 可能有不只一 個client，用List管理
	List<SseEmitter> emitters = new CopyOnWriteArrayList<SseEmitter>();

	public void addEmitter(final SseEmitter emitter) {
		emitters.add(emitter);
	}

	public void removeEmitter(final SseEmitter emitter) {
		emitters.remove(emitter);
	}

	@Async // 有跟沒有好像都沒差？
	@Scheduled(fixedRate = 500)
	public void doNotify() {
		List<SseEmitter> deadEmitters = new ArrayList<SseEmitter>();
		for (SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event().data(DATE_FORMATTER.format(new Date()) + " : " + UUID.randomUUID().toString()));
			}
			catch (Exception ex) {
				deadEmitters.add(emitter);
			}
		}
		emitters.removeAll(deadEmitters);
	}

}
