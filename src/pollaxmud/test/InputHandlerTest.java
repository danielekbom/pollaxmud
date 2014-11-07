package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.handlers.InputHandler;

public class InputHandlerTest {

	@Test
	public void test() {
		InputHandler.handleInput(null, null, null);
		InputHandler.handleInput("", null, null);
	}

}
