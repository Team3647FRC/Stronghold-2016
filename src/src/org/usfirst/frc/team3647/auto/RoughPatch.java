package org.usfirst.frc.team3647.auto;

import edu.wpi.first.wpilibj.command.Command;


import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3647.subsystems.BallShooter;
import org.usfirst.frc.team3647.subsystems.Drivetrain;
import org.usfirst.frc.team3647.subsystems.ShooterAngleMotor;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class RoughPatch extends Command {
	
	private boolean runOnce;

    public RoughPatch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	BallShooter.angleLock.set(true);
		BallShooter.angleUnlock.set(false);
    	LowBar.gyro.calibrate();
    	
    	ShooterAngleMotor.angle.set(-1);
    		//Drivetrain.RightTalon.set(0.5);
    		//Drivetrain.LeftTalon.set(-0.5);
    	Timer.delay(3);
    	ShooterAngleMotor.angle.set(0);
    	Timer.delay(1);
    	LowBar.gyro.reset();
    	runOnce =	false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Drivetrain.RightTalon.set(-0.5);
		Drivetrain.LeftTalon.set(0.5);
		Timer.delay(4);
		
		Drivetrain.RightTalon.set(0);
		Drivetrain.LeftTalon.set(0);
		
		Timer.delay(0.5);
		
		
		runOnce =	true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return runOnce;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Drivetrain.RightTalon.set(0);
		Drivetrain.LeftTalon.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
