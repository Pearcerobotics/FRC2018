package org.usfirst.frc.team1745.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Arm {

	TalonSRX arm;
	
	int currentSetPos = -250;
	
	public Arm ()
	{
		arm = new TalonSRX(32);
		
		arm.setInverted(false);
		arm.setSensorPhase(true);
		
		arm.configForwardSoftLimitThreshold(800, 10);
		arm.configForwardSoftLimitEnable(true, 10);
		
		arm.configReverseSoftLimitThreshold(-300, 10);
		arm.configReverseSoftLimitEnable(true, 10);
		
		arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		
		arm.config_kP(0, 5, 10);
		arm.config_kI(0, .0002, 10);
		arm.config_kD(0, 1, 10);
	}
	
	public void setPos(int pos)
	{
		currentSetPos = pos;
	}
	
	public void control()
	{
		arm.set(ControlMode.Position, currentSetPos);
	}
	
	public int getPos()
	{
		return currentSetPos;
	}
	
}
