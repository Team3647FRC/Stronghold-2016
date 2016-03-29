package org.usfirst.frc.team3647.auto;


import org.usfirst.frc.team3647.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowBarSetter extends Command {

    public LowBarSetter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivetrain.RightTalon.set(-0.5);

		Drivetrain.LeftTalon.set(0.5);
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Timer.delay(6);
        return true;
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
