package main;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyStroker implements KeyListener
{
	int SNES_B = KeyEvent.VK_A;
	int SNES_A = KeyEvent.VK_S;
	int SNES_Y = KeyEvent.VK_Q;
	int SNES_X = KeyEvent.VK_W;
	int SNES_START = KeyEvent.VK_ENTER;
	int SNES_SELECT = KeyEvent.VK_SPACE;
	int SNES_UP = KeyEvent.VK_UP;
	int SNES_DOWN = KeyEvent.VK_DOWN;
	int SNES_LEFT = KeyEvent.VK_LEFT;
	int SNES_RIGHT = KeyEvent.VK_RIGHT;

	// Keyboard	  |   SNES Controller
	// 	  Z					L
	// 	  X					R

	private enum Direction
	{
		UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
	}

	Robot robot;

	public KeyStroker()
	{
		try
		{
			robot = new Robot();
		}
		catch (Exception e)
		{
			// who cares
		}
	}

	public void startRun()
	{
		leaveHouseAndGetToCastleSewer();
	}

	private void leaveHouseAndGetToCastleSewer()
	{
		sleep(3000);
		keyPressAndRelease(SNES_START);

		//wait for opening text to be done
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;

		while (elapsedTime < 27000)
		{
			keyPressAndRelease(SNES_A);
			sleep(200);
			elapsedTime = System.currentTimeMillis() - startTime;
		}

		//wait for uncle to leave the house
		sleep(3500);

		//move out of bed to the chest
		keyDownForTime(SNES_DOWN, 500);
		keyDownForTimeDiagonal(Direction.DOWN_RIGHT, 1200);
		keyDownForTime(SNES_DOWN, 150);
		keyDownForTime(SNES_RIGHT, 400);
		keyPressAndRelease(SNES_UP);
		keyPressAndRelease(SNES_A);

		sleep(2000);
		//confirmation of lamp retrieval
		keyPressAndRelease(SNES_A);

		//leave the house
		keyDownForTime(SNES_LEFT, 800);
		keyDownForTime(SNES_DOWN, 3600);

		//go right and jump off, then up a while
		keyDownForTime(SNES_RIGHT, 1800);
		keyDownForTime(SNES_UP, 1500);
		keyDownForTimeDiagonal(Direction.UP_LEFT, 3000);
		keyDownForTime(SNES_UP, 2000);
		keyDownForTime(SNES_LEFT, 1750);
		keyDownForTime(SNES_UP, 2400);

		//entering castle screen
		keyDownForTime(SNES_RIGHT, 3800);
		keyDownForTime(SNES_UP, 6000);
		keyDownForTime(SNES_RIGHT, 200); //THIS IS THE MS TO MOVE ONE BLOCK OF SPACE
		keyDownForTime(SNES_UP, 400);

		//pick up bush
		keyPressAndRelease(SNES_A);

		//fall down pit
		keyDownForTime(SNES_UP, 1000);

		navigateToBehindThroneRoom();
	}

	private void navigateToBehindThroneRoom()
	{
		//move left to uncle

		//talk to uncle

		//move to next room

		//move to outside

		//enter castle without dying

		//proceed forward to throne room

		//push throne out of the way
	}

	private void keyDownForTime(int keyCode, int msToHoldKeyDown)
	{
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;

		keyDown(keyCode);

		while (elapsedTime < msToHoldKeyDown)
		{
			elapsedTime = System.currentTimeMillis() - startTime;
		}

		keyUp(keyCode);
	}

	private void keyDown(int keyCode)
	{
		robot.keyPress(keyCode);
	}

	private void keyUp(int keyCode)
	{
		robot.keyRelease(keyCode);
	}

	private void keyPressAndRelease(int keyCode)
	{
		keyDown(keyCode);
		sleep(50);
		keyUp(keyCode);
	}

	private void keyDownForTimeDiagonal(Direction direction, int msToHoldKeyDown)
	{
		switch (direction)
		{
		case UP_LEFT:
			diagonalKeyPress(SNES_UP, SNES_LEFT, msToHoldKeyDown);
			break;
		case UP_RIGHT:
			diagonalKeyPress(SNES_UP, SNES_RIGHT, msToHoldKeyDown);
			break;
		case DOWN_LEFT:
			diagonalKeyPress(SNES_DOWN, SNES_LEFT, msToHoldKeyDown);
			break;
		case DOWN_RIGHT:
			diagonalKeyPress(SNES_DOWN, SNES_RIGHT, msToHoldKeyDown);
			break;
		}

	}

	private void diagonalKeyPress(int keyCode1, int keyCode2, int msToHoldKeyDown)
	{
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;

		keyDown(keyCode1);
		keyDown(keyCode2);

		while (elapsedTime < msToHoldKeyDown)
		{
			elapsedTime = System.currentTimeMillis() - startTime;
		}

		keyUp(keyCode1);
		keyUp(keyCode2);
	}

	private void sleep(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch (InterruptedException e)
		{
			// who cares
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if (arg0.getKeyCode() == KeyEvent.VK_C && arg0.isControlDown() && arg0.isShiftDown())
		{
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		if (arg0.getKeyCode() == KeyEvent.VK_C && arg0.isControlDown() && arg0.isShiftDown())
		{
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		if (arg0.getKeyCode() == KeyEvent.VK_C && arg0.isControlDown() && arg0.isShiftDown())
		{
			System.exit(0);
		}
	}
}
