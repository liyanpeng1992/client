import com.caucho.hessian.client.HessianProxyFactory;
import com.run.ayena.midware.service.IDataService;

import java.net.MalformedURLException;

public class Client {
	/**
	 * 中间件hessian服务
	 */
	private static String url;
	private static IDataService dataService;
	public static IDataService getDataService() {
		if (dataService == null) {
			HessianProxyFactory factory = new HessianProxyFactory();
			try {
				dataService = (IDataService) factory.create(IDataService.class, url);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		return dataService;
	}

	public static void setUrl(String url) {
		Client.url = url;
	}
}