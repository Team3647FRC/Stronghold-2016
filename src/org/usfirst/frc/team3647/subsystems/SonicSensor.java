package org.usfirst.frc.team3647.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.SampleRobot;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SonicSensor {

	// public static AnalogInput SonicSensor = new AnalogInput(1);
	//public static Ultrasonic SonicSensor1 = new Ultrasonic(0, 1);
	public static AnalogInput SonicSensor2 = new AnalogInput(0);
	// public static Ultrasonic SonicSensor3 = new Ultrasonic(1,1);

	public static double Sonic1;
	public static double Sonic2;
	// public static double Sonic3;

	public static void updateSensors() {
		// Sonic1 = SonicSensor1.getRangeInches();
		Sonic2 = SonicSensor2.getValue();
		// Sonic3 = SonicSensor3.getRangeInches();

	}

	public void robotInit() {
	//	SonicSensor1.setAutomaticMode(true);
	}

	//public static double ultrasonicSample() {
		//double Sonic1 = SonicSensor1.getRangeInches(); // reads the range on the
														// ultrasonic sensor
		//return Sonic1;
		// if (Sonic1 < 10){
		// BallShooter.rShooter.set(-.3);
		// }
	//}

	// public static double sensorValue0()
	// {
	// //return SonicSensor.getValue();
	// }
	//
	// public static double sensorValue1()
	// {
	// return SonicSensor2.getValue();
	// }

}
