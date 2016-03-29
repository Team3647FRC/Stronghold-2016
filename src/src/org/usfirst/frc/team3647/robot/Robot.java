package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;
import org.usfirst.frc.team3647.auto.*;
import org.usfirst.frc.team3647.subsystems.*;

import com.kauailabs.navx.frc.AHRS;


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
	
	final DriverStation.Alliance color = DriverStation.getInstance().getAlliance();
	
	Compressor c = new Compressor();
	
	private BuiltInAccelerometer accel = new BuiltInAccelerometer();
	CameraServer server = CameraServer.getInstance();
	
	//ADIS16448_IMU imu=new ADIS16448_IMU();
	
	//IMU stuff
	Command autonomousCommand;
	SendableChooser autoChooser;
	
	AHRS ahrs;
	RobotDrive myRobot;
	Joystick stick;
	double last_world_linear_accel_x;
	double last_world_linear_accel_y;
	static double kCollisionThreshold_DeltaG = 0.5f;
	
	boolean collisionDetected = false;

	
	

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
    	
    	try {
            /* Communicate w/navX MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
    	
    	DriverStation.reportWarning("The Robot is locked and loaded. Time to kick some ass guys!", !(server.isAutoCaptureStarted()));
    	DriverStation.reportWarning("We are on team "+color.name(), false);
    
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
		
		if (autonomousCommand != null) autonomousCommand.cancel();
		//kills Autonomous if running during teleop
		
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
		LiveWindow.run();
	}

	//public void resetAndDisable() {
    	//BallShooter.LShooter.set(0);
    	//BallShooter.rShooter.set(0);
    	//ShooterAngleMotor.angle.set(0);
    	//Piston.BallShooterPiston.set(0);
    //}
	
	public void updateDashboard() {
		 /* Display 6-axis Processed Angle Data                                      */
        SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
        SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
        SmartDashboard.putNumber(   "IMU_Pitch",            ahrs.getPitch());
        SmartDashboard.putNumber(   "IMU_Roll",             ahrs.getRoll());
        
        /* Display tilt-corrected, Magnetometer-based heading (requires             */
        /* magnetometer calibration to be useful)                                   */
        
        SmartDashboard.putNumber(   "IMU_CompassHeading",   ahrs.getCompassHeading());
        
        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
        SmartDashboard.putNumber(   "IMU_FusedHeading",     ahrs.getFusedHeading());

        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
        /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */
        
        SmartDashboard.putNumber(   "IMU_TotalYaw",         ahrs.getAngle());
        SmartDashboard.putNumber(   "IMU_YawRateDPS",       ahrs.getRate());

        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
        
        SmartDashboard.putNumber(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
        SmartDashboard.putNumber(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
        SmartDashboard.putBoolean(  "IMU_IsMoving",         ahrs.isMoving());
        SmartDashboard.putBoolean(  "IMU_IsRotating",       ahrs.isRotating());

        /* Display estimates of velocity/displacement.  Note that these values are  */
        /* not expected to be accurate enough for estimating robot position on a    */
        /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
        /* of these errors due to single (velocity) integration and especially      */
        /* double (displacement) integration.                                       */
        
        SmartDashboard.putNumber(   "Velocity_X",           ahrs.getVelocityX());
        SmartDashboard.putNumber(   "Velocity_Y",           ahrs.getVelocityY());
        SmartDashboard.putNumber(   "Displacement_X",       ahrs.getDisplacementX());
        SmartDashboard.putNumber(   "Displacement_Y",       ahrs.getDisplacementY());
        
        /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
        /* NOTE:  These values are not normally necessary, but are made available   */
        /* for advanced users.  Before using this data, please consider whether     */
        /* the processed data (see above) will suit your needs.                     */
        
        SmartDashboard.putNumber(   "RawGyro_X",            ahrs.getRawGyroX());
        SmartDashboard.putNumber(   "RawGyro_Y",            ahrs.getRawGyroY());
        SmartDashboard.putNumber(   "RawGyro_Z",            ahrs.getRawGyroZ());
        SmartDashboard.putNumber(   "RawAccel_X",           ahrs.getRawAccelX());
        SmartDashboard.putNumber(   "RawAccel_Y",           ahrs.getRawAccelY());
        SmartDashboard.putNumber(   "RawAccel_Z",           ahrs.getRawAccelZ());
        SmartDashboard.putNumber(   "RawMag_X",             ahrs.getRawMagX());
        SmartDashboard.putNumber(   "RawMag_Y",             ahrs.getRawMagY());
        SmartDashboard.putNumber(   "RawMag_Z",             ahrs.getRawMagZ());
        SmartDashboard.putNumber(   "IMU_Temp_C",           ahrs.getTempC());
        
        /* Omnimount Yaw Axis Information                                           */
        /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
        AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
        SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
        SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
        
        /* Sensor Board Information                                                 */
        SmartDashboard.putString(   "FirmwareVersion",      ahrs.getFirmwareVersion());
        
        /* Quaternion Data                                                          */
        /*Quaternions are fascinating, and are the most compact representation of  */
        /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
        /* from the Quaternions.  If interested in motion processing, knowledge of  */
        /* Quaternions is highly recommended.                                       */
        SmartDashboard.putNumber(   "QuaternionW",          ahrs.getQuaternionW());
        SmartDashboard.putNumber(   "QuaternionX",          ahrs.getQuaternionX());
        SmartDashboard.putNumber(   "QuaternionY",          ahrs.getQuaternionY());
        SmartDashboard.putNumber(   "QuaternionZ",          ahrs.getQuaternionZ());
        
        /* Sensor Data Timestamp */
        SmartDashboard.putNumber(   "SensorTimestamp",		ahrs.getLastSensorTimestamp());
        
        /* Connectivity Debugging Support                                           */
        SmartDashboard.putNumber(   "IMU_Byte_Count",       ahrs.getByteCount());
        SmartDashboard.putNumber(   "IMU_Update_Count",     ahrs.getUpdateCount());
    }
    
	
	 public void collisionDection() {
	        
	        double curr_world_linear_accel_x = ahrs.getWorldLinearAccelX();
	        double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
	        last_world_linear_accel_x = curr_world_linear_accel_x;
	        double curr_world_linear_accel_y = ahrs.getWorldLinearAccelY();
	        double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
	        last_world_linear_accel_y = curr_world_linear_accel_y;
	        
	        if ( ( Math.abs(currentJerkX) > kCollisionThreshold_DeltaG ) ||
	             ( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) ) {
	            collisionDetected = true;
	        }
	        SmartDashboard.putBoolean(  "CollisionDetected", collisionDetected);
	       
	    }
	}
