package com.service;

import java.util.*;
import org.springframework.stereotype.*;
import lombok.*;

@RequiredArgsConstructor
@Service
public class GeneralService {

	public static String bodyBuilder(Map<String, Object> obj) {
		String body = "{";
		for (Map.Entry<String, Object> o : obj.entrySet()) {
			if (o.getValue() instanceof Number) {
				body += "\"" + o.getKey() + "\":" + o.getValue() + ",";
			} else {
				body += "\"" + o.getKey() + "\":\"" + o.getValue() + "\",";
			}
		}
		body = body.substring(0, body.length() - 1);
		return body + "}";
	}

}
