package com.service;

import java.util.*;
import org.springframework.stereotype.*;
import lombok.*;

@RequiredArgsConstructor
@Service
public class GeneralService {
	/* Only for simple structures */
	public static String bodyBuilder(Map<String, Object> obj) {
		String body = "{";
		for (Map.Entry<String, Object> o : obj.entrySet()) {
			if (o.getValue() instanceof Number) {
				body += "\"" + o.getKey() + "\":" + o.getValue().toString() + ",";
			} else {
				body += "\"" + o.getKey() + "\":\"" + o.getValue().toString() + "\",";
			}
		}
		body = body.substring(0, body.length() - 1);
		return body + "}";
	}

	public String testJsonEscapeChars(String value) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			if (value.subSequence(i, i).equals("-")) {
				sb.append("\\-");
			} else {
				sb.append(value.subSequence(i, i));
			}
		}
		return sb.toString();

	}

}
