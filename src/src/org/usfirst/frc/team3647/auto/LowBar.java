package org.usfirst.frc.team3647.auto;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

import org.usfirst.frc.team3647.robot.*;
import org.usfirst.frc.team3647.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;

public class LowBar extends Command {
	//It is going to start straight up so when it goes to negative 90 or whatever (Straight) calibrate)
	//public ADIS16448_IMU imu =	new ADIS16448_IMU();
	//public double gyroAngle = 0;
	
	
	public static ADXRS450_Gyro gyro= new ADXRS450_Gyro();
	
	
	//private boolean seq1, seq2;

    public LowBar() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public static double getAngle()
    {
    	return gyro.getAngle();
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	BallShooter.angleLock.set(true);
		BallShooter.angleUnlock.set(false);
    	LowBar.gyro.calibrate();
    	while(LowBar.getAngle() > -87)
    	{
    		ShooterAngleMotor.angle.set(-1);
    	}
    	ShooterAngleMotor.angle.set(0);
    	
    	//seq1 =	true;
    	//
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    //	seq2 =	true;
    }
    
    
    public static void makeArmStable()
    {
    	if (gyro.getAngle()+90<3 && gyro.getAngle()+90>-3)
    	{
    		ShooterAngleMotor.angle.set(0);
    		
    	}
    	if (gyro.getAngle()+90>0.2)
    	{
    		ShooterAngleMotor.angle.set(-0.15);
    		if (gyro.getAngle()<+90 && gyro.getAngle()+90>-3)
        	{
        		ShooterAngleMotor.angle.set(0);
        		
        	}
    	}
    	if (gyro.getAngle()+90<-0.2)
    	{
    		ShooterAngleMotor.angle.set(0.15);
    		if (gyro.getAngle()+90<3 && gyro.getAngle()+90>-3)
        	{
        		ShooterAngleMotor.angle.set(0);
        		
        	}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	makeArmStable();
    	Drivetrain.RightTalon.set(-0.5);
		Drivetrain.LeftTalon.set(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		Timer.delay(7);
        	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ShooterAngleMotor.angle.set(0);
    	Drivetrain.RightTalon.set(0);
		Drivetrain.LeftTalon.set(0);

    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
