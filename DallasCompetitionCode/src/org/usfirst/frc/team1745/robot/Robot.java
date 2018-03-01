/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1745.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	DriveTrain driveTrain;
	Arm arm;
	Lift lift;
	Intake intake;
	Winch winch;
	
	Joystick lJoy = new Joystick(0), rJoy = new Joystick(1), sJoy = new Joystick(2);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		driveTrain = new DriveTrain();
		arm = new Arm();
		lift = new Lift();
		intake = new Intake();
		winch = new Winch();
		
	}


	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// m_autoSelected = SmartDashboard.getString("Auto Selector",
		// 		kDefaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	public void teleopInit()
	{
		arm.setPos(-250);
		lift.setPos(100);
	}
	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		driveTrain.setDrive(-lJoy.getY(), -rJoy.getY());
		
		controlArm();
		controlLift();
		controlIntake();
		winch.controlWinch(sJoy.getRawAxis(3));
		if(sJoy.getRawButton(1))
		{
			winch.deploy();
		}
		
		SmartDashboard.putNumber("LiftPos", lift.rLift.getSelectedSensorPosition(0));
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void controlArm()
	{
		if(Math.abs(sJoy.getRawAxis(5)) > .2)
		{
			arm.setPos((int)(arm.getPos() - sJoy.getRawAxis(5) * 20));
		}
		
		if(sJoy.getRawButton(3))
		{
			arm.setPos(-150);
		} else if (sJoy.getRawButton(4))
		{
			arm.setPos(200);
		} else if(sJoy.getRawButton(2))
		{
			arm.setPos(600);
		}
		
		arm.control();
	}
	
	public void controlLift()
	{
		if(Math.abs(sJoy.getRawAxis(1)) > .2)
		{
			lift.setPos((int)(lift.getPos() - sJoy.getRawAxis(1) * 150));
		}
		
		if(sJoy.getRawButton(3))
		{
			lift.setPos(500);
		} else if (sJoy.getRawButton(4))
		{
			lift.setPos(500);
		} else if(sJoy.getRawButton(2))
		{
			lift.setPos(20000);
		}
		
		lift.control();
	}
	
	public void controlIntake()
	{
		if(sJoy.getRawButton(5))
		{
			intake.intake();
		}else if(sJoy.getRawButton(6))
		{
			intake.outtake();
		}else
		{
			intake.rest();
		}
	}
}
