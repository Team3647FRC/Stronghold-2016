package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team3647.auto.*;
import org.usfirst.frc.team3647.subsystems.*;


//import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	public static boolean shootTest = false;
	public static boolean shootTest1 = false;
	
	Compressor c = new Compressor();
	
	private BuiltInAccelerometer accel = new BuiltInAccelerometer();
	CameraServer server = CameraServer.getInstance();
	
	//ADIS16448_IMU imu=new ADIS16448_IMU();
	
	Command autonomousCommand;
	SendableChooser autoChooser;
	
	
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
		updateDashboard();
		
    	autoChooser = new SendableChooser();
    	autoChooser.addDefault("Default Autonomous does nothing!", new Default());
    	// Default Autonomous does nothing
    	autoChooser.addObject("Cross the Low Bar Don't Run This it doesn't work", new LowBar());
    	autoChooser.addObject("Cross Rough Patch/Stone Wall", new Main());
    	autoChooser.addObject("Cross the Low Bar, Experimental!", new LowBarEx());
    	//autoChooser.addObject("If Jonathan lied to us and we can cross twice", new RoughPatch());
    	CameraServer server = CameraServer.getInstance();

    	server.setQuality(50);
    	
    	SmartDashboard.putData("Autonomous", autoChooser);

    	server.startAutomaticCapture("cam0");
    	LowBar.gyro.reset();
    	
    	DriverStation.reportWarning("The Robot is locked and loaded. Time to kick some ass guys!", !(server.isAutoCaptureStarted()));

	}

	/**
	 * This function is called periodically during autonomous
	 */
	 public void autonomousInit(){
	    	//resetAndDisableSystems();
		 
	    	autonomousCommand = (Command) autoChooser.getSelected();
	    	autonomousCommand.start();
	    	
	    	
	    }
	
	public void autonomousPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// resetAndDisable();
		updateDashboard();
		LogitechJoystick.controllers();
		Drivetrain.arcadeDrive();
		ShooterAngleMotor.angleShooter();
		BallShooter.shootBalls();
		SonicSensor.updateSensors();
		
		//if(LogitechJoystick.rightBumper2)
			//{
				//LowBar.makeArmStable();
			//}

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

	//public void resetAndDisable() {
    	//BallShooter.LShooter.set(0);
    	//BallShooter.rShooter.set(0);
    	//ShooterAngleMotor.angle.set(0);
    	//Piston.BallShooterPiston.set(0);
    //}
	
	public void updateDashboard() {
		double gyroAngle = LowBar.gyro.getAngle();
    	//SmartDashboard.putNumber("Sensor2", SonicSensor.Sonic2);
        SmartDashboard.putNumber("Gyro angle", gyroAngle + 90);
    	SmartDashboard.putNumber("1000 is about 9 feet", SonicSensor.Sonic2);
    	SmartDashboard.putData("Accelerometer Data (Units are in terms of gravity)", accel);
    	

	}
	}
