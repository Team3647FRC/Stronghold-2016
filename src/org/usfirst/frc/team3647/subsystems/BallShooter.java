package org.usfirst.frc.team3647.subsystems;


//import org.usfirst.frc.team3648.robot.Joysticks;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3647.robot.Robot;

public class BallShooter {

	public static Talon rShooter = new Talon(1);
	public static Solenoid pistonOut = new Solenoid(7);
	public static Solenoid pistonIn = new Solenoid(0);
	public static Solenoid extendpiston = new Solenoid(3);
	public static Solenoid retractpiston = new Solenoid(4);
	public static Solenoid angleLock = new Solenoid(2);
	public static Solenoid angleUnlock = new Solenoid(5);

	private static boolean stopped = false, value = false, shoot = false,drop = false,stopped1 = false, value1 = false, stopped2 = false, value2=false,low = false;

	public static void shootBalls() {
		if (LogitechJoystick.AButton1 && !stopped1) {
			value1 = !value1;
			drop = true;

			stopped1 = true;
		} else if (!LogitechJoystick.AButton1) {
			stopped1 = false;
		}
		if (drop) {
			rShooter.set(.25);

			Timer.delay(.75);

			pistonOut.set(true); // Pushes the piston out
			pistonIn.set(false);

			Timer.delay(1);

			pistonIn.set(true); // Pulls the piston in
			pistonOut.set(false);
			Robot.shootTest = true;
			rShooter.set(0);
			drop = false;

		}
		if (LogitechJoystick.BButton1 && !stopped2) {
			value2 = !value2;
			low = true;

			stopped2 = true;
		} else if (!LogitechJoystick.BButton1) {
			stopped2 = false;
		}
		if (low) {
			rShooter.set(.5);

			Timer.delay(1.5);

			pistonOut.set(true); // Pushes the piston out
			pistonIn.set(false);

			Timer.delay(1);

			pistonIn.set(true); // Pulls the piston in
			pistonOut.set(false);
			Robot.shootTest = true;
			rShooter.set(0);
			low = false;

		}
		if (LogitechJoystick.XButton2) {
			extendpiston.set(true);
			retractpiston.set(false);

		}

		if (LogitechJoystick.AButton2) {
			retractpiston.set(true);
			extendpiston.set(false);
		}

		if (LogitechJoystick.YButton2) {
			angleLock.set(true);
			angleUnlock.set(false);
		}
		if (LogitechJoystick.BButton2) {
			angleLock.set(false);
			angleUnlock.set(true);
		}

		if (LogitechJoystick.leftBumper && !stopped) {
			value = !value;
			shoot = true;

			stopped = true;
		} else if (!LogitechJoystick.leftBumper) {
			stopped = false;
		}
		if (shoot) {
			rShooter.set(1);

			Timer.delay(1.5);

			pistonOut.set(true); // Pushes the piston out
			pistonIn.set(false);

			Timer.delay(1);

			pistonIn.set(true); // Pulls the piston in
			pistonOut.set(false);
			Robot.shootTest = true;
			rShooter.set(0);
			shoot = false;

		}
		if (LogitechJoystick.rightBumper) {

			rShooter.set(-0.5);
		}

		else {
			rShooter.set(0);
			Robot.shootTest1 = false;
			Robot.shootTest = false;
		}
	}
}
