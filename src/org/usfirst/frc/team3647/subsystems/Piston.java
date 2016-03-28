package org.usfirst.frc.team3647.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team3647.robot.*;

public class Piston {
	public static Talon rShooter = new Talon(1);

	public static void BallShooterPiston(){
		

		Solenoid pistonOut = new Solenoid(7); // creates a Solenoid object in
												// slot 7, channel 4.

		Solenoid pistonIn = new Solenoid(0);

		if (LogitechJoystick.leftBumper) // this shoots the ball out

		{
			Robot.shootTest1 = true;
			if (Robot.shootTest1 = !Robot.shootTest) {
				rShooter.set(-1);
				// lShooter.set(1);

				Timer.delay(2);

				pistonOut.set(true); // Pushes the piston out
				pistonIn.set(false);

				Timer.delay(2);

				pistonIn.set(true); // Pulls the piston in
				pistonOut.set(false);
				Robot.shootTest = true;
				rShooter.set(0);

			}

		}
		
		if (LogitechJoystick.rightBumper2) // this shoots the ball out

		{
			Robot.shootTest1 = true;
			if (Robot.shootTest1 = !Robot.shootTest) {
				rShooter.set(-0.5);
				// lShooter.set(1);

				Timer.delay(2);

				pistonOut.set(true); // Pushes the piston out
				pistonIn.set(false);

				Timer.delay(2);

				pistonIn.set(true); // Pulls the piston in
				pistonOut.set(false);
				Robot.shootTest = true;
				rShooter.set(0);

			}

		}
		
		if(LogitechJoystick.rightBumper){
			
			rShooter.set(.25);
		}

		else {
			rShooter.set(0);
			Robot.shootTest1 = false;
			Robot.shootTest = false;
			// lShooter.set(0);
		}

	}
}
