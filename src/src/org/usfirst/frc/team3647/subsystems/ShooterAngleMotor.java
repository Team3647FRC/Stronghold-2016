package org.usfirst.frc.team3647.subsystems;

import edu.wpi.first.wpilibj.Talon;

public class ShooterAngleMotor {
	public static Talon angle = new Talon(2);
	
	public static void angleShooter(){
		
		if(LogitechJoystick.LTButton1 > 0){
			angle.set(LogitechJoystick.LTButton1);
		}
		else if(0.6*LogitechJoystick.RTButton1 > 0){
			angle.set(-0.6*LogitechJoystick.RTButton1);
		}
		else{
			angle.set(0);
		}
	}
}

