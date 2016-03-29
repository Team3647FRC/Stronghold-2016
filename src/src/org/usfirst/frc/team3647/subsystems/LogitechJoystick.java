package org.usfirst.frc.team3647.subsystems;

import edu.wpi.first.wpilibj.Joystick;


public class LogitechJoystick {
	
	public static Joystick wirelessController = new Joystick(0);
	public static Joystick wirelessController2 = new Joystick(1);
	
	public static boolean rightBumper =	false;
	public static boolean leftBumper = false;
	public static boolean XButton2 = false;
	public static boolean AButton1 = false;
	public static boolean AButton2 = false;
	public static boolean BButton1 = false;
	public static boolean BButton2 = false;
	public static double RTButton1=0;
	public static double LTButton1=0;
	public static boolean rightBumper2 =	false;
	public static boolean leftBumper2 =	false;
	
	public static boolean AButton =	false;
	
	public static boolean YButton2 = false;
	
	public static double LeftJoySticky =0;
	public static double RightJoyStickx =0;
	public static double RightJoySticky = 0;
	
	public static void controllers()
	{
		rightBumper =	wirelessController.getRawButton(6);
		leftBumper =	wirelessController.getRawButton(5);
		LTButton1 = wirelessController.getRawAxis(2);
		AButton1 =	wirelessController.getRawButton(1);
		BButton1 = wirelessController.getRawButton(2);
		
		BButton2 = wirelessController2.getRawButton(2);
		XButton2 = wirelessController2.getRawButton(3);
		AButton2 = wirelessController2.getRawButton(1);
		RTButton1 = wirelessController.getRawAxis(3);		
		YButton2 =	wirelessController2.getRawButton(4);
		rightBumper2 =	wirelessController2.getRawButton(6);
		leftBumper2 =	wirelessController2.getRawButton(5);
		
		
		
		LeftJoySticky = wirelessController.getRawAxis(1);
		RightJoyStickx = wirelessController.getRawAxis(4);
		RightJoySticky = wirelessController.getRawAxis(5);
		
	}

}
