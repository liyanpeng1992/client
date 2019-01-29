import com.google.gson.Gson;

import com.run.ayena.midware.service.IDataService;

import java.util.HashMap;
import java.util.Map;

import static com.run.ayena.midware.service.IConst.Context.CAPTURETIME_BEGIN;
import static com.run.ayena.midware.service.IConst.Context.CAPTURETIME_END;
import static com.run.ayena.midware.service.IConst.Context.PARAM_CONTEXT_SYSTEMID;

public class Service {
	public static void main(String[] args) {
		Client.setUrl("http://15.9.109.60:9080/dataServer/IDataService");
		IDataService dataService = Client.getDataService();

		String sql = "select * from s003.datasetall";
		Map<String, Object> contextMap = new HashMap<>(16);
		// context里面把需要加的参数都按照键值对加进去
		contextMap.put(PARAM_CONTEXT_SYSTEMID, "hj");
		// 开始结束时间都是10位的时间戳，没有特殊的情况，不要把开始时间写为0
		// 例如查1天的，那就把1天前时间戳填上就好，
		contextMap.put(CAPTURETIME_BEGIN, 1548604800);
		contextMap.put(CAPTURETIME_END, 1548741091);

		Gson gson = new Gson();
		String context = gson.toJson(contextMap);

		String result = dataService.queryData(sql, context);

		//result也是一个JSON字符串
		Map resultMap = gson.fromJson(result, Map.class);
		// 然后按照你们的业务需求获取map里的值就行了
	}
}
