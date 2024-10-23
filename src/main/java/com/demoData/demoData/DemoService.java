package com.demoData.demoData;

import java.util.*;
import org.springframework.stereotype.*;

@Service
public class DemoService implements Demointerface {
  private static Map<String, DemoCompany> companies;
  private static Map<String, DemoPerson> persons;

  public DemoCompany findCompany(String search) {
    if (companies == null) {
      createDemoData();
    }
    if (companies.containsKey(search)) {
      return companies.get(search);
    }
    throw new Error();
  }

  public DemoPerson findPerson(String search) {

    if (persons == null) {
      createDemoData();
    }
    if (persons.containsKey(search)) {
      return persons.get(search);
    }
    throw new Error();
  }

  private void createDemoData() {
    DemoCompany a = new DemoCompany("1", "7777777-1", "Demo Tiskaamo Oy ", "Cleanroad 2", "00110", "Gotham");
    DemoCompany b = new DemoCompany("1", "8888888-1", "Dummy Diamond Oy ", "West coast 22", "00110",
        "Gotham");
    DemoCompany c = new DemoCompany("1", "9999999-1", "Real Sky Shadow Oy ", "Pickard Street 222", "00110", "Gotham");
    companies = new HashMap<>();
    for (DemoCompany comp : Arrays.asList(a, b, c)) {
      companies.put(comp.name(), comp);
      companies.put(comp.id(), comp);
      companies.put(comp.businessId(), comp);
      companies.put(comp.address(), comp);
      companies.put(comp.postalCode(), comp);
      companies.put(comp.postalPlace(), comp);
    }

    Set<Locale> lSet = new HashSet<>();
    lSet.add(Locale.of("fi", "FI"));
    DemoPerson aa = new DemoPerson("010101AXXXX", "Eetu", "Rautanen", lSet);
    DemoPerson bb = new DemoPerson("020202AXXXX", "Elina", "Siir", lSet);
    lSet.clear();
    lSet.add(Locale.of("en", "EN"));
    DemoPerson cc = new DemoPerson("030303-XXXX", "Eerika", "Nemo", lSet);
    lSet.add(Locale.of("se", "SE"));
    DemoPerson dd = new DemoPerson("040404AXXXX", "Erik", "Exempel", lSet);

    persons = new HashMap<>();
    for (DemoPerson pers : Arrays.asList(aa, bb, cc, dd)) {
      persons.put(pers.firstname(), pers);
      persons.put(pers.lastname(), pers);
      persons.put(pers.personId(), pers);

    }
  }

}
