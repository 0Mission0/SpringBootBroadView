package idv.mission.example.SpringAware;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {

	private String beanName;
	private ResourceLoader resourceLoader;

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public void print() {
		System.out.println("Bean's name = " + beanName);
		String classPath = "classpath:idv/mission/example/SpringAware/test.txt";
		Resource resource = resourceLoader.getResource(classPath);
		try {
			String resourceStr = IOUtils.toString(resource.getInputStream(), Charset.forName("UTF-8"));
			System.out.println(resourceStr);
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
