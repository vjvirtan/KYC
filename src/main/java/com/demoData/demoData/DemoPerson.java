package com.demoData.demoData;

import java.util.*;

public record DemoPerson(
		String personId,
		String firstname,
		String lastname,
		Set<Locale> nationalities) {

}
