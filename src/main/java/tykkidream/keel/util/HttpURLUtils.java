package tykkidream.keel.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class HttpURLUtils {

	public static String escapeParameterToURL(HttpServletRequest request, String[] params, boolean flag) {
		String url = null;
		if (request != null && params != null) {
			url = escapeParameter(request, params, flag);
		} else if (request != null) {
			url = escapeParameter(request);
		}
		return url;
	}

	public static String escapeParameter(HttpServletRequest request, String[] params, boolean flag) {
		StringBuilder url = new StringBuilder();


		if (flag) {
			for (int i = 0; i < params.length; i++) {
				String[] values = request.getParameterValues(params[i]);
				if (values == null)
					continue;
				for (int j = 0; j < values.length; j++) {
					if (values[j] == null || ((values[j] = values[j].trim()).equals("")))
						continue;
					url.append("&");
					url.append(params[i]);
					url.append("=");
					url.append(values[j]);
				}
			}
		} else {
			Map<String, String[]> map = new HashMap<String, String[]>(request.getParameterMap());
			
			for (int i = 0; i < params.length; i++)
				map.remove(params[i]);

			Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String[]> next = iterator.next();
				String[] values = next.getValue();
				if (values == null)
					continue;
				for (int j = 0; j < values.length; j++) {
					if (values[j] != null & ((values[j] = values[j].trim()).equals("")))
						continue;
					url.append("&");
					url.append(next.getKey());
					url.append("=");
					url.append(values[j]);
				}
			}
		}

		if (url.length() > 0) {
			url.replace(0, 1, "?");
		}
		url.insert(0, request.getRequestURL());

		return url.toString();
	}

	public static String escapeParameter(HttpServletRequest request) {
		StringBuilder url = new StringBuilder();

		Map<String, String[]> map = request.getParameterMap();
		Iterator<Entry<String, String[]>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String[]> next = iterator.next();
			String[] values = next.getValue();
			if (values != null) {
				for (int j = 0; j < values.length; j++) {
					if (values[j] != null & !((values[j] = values[j].trim()).equals(""))) {
						url.append("&");
						url.append(next.getKey());
						url.append("=");
						url.append(values[j]);
					}
				}
			}
		}

		if (url.length() > 0) {
			url.replace(0, 1, "?");
		}
		url.insert(0, request.getRequestURL());

		return url.toString();
	}
}
