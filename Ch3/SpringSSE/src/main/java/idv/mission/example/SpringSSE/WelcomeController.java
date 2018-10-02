package idv.mission.example.SpringSSE;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class WelcomeController {

	@Autowired
	private SSE_Service sseService;

	final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");

	/**
	 * http://localhost:8080/sse
	 */
	@RequestMapping(value = "/sse", method = RequestMethod.GET)
	public String sse() {
		return "sse";
	}

	/**
	 * http://localhost:8080/sse2
	 */
	@RequestMapping(value = "/sse2", method = RequestMethod.GET)
	public String sse2() {
		return "sse2";
	}

	@ResponseBody
	@RequestMapping(value = "/notify", produces = "text/event-stream;charset=utf-8")
	public String doNotify() throws InterruptedException, IOException {
		String message = "data:Test print " + DATE_FORMATTER.format(new Date()) + "\n\n";
		return message;
	}

	@GetMapping("/notify2")
	public ResponseEntity<SseEmitter> doNotify2() throws InterruptedException, IOException {
		final SseEmitter emitter = new SseEmitter();
		sseService.addEmitter(emitter);
		sseService.doNotify();
		emitter.onCompletion(new Runnable() {
			@Override
			public void run() {
				sseService.removeEmitter(emitter);
			}
		});
		emitter.onTimeout(new Runnable() {
			@Override
			public void run() {
				sseService.removeEmitter(emitter);
			}
		});
		return new ResponseEntity<SseEmitter>(emitter, HttpStatus.OK);
	}

}