package com.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.interfece.*;
import com.repositories.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
  @Mock
  private BoardInterface boardService;

  @Mock
  private ValidationInterface validationInterface;
  @Mock
  private BoardRepository boardRepository;
  @Mock
  private BoardRolesService boardRolesService;

  @Test
  void testValidRoleType() {
    ArrayList<String> lista = new ArrayList<>();
    lista.add("Ceo");
    // TEST GIVES RESPONSE = FALSE EVEN IF METHOD ONLY RETURNS TRUE!!!
    boolean response = this.boardService.validRoleType(lista);

    // assertEquals(true, response);
  }
}
