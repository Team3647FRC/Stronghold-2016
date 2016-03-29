package org.usfirst.frc.team3647.subsystems;

/*----------------------------------------------------------------------------*/
/* Team 3647
 /* DriveTrain.java
 /* Drive functions and compressor 
 /*----------------------------------------------------------------------------*/
//package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {

    public	static Talon LeftTalon  = new Talon(3);
    public	static Talon RightTalon = new Talon(0);
//
//
        public static void arcadeDrive() {
        
	double checks,checkt,speed,turn;
	checks = Math.abs(LogitechJoystick.LeftJoySticky);
	checkt = Math.abs(LogitechJoystick.RightJoyStickx);
        
	
       
	if (checks <.05) 	{
		speed = 0;
		}
	else 			{
		speed = 0.8*(checks * LogitechJoystick.LeftJoySticky );
		}
	if (checkt <.05) 	{
		turn = 0;
		}
	else 			{
		turn = 0.5*(checkt * LogitechJoystick.RightJoyStickx );
		}    
        LeftTalon.set(((speed+turn)));
        RightTalon.set(((-speed+turn)));


    }
   /*  public static void Slidedrive() {
        
 
        double speeds = (JoySticks.TriggerRtandLt);//triggersjoystick 1
        double checks    = Math.abs(JoySticks.TriggerRtandLt);//ditto
        if (checks <.05) 	{
		speeds = 0;
		}
	else 			{
		speeds = (checks * JoySticks.JoySticks.TriggerRtandLt );
		}

        SlideTalon.set(speeds);
        
        
       
    }
    */
    public static void TankCD() {
        
 
        double speedr = (LogitechJoystick.LeftJoySticky);//y-axis/2
        double speedl    = (LogitechJoystick.RightJoySticky);//ditto
        double checkl,checkr;
	checkr = Math.abs(LogitechJoystick.LeftJoySticky);
	checkl = Math.abs(LogitechJoystick.RightJoySticky);
        LeftTalon.set(speedl * checkl);
        RightTalon.set(-speedr * checkr);
        
       
    }
    
    
    
    public static boolean isInBand(double input, double low, double high) {
        return input > low && input < high;
    }



    public static void Drive(double left, double right) {
        LeftTalon.set(-left);
        RightTalon.set(right);
    }

    public static void runCompessor() {
    //    comp.start();
    }
}
